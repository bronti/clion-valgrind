package ru.spbau.devdays.clionvalgrind.results;

import com.google.common.collect.Lists;
import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.spbau.devdays.clionvalgrind.parser.Parser;
import ru.spbau.devdays.clionvalgrind.parser.errors.ErrorsHolder;
import sun.java2d.loops.ProcessPath;

import java.util.ArrayList;

/**
 * Created by bronti on 03.05.17.
 */
public class ValgrindRunConsoleBuilder extends TextConsoleBuilder {
    private final Project project;
    private final ArrayList<Filter> myFilters = Lists.newArrayList();
    private String pathToXml;
    private ProcessHandler process;

    public ValgrindRunConsoleBuilder(final Project project, ProcessHandler process, String pathToXml) {
        this.project = project;
        this.pathToXml = pathToXml;
        this.process = process;
    }

    @Override
    public ConsoleView getConsole() {
        final ConsoleView consoleView = createConsole();
        for (final Filter filter : myFilters) {
            consoleView.addMessageFilter(filter);
        }
        return consoleView;
    }

    protected ConsoleView createConsole() {
        ConsoleView outputConsole = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        outputConsole.attachToProcess(process);

        ValgrindConsoleView resultConsole = new ValgrindConsoleView(project, outputConsole, pathToXml);
        process.addProcessListener(new ProcessAdapter() {
            @Override
            public void processTerminated(ProcessEvent event) {
                resultConsole.refreshErrors();
            }
        });
        return resultConsole;
    }

    @Override
    public void addFilter(@NotNull final Filter filter) {
        myFilters.add(filter);
    }

    @Override
    public void setViewer(boolean isViewer) {
    }
}
