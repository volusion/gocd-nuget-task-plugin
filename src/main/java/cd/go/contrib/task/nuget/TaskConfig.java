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

import java.util.Map;

/**
 * Class to hold the NuGet Task Configuration data
 */
public class TaskConfig {
    private final String command;
    private final String argPackageIdOrFilePath;
    private final String argKey;
    private final String argPackageVersion;
    private final String argSource;
    private final String argDestination;
    private final String argOperation;
    private final boolean optAllowInsecureConnections;
    private final String optApiKey;
    private final String optAsPath;
    private final String optBasePath;
    private final boolean optBuild;
    private final String optConfigFile;
    private final String optDependencyVersion;
    private final boolean optDirectDownload;
    private final boolean optDisableBuffering;
    private final boolean optDisableParallelProcessing;
    private final String optExclude;
    private final boolean optExcludeEmptyDirectories;
    private final boolean optExcludeVersion;
    private final boolean optExpand;
    private final String optFallbackSource;
    private final String optFileConflictAction;
    private final boolean optForce;
    private final boolean optForceEnglishOutput;
    private final boolean optForceEvaluate;
    private final String optFormat;
    private final String optFramework;
    private final String optId;
    private final boolean optIncludeReferencedProjects;
    private final boolean optInstallPackageToOutputPath;
    private final String optLockFilePath;
    private final boolean optLockedMode;
    private final String optMinClientVersion;
    private final String optMSBuildPath;
    private final String optMSBuildVersion;
    private final String optName;
    private final boolean optNoDefaultExcludes;
    private final boolean optNoHttpCache;
    private final boolean optNoPackageAnalysis;
    private final boolean optNoPrompt;
    private final boolean optNoServiceEndpoint;
    private final boolean optNoSymbols;
    private final String optOutputDirectory;
    private final boolean optOutputFileNamesWithoutVersion;
    private final String optPackageSaveMode;
    private final String optPackagesDirectory;
    private final String optPassword;
    private final boolean optPreRelease;
    private final String optProject2ProjectTimeOut;
    private final String optProperties;
    private final String optProtocolVersion;
    private final boolean optRecursive;
    private final boolean optRequireConsent;
    private final String optRepositoryPath;
    private final boolean optSafe;
    private final boolean optSelf;
    private final String optSet;
    private final boolean optSkipDuplicate;
    private final String optSolutionDirectory;
    private final String optSource;
    private final boolean optStorePasswordInClearText;
    private final String optSuffix;
    private final String optSymbolApiKey;
    private final String optSymbolPackageFormat;
    private final String optSymbolSource;
    private final boolean optSymbols;
    private final String optTimeout;
    private final boolean optTool;
    private final boolean optUseLockFile;
    private final String optUsername;
    private final String optValidAuthenticationTypes;
    private final String optVerbosity;
    private final String optVersion;
    private final boolean argPackageIdOrFilePathFromEnv;
    private final boolean argKeyFromEnv;
    private final boolean argPackageVersionFromEnv;
    private final boolean argSourceFromEnv;
    private final boolean argDestinationFromEnv;
    private final boolean argOperationFromEnv;
    private final boolean optVersionFromEnv;
    private final boolean optSourceFromEnv;
    private final boolean optOutputDirectoryFromEnv;
    private final boolean optConfigFileFromEnv;
    private final boolean optSolutionDirectoryFromEnv;
    private final boolean optFrameworkFromEnv;
    private final boolean optBasePathFromEnv;
    private final boolean optExcludeFromEnv;
    private final boolean optMinClientVersionFromEnv;
    private final boolean optPropertiesFromEnv;
    private final boolean optSuffixFromEnv;
    private final boolean optFallbackSourceFromEnv;
    private final boolean optMSBuildPathFromEnv;
    private final boolean optMSBuildVersionFromEnv;
    private final boolean optPackagesDirectoryFromEnv;
    private final boolean optApiKeyFromEnv;
    private final boolean optSymbolSourceFromEnv;
    private final boolean optSymbolApiKeyFromEnv;
    private final boolean optTimeoutFromEnv;
    private final boolean optSetFromEnv;
    private final boolean optAsPathFromEnv;
    private final boolean optLockFilePathFromEnv;
    private final boolean optProject2ProjectTimeOutFromEnv;
    private final boolean optNameFromEnv;
    private final boolean optProtocolVersionFromEnv;
    private final boolean optUsernameFromEnv;
    private final boolean optPasswordFromEnv;
    private final boolean optValidAuthenticationTypesFromEnv;
    private final boolean optIdFromEnv;
    private final boolean optRepositoryPathFromEnv;

