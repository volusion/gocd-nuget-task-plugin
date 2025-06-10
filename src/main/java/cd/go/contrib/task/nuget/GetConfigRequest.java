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

import java.util.HashMap;

import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

/**
 * Class handler to get the configured task in a pipeline config
 */
public class GetConfigRequest {
    private int addConfigPropertyIndex = 0;

    private void addConfigProperty(HashMap<String, Object> config, String propertyName, String displayName) {
        this.addConfigProperty(config, propertyName, displayName, false);
    }

    private void addConfigProperty(HashMap<String, Object> config, String propertyName, String displayName, String defaultValue) {
        this.addConfigProperty(config, propertyName, displayName, defaultValue, addConfigPropertyIndex++);
    }

    private void addConfigProperty(HashMap<String, Object> config, String propertyName, String displayName, boolean required) {
        this.addConfigProperty(config, propertyName, displayName, required, addConfigPropertyIndex++);
    }

    private void addConfigProperty(HashMap<String, Object> config, String propertyName, String displayName, boolean required, boolean secure) {
        this.addConfigProperty(config, propertyName, displayName, null, required, secure, addConfigPropertyIndex++);
    }

    private void addConfigProperty(HashMap<String, Object> config, String propertyName, String displayName, boolean required, int displayOrder) {
        this.addConfigProperty(config, propertyName, displayName, null, required, false, displayOrder);
    }

    private void addConfigProperty(HashMap<String, Object> config, String propertyName, String displayName, String defaultValue, int displayOrder) {
        this.addConfigProperty(config, propertyName, displayName, defaultValue, false, false, displayOrder);
    }

    private void addConfigProperty(HashMap<String, Object> config, String propertyName, String displayName, String defaultValue, boolean required, boolean secure, int displayOrder) {
        HashMap<String, Object> property = new HashMap<>();

        if (defaultValue != null && !defaultValue.isEmpty()) {
            property.put("default-value", defaultValue);
        }

        if (displayName != null && !displayName.isEmpty()) {
            property.put("display-name", displayName);
        }

        property.put("display-order", Integer.toString(displayOrder));

        property.put("secure", secure);

        property.put("required", required);

        config.put(propertyName, property);
    }

