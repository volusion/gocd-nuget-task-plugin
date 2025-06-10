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

import java.util.Arrays;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.go.plugin.api.GoApplicationAccessor;
import com.thoughtworks.go.plugin.api.GoPlugin;
import com.thoughtworks.go.plugin.api.GoPluginIdentifier;
import com.thoughtworks.go.plugin.api.annotation.Extension;
import com.thoughtworks.go.plugin.api.exceptions.UnhandledRequestTypeException;
import com.thoughtworks.go.plugin.api.logging.Logger;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

/**
 * The main plugin class that implements the GoPlugin interface.
 * It handles requests from the GoCD server and provides responses.
 */
@Extension
public class TaskPlugin implements GoPlugin {
    /**
     * Command: install
     */
    protected static final String CMD_INSTALL = "install";

    /**
     * Command: pack
     */
    protected static final String CMD_PACK = "pack";

    /**
     * Command: push
     */
    protected static final String CMD_PUSH = "push";

    /**
     * Command: config
     */
    protected static final String CMD_CONFIG = "config";

    /**
     * Command: restore
     */
    protected static final String CMD_RESTORE = "restore";

    /**
     * Command: setapikey
     */
    protected static final String CMD_SETAPIKEY = "setapikey";

    /**
     * Command: add
     */
    protected static final String CMD_ADD = "add";

    /**
     * Command: delete
     */
    protected static final String CMD_DELETE = "delete";

    /**
     * Command: init
     */
    protected static final String CMD_INIT = "init";

    /**
     * Command: sources
     */
    protected static final String CMD_SOURCES = "sources";

    /**
     * Command: update
     */
    protected static final String CMD_UPDATE = "update";

    /**
     * Command Set: add, init
     */
    protected static final Set<String> CMDSET_ADD_INIT = Set.of(CMD_ADD, CMD_INIT);

    /**
     * Command Set: install, pack, push, restore, add, update, delete
     */
    protected static final Set<String> CMDSET_INSTALL_PACK_PUSH_RESTORE_ADD_DELETE_UPDATE = Set.of(CMD_INSTALL, CMD_PACK, CMD_PUSH, CMD_RESTORE, CMD_ADD, CMD_DELETE, CMD_UPDATE);

    /**
     * Command Set: install, pack, restore
     */
    protected static final Set<String> CMDSET_INSTALL_PACK_RESTORE = Set.of(CMD_INSTALL, CMD_PACK, CMD_RESTORE);

    /**
     * Command Set: install, pack, update
     */
    protected static final Set<String> CMDSET_INSTALL_PACK_UPDATE = Set.of(CMD_INSTALL, CMD_PACK, CMD_UPDATE);

    /**
     * Command Set: install, push, restore, add, delete, sources, update
     */
    protected static final Set<String> CMDSET_INSTALL_PUSH_RESTORE_SETAPIKEY_ADD_DELETE_SOURCES_UPDATE = Set.of(CMD_INSTALL, CMD_PUSH, CMD_RESTORE, CMD_SETAPIKEY, CMD_ADD, CMD_DELETE, CMD_SOURCES, CMD_UPDATE);

    /**
     * Command Set: install, restore
     */
    protected static final Set<String> CMDSET_INSTALL_RESTORE = Set.of(CMD_INSTALL, CMD_RESTORE);

    /**
     * Command Set: install, update
     */
    protected static final Set<String> CMDSET_INSTALL_UPDATE = Set.of(CMD_INSTALL, CMD_UPDATE);

    /**
     * Command Set: pack, restore, update
     */
    protected static final Set<String> CMDSET_PACK_RESTORE_UPDATE = Set.of(CMD_PACK, CMD_RESTORE, CMD_UPDATE);

    /**
     * Command Set: pack, restore
     */
    protected static final Set<String> CMDSET_PACK_RESTORE = Set.of(CMD_PACK, CMD_RESTORE);

    /**
     * Command Set: push, add
     */
    protected static final Set<String> CMDSET_PUSH_ADD = Set.of(CMD_PUSH, CMD_ADD);

    /**
     * Command Set: push, delete
     */
    protected static final Set<String> CMDSET_PUSH_DELETE = Set.of(CMD_PUSH, CMD_DELETE);

    /**
     * Property name for the NuGet `Command` task configuration.
     */
    public static final String COMMAND_PROPERTY = "command";

