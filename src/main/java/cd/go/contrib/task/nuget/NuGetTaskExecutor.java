/*
 * Copyright 2025 Volusion, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.contrib.task.nuget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.thoughtworks.go.plugin.api.task.JobConsoleLogger;

/**
 * Main NuGet Task Executor
 */
public class NuGetTaskExecutor {
    private final Map<String, String> envMap = System.getenv();

    private String expandEnvVars(Context taskContext, String text) {
        Map<String, String> contextEnvMap = taskContext.getEnvironmentVariables();

        String expanded = text;
        for (Entry<String, String> entry : contextEnvMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null) {
                expanded = expanded.replaceAll("\\$\\{" + key + "\\}", value);
            }
        }

        for (Entry<String, String> entry : envMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null) {
                expanded = expanded.replaceAll("\\$\\{" + key + "\\}", value);
            }
        }

        // If no replacements have been made, attempt to see if the entire text string is an env var
        if (text.equals(expanded)) {
            String contextEnvVar = taskContext.getEnvironmentVariable(text);

            if (contextEnvVar != null && !contextEnvVar.isEmpty())
                expanded = contextEnvVar;
            else {
                expanded = System.getenv(text);
            }
        }

        return expanded;
    }

    /**
     * Execution handler
     * @param taskConfig The configuration for the NuGet task, including the executable path, mode, file, command, and execution policy
     * @param taskContext The runtime context for the task, including environment variables and working directory
     * @param console The console logger to log output and errors during the execution of the NuGet task
     * @return A Result object indicating the success or failure of the NuGet execution, along with any relevant messages
     */
    public Result execute(TaskConfig taskConfig, Context taskContext, JobConsoleLogger console) {
        try {
            return runCommand(taskContext, taskConfig, console);
        } catch (IOException e) {
            String errorMessage = "NuGet execution failed: " + e.getMessage();
            console.printLine(errorMessage);
            return new Result(false, errorMessage);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Re-interrupt the thread
            String errorMessage = "NuGet execution was interrupted: " + e.getMessage();
            console.printLine(errorMessage);
            return new Result(false, errorMessage);
        }
    }

    /**
     * Run the NuGet process
     * @param taskContext The runtime context for the task, including environment variables and working directory
     * @param taskConfig The configuration for the NuGet task, including the executable path, mode, file, command, and execution policy
     * @param console The console logger to log output and errors during the execution of the NuGet task
     * @return A Result object indicating the success or failure of the NuGet execution, along with any relevant messages
     * @throws IOException
     * @throws InterruptedException
     */
    private Result runCommand(Context taskContext, TaskConfig taskConfig, JobConsoleLogger console) throws IOException, InterruptedException {
        ProcessBuilder nuget = createNuGetProcessWithOptions(taskContext, taskConfig);
        console.printLine("Launching command: " + nuget.command());
        Map<String, String> environment = taskContext.getEnvironmentVariables();
        nuget.environment().putAll(environment);
        console.printEnvironment(nuget.environment());

        Process nugetProcess = nuget.start();

        console.readErrorOf(nugetProcess.getErrorStream());
        console.readOutputOf(nugetProcess.getInputStream());

        int exitCode = nugetProcess.waitFor();
        nugetProcess.destroy();

        if (exitCode != 0) {
            return new Result(false, "NuGet execution failed. Please check the output.");
        }

        return new Result(true, "NuGet execution complete.");
    }

    /**
     * Helper function to create the NuGet process
     * @param taskConfig The configuration for the NuGet task, including the executable path, mode, file, command, and execution policy
     * @param taskContext The runtime context for the task, including environment variables and working directory
     * @return A ProcessBuilder configured with the NuGet command and options
     */
    ProcessBuilder createNuGetProcessWithOptions(Context taskContext, TaskConfig taskConfig) {
        List<String> cmd = new ArrayList<>();
        cmd.add("nuget");

        String command = taskConfig.getCommand();
        cmd.add(command);

        // Handle arguments based on the command
        if (TaskPlugin.CMDSET_INSTALL_PACK_PUSH_RESTORE_ADD_DELETE_UPDATE.contains(command)) {
            String packageIdOrFilePath = taskConfig.getArgPackageIdOrFilePath();
            cmd.add(taskConfig.isArgPackageIdOrFilePathFromEnv() ? expandEnvVars(taskContext, packageIdOrFilePath) : packageIdOrFilePath);

            // The `delete` command has an extra argument
            if (TaskPlugin.CMD_DELETE.equals(command)) {
                cmd.add(taskConfig.isArgPackageVersionFromEnv() ? expandEnvVars(taskContext, taskConfig.getArgPackageVersion()) : taskConfig.getArgPackageVersion());
            }
        } else if (TaskPlugin.CMD_SETAPIKEY.equals(command)) {
            cmd.add(taskConfig.isArgKeyFromEnv() ? expandEnvVars(taskContext, taskConfig.getArgKey()) : taskConfig.getArgKey());
        }  else if (TaskPlugin.CMD_INIT.equals(command)) {
            cmd.add(taskConfig.isArgSourceFromEnv() ? expandEnvVars(taskContext, taskConfig.getArgSource()) : taskConfig.getArgSource());
            cmd.add(taskConfig.isArgDestinationFromEnv() ? expandEnvVars(taskContext, taskConfig.getArgDestination()) : taskConfig.getArgDestination());
        } else if (TaskPlugin.CMD_SOURCES.equals(command)) {
            cmd.add(taskConfig.isArgOperationFromEnv() ? expandEnvVars(taskContext, taskConfig.getArgOperation()) : taskConfig.getArgOperation());
        }

        // Handle global options
        cmd.add("-NonInteractive"); // Always add this option to avoid interactive prompts

        String configFile = taskConfig.getOptConfigFile();
        if (configFile != null && !configFile.isEmpty()) {
            cmd.add("-ConfigFile");
            cmd.add(taskConfig.isOptConfigFileFromEnv() ? expandEnvVars(taskContext, configFile) : configFile);
        }

        if (taskConfig.hasOptForceEnglishOutput()) {
            cmd.add("-ForceEnglishOutput");
        }

        String verbosity = taskConfig.getOptVerbosity();
        if (verbosity != null && !verbosity.isEmpty()) {
            cmd.add("-Verbosity");
            cmd.add(verbosity);
        }

        // Handle options based on the command
        if (TaskPlugin.CMDSET_INSTALL_PACK_UPDATE.contains(command)) {
            String version = taskConfig.getOptVersion();
            if (version != null && !version.isEmpty()) {
                cmd.add("-Version");
                cmd.add(taskConfig.isOptVersionFromEnv() ? expandEnvVars(taskContext, version) : version);
            }
        }

        if (TaskPlugin.CMDSET_INSTALL_PUSH_RESTORE_SETAPIKEY_ADD_DELETE_SOURCES_UPDATE.contains(command)) {
            String source = taskConfig.getOptSource();
            if (source != null && !source.isEmpty()) {
                cmd.add("-Source");
                cmd.add(taskConfig.isOptSourceFromEnv() ? expandEnvVars(taskContext, source) : source);
            }
        }

        if (TaskPlugin.CMDSET_INSTALL_PACK_RESTORE.contains(command)) {
            String outputDirectory = taskConfig.getOptOutputDirectory();
            if (outputDirectory != null && !outputDirectory.isEmpty()) {
                cmd.add("-OutputDirectory");
                cmd.add(taskConfig.isOptOutputDirectoryFromEnv() ? expandEnvVars(taskContext, outputDirectory) : outputDirectory);
            }

            String solutionDirectory = taskConfig.getOptSolutionDirectory();
            if (solutionDirectory != null && !solutionDirectory.isEmpty()) {
                cmd.add("-SolutionDirectory");
                cmd.add(taskConfig.isOptSolutionDirectoryFromEnv() ? expandEnvVars(taskContext, solutionDirectory) : solutionDirectory);
            }
        }

        if (TaskPlugin.CMD_INSTALL.equals(command)) {
            if (taskConfig.hasOptExcludeVersion()) {
                cmd.add("-ExcludeVersion");
            }

            String framework = taskConfig.getOptFramework();
            if (framework != null && !framework.isEmpty()) {
                cmd.add("-Framework");
                cmd.add(taskConfig.isOptFrameworkFromEnv() ? expandEnvVars(taskContext, framework) : framework);
            }
        }

        if (TaskPlugin.CMD_PACK.equals(command)) {
            String basePath = taskConfig.getOptBasePath();
            if (basePath != null && !basePath.isEmpty()) {
                cmd.add("-BasePath");
                cmd.add(taskConfig.isOptBasePathFromEnv() ? expandEnvVars(taskContext, basePath) : basePath);
            }

            if (taskConfig.hasOptBuild()) {
                cmd.add("-Build");
            }

            String exclude = taskConfig.getOptExclude();
            if (exclude != null && !exclude.isEmpty()) {
                cmd.add("-Exclude");
                cmd.add(taskConfig.isOptExcludeFromEnv() ? expandEnvVars(taskContext, exclude) : exclude);
            }

            if (taskConfig.hasOptExcludeEmptyDirectories()) {
                cmd.add("-ExcludeEmptyDirectories");
            }

            if (taskConfig.hasOptIncludeReferencedProjects()) {
                cmd.add("-IncludeReferencedProjects");
            }

            if (taskConfig.hasOptInstallPackageToOutputPath()) {
                cmd.add("-InstallPackageToOutputPath");
            }

            String minClientVersion = taskConfig.getOptMinClientVersion();
            if (minClientVersion != null && !minClientVersion.isEmpty()) {
                cmd.add("-MinClientVersion");
                cmd.add(taskConfig.isOptMinClientVersionFromEnv() ? expandEnvVars(taskContext, minClientVersion) : minClientVersion);
            }

            if (taskConfig.hasOptNoDefaultExcludes()) {
                cmd.add("-NoDefaultExcludes");
            }

            if (taskConfig.hasOptNoPackageAnalysis()) {
                cmd.add("-NoPackageAnalysis");
            }

            if (taskConfig.hasOptOutputFileNamesWithoutVersion()) {
                cmd.add("-OutputFileNamesWithoutVersion");
            }

            String properties = taskConfig.getOptProperties();
            if (properties != null && !properties.isEmpty()) {
                cmd.add("-Properties");
                cmd.add(taskConfig.isOptPropertiesFromEnv() ? expandEnvVars(taskContext, properties) : properties);
            }

            String suffix = taskConfig.getOptSuffix();
            if (suffix != null && !suffix.isEmpty()) {
                cmd.add("-Suffix");
                cmd.add(taskConfig.isOptSuffixFromEnv() ? expandEnvVars(taskContext, suffix) : suffix);
            }

            String symbolPackageFormat = taskConfig.getOptSymbolPackageFormat();
            if (symbolPackageFormat != null && !symbolPackageFormat.isEmpty()) {
                cmd.add("-SymbolPackageFormat");
                cmd.add(symbolPackageFormat);
            }

            if (taskConfig.hasOptSymbols()) {
                cmd.add("-Symbols");
            }

            if (taskConfig.hasOptTool()) {
                cmd.add("-Tool");
            }
        }

        if (TaskPlugin.CMDSET_INSTALL_RESTORE.contains(command)) {
            if (taskConfig.hasOptDirectDownload()) {
                cmd.add("-DirectDownload");
            }

            if (taskConfig.hasOptDisableParallelProcessing()) {
                cmd.add("-DisableParallelProcessing");
            }

            if (taskConfig.hasOptNoHttpCache()) {
                cmd.add("-NoHttpCache");
            }

            String fallbackSource = taskConfig.getOptFallbackSource();
            if (fallbackSource != null && !fallbackSource.isEmpty()) {
                cmd.add("-FallbackSource");
                cmd.add(taskConfig.isOptFallbackSourceFromEnv() ? expandEnvVars(taskContext, fallbackSource) : fallbackSource);
            }

            String packageSaveMode = taskConfig.getOptPackageSaveMode();
            if (packageSaveMode != null && !packageSaveMode.isEmpty()) {
                cmd.add("-PackageSaveMode");
                cmd.add(packageSaveMode);
            }

            if (taskConfig.hasOptRequireConsent()) {
                cmd.add("-RequireConsent");
            }
        }

        if (TaskPlugin.CMDSET_INSTALL_UPDATE.contains(command)) {
            String dependencyVersion = taskConfig.getOptDependencyVersion();
            if (dependencyVersion != null && !dependencyVersion.isEmpty()) {
                cmd.add("-DependencyVersion");
                cmd.add(dependencyVersion);
            }

            if (taskConfig.hasOptPreRelease()) {
                cmd.add("-PreRelease");
            }
        }

        if (TaskPlugin.CMDSET_PACK_RESTORE_UPDATE.contains(command)) {
            String msBuildPath = taskConfig.getOptMsBuildPath();
            if (msBuildPath != null && !msBuildPath.isEmpty()) {
                cmd.add("-MSBuildPath");
                cmd.add(taskConfig.isOptMSBuildPathFromEnv() ? expandEnvVars(taskContext, msBuildPath) : msBuildPath);
            }

            String msBuildVersion = taskConfig.getOptMsBuildVersion();
            if (msBuildVersion != null && !msBuildVersion.isEmpty()) {
                cmd.add("-MSBuildVersion");
                cmd.add(taskConfig.isOptMSBuildVersionFromEnv() ? expandEnvVars(taskContext, msBuildVersion) : msBuildVersion);
            }
        }

        if (TaskPlugin.CMDSET_PACK_RESTORE.contains(command)) {
            String packagesDirectory = taskConfig.getOptPackagesDirectory();
            if (packagesDirectory != null && !packagesDirectory.isEmpty()) {
                cmd.add("-PackagesDirectory");
                cmd.add(taskConfig.isOptPackagesDirectoryFromEnv() ? expandEnvVars(taskContext, packagesDirectory) : packagesDirectory);
            }
        }

        if (TaskPlugin.CMDSET_PUSH_DELETE.contains(command)) {
            String apiKey = taskConfig.getOptApiKey();
            if (apiKey != null && !apiKey.isEmpty()) {
                cmd.add("-ApiKey");
                cmd.add(taskConfig.isOptApiKeyFromEnv() ? expandEnvVars(taskContext, apiKey) : apiKey);
            }

            if (taskConfig.hasOptNoServiceEndpoint()) {
                cmd.add("-NoServiceEndpoint");
            }
        }

        if (TaskPlugin.CMD_PUSH.equals(command)) {
            if (taskConfig.hasOptDisableBuffering()) {
                cmd.add("-DisableBuffering");
            }

            if (taskConfig.hasOptNoSymbols()) {
                cmd.add("-NoSymbols");
            }

            if (taskConfig.hasOptSkipDuplicate()) {
                cmd.add("-SkipDuplicate");
            }

            String symbolSource = taskConfig.getOptSymbolSource();
            if (symbolSource != null && !symbolSource.isEmpty()) {
                cmd.add("-SymbolSource");
                cmd.add(taskConfig.isOptSymbolSourceFromEnv() ? expandEnvVars(taskContext, symbolSource) : symbolSource);
            }

            String symbolApiKey = taskConfig.getOptSymbolApiKey();
            if (symbolApiKey != null && !symbolApiKey.isEmpty()) {
                cmd.add("-SymbolApiKey");
                cmd.add(taskConfig.isOptSymbolApiKeyFromEnv() ? expandEnvVars(taskContext, symbolApiKey) : symbolApiKey);
            }

            String timeout = taskConfig.getOptTimeout();
            if (timeout != null && !timeout.isEmpty()) {
                cmd.add("-Timeout");
                cmd.add(taskConfig.isOptTimeoutFromEnv() ? expandEnvVars(taskContext, timeout) : timeout);
            }
        }

        if (TaskPlugin.CMD_CONFIG.equals(command)) {
            String set = taskConfig.getOptSet();
            if (set != null && !set.isEmpty()) {
                cmd.add("-Set");
                cmd.add(taskConfig.isOptSetFromEnv() ? expandEnvVars(taskContext, set) : set);
            }

            String asPath = taskConfig.getOptAsPath();
            if (asPath != null && !asPath.isEmpty()) {
                cmd.add("-AsPath");
                cmd.add(taskConfig.isOptAsPathFromEnv() ? expandEnvVars(taskContext, asPath) : asPath);
            }
        }

        if (TaskPlugin.CMD_RESTORE.equals(command)) {
            if (taskConfig.hasOptForce()) {
                cmd.add("-Force");
            }

            if (taskConfig.hasOptForceEvaluate()) {
                cmd.add("-ForceEvaluate");
            }

            String lockFilePath = taskConfig.getOptLockFilePath();
            if (lockFilePath != null && !lockFilePath.isEmpty()) {
                cmd.add("-LockFilePath");
                cmd.add(taskConfig.isOptLockFilePathFromEnv() ? expandEnvVars(taskContext, lockFilePath) : lockFilePath);
            }

            if (taskConfig.hasOptLockedMode()) {
                cmd.add("-LockedMode");
            }

            String project2ProjectTimeout = taskConfig.getOptProject2ProjectTimeOut();
            if (project2ProjectTimeout != null && !project2ProjectTimeout.isEmpty()) {
                cmd.add("-Project2ProjectTimeOut");
                cmd.add(taskConfig.isOptProject2ProjectTimeOutFromEnv() ? expandEnvVars(taskContext, project2ProjectTimeout) : project2ProjectTimeout);
            }

            if (taskConfig.hasOptRecursive()) {
                cmd.add("-Recursive");
            }

            if (taskConfig.hasOptUseLockFile()) {
                cmd.add("-UseLockFile");
            }
        }

        if (TaskPlugin.CMDSET_ADD_INIT.contains(command)) {
            if (taskConfig.hasOptExpand()) {
                cmd.add("-Expand");
            }
        }

        if (TaskPlugin.CMD_DELETE.equals(command)) {
            if (taskConfig.hasOptNoPrompt()) {
                cmd.add("-NoPrompt");
            }
        }

        if (TaskPlugin.CMD_SOURCES.equals(command)) {
            if (taskConfig.hasOptAllowInsecureConnections()) {
                cmd.add("-AllowInsecureConnections");
            }

            String format = taskConfig.getOptFormat();
            if (format != null && !format.isEmpty()) {
                cmd.add("-Format");
                cmd.add(format);
            }

            String name = taskConfig.getOptName();
            if (name != null && !name.isEmpty()) {
                cmd.add("-Name");
                cmd.add(taskConfig.isOptNameFromEnv() ? expandEnvVars(taskContext, name) : name);
            }

            String protocolVersion = taskConfig.getOptProtocolVersion();
            if (protocolVersion != null && !protocolVersion.isEmpty()) {
                cmd.add("-ProtocolVersion");
                cmd.add(taskConfig.isOptProtocolVersionFromEnv() ? expandEnvVars(taskContext, protocolVersion) : protocolVersion);
            }

            String username = taskConfig.getOptUsername();
            if (username != null && !username.isEmpty()) {
                cmd.add("-Username");
                cmd.add(taskConfig.isOptUsernameFromEnv() ? expandEnvVars(taskContext, username) : username);
            }

            String password = taskConfig.getOptPassword();
            if (password != null && !password.isEmpty()) {
                cmd.add("-Password");
                cmd.add(taskConfig.isOptPasswordFromEnv() ? expandEnvVars(taskContext, password) : password);
            }

            if (taskConfig.hasOptStorePasswordInClearText()) {
                cmd.add("-StorePasswordInClearText");
            }

            String validAuthenticationTypes = taskConfig.getOptValidAuthenticationTypes();
            if (validAuthenticationTypes != null && !validAuthenticationTypes.isEmpty()) {
                cmd.add("-ValidAuthenticationTypes");
                cmd.add(taskConfig.isOptValidAuthenticationTypesFromEnv() ? expandEnvVars(taskContext, validAuthenticationTypes) : validAuthenticationTypes);
            }
        }

        if (TaskPlugin.CMD_UPDATE.equals(command)) {
            String id = taskConfig.getOptId();
            if (id != null && !id.isEmpty()) {
                cmd.add("-Id");
                cmd.add(taskConfig.isOptIdFromEnv() ? expandEnvVars(taskContext, id) : id);
            }

            String repositoryPath = taskConfig.getOptRepositoryPath();
            if (repositoryPath != null && !repositoryPath.isEmpty()) {
                cmd.add("-RepositoryPath");
                cmd.add(taskConfig.isOptRepositoryPathFromEnv() ? expandEnvVars(taskContext, repositoryPath) : repositoryPath);
            }

            if (taskConfig.hasOptSafe()) {
                cmd.add("-Safe");
            }

            String fileConflictAction = taskConfig.getOptFileConflictAction();
            if (fileConflictAction != null && !fileConflictAction.isEmpty()) {
                cmd.add("-FileConflictAction");
                cmd.add(fileConflictAction);
            }

            if (taskConfig.hasOptSelf()) {
                cmd.add("-Self");
            }
        }

        return new ProcessBuilder(cmd);
    }
}
