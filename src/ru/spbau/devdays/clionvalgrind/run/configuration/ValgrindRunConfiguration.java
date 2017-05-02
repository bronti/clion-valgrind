package ru.spbau.devdays.clionvalgrind.run.configuration;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.spbau.devdays.clionvalgrind.run.ValgrindCommandLineState;

/**
 * Created by bronti on 02.05.17.
 */

public class ValgrindRunConfiguration  extends RunConfigurationBase {
    protected ValgrindRunConfiguration(Project project, ConfigurationFactory factory, String name) {
        super(project, factory, name);
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
        // todo:
        String executable = "cmake-build-debug/" + executionEnvironment.getProject().getName();
        GeneralCommandLine cl = new GeneralCommandLine("valgrind", executable).withWorkDirectory(executionEnvironment.getProject().getBasePath());
        return createCommandLineState(executionEnvironment, cl);
    }

    private RunProfileState createCommandLineState(@NotNull ExecutionEnvironment executionEnvironment, GeneralCommandLine commandLine) {
        return new ValgrindCommandLineState(executionEnvironment, commandLine);
    }
}