    /**
     * Property name for the NuGet `PackageId` or `ConfigFilePath/ProjectFilePath/PackagePath` argument.
     */
    public static final String ARG_PACKAGE_ID_OR_FILE_PATH = "argPackageIdOrFilePath";

    /**
     * Property name for the NuGet `Key` argument.
     */
    public static final String ARG_KEY = "argKey";

    /**
     * Property name for the NuGet `PackageVersion` argument.
     */
    public static final String ARG_PACKAGE_VERSION = "argPackageVersion";

    /**
     * Property name for the NuGet `Source` argument.
     */
    public static final String ARG_SOURCE = "argSource";

    /**
     * Property name for the NuGet `Destination` argument.
     */
    public static final String ARG_DESTINATION = "argDestination";

    /**
     * Property name for the NuGet `Operation` argument.
     */
    public static final String ARG_OPERATION = "argOperation";

    /**
     * Property name for the NuGet `-AllowInsecureConnections` option.
     */
    public static final String OPT_ALLOW_INSECURE_CONNECTIONS = "optAllowInsecureConnections";

    /**
     * Property name for the NuGet `-ApiKey` option.
     */
    public static final String OPT_API_KEY = "optApiKey";

    /**
     * Property name for the NuGet `-AsPath` option.
     */
    public static final String OPT_AS_PATH = "optAsPath";

    /**
     * Property name for the NuGet `-BasePath` option.
     */
    public static final String OPT_BASE_PATH = "optBasePath";

    /**
     * Property name for the NuGet `-Build` option.
     */
    public static final String OPT_BUILD = "optBuild";

    /**
     * Property name for the NuGet `-ConfigFile` option.
     */
    public static final String OPT_CONFIG_FILE = "optConfigFile";

    /**
     * Property name for the NuGet `-DependencyVersion` option.
     */
    public static final String OPT_DEPENDENCY_VERSION = "optDependencyVersion";

    /**
     * Property name for the NuGet `-DirectDownload` option.
     */
    public static final String OPT_DIRECT_DOWNLOAD = "optDirectDownload";

    /**
     * Property name for the NuGet `-DisableBuffering` option.
     */
    public static final String OPT_DISABLE_BUFFERING = "optDisableBuffering";

    /**
     * Property name for the NuGet `-DisableParallelProcessing` option.
     */
    public static final String OPT_DISABLE_PARALLEL_PROCESSING = "optDisableParallelProcessing";

    /**
     * Property name for the NuGet `-Exclude` option.
     */
    public static final String OPT_EXCLUDE = "optExclude";

    /**
     * Property name for the NuGet `-ExcludeEmptyDirectories` option.
     */
    public static final String OPT_EXCLUDE_EMPTY_DIRECTORIES = "optExcludeEmptyDirectories";

    /**
     * Property name for the NuGet `-ExcludeVersion` option.
     */
    public static final String OPT_EXCLUDE_VERSION = "optExcludeVersion";

    /**
     * Property name for the NuGet `-Expand` option.
     */
    public static final String OPT_EXPAND = "optExpand";

    /**
     * Property name for the NuGet `-FallbackSource` option.
     */
    public static final String OPT_FALLBACK_SOURCE = "optFallbackSource";

    /**
     * Property name for the NuGet `-FileConflictAction` option.
     */
    public static final String OPT_FILE_CONFLICT_ACTION = "optFileConflictAction";

    /**
     * Property name for the NuGet `-Force` option.
     */
    public static final String OPT_FORCE = "optForce";

    /**
     * Property name for the NuGet `-ForceEnglishOutput` option.
     */
    public static final String OPT_FORCE_ENGLISH_OUTPUT = "optForceEnglishOutput";

    /**
     * Property name for the NuGet `-ForceEvaluate` option.
     */
    public static final String OPT_FORCE_EVALUATE = "optForceEvaluate";

    /**
     * Property name for the NuGet `-Format` option.
     */
    public static final String OPT_FORMAT = "optFormat";

    /**
     * Property name for the NuGet `-Framework` option.
     */
    public static final String OPT_FRAMEWORK = "optFramework";

    /**
     * Property name for the NuGet `-Id` option.
     */
    public static final String OPT_ID = "optId";

    /**
     * Property name for the NuGet `-IncludeReferencedProjects` option.
     */
    public static final String OPT_INCLUDE_REFERENCED_PROJECTS = "optIncludeReferencedProjects";