    /**
     * This function runs on the server, to get the configuration for the NuGet task
     * @return A GoPluginApiResponse containing the configuration properties for the NuGet task
     */
    public GoPluginApiResponse execute() {
        HashMap<String, Object> config = new HashMap<>();

        // Main command
        this.addConfigProperty(config, TaskPlugin.COMMAND_PROPERTY, "Command", true);

        // Arguments
        this.addConfigProperty(config, TaskPlugin.ARG_PACKAGE_ID_OR_FILE_PATH, "<Package ID> or <File Path>");
        this.addConfigProperty(config, TaskPlugin.ARG_PACKAGE_VERSION, "<Package Version>");
        this.addConfigProperty(config, TaskPlugin.ARG_SOURCE, "<Source>");
        this.addConfigProperty(config, TaskPlugin.ARG_DESTINATION, "<Destination>");
        this.addConfigProperty(config, TaskPlugin.ARG_OPERATION, "<Operation>");

        // Options
        this.addConfigProperty(config, TaskPlugin.OPT_ALLOW_INSECURE_CONNECTIONS, "-AllowInsecureConnections");
        this.addConfigProperty(config, TaskPlugin.OPT_API_KEY, "-ApiKey", false, true);
        this.addConfigProperty(config, TaskPlugin.OPT_AS_PATH, "-AsPath");
        this.addConfigProperty(config, TaskPlugin.OPT_BASE_PATH, "-BasePath");
        this.addConfigProperty(config, TaskPlugin.OPT_BUILD, "-Build");
        this.addConfigProperty(config, TaskPlugin.OPT_CONFIG_FILE, "-ConfigFile");
        this.addConfigProperty(config, TaskPlugin.OPT_DEPENDENCY_VERSION, "-DependencyVersion");
        this.addConfigProperty(config, TaskPlugin.OPT_DIRECT_DOWNLOAD, "-DirectDownload");
        this.addConfigProperty(config, TaskPlugin.OPT_DISABLE_BUFFERING, "-DisableBuffering");
        this.addConfigProperty(config, TaskPlugin.OPT_DISABLE_PARALLEL_PROCESSING, "-DisableParallelProcessing");
        this.addConfigProperty(config, TaskPlugin.OPT_EXCLUDE, "-Exclude");
        this.addConfigProperty(config, TaskPlugin.OPT_EXCLUDE_EMPTY_DIRECTORIES, "-ExcludeEmptyDirectories");
        this.addConfigProperty(config, TaskPlugin.OPT_EXCLUDE_VERSION, "-ExcludeVersion");
        this.addConfigProperty(config, TaskPlugin.OPT_EXPAND, "-Expand");
        this.addConfigProperty(config, TaskPlugin.OPT_FALLBACK_SOURCE, "-FallbackSource");
        this.addConfigProperty(config, TaskPlugin.OPT_FILE_CONFLICT_ACTION, "-FileConflictAction");
        this.addConfigProperty(config, TaskPlugin.OPT_FORCE_ENGLISH_OUTPUT, "-ForceEnglishOutput");
        this.addConfigProperty(config, TaskPlugin.OPT_FORCE_EVALUATE, "-ForceEvaluate");
        this.addConfigProperty(config, TaskPlugin.OPT_FORCE, "-Force");
        this.addConfigProperty(config, TaskPlugin.OPT_FORMAT, "-Format");
        this.addConfigProperty(config, TaskPlugin.OPT_FRAMEWORK, "-Framework");
        this.addConfigProperty(config, TaskPlugin.OPT_ID, "-Id");
        this.addConfigProperty(config, TaskPlugin.OPT_INCLUDE_REFERENCED_PROJECTS, "-IncludeReferencedProjects");
        this.addConfigProperty(config, TaskPlugin.OPT_INSTALL_PACKAGE_TO_OUTPUT_PATH, "-InstallPackageToOutputPath");
        this.addConfigProperty(config, TaskPlugin.OPT_LOCK_FILE_PATH, "-LockFilePath");
        this.addConfigProperty(config, TaskPlugin.OPT_LOCKED_MODE, "-LockedMode");
        this.addConfigProperty(config, TaskPlugin.OPT_MIN_CLIENT_VERSION, "-MinClientVersion");
        this.addConfigProperty(config, TaskPlugin.OPT_MS_BUILD_PATH, "-MsBuildPath");
        this.addConfigProperty(config, TaskPlugin.OPT_MS_BUILD_VERSION, "-MsBuildVersion");
        this.addConfigProperty(config, TaskPlugin.OPT_NAME, "-Name");
        this.addConfigProperty(config, TaskPlugin.OPT_NO_DEFFAULT_EXCLUDES, "-NoDefaultExcludes");
        this.addConfigProperty(config, TaskPlugin.OPT_NO_HTTP_CACHE, "-NoHttpCache");
        this.addConfigProperty(config, TaskPlugin.OPT_NO_PACKAGE_ANALYSIS, "-NoPackageAnalysis");
        this.addConfigProperty(config, TaskPlugin.OPT_NO_PROMPT, "-NoPrompt");
        this.addConfigProperty(config, TaskPlugin.OPT_NO_SERVICE_ENDPOINT, "-NoServiceEndpoint");
        this.addConfigProperty(config, TaskPlugin.OPT_NO_SYMBOLS, "-NoSymbols");
        this.addConfigProperty(config, TaskPlugin.OPT_OUTPUT_DIRECTORY, "-OutputDirectory");
        this.addConfigProperty(config, TaskPlugin.OPT_OUTPUT_FILE_NAMES_WITHOUT_VERSION, "-OutputFileNamesWithoutVersion");
        this.addConfigProperty(config, TaskPlugin.OPT_PACKAGE_SAVE_MODE, "-PackageSaveMode");
        this.addConfigProperty(config, TaskPlugin.OPT_PACKAGES_DIRECTORY, "-PackagesDirectory");
        this.addConfigProperty(config, TaskPlugin.OPT_PASSWORD, "-Password", false, true);
        this.addConfigProperty(config, TaskPlugin.OPT_PRE_RELEASE, "-PreRelease");
        this.addConfigProperty(config, TaskPlugin.OPT_PROJECT_2_PROJECT_TIME_OUT, "-Project2ProjectTimeOut");
        this.addConfigProperty(config, TaskPlugin.OPT_PROPERTIES, "-Properties");
        this.addConfigProperty(config, TaskPlugin.OPT_PROTOCOL_VERSION, "-ProtocolVersion");
        this.addConfigProperty(config, TaskPlugin.OPT_RECURSIVE, "-Recursive");
        this.addConfigProperty(config, TaskPlugin.OPT_REPOSITORY_PATH, "-RepositoryPath");
        this.addConfigProperty(config, TaskPlugin.OPT_REQUIRE_CONSENT, "-RequireConsent");
        this.addConfigProperty(config, TaskPlugin.OPT_SAFE, "-Safe");
        this.addConfigProperty(config, TaskPlugin.OPT_SELF, "-Self");
        this.addConfigProperty(config, TaskPlugin.OPT_SET, "-Set");
        this.addConfigProperty(config, TaskPlugin.OPT_SKIP_DUPLICATE, "-SkipDuplicate");
        this.addConfigProperty(config, TaskPlugin.OPT_SOLUTION_DIRECTORY, "-SolutionDirectory");
        this.addConfigProperty(config, TaskPlugin.OPT_SOURCE, "-Source");
        this.addConfigProperty(config, TaskPlugin.OPT_STORE_PASSWORD_IN_CLEAR_TEXT, "-StorePasswordInClearText");
        this.addConfigProperty(config, TaskPlugin.OPT_SUFFIX, "-Suffix");
        this.addConfigProperty(config, TaskPlugin.OPT_SYMBOL_API_KEY, "-SymbolApiKey", false, true);
        this.addConfigProperty(config, TaskPlugin.OPT_SYMBOL_PACKAGE_FORMAT, "-SymbolPackageFormat");
        this.addConfigProperty(config, TaskPlugin.OPT_SYMBOL_SOURCE, "-SymbolSource");
        this.addConfigProperty(config, TaskPlugin.OPT_SYMBOLS, "-Symbols");
        this.addConfigProperty(config, TaskPlugin.OPT_TIMEOUT, "-Timeout");
        this.addConfigProperty(config, TaskPlugin.OPT_TOOL, "-Tool");
        this.addConfigProperty(config, TaskPlugin.OPT_USE_LOCK_FILE, "-UseLockFile");
        this.addConfigProperty(config, TaskPlugin.OPT_USERNAME, "-Username");
        this.addConfigProperty(config, TaskPlugin.OPT_VALID_AUTHENTICATION_TYPES, "-ValidAuthenticationTypes");
        this.addConfigProperty(config, TaskPlugin.OPT_VERBOSITY, "-Verbosity");
        this.addConfigProperty(config, TaskPlugin.OPT_VERSION, "-Version");

        // Text fields might need environment variable expansion
        this.addConfigProperty(config, TaskPlugin.ARG_PACKAGE_ID_OR_FILE_PATH_FROM_ENV, "argPackageIdOrFilePath Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.ARG_PACKAGE_VERSION_FROM_ENV, "argPackageVersion Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.ARG_SOURCE_FROM_ENV, "argSource Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.ARG_DESTINATION_FROM_ENV, "argDestination Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.ARG_OPERATION_FROM_ENV, "argOperation Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_VERSION_FROM_ENV, "optVersion Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_SOURCE_FROM_ENV, "optSource Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_OUTPUT_DIRECTORY_FROM_ENV, "optOutputDirectory Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_SOLUTION_DIRECTORY_FROM_ENV, "optSolutionDirectory Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_FRAMEWORK_FROM_ENV, "optFramework Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_BASE_PATH_FROM_ENV, "optBasePath Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_EXCLUDE_FROM_ENV, "optExclude Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_MIN_CLIENT_VERSION_FROM_ENV, "optMinClientVersion Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_PROPERTIES_FROM_ENV, "optProperties Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_SUFFIX_FROM_ENV, "optSuffix Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_FALLBACK_SOURCE_FROM_ENV, "optFallbackSource Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_MS_BUILD_PATH_FROM_ENV, "optMSBuildPath Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_MS_BUILD_VERSION_FROM_ENV, "optMSBuildVersion Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_PACKAGES_DIRECTORY_FROM_ENV, "optPackagesDirectory Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_API_KEY_FROM_ENV, "optApiKey Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_SYMBOL_SOURCE_FROM_ENV, "optSymbolSource Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_SYMBOL_API_KEY_FROM_ENV, "optSymbolApiKey Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_TIMEOUT_FROM_ENV, "optTimeout Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_SET_FROM_ENV, "optSet Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_AS_PATH_FROM_ENV, "optAsPath Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_LOCK_FILE_PATH_FROM_ENV, "optLockFilePath Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_PROJECT_2_PROJECT_TIME_OUT_FROM_ENV, "optProject2ProjectTimeOut Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_NAME_FROM_ENV, "optName Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_PROTOCOL_VERSION_FROM_ENV, "optProtocolVersion Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_USERNAME_FROM_ENV, "optUsername Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_PASSWORD_FROM_ENV, "optPassword Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_VALID_AUTHENTICATION_TYPES_FROM_ENV, "optValidAuthenticationTypes Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_ID_FROM_ENV, "optId Is Env Var?");
        this.addConfigProperty(config, TaskPlugin.OPT_REPOSITORY_PATH_FROM_ENV, "optRepositoryPath Is Env Var?");


        return DefaultGoPluginApiResponse.success(TaskPlugin.GSON.toJson(config));
    }
}