    /**
     * Construct a task configuration from a Map (e.g. derived from JSON request)
     * @param config The configuration map containing the task properties
     */
    public TaskConfig(Map config) {
        command = getValue(config, TaskPlugin.COMMAND_PROPERTY);
        argPackageIdOrFilePath = getValue(config, TaskPlugin.ARG_PACKAGE_ID_OR_FILE_PATH);
        argKey = getValue(config, TaskPlugin.ARG_KEY);
        argPackageVersion = getValue(config, TaskPlugin.ARG_PACKAGE_VERSION);
        argSource = getValue(config, TaskPlugin.ARG_SOURCE);
        argDestination = getValue(config, TaskPlugin.ARG_DESTINATION);
        argOperation = getValue(config, TaskPlugin.ARG_OPERATION);
        optAllowInsecureConnections = getBooleanValue(config, TaskPlugin.OPT_ALLOW_INSECURE_CONNECTIONS);
        optApiKey = getValue(config, TaskPlugin.OPT_API_KEY);
        optAsPath = getValue(config, TaskPlugin.OPT_AS_PATH);
        optBasePath = getValue(config, TaskPlugin.OPT_BASE_PATH);
        optBuild = getBooleanValue(config, TaskPlugin.OPT_BUILD);
        optConfigFile = getValue(config, TaskPlugin.OPT_CONFIG_FILE);
        optDependencyVersion = getValue(config, TaskPlugin.OPT_DEPENDENCY_VERSION);
        optDirectDownload = getBooleanValue(config, TaskPlugin.OPT_DIRECT_DOWNLOAD);
        optDisableBuffering = getBooleanValue(config, TaskPlugin.OPT_DISABLE_BUFFERING);
        optDisableParallelProcessing = getBooleanValue(config, TaskPlugin.OPT_DISABLE_PARALLEL_PROCESSING);
        optExclude = getValue(config, TaskPlugin.OPT_EXCLUDE);
        optExcludeEmptyDirectories = getBooleanValue(config, TaskPlugin.OPT_EXCLUDE_EMPTY_DIRECTORIES);
        optExcludeVersion = getBooleanValue(config, TaskPlugin.OPT_EXCLUDE_VERSION);
        optExpand = getBooleanValue(config, TaskPlugin.OPT_EXPAND);
        optFallbackSource = getValue(config, TaskPlugin.OPT_FALLBACK_SOURCE);
        optFileConflictAction = getValue(config, TaskPlugin.OPT_FILE_CONFLICT_ACTION);
        optForce = getBooleanValue(config, TaskPlugin.OPT_FORCE);
        optForceEnglishOutput = getBooleanValue(config, TaskPlugin.OPT_FORCE_ENGLISH_OUTPUT);
        optForceEvaluate = getBooleanValue(config, TaskPlugin.OPT_FORCE_EVALUATE);
        optFormat = getValue(config, TaskPlugin.OPT_FORMAT);
        optFramework = getValue(config, TaskPlugin.OPT_FRAMEWORK);
        optId = getValue(config, TaskPlugin.OPT_ID);
        optIncludeReferencedProjects = getBooleanValue(config, TaskPlugin.OPT_INCLUDE_REFERENCED_PROJECTS);
        optInstallPackageToOutputPath = getBooleanValue(config, TaskPlugin.OPT_INSTALL_PACKAGE_TO_OUTPUT_PATH);
        optLockFilePath = getValue(config, TaskPlugin.OPT_LOCK_FILE_PATH);
        optLockedMode = getBooleanValue(config, TaskPlugin.OPT_LOCKED_MODE);
        optMinClientVersion = getValue(config, TaskPlugin.OPT_MIN_CLIENT_VERSION);
        optMSBuildPath = getValue(config, TaskPlugin.OPT_MS_BUILD_PATH);
        optMSBuildVersion = getValue(config, TaskPlugin.OPT_MS_BUILD_VERSION);
        optName = getValue(config, TaskPlugin.OPT_NAME);
        optNoDefaultExcludes = getBooleanValue(config, TaskPlugin.OPT_NO_DEFFAULT_EXCLUDES);
        optNoHttpCache = getBooleanValue(config, TaskPlugin.OPT_NO_HTTP_CACHE);
        optNoPackageAnalysis = getBooleanValue(config, TaskPlugin.OPT_NO_PACKAGE_ANALYSIS);
        optNoPrompt = getBooleanValue(config, TaskPlugin.OPT_NO_PROMPT);
        optNoServiceEndpoint = getBooleanValue(config, TaskPlugin.OPT_NO_SERVICE_ENDPOINT);
        optNoSymbols = getBooleanValue(config, TaskPlugin.OPT_NO_SYMBOLS);
        optOutputDirectory = getValue(config, TaskPlugin.OPT_OUTPUT_DIRECTORY);
        optOutputFileNamesWithoutVersion = getBooleanValue(config, TaskPlugin.OPT_OUTPUT_FILE_NAMES_WITHOUT_VERSION);
        optPackageSaveMode = getValue(config, TaskPlugin.OPT_PACKAGE_SAVE_MODE);
        optPackagesDirectory = getValue(config, TaskPlugin.OPT_PACKAGES_DIRECTORY);
        optPassword = getValue(config, TaskPlugin.OPT_PASSWORD);
        optPreRelease = getBooleanValue(config, TaskPlugin.OPT_PRE_RELEASE);
        optProject2ProjectTimeOut = getValue(config, TaskPlugin.OPT_PROJECT_2_PROJECT_TIME_OUT);
        optProperties = getValue(config, TaskPlugin.OPT_PROPERTIES);
        optProtocolVersion = getValue(config, TaskPlugin.OPT_PROTOCOL_VERSION);
        optRecursive = getBooleanValue(config, TaskPlugin.OPT_RECURSIVE);
        optRepositoryPath = getValue(config, TaskPlugin.OPT_REPOSITORY_PATH);
        optRequireConsent = getBooleanValue(config, TaskPlugin.OPT_REQUIRE_CONSENT);
        optSafe = getBooleanValue(config, TaskPlugin.OPT_SAFE);
        optSelf = getBooleanValue(config, TaskPlugin.OPT_SELF);
        optSet = getValue(config, TaskPlugin.OPT_SET);
        optSkipDuplicate = getBooleanValue(config, TaskPlugin.OPT_SKIP_DUPLICATE);
        optSolutionDirectory = getValue(config, TaskPlugin.OPT_SOLUTION_DIRECTORY);
        optSource = getValue(config, TaskPlugin.OPT_SOURCE);
        optStorePasswordInClearText = getBooleanValue(config, TaskPlugin.OPT_STORE_PASSWORD_IN_CLEAR_TEXT);
        optSuffix = getValue(config, TaskPlugin.OPT_SUFFIX);
        optSymbolApiKey = getValue(config, TaskPlugin.OPT_SYMBOL_API_KEY);
        optSymbolPackageFormat = getValue(config, TaskPlugin.OPT_SYMBOL_PACKAGE_FORMAT);
        optSymbolSource = getValue(config, TaskPlugin.OPT_SYMBOL_SOURCE);
        optSymbols = getBooleanValue(config, TaskPlugin.OPT_SYMBOLS);
        optTimeout = getValue(config, TaskPlugin.OPT_TIMEOUT);
        optTool = getBooleanValue(config, TaskPlugin.OPT_TOOL);
        optUseLockFile = getBooleanValue(config, TaskPlugin.OPT_USE_LOCK_FILE);
        optUsername = getValue(config, TaskPlugin.OPT_USERNAME);
        optValidAuthenticationTypes = getValue(config, TaskPlugin.OPT_VALID_AUTHENTICATION_TYPES);
        optVerbosity = getValue(config, TaskPlugin.OPT_VERBOSITY);
        optVersion = getValue(config, TaskPlugin.OPT_VERSION);

        argPackageIdOrFilePathFromEnv = getBooleanValue(config, TaskPlugin.ARG_PACKAGE_ID_OR_FILE_PATH_FROM_ENV);
        argKeyFromEnv = getBooleanValue(config, TaskPlugin.ARG_KEY_FROM_ENV);
        argPackageVersionFromEnv = getBooleanValue(config, TaskPlugin.ARG_PACKAGE_VERSION_FROM_ENV);
        argSourceFromEnv = getBooleanValue(config, TaskPlugin.ARG_SOURCE_FROM_ENV);
        argDestinationFromEnv = getBooleanValue(config, TaskPlugin.ARG_DESTINATION_FROM_ENV);
        argOperationFromEnv = getBooleanValue(config, TaskPlugin.ARG_OPERATION_FROM_ENV);
        optVersionFromEnv = getBooleanValue(config, TaskPlugin.OPT_VERSION_FROM_ENV);
        optSourceFromEnv = getBooleanValue(config, TaskPlugin.OPT_SOURCE_FROM_ENV);
        optOutputDirectoryFromEnv = getBooleanValue(config, TaskPlugin.OPT_OUTPUT_DIRECTORY_FROM_ENV);
        optConfigFileFromEnv = getBooleanValue(config, TaskPlugin.OPT_CONFIG_FILE_FROM_ENV);
        optSolutionDirectoryFromEnv = getBooleanValue(config, TaskPlugin.OPT_SOLUTION_DIRECTORY_FROM_ENV);
        optFrameworkFromEnv = getBooleanValue(config, TaskPlugin.OPT_FRAMEWORK_FROM_ENV);
        optBasePathFromEnv = getBooleanValue(config, TaskPlugin.OPT_BASE_PATH_FROM_ENV);
        optExcludeFromEnv = getBooleanValue(config, TaskPlugin.OPT_EXCLUDE_FROM_ENV);
        optMinClientVersionFromEnv = getBooleanValue(config, TaskPlugin.OPT_MIN_CLIENT_VERSION_FROM_ENV);
        optPropertiesFromEnv = getBooleanValue(config, TaskPlugin.OPT_PROPERTIES_FROM_ENV);
        optSuffixFromEnv = getBooleanValue(config, TaskPlugin.OPT_SUFFIX_FROM_ENV);
        optFallbackSourceFromEnv = getBooleanValue(config, TaskPlugin.OPT_FALLBACK_SOURCE_FROM_ENV);
        optMSBuildPathFromEnv = getBooleanValue(config, TaskPlugin.OPT_MS_BUILD_PATH_FROM_ENV);
        optMSBuildVersionFromEnv = getBooleanValue(config, TaskPlugin.OPT_MS_BUILD_VERSION_FROM_ENV);
        optPackagesDirectoryFromEnv = getBooleanValue(config, TaskPlugin.OPT_PACKAGES_DIRECTORY_FROM_ENV);
        optApiKeyFromEnv = getBooleanValue(config, TaskPlugin.OPT_API_KEY_FROM_ENV);
        optSymbolSourceFromEnv = getBooleanValue(config, TaskPlugin.OPT_SYMBOL_SOURCE_FROM_ENV);
        optSymbolApiKeyFromEnv = getBooleanValue(config, TaskPlugin.OPT_SYMBOL_API_KEY_FROM_ENV);
        optTimeoutFromEnv = getBooleanValue(config, TaskPlugin.OPT_TIMEOUT_FROM_ENV);
        optSetFromEnv = getBooleanValue(config, TaskPlugin.OPT_SET_FROM_ENV);
        optAsPathFromEnv = getBooleanValue(config, TaskPlugin.OPT_AS_PATH_FROM_ENV);
        optLockFilePathFromEnv = getBooleanValue(config, TaskPlugin.OPT_LOCK_FILE_PATH_FROM_ENV);
        optProject2ProjectTimeOutFromEnv = getBooleanValue(config, TaskPlugin.OPT_PROJECT_2_PROJECT_TIME_OUT_FROM_ENV);
        optNameFromEnv = getBooleanValue(config, TaskPlugin.OPT_NAME_FROM_ENV);
        optProtocolVersionFromEnv = getBooleanValue(config, TaskPlugin.OPT_PROTOCOL_VERSION_FROM_ENV);
        optUsernameFromEnv = getBooleanValue(config, TaskPlugin.OPT_USERNAME_FROM_ENV);
        optPasswordFromEnv = getBooleanValue(config, TaskPlugin.OPT_PASSWORD_FROM_ENV);
        optValidAuthenticationTypesFromEnv = getBooleanValue(config, TaskPlugin.OPT_VALID_AUTHENTICATION_TYPES_FROM_ENV);
        optIdFromEnv = getBooleanValue(config, TaskPlugin.OPT_ID_FROM_ENV);
        optRepositoryPathFromEnv = getBooleanValue(config, TaskPlugin.OPT_REPOSITORY_PATH_FROM_ENV);
    }