    /**
     * Property name for the NuGet `-InstallPackageToOutputPath` option.
     */
    public static final String OPT_INSTALL_PACKAGE_TO_OUTPUT_PATH = "optInstallPackageToOutputPath";

    /**
     * Property name for the NuGet `-LockFilePath` option.
     */
    public static final String OPT_LOCK_FILE_PATH = "optLockFilePath";

    /**
     * Property name for the NuGet `-LockedMode` option.
     */
    public static final String OPT_LOCKED_MODE = "optLockedMode";

    /**
     * Property name for the NuGet `-MinClientVersion` option.
     */
    public static final String OPT_MIN_CLIENT_VERSION = "optMinClientVersion";

    /**
     * Property name for the NuGet `-MSBuildPath` option.
     */
    public static final String OPT_MS_BUILD_PATH = "optMSBuildPath";

    /**
     * Property name for the NuGet `-MSBuildVersion` option.
     */
    public static final String OPT_MS_BUILD_VERSION = "optMSBuildVersion";

    /**
     * Property name for the NuGet `-Name` option.
     */
    public static final String OPT_NAME = "optName";

    /**
     * Property name for the NuGet `-NoDefaultExcludes` option.
     */
    public static final String OPT_NO_DEFFAULT_EXCLUDES = "optNoDefaultExcludes";

    /**
     * Property name for the NuGet `-NoHttpCache` option.
     */
    public static final String OPT_NO_HTTP_CACHE = "optNoHttpCache";

    /**
     * Property name for the NuGet `-NoPackageAnalysis` option.
     */
    public static final String OPT_NO_PACKAGE_ANALYSIS = "optNoPackageAnalysis";

    /**
     * Property name for the NuGet `-NoPrompt` option.
     */
    public static final String OPT_NO_PROMPT = "optNoPrompt";

    /**
     * Property name for the NuGet `-NoServiceEndpoint` option.
     */
    public static final String OPT_NO_SERVICE_ENDPOINT = "optNoServiceEndpoint";

    /**
     * Property name for the NuGet `-NoSymbols` option.
     */
    public static final String OPT_NO_SYMBOLS = "optNoSymbols";

    /**
     * Property name for the NuGet `-OutputDirectory` option.
     */
    public static final String OPT_OUTPUT_DIRECTORY = "optOutputDirectory";

    /**
     * Property name for the NuGet `-OutputFileNamesWithoutVersion` option.
     */
    public static final String OPT_OUTPUT_FILE_NAMES_WITHOUT_VERSION = "optOutputFileNamesWithoutVersion";

    /**
     * Property name for the NuGet `-PackageSaveMode` option.
     */
    public static final String OPT_PACKAGE_SAVE_MODE = "optPackageSaveMode";

    /**
     * Property name for the NuGet `-PackagesDirectory` option.
     */
    public static final String OPT_PACKAGES_DIRECTORY = "optPackagesDirectory";

    /**
     * Property name for the NuGet `-Password` option.
     */
    public static final String OPT_PASSWORD = "optPassword";

    /**
     * Property name for the NuGet `-PreRelease` option.
     */
    public static final String OPT_PRE_RELEASE = "optPreRelease";

    /**
     * Property name for the NuGet `-Project2ProjectTimeOut` option.
     */
    public static final String OPT_PROJECT_2_PROJECT_TIME_OUT = "optProject2ProjectTimeOut";

    /**
     * Property name for the NuGet `-Properties` option.
     */
    public static final String OPT_PROPERTIES = "optProperties";

    /**
     * Property name for the NuGet `-ProtocolVersion` option.
     */
    public static final String OPT_PROTOCOL_VERSION = "optProtocolVersion";

    /**
     * Property name for the NuGet `-Recursive` option.
     */
    public static final String OPT_RECURSIVE = "optRecursive";

    /**
     * Property name for the NuGet `-RepositoryPath` option.
     */
    public static final String OPT_REPOSITORY_PATH = "optRepositoryPath";

    /**
     * Property name for the NuGet `-RequireConsent` option.
     */
    public static final String OPT_REQUIRE_CONSENT = "optRequireConsent";

    /**
     * Property name for the NuGet `-Safe` option.
     */
    public static final String OPT_SAFE = "optSafe";

    /**
     * Property name for the NuGet `-Self` option.
     */
    public static final String OPT_SELF = "optSelf";

    /**
     * Property name for the NuGet `-Set` option.
     */
    public static final String OPT_SET = "optSet";

