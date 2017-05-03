package ru.spbau.devdays.clionvalgrind.results;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * Created by bronti on 03.05.17.
 */
public class ValgrindResultsWindowFactory implements ToolWindowFactory {
    public static String VALGRIND_RESULTS_WINDOW_ID = "Valgrind Results";

//    private ToolWindow myToolWindow;
    private JPanel valgrindResultsContent;
    private JTextArea consoleOutput;


    @Override
    public void createToolWindowContent(@NotNull final Project project, @NotNull final ToolWindow toolWindow) {
//        myToolWindow = toolWindow;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(valgrindResultsContent, "", false);
        toolWindow.getContentManager().addContent(content);
        feedConsoleOutput("Hi! I'm working!");
//        ProjectManager.getInstance().addProjectManagerListener(project, new Exec);
    }

    public void feedConsoleOutput(String output) {
        consoleOutput.setText(output);
    }

}