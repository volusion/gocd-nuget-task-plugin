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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import com.google.gson.GsonBuilder;
import com.thoughtworks.go.plugin.api.request.DefaultGoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoApiResponse;

public class NuGetTaskExecutorTest {
    @Test
    void canExecuteInstallCommand() {
        NuGetTaskExecutor executor = new NuGetTaskExecutor();

        DefaultGoPluginApiRequest request = new DefaultGoPluginApiRequest("task", "1.0", "execute");
        String installCommand = TestUtil.readResource("/fixtures/requests/nuget-install-minimal.json");
        request.setRequestBody(installCommand);

        Map executionRequest = (Map) new GsonBuilder().create().fromJson(request.requestBody(), Object.class);
        Map config = (Map) executionRequest.get("config");
        Map context = (Map) executionRequest.get("context");
        MockConsoleLogger mockConsoleLogger = new MockConsoleLogger(context);

        Result result = executor.execute(new TaskConfig(config), new Context(context), mockConsoleLogger);

        assertThat(result.responseCode(), equalTo(DefaultGoApiResponse.SUCCESS_RESPONSE_CODE));
        assertThat(mockConsoleLogger.getPrintLines().size(), equalTo(1));
        assertThat(mockConsoleLogger.getPrintLines().get(0), equalTo("Launching command: [nuget, install, Newtonsoft.Json, -NonInteractive, -OutputDirectory, ./build/test-results/Newtonsoft.Json]"));
    }

    @Test
    void canExecuteInstallWithEnvCommand() {
        NuGetTaskExecutor executor = new NuGetTaskExecutor();

        DefaultGoPluginApiRequest request = new DefaultGoPluginApiRequest("task", "1.0", "execute");
        String installCommand = TestUtil.readResource("/fixtures/requests/nuget-install-envvars.json");
        request.setRequestBody(installCommand);

        Map executionRequest = (Map) new GsonBuilder().create().fromJson(request.requestBody(), Object.class);
        Map config = (Map) executionRequest.get("config");
        Map context = (Map) executionRequest.get("context");
        MockConsoleLogger mockConsoleLogger = new MockConsoleLogger(context);

        Result result = executor.execute(new TaskConfig(config), new Context(context), mockConsoleLogger);

        assertThat(result.responseCode(), equalTo(DefaultGoApiResponse.SUCCESS_RESPONSE_CODE));
        assertThat(mockConsoleLogger.getPrintLines().size(), equalTo(1));
        assertThat(mockConsoleLogger.getPrintLines().get(0), equalTo("Launching command: [nuget, install, Newtonsoft.Json, -NonInteractive, -OutputDirectory, ./build/test-results/13.0.3/Newtonsoft.Json]"));
    }

    @Test
    void canExecutePackCommand() {
        NuGetTaskExecutor executor = new NuGetTaskExecutor();

        DefaultGoPluginApiRequest request = new DefaultGoPluginApiRequest("task", "1.0", "execute");
        String installCommand = TestUtil.readResource("/fixtures/requests/nuget-pack-minimal.json");
        request.setRequestBody(installCommand);

        Map executionRequest = (Map) new GsonBuilder().create().fromJson(request.requestBody(), Object.class);
        Map config = (Map) executionRequest.get("config");
        Map context = (Map) executionRequest.get("context");
        MockConsoleLogger mockConsoleLogger = new MockConsoleLogger(context);

        Result result = executor.execute(new TaskConfig(config), new Context(context), mockConsoleLogger);

        assertThat(result.responseCode(), equalTo(DefaultGoApiResponse.SUCCESS_RESPONSE_CODE));
        assertThat(mockConsoleLogger.getPrintLines().size(), equalTo(1));
        assertThat(mockConsoleLogger.getPrintLines().get(0), equalTo("Launching command: [nuget, pack, ./src/test/resources/fixtures/FixturePackage/FixturePackage.nuspec, -NonInteractive]"));
    }

    @Test
    void canExecuteConfigCommand() {
        NuGetTaskExecutor executor = new NuGetTaskExecutor();

        DefaultGoPluginApiRequest request = new DefaultGoPluginApiRequest("task", "1.0", "execute");
        String installCommand = TestUtil.readResource("/fixtures/requests/nuget-config-minimal.json");
        request.setRequestBody(installCommand);

        Map executionRequest = (Map) new GsonBuilder().create().fromJson(request.requestBody(), Object.class);
        Map config = (Map) executionRequest.get("config");
        Map context = (Map) executionRequest.get("context");
        MockConsoleLogger mockConsoleLogger = new MockConsoleLogger(context);

        Result result = executor.execute(new TaskConfig(config), new Context(context), mockConsoleLogger);

        assertThat(result.responseCode(), equalTo(DefaultGoApiResponse.SUCCESS_RESPONSE_CODE));
        assertThat(mockConsoleLogger.getPrintLines().size(), equalTo(1));
        assertThat(mockConsoleLogger.getPrintLines().get(0), equalTo("Launching command: [nuget, config, -NonInteractive, -Set, test_config=test_value]"));
    }

    @Test
    void canExecuteConfigCleanupCommand() {
        NuGetTaskExecutor executor = new NuGetTaskExecutor();

        DefaultGoPluginApiRequest request = new DefaultGoPluginApiRequest("task", "1.0", "execute");
        String installCommand = TestUtil.readResource("/fixtures/requests/nuget-config-cleanup.json");
        request.setRequestBody(installCommand);

        Map executionRequest = (Map) new GsonBuilder().create().fromJson(request.requestBody(), Object.class);
        Map config = (Map) executionRequest.get("config");
        Map context = (Map) executionRequest.get("context");
        MockConsoleLogger mockConsoleLogger = new MockConsoleLogger(context);

        Result result = executor.execute(new TaskConfig(config), new Context(context), mockConsoleLogger);

        assertThat(result.responseCode(), equalTo(DefaultGoApiResponse.SUCCESS_RESPONSE_CODE));
        assertThat(mockConsoleLogger.getPrintLines().size(), equalTo(1));
        assertThat(mockConsoleLogger.getPrintLines().get(0), equalTo("Launching command: [nuget, config, -NonInteractive, -Set, test_config=]"));
    }
}