    /**
     * Property name for the NuGet `-SkipDuplicate` option.
     */
    public static final String OPT_SKIP_DUPLICATE = "optSkipDuplicate";

    /**
     * Property name for the NuGet `-SolutionDirectory` option.
     */
    public static final String OPT_SOLUTION_DIRECTORY = "optSolutionDirectory";

    /**
     * Property name for the NuGet `-Source` option.
     */
    public static final String OPT_SOURCE = "optSource";

    /**
     * Property name for the NuGet `-StorePasswordInClearText` option.
     */
    public static final String OPT_STORE_PASSWORD_IN_CLEAR_TEXT = "optStorePasswordInClearText";

    /**
     * Property name for the NuGet `-Suffix` option.
     */
    public static final String OPT_SUFFIX = "optSuffix";

    /**
     * Property name for the NuGet `-SymbolApiKey` option.
     */
    public static final String OPT_SYMBOL_API_KEY = "optSymbolApiKey";

    /**
     * Property name for the NuGet `-SymbolPackageFormat` option.
     */
    public static final String OPT_SYMBOL_PACKAGE_FORMAT = "optSymbolPackageFormat";

    /**
     * Property name for the NuGet `-SymbolSource` option.
     */
    public static final String OPT_SYMBOL_SOURCE = "optSymbolSource";

    /**
     * Property name for the NuGet `-Symbols` option.
     */
    public static final String OPT_SYMBOLS = "optSymbols";

    /**
     * Property name for the NuGet `-Timeout` option.
     */
    public static final String OPT_TIMEOUT = "optTimeout";

    /**
     * Property name for the NuGet `-Tool` option.
     */
    public static final String OPT_TOOL = "optTool";

    /**
     * Property name for the NuGet `-UseLockFile` option.
     */
    public static final String OPT_USE_LOCK_FILE = "optUseLockFile";

    /**
     * Property name for the NuGet `-Username` option.
     */
    public static final String OPT_USERNAME = "optUsername";

    /**
     * Property name for the NuGet `-ValidAuthenticationTypes` option.
     */
    public static final String OPT_VALID_AUTHENTICATION_TYPES = "optValidAuthenticationTypes";

    /**
     * Property name for the NuGet `-Verbosity` option.
     */
    public static final String OPT_VERBOSITY = "optVerbosity";

    /**
     * Property name for the NuGet `-Version` option.
     */
    public static final String OPT_VERSION = "optVersion";