    /**
     * Get a string value in the config
     * @param config
     * @param property
     * @return
     */
    private String getValue(Map config, String property) {
        Map propConfig = (Map) config.get(property);

        if (propConfig == null) {
            return null;
        }

        return (String) ((Map) config.get(property)).get("value");
    }

    /**
     * Get a boolean value in the config
     * @param config
     * @param property
     * @return
     */
    private boolean getBooleanValue(Map config, String property) {
        Map propConfig = (Map) config.get(property);

        if (propConfig == null) {
            return false;
        }

        Object value = (propConfig).get("value");

        if (value == null) {
            return false;
        } else if (value instanceof String) {
            return Boolean.parseBoolean((String) value);
        }

        return (boolean) value;
    }

    /**
     * Get the NuGet `Command` to run
     * @return The NuGet command to run, e.g. `install`, `push`, `update`, etc.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Get the NuGet argument `PackageId`, `ConfigFilePath`, `nuspecPath`, `projectPath`, etc.
     * @return the NuGet argument `PackageId`, `ConfigFilePath`, `nuspecPath`, `projectPath`, etc.
     */
    public String getArgPackageIdOrFilePath() {
        return argPackageIdOrFilePath;
    }

    /**
     * Get the NuGet argument `Key`
     * @return the NuGet argument `Key`
     */
    public String getArgKey() {
        return argKey;
    }

