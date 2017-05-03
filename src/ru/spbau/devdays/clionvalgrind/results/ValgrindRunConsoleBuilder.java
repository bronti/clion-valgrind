package ru.spbau.devdays.clionvalgrind.results;

import com.google.common.collect.Lists;
import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Created by bronti on 03.05.17.
 */
public class ValgrindRunConsoleBuilder extends TextConsoleBuilder {
    private final Project project;
    private final ArrayList<Filter> myFilters = Lists.newArrayList();

    public ValgrindRunConsoleBuilder(final Project project) {
        this.project = project;
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
        return null;
    }

    @Override
    public void addFilter(@NotNull final Filter filter) {
        myFilters.add(filter);
    }

    @Override
    public void setViewer(boolean isViewer) {
    }

}