    /**
     * Property name to instruct env var expansion for the NuGet `PackageId` or `ConfigFilePath/ProjectFilePath/PackagePath` argument.
     */
    public static final String ARG_PACKAGE_ID_OR_FILE_PATH_FROM_ENV = "argPackageIdOrFilePathFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `Key` argument.
     */
    public static final String ARG_KEY_FROM_ENV = "argKeyFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `PackageVersion` argument.
     */
    public static final String ARG_PACKAGE_VERSION_FROM_ENV = "argPackageVersionFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `Source` argument.
     */
    public static final String ARG_SOURCE_FROM_ENV = "argSourceFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `Destination` argument.
     */
    public static final String ARG_DESTINATION_FROM_ENV = "argDestinationFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `Operation` argument.
     */
    public static final String ARG_OPERATION_FROM_ENV = "argOperationFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-Version` option.
     */
    public static final String OPT_VERSION_FROM_ENV = "optVersionFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-Source` option.
     */
    public static final String OPT_SOURCE_FROM_ENV = "optSourceFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-OutputDirectory` option.
     */
    public static final String OPT_OUTPUT_DIRECTORY_FROM_ENV = "optOutputDirectoryFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-ConfigFile` option.
     */
    public static final String OPT_CONFIG_FILE_FROM_ENV = "optConfigFileFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-SolutionDirectory` option.
     */
    public static final String OPT_SOLUTION_DIRECTORY_FROM_ENV = "optSolutionDirectoryFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-Framework` option.
     */
    public static final String OPT_FRAMEWORK_FROM_ENV = "optFrameworkFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-BasePath` option.
     */
    public static final String OPT_BASE_PATH_FROM_ENV = "optBasePathFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-Exclude` option.
     */
    public static final String OPT_EXCLUDE_FROM_ENV = "optExcludeFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-MinClientVersion` option.
     */
    public static final String OPT_MIN_CLIENT_VERSION_FROM_ENV = "optMinClientVersionFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-Properties` option.
     */
    public static final String OPT_PROPERTIES_FROM_ENV = "optPropertiesFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-Suffix` option.
     */
    public static final String OPT_SUFFIX_FROM_ENV = "optSuffixFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-FallbackSource` option.
     */
    public static final String OPT_FALLBACK_SOURCE_FROM_ENV = "optFallbackSourceFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-MSBuildPath` option.
     */
    public static final String OPT_MS_BUILD_PATH_FROM_ENV = "optMSBuildPathFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-MSBuildVersion` option.
     */
    public static final String OPT_MS_BUILD_VERSION_FROM_ENV = "optMSBuildVersionFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-PackagesDirectory` option.
     */
    public static final String OPT_PACKAGES_DIRECTORY_FROM_ENV = "optPackagesDirectoryFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-ApiKey` option.
     */
    public static final String OPT_API_KEY_FROM_ENV = "optApiKeyFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-SymbolSource` option.
     */
    public static final String OPT_SYMBOL_SOURCE_FROM_ENV = "optSymbolSourceFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-SymbolApiKey` option.
     */
    public static final String OPT_SYMBOL_API_KEY_FROM_ENV = "optSymbolApiKeyFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-Timeout` option.
     */
    public static final String OPT_TIMEOUT_FROM_ENV = "optTimeoutFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-Set` option.
     */
    public static final String OPT_SET_FROM_ENV = "optSetFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-AsPath` option.
     */
    public static final String OPT_AS_PATH_FROM_ENV = "optAsPathFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-LockFilePath` option.
     */
    public static final String OPT_LOCK_FILE_PATH_FROM_ENV = "optLockFilePathFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-Project2ProjectTimeOut` option.
     */
    public static final String OPT_PROJECT_2_PROJECT_TIME_OUT_FROM_ENV = "optProject2ProjectTimeOutFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-Name` option.
     */
    public static final String OPT_NAME_FROM_ENV = "optNameFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-ProtocolVersion` option.
     */
    public static final String OPT_PROTOCOL_VERSION_FROM_ENV = "optProtocolVersionFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-Username` option.
     */
    public static final String OPT_USERNAME_FROM_ENV = "optUsernameFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-Password` option.
     */
    public static final String OPT_PASSWORD_FROM_ENV = "optPasswordFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-ValidAuthenticationTypes` option.
     */
    public static final String OPT_VALID_AUTHENTICATION_TYPES_FROM_ENV = "optValidAuthenticationTypesFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-Id` option.
     */
    public static final String OPT_ID_FROM_ENV = "optIdFromEnv";

    /**
     * Property name to instruct env var expansion for the NuGet `-RepositoryPath` option.
     */
    public static final String OPT_REPOSITORY_PATH_FROM_ENV = "optRepositoryPathFromEnv";

    /**
     * Gson instance for serializing and deserializing JSON.
     */
    public static final Gson GSON = new GsonBuilder().serializeNulls().create();

    /**
     * Logger instance for logging messages from the plugin.
     */
    public static Logger LOGGER = Logger.getLoggerFor(TaskPlugin.class);

    /**
     * Initializes the plugin with the GoApplicationAccessor.
     * @param goApplicationAccessor The GoApplicationAccessor instance to interact with the GoCD server.
     */
    @Override
    public void initializeGoApplicationAccessor(GoApplicationAccessor goApplicationAccessor) {
    }

    /**
     * Handles incoming requests from the GoCD server.
     * @param request The request from the GoCD server.
     * @return A response to the request, which could be a configuration, view, validation, execution result, or an icon.
     */
    @Override
    public GoPluginApiResponse handle(GoPluginApiRequest request) throws UnhandledRequestTypeException {
        if ("configuration".equals(request.requestName())) {
            return new GetConfigRequest().execute();
        } else if ("view".equals(request.requestName())) {
            return new GetViewRequest().execute();
        } else if ("validate".equals(request.requestName())) {
            return new ValidateRequest().execute(request);
        } else if ("execute".equals(request.requestName())) {
            return new ExecuteRequest().execute(request);
        } else if ("go.cd.task.get-icon".equals(request.requestName())) {
            return new GetPluginSettingsIconExecutor().execute();
        }
        throw new UnhandledRequestTypeException(request.requestName());
    }

    /**
     * Returns the identifier for the plugin.
     * @return The type of plugin and its GoAPI version number.
     */
    @Override
    public GoPluginIdentifier pluginIdentifier() {
        return new GoPluginIdentifier("task", Arrays.asList("1.0"));
    }
}