    /**
     * Get the NuGet argument `PackageVersion`
     * @return the NuGet argument `PackageVersion`
     */
    public String getArgPackageVersion() {
        return argPackageVersion;
    }

    /**
     * Get the NuGet argument `Source`
     * @return the NuGet argument `Source`
     */
    public String getArgSource() {
        return argSource;
    }

    /**
     * Get the NuGet argument `Destination`
     * @return the NuGet argument `Destination`
     */
    public String getArgDestination() {
        return argDestination;
    }

    /**
     * Get the NuGet argument `Operation`
     * @return the NuGet argument `Operation`
     */
    public String getArgOperation() {
        return argOperation;
    }

    /**
     * Get the NuGet option `-AllowInsecureConnections`
     * @return true if the NuGet option `-AllowInsecureConnections` should be used, false otherwise
     */
    public boolean hasOptAllowInsecureConnections() {
        return optAllowInsecureConnections;
    }

    /**
     * Get the NuGet option `-ApiKey`
     * @return the NuGet option `-ApiKey`
     */
    public String getOptApiKey() {
        return optApiKey;
    }

    /**
     * Get the NuGet option `-AsPath`
     * @return the NuGet option `-AsPath`
     */
    public String getOptAsPath() {
        return optAsPath;
    }

    /**
     * Get the NuGet option `-BasePath`
     * @return the NuGet option `-BasePath`
     */
    public String getOptBasePath() {
        return optBasePath;
    }

    /**
     * Get the NuGet option `-Build`
     * @return true if the NuGet option `-Build` should be used, false otherwise
     */
    public boolean hasOptBuild() {
        return optBuild;
    }

    /**
     * Get the NuGet option `-ConfigFile`
     * @return the NuGet option `-ConfigFile`
     */
    public String getOptConfigFile() {
        return optConfigFile;
    }

    /**
     * Get the NuGet option `-DependencyVersion`
     * @return the NuGet option `-DependencyVersion`
     */
    public String getOptDependencyVersion() {
        return optDependencyVersion;
    }

    /**
     * Get the NuGet option `-DirectDownload`
     * @return true if the NuGet option `-DirectDownload` should be used, false otherwise
     */
    public boolean hasOptDirectDownload() {
        return optDirectDownload;
    }

