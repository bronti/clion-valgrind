package ru.spbau.devdays.clionvalgrind.run.configuration;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.cpp.cmake.CMakeSettings;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.spbau.devdays.clionvalgrind.run.ValgrindCommandLineState;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ValgrindRunConfiguration extends RunConfigurationBase {
    Project myProject;
    protected ValgrindRunConfiguration(Project project, ConfigurationFactory factory, String name) {
        super(project, factory, name);
        myProject = project;
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new ValgrindSettingsEditor();
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) throws ExecutionException {

        CMakeWorkspace cMakeWorkspace = CMakeWorkspace.getInstance(myProject);

        List<CMakeSettings.Configuration> configurations =
                cMakeWorkspace.getSettings().getConfigurations();
        if (configurations.isEmpty()) {
            throw new RuntimeException();
        }

        // select the first configuration in the list
        // cannot get active configuration for the current project.
        // code from https://intellij-support.jetbrains.com
        // /hc/en-us/community/posts/115000107544-CLion-Get-cmake-output-directory
        // doesn't work
        CMakeSettings.Configuration selectedConfiguration = configurations.get(0);
        String selectedConfigurationName = selectedConfiguration.getConfigName();

        // get the path of generated files of the selected configuration
        List<File> configDir = cMakeWorkspace.getEffectiveConfigurationGenerationDirs(
                Arrays.asList(Pair.create(selectedConfigurationName, null)));

        String executable = configDir.get(0).getAbsolutePath() + "/"
                            + executionEnvironment.getProject().getName();
        GeneralCommandLine cl = new GeneralCommandLine("valgrind", executable)
                                    .withWorkDirectory(executionEnvironment.getProject().getBasePath());
        return createCommandLineState(executionEnvironment, cl);
    }

    private RunProfileState createCommandLineState(@NotNull ExecutionEnvironment executionEnvironment,
                                                   GeneralCommandLine commandLine) {
        // todo: only run/debug/wcoverage
        // todo: String -> Path
        String pathToExecutable = "/cmake-build-debug/" + executionEnvironment.getProject().getName();
        String pathToXml = "/cmake-build-debug/" + executionEnvironment.getProject().getName() + "-valgrind-results.xml";
        GeneralCommandLine cl = new GeneralCommandLine("valgrind", "--xml=yes", "--xml-file=" + pathToXml, pathToExecutable);
        cl = cl.withWorkDirectory(executionEnvironment.getProject().getBasePath());
        return new ValgrindCommandLineState(executionEnvironment, pathToXml, cl);
    }
}
