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
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

/**
 * Class handler for validating the NuGet task configuration
 */
public class ValidateRequest {
    /**
     * Check if config var is not defined
     * @param configMap
     * @param property
     * @return
     */
    private boolean configVarIsNotDefined(Map configMap, String property) {
        return !configMap.containsKey(property) || ((Map) configMap.get(property)).get("value") == null || ((String) ((Map) configMap.get(property)).get("value")).trim().isEmpty();
    }

    /**
     * Get an error message for a missing argument
     * @param command
     * @param property
     * @param cliPlaceholderText
     * @return
     */
    private String getMissingArgumentErrorMsg(String command, String property, String cliPlaceholderText) {
        return String.format("Command %s must have Argument %s set to %s", command, property, cliPlaceholderText);
    }

    /**
     * This function receives a NuGet task configuration and validates it
     * @param request The API request that contains the configuration to validate
     * @return The validation result, including any errors found in the configuration
     */
    public GoPluginApiResponse execute(GoPluginApiRequest request) {
        Map configMap = (Map) new GsonBuilder().create().fromJson(request.requestBody(), Object.class);
        HashMap errorMap = new HashMap<String, String>();

        if (configVarIsNotDefined(configMap, TaskPlugin.COMMAND_PROPERTY)) {
            errorMap.put(TaskPlugin.COMMAND_PROPERTY, "Command must be set to one of [install, pack, push, config, restore, add, delete, init, sources, update]");
        }

        String command = ((String) ((Map) configMap.get(TaskPlugin.COMMAND_PROPERTY)).get("value")).trim();

        if (TaskPlugin.CMDSET_INSTALL_PACK_PUSH_RESTORE_ADD_DELETE_UPDATE.contains(command)) {
            HashMap<String, String> firstArgLookup = new HashMap<String, String>();
            firstArgLookup.put(TaskPlugin.CMD_INSTALL, "<packageID | configFilePath>");
            firstArgLookup.put(TaskPlugin.CMD_PACK, "<nuspecPath | projectPath>");
            firstArgLookup.put(TaskPlugin.CMD_PUSH, "<packagePath>");
            firstArgLookup.put(TaskPlugin.CMD_RESTORE, "<projectPath>");
            firstArgLookup.put(TaskPlugin.CMD_ADD, "<packagePath>");
            firstArgLookup.put(TaskPlugin.CMD_DELETE, "<packageID>");
            firstArgLookup.put(TaskPlugin.CMD_UPDATE, "<packageID>");

            if (configVarIsNotDefined(configMap, TaskPlugin.ARG_PACKAGE_ID_OR_FILE_PATH)) {
                errorMap.put(TaskPlugin.ARG_PACKAGE_ID_OR_FILE_PATH, getMissingArgumentErrorMsg(command, TaskPlugin.ARG_PACKAGE_ID_OR_FILE_PATH, firstArgLookup.get(command)));
            }

            if (TaskPlugin.CMD_DELETE.equals(command)) {
                if (configVarIsNotDefined(configMap, TaskPlugin.ARG_PACKAGE_VERSION)) {
                    errorMap.put(TaskPlugin.ARG_PACKAGE_VERSION, getMissingArgumentErrorMsg(command, TaskPlugin.ARG_PACKAGE_ID_OR_FILE_PATH, "<packageVersion>"));
                }
            }
        } else if (TaskPlugin.CMD_INIT.equals(command)) {
            if (configVarIsNotDefined(configMap, TaskPlugin.ARG_SOURCE)) {
                errorMap.put(TaskPlugin.ARG_SOURCE, getMissingArgumentErrorMsg(command, TaskPlugin.ARG_SOURCE, "<source>"));
            }

            if (configVarIsNotDefined(configMap, TaskPlugin.ARG_DESTINATION)) {
                errorMap.put(TaskPlugin.ARG_DESTINATION, getMissingArgumentErrorMsg(command, TaskPlugin.ARG_DESTINATION, "<destination>"));
            }
        } else if (TaskPlugin.CMD_SOURCES.equals(command)) {
            if (configVarIsNotDefined(configMap, TaskPlugin.ARG_OPERATION)) {
                errorMap.put(TaskPlugin.ARG_OPERATION, getMissingArgumentErrorMsg(command, TaskPlugin.ARG_OPERATION, "<operation>"));
            }
        }

        HashMap<String, Object> validationResult = new HashMap<>();
        validationResult.put("errors", errorMap);
        return new DefaultGoPluginApiResponse(DefaultGoPluginApiResponse.SUCCESS_RESPONSE_CODE, TaskPlugin.GSON.toJson(validationResult));
    }
}