    /**
     * Get the NuGet option `-DisableBuffering`
     * @return true if the NuGet option `-DisableBuffering` should be used, false otherwise
     */
    public boolean hasOptDisableBuffering() {
        return optDisableBuffering;
    }

    /**
     * Get the NuGet option `-DisableParallelProcessing`
     * @return true if the NuGet option `-DisableParallelProcessing` should be used, false otherwise
     */
    public boolean hasOptDisableParallelProcessing() {
        return optDisableParallelProcessing;
    }

    /**
     * Get the NuGet option `-Exclude`
     * @return the NuGet option `-Exclude`
     */
    public String getOptExclude() {
        return optExclude;
    }

    /**
     * Get the NuGet option `-ExcludeEmptyDirectories`
     * @return true if the NuGet option `-ExcludeEmptyDirectories` should be used, false otherwise
     */
    public boolean hasOptExcludeEmptyDirectories() {
        return optExcludeEmptyDirectories;
    }

    /**
     * Get the NuGet option `-ExcludeVersion`
     * @return true if the NuGet option `-ExcludeVersion` should be used, false otherwise
     */
    public boolean hasOptExcludeVersion() {
        return optExcludeVersion;
    }

    /**
     * Get the NuGet option `-Expand`
     * @return the NuGet option `-Expand`
     */
    public boolean hasOptExpand() {
        return optExpand;
    }

    /**
     * Get the NuGet option `-FallbackSource`
     * @return the NuGet option `-FallbackSource`
     */
    public String getOptFallbackSource() {
        return optFallbackSource;
    }

    /**
     * Get the NuGet option `-FileConflictAction`
     * @return the NuGet option `-FileConflictAction`
     */
    public String getOptFileConflictAction() {
        return optFileConflictAction;
    }

    /**
     * Get the NuGet option `-Force`
     * @return true if the NuGet option `-Force` should be used, false otherwise
     */
    public boolean hasOptForce() {
        return optForce;
    }

    /**
     * Get the NuGet option `-ForceEnglishOutput`
     * @return true if the NuGet option `-ForceEnglishOutput` should be used, false otherwise
     */
    public boolean hasOptForceEnglishOutput() {
        return optForceEnglishOutput;
    }

    /**
     * Get the NuGet option `-ForceEvaluate`
     * @return true if the NuGet option `-ForceEvaluate` should be used, false otherwise
     */
    public boolean hasOptForceEvaluate() {
        return optForceEvaluate;
    }

    /**
     * Get the NuGet option `-Format`
     * @return the NuGet option `-Format`
     */
    public String getOptFormat() {
        return optFormat;
    }

    /**
     * Get the NuGet option `-Framework`
     * @return the NuGet option `-Framework`
     */
    public String getOptFramework() {
        return optFramework;
    }

    /**
     * Get the NuGet option `-Id`
     * @return the NuGet option `-Id`
     */
    public String getOptId() {
        return optId;
    }

    /**
     * Get the NuGet option `-IncludeReferencedProjects`
     * @return true if the NuGet option `-IncludeReferencedProjects` should be used, false otherwise
     */
    public boolean hasOptIncludeReferencedProjects() {
        return optIncludeReferencedProjects;
    }

    /**
     * Get the NuGet option `-InstallPackageToOutputPath`
     * @return true if the NuGet option `-InstallPackageToOutputPath` should be used, false otherwise
     */
    public boolean hasOptInstallPackageToOutputPath() {
        return optInstallPackageToOutputPath;
    }

    /**
     * Get the NuGet option `-LockFilePath`
     * @return the NuGet option `-LockFilePath`
     */
    public String getOptLockFilePath() {
        return optLockFilePath;
    }

    /**
     * Get the NuGet option `-LockedMode`
     * @return true if the NuGet option `-LockedMode` should be used, false otherwise
     */
    public boolean hasOptLockedMode() {
        return optLockedMode;
    }

    /**
     * Get the NuGet option `-MinClientVersion`
     * @return the NuGet option `-MinClientVersion`
     */
    public String getOptMinClientVersion() {
        return optMinClientVersion;
    }

    /**
     * Get the NuGet option `-MsBuildPath`
     * @return the NuGet option `-MsBuildPath`
     */
    public String getOptMsBuildPath() {
        return optMSBuildPath;
    }

    /**
     * Get the NuGet option `-MsBuildVersion`
     * @return the NuGet option `-MsBuildVersion`
     */
    public String getOptMsBuildVersion() {
        return optMSBuildVersion;
    }

    /**
     * Get the NuGet option `-Name`
     * @return the NuGet option `-Name`
     */
    public String getOptName() {
        return optName;
    }

    /**
     * Get the NuGet option `-NoDefaultExcludes`
     * @return true if the NuGet option `-NoDefaultExcludes` should be used, false otherwise
     */
    public boolean hasOptNoDefaultExcludes() {
        return optNoDefaultExcludes;
    }

    /**
     * Get the NuGet option `-NoHttpCache`
     * @return true if the NuGet option `-NoHttpCache` should be used, false otherwise
     */
    public boolean hasOptNoHttpCache() {
        return optNoHttpCache;
    }

