package ru.spbau.devdays.clionvalgrind.run.configuration;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by bronti on 02.05.17.
 */
public class ValgrindRunConfigurationType implements ConfigurationType {
    @Override
    public String getDisplayName() {
        return "Valgrind";
    }

    @Override
    public String getConfigurationTypeDescription() {
        return "Valgrind Run Configuration Type";
    }

    @Override
    public Icon getIcon() {
//        return new ImageIcon("../resources/icon.jpg");
        return AllIcons.General.Information;
    }

    @NotNull
    @Override
    public String getId() {
        return "VALGRIND_RUN_CONFIGURATION";
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{new ValgrindConfigurationFactory(this)};
    }
}
