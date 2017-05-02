package ru.spbau.devdays.clionvalgrind.run.configuration;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Created by bronti on 02.05.17.
 */
public class ValgrindConfigurationFactory extends ConfigurationFactory {
    private static final String FACTORY_NAME = "Valgrind configuration factory";

    protected ValgrindConfigurationFactory(ConfigurationType type) {
        super(type);
    }

    @Override
    @NotNull
    public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new ValgrindRunConfiguration(project, this, "Valgrind");
    }

    @Override
    public String getName() {
        return FACTORY_NAME;
    }
}