    /**
     * Get the NuGet option `-NoPackageAnalysis`
     * @return true if the NuGet option `-NoPackageAnalysis` should be used, false otherwise
     */
    public boolean hasOptNoPackageAnalysis() {
        return optNoPackageAnalysis;
    }

    /**
     * Get the NuGet option `-NoPrompt`
     * @return true if the NuGet option `-NoPrompt` should be used, false otherwise
     */
    public boolean hasOptNoPrompt() {
        return optNoPrompt;
    }

    /**
     * Get the NuGet option `-NoServiceEndpoint`
     * @return true if the NuGet option `-NoServiceEndpoint` should be used, false otherwise
     */
    public boolean hasOptNoServiceEndpoint() {
        return optNoServiceEndpoint;
    }

    /**
     * Get the NuGet option `-NoSymbols`
     * @return true if the NuGet option `-NoSymbols` should be used, false otherwise
     */
    public boolean hasOptNoSymbols() {
        return optNoSymbols;
    }

    /**
     * Get the NuGet option `-OutputDirectory`
     * @return the NuGet option `-OutputDirectory`
     */
    public String getOptOutputDirectory() {
        return optOutputDirectory;
    }

    /**
     * Get the NuGet option `-OutputFileNamesWithoutVersion`
     * @return true if the NuGet option `-OutputFileNamesWithoutVersion` should be used, false otherwise
     */
    public boolean hasOptOutputFileNamesWithoutVersion() {
        return optOutputFileNamesWithoutVersion;
    }

    /**
     * Get the NuGet option `-PackageSaveMode`
     * @return the NuGet option `-PackageSaveMode`
     */
    public String getOptPackageSaveMode() {
        return optPackageSaveMode;
    }

    /**
     * Get the NuGet option `-PackagesDirectory`
     * @return the NuGet option `-PackagesDirectory`
     */
    public String getOptPackagesDirectory() {
        return optPackagesDirectory;
    }

    /**
     * Get the NuGet option `-Password`
     * @return the NuGet option `-Password`
     */
    public String getOptPassword() {
        return optPassword;
    }

    /**
     * Get the NuGet option `-PreRelease`
     * @return true if the NuGet option `-PreRelease` should be used, false otherwise
     */
    public boolean hasOptPreRelease() {
        return optPreRelease;
    }

    /**
     * Get the NuGet option `-Project2ProjectTimeout`
     * @return the NuGet option `-Project2ProjectTimeout`
     */
    public String getOptProject2ProjectTimeOut() {
        return optProject2ProjectTimeOut;
    }

    /**
     * Get the NuGet option `-Properties`
     * @return the NuGet option `-Properties`
     */
    public String getOptProperties() {
        return optProperties;
    }

    /**
     * Get the NuGet option `-ProtocolVersion`
     * @return the NuGet option `-ProtocolVersion`
     */
    public String getOptProtocolVersion() {
        return optProtocolVersion;
    }

    /**
     * Get the NuGet option `-Recursive`
     * @return true if the NuGet option `-Recursive` should be used, false otherwise
     */
    public boolean hasOptRecursive() {
        return optRecursive;
    }

    /**
     * Get the NuGet option `-RepositoryPath`
     * @return the NuGet option `-RepositoryPath`
     */
    public String getOptRepositoryPath() {
        return optRepositoryPath;
    }

    /**
     * Get the NuGet option `-RequireConsent`
     * @return true if the NuGet option `-RequireConsent` should be used, false otherwise
     */
    public boolean hasOptRequireConsent() {
        return optRequireConsent;
    }

    /**
     * Get the NuGet option `-Safe`
     * @return true if the NuGet option `-Safe` should be used, false otherwise
     */
    public boolean hasOptSafe() {
        return optSafe;
    }

    /**
     * Get the NuGet option `-Self`
     * @return true if the NuGet option `-Self` should be used, false otherwise
     */
    public boolean hasOptSelf() {
        return optSelf;
    }

    /**
     * Get the NuGet option `-SkipDuplicate`
     * @return true if the NuGet option `-SkipDuplicate` should be used, false otherwise
     */
    public boolean hasOptSkipDuplicate() {
        return optSkipDuplicate;
    }

    /**
     * Get the NuGet option `-Set`
     * @return the NuGet option `-Set`
     */
    public String getOptSet() {
        return optSet;
    }

    /**
     * Get the NuGet option `-SolutionDirectory`
     * @return the NuGet option `-SolutionDirectory`
     */
    public String getOptSolutionDirectory() {
        return optSolutionDirectory;
    }

    /**
     * Get the NuGet option `-Source`
     * @return the NuGet option `-Source`
     */
    public String getOptSource() {
        return optSource;
    }

    /**
     * Get the NuGet option `-StorePasswordInClearText`
     * @return true if the NuGet option `-StorePasswordInClearText` should be used, false otherwise
     */
    public boolean hasOptStorePasswordInClearText() {
        return optStorePasswordInClearText;
    }

    /**
     * Get the NuGet option `-Suffix`
     * @return the NuGet option `-Suffix`
     */
    public String getOptSuffix() {
        return optSuffix;
    }

    /**
     * Get the NuGet option `-SymbolApiKey`
     * @return the NuGet option `-SymbolApiKey`
     */
    public String getOptSymbolApiKey() {
        return optSymbolApiKey;
    }

