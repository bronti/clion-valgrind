package ru.spbau.devdays.clionvalgrind.run.configuration;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by bronti on 02.05.17.
 */
public class ValgrindSettingsEditor extends SettingsEditor<ValgrindRunConfiguration> {
    private JPanel myPanel;
    private LabeledComponent<ComponentWithBrowseButton> myMainClass;

    @Override
    protected void resetEditorFrom(ValgrindRunConfiguration demoRunConfiguration) {
    }

    @Override
    protected void applyEditorTo(ValgrindRunConfiguration demoRunConfiguration) throws ConfigurationException {
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return myPanel;
    }

    private void createUIComponents() {
        myMainClass = new LabeledComponent<ComponentWithBrowseButton>();
        myMainClass.setComponent(new TextFieldWithBrowseButton());
    }
}