    /**
     * Get the NuGet option `-SymbolPackageFormat`
     * @return the NuGet option `-SymbolPackageFormat`
     */
    public String getOptSymbolPackageFormat() {
        return optSymbolPackageFormat;
    }

    /**
     * Get the NuGet option `-SymbolSource`
     * @return the NuGet option `-SymbolSource`
     */
    public String getOptSymbolSource() {
        return optSymbolSource;
    }

    /**
     * Get the NuGet option `-Symbols`
     * @return true if the NuGet option `-Symbols` should be used, false otherwise
     */
    public boolean hasOptSymbols() {
        return optSymbols;
    }

    /**
     * Get the NuGet option `-Timeout`
     * @return the NuGet option `-Timeout`
     */
    public String getOptTimeout() {
        return optTimeout;
    }

    /**
     * Get the NuGet option `-Tool`
     * @return true if the NuGet option `-Tool` should be used, false otherwise
     */
    public boolean hasOptTool() {
        return optTool;
    }

    /**
     * Get the NuGet option `-UseLockFile`
     * @return true if the NuGet option `-UseLockFile` should be used, false otherwise
     */
    public boolean hasOptUseLockFile() {
        return optUseLockFile;
    }

    /**
     * Get the NuGet option `-Username`
     * @return the NuGet option `-Username`
     */
    public String getOptUsername() {
        return optUsername;
    }

    /**
     * Get the NuGet option `-ValidAuthenticationTypes`
     * @return the NuGet option `-ValidAuthenticationTypes`
     */
    public String getOptValidAuthenticationTypes() {
        return optValidAuthenticationTypes;
    }

    /**
     * Get the NuGet option `-Verbosity`
     * @return the NuGet option `-Verbosity`
     */
    public String getOptVerbosity() {
        return optVerbosity;
    }

    /**
     * Get the NuGet option `-Version`
     * @return the NuGet option `-Version`
     */
    public String getOptVersion() {
        return optVersion;
    }

    /**
     * Should the argPackageIdOrFilePath expand environment variables?
     * @return true if the argPackageIdOrFilePath should load from environment variables, false otherwise
     */
    public boolean isArgPackageIdOrFilePathFromEnv() {
        return argPackageIdOrFilePathFromEnv;
    }

    /**
     * Should the argKey expand environment variables?
     * @return true if the argKey should load from environment variables, false otherwise
     */
    public boolean isArgKeyFromEnv() {
        return argKeyFromEnv;
    }

    /**
     * Should the argPackageVersion expand environment variables?
     * @return true if the argPackageVersion should load from environment variables, false otherwise
     */
    public boolean isArgPackageVersionFromEnv() {
        return argPackageVersionFromEnv;
    }

    /**
     * Should the argSource expand environment variables?
     * @return true if the argSource should load from environment variables, false otherwise
     */
    public boolean isArgSourceFromEnv() {
        return argSourceFromEnv;
    }

    /**
     * Should the argDestination expand environment variables?
     * @return true if the argDestination should load from environment variables, false otherwise
     */
    public boolean isArgDestinationFromEnv() {
        return argDestinationFromEnv;
    }

    /**
     * Should the argOperation expand environment variables?
     * @return true if the argOperation should load from environment variables, false otherwise
     */
    public boolean isArgOperationFromEnv() {
        return argOperationFromEnv;
    }

    /**
     * Should the -Version option expand environment variables?
     * @return true if the -Version option should load from environment variables, false otherwise
     */
    public boolean isOptVersionFromEnv() {
        return optVersionFromEnv;
    }

    /**
     * Should the -Source option expand environment variables?
     * @return true if the -Source option should load from environment variables, false otherwise
     */
    public boolean isOptSourceFromEnv() {
        return optSourceFromEnv;
    }

    /**
     * Should the -OutputDirectory option expand environment variables?
     * @return true if the -OutputDirectory option should load from environment variables, false otherwise
     */
    public boolean isOptOutputDirectoryFromEnv() {
        return optOutputDirectoryFromEnv;
    }

    /**
     * Should the -ConfigFile option expand environment variables?
     * @return true if the -ConfigFile option should load from environment variables, false otherwise
     */
    public boolean isOptConfigFileFromEnv() {
        return optConfigFileFromEnv;
    }

    /**
     * Should the -SolutionDirectory option expand environment variables?
     * @return true if the -SolutionDirectory option should load from environment variables, false otherwise
     */
    public boolean isOptSolutionDirectoryFromEnv() {
        return optSolutionDirectoryFromEnv;
    }

    /**
     * Should the -Framework option expand environment variables?
     * @return true if the -Framework option should load from environment variables, false otherwise
     */
    public boolean isOptFrameworkFromEnv() {
        return optFrameworkFromEnv;
    }

    /**
     * Should the -BasePath option expand environment variables?
     * @return true if the -BasePath option should load from environment variables, false otherwise
     */
    public boolean isOptBasePathFromEnv() {
        return optBasePathFromEnv;
    }

    /**
     * Should the -Exclude option expand environment variables?
     * @return true if the -Exclude option should load from environment variables, false otherwise
     */
    public boolean isOptExcludeFromEnv() {
        return optExcludeFromEnv;
    }

    /**
     * Should the -MinClientVersion option expand environment variables?
     * @return true if the -MinClientVersion option should load from environment variables, false otherwise
     */
    public boolean isOptMinClientVersionFromEnv() {
        return optMinClientVersionFromEnv;
    }

    /**
     * Should the -Properties option expand environment variables?
     * @return true if the -Properties option should load from environment variables, false otherwise
     */
    public boolean isOptPropertiesFromEnv() {
        return optPropertiesFromEnv;
    }

    /**
     * Should the -Suffix option expand environment variables?
     * @return true if the -Suffix option should load from environment variables, false otherwise
     */
    public boolean isOptSuffixFromEnv() {
        return optSuffixFromEnv;
    }

    /**
     * Should the -FallbackSource option expand environment variables?
     * @return true if the -FallbackSource option should load from environment variables, false otherwise
     */
    public boolean isOptFallbackSourceFromEnv() {
        return optFallbackSourceFromEnv;
    }

    /**
     * Should the -MSBuildPath option expand environment variables?
     * @return true if the -MSBuildPath option should load from environment variables, false otherwise
     */
    public boolean isOptMSBuildPathFromEnv() {
        return optMSBuildPathFromEnv;
    }

    /**
     * Should the -MSBuildVersion option expand environment variables?
     * @return true if the -MSBuildVersion option should load from environment variables, false otherwise
     */
    public boolean isOptMSBuildVersionFromEnv() {
        return optMSBuildVersionFromEnv;
    }

    /**
     * Should the -PackagesDirectory option expand environment variables?
     * @return true if the -PackagesDirectory option should load from environment variables, false otherwise
     */
    public boolean isOptPackagesDirectoryFromEnv() {
        return optPackagesDirectoryFromEnv;
    }

    /**
     * Should the -ApiKey option expand environment variables?
     * @return true if the -ApiKey option should load from environment variables, false otherwise
     */
    public boolean isOptApiKeyFromEnv() {
        return optApiKeyFromEnv;
    }

    /**
     * Should the -SymbolSource option expand environment variables?
     * @return true if the -SymbolSource option should load from environment variables, false otherwise
     */
    public boolean isOptSymbolSourceFromEnv() {
        return optSymbolSourceFromEnv;
    }

    /**
     * Should the -SymbolApiKey option expand environment variables?
     * @return true if the -SymbolApiKey option should load from environment variables, false otherwise
     */
    public boolean isOptSymbolApiKeyFromEnv() {
        return optSymbolApiKeyFromEnv;
    }

    /**
     * Should the -Timeout option expand environment variables?
     * @return true if the -Timeout option should load from environment variables, false otherwise
     */
    public boolean isOptTimeoutFromEnv() {
        return optTimeoutFromEnv;
    }

    /**
     * Should the -Set option expand environment variables?
     * @return true if the -Set option should load from environment variables, false otherwise
     */
    public boolean isOptSetFromEnv() {
        return optSetFromEnv;
    }

    /**
     * Should the -AsPath option expand environment variables?
     * @return true if the -AsPath option should load from environment variables, false otherwise
     */
    public boolean isOptAsPathFromEnv() {
        return optAsPathFromEnv;
    }

    /**
     * Should the -LockFilePath option expand environment variables?
     * @return true if the -LockFilePath option should load from environment variables, false otherwise
     */
    public boolean isOptLockFilePathFromEnv() {
        return optLockFilePathFromEnv;
    }

    /**
     * Should the -Project2ProjectTimeout option expand environment variables?
     * @return true if the -Project2ProjectTimeout option should load from environment variables, false otherwise
     */
    public boolean isOptProject2ProjectTimeOutFromEnv() {
        return optProject2ProjectTimeOutFromEnv;
    }

    /**
     * Should the -Name option expand environment variables?
     * @return true if the -Name option should load from environment variables, false otherwise
     */
    public boolean isOptNameFromEnv() {
        return optNameFromEnv;
    }

    /**
     * Should the -ProtocolVersion option expand environment variables?
     * @return true if the -ProtocolVersion option should load from environment variables, false otherwise
     */
    public boolean isOptProtocolVersionFromEnv() {
        return optProtocolVersionFromEnv;
    }

    /**
     * Should the -Username option expand environment variables?
     * @return true if the -Username option should load from environment variables, false otherwise
     */
    public boolean isOptUsernameFromEnv() {
        return optUsernameFromEnv;
    }

    /**
     * Should the -Password option expand environment variables?
     * @return true if the -Password option should load from environment variables, false otherwise
     */
    public boolean isOptPasswordFromEnv() {
        return optPasswordFromEnv;
    }

    /**
     * Should the -ValidAuthenticationTypes option expand environment variables?
     * @return true if the -ValidAuthenticationTypes option should load from environment variables, false otherwise
     */
    public boolean isOptValidAuthenticationTypesFromEnv() {
        return optValidAuthenticationTypesFromEnv;
    }

    /**
     * Should the -Id option expand environment variables?
     * @return true if the -Id option should load from environment variables, false otherwise
     */
    public boolean isOptIdFromEnv() {
        return optIdFromEnv;
    }

    /**
     * Should the -RepositoryPath option expand environment variables?
     * @return true if the -RepositoryPath option should load from environment variables, false otherwise
     */
    public boolean isOptRepositoryPathFromEnv() {
        return optRepositoryPathFromEnv;
    }
}
