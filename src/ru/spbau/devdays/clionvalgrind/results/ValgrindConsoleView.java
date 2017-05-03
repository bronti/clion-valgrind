package ru.spbau.devdays.clionvalgrind.results;

import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.HyperlinkInfo;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.*;
import com.intellij.ui.speedSearch.SpeedSearchUtil;
import com.intellij.ui.treeStructure.SimpleTreeBuilder;
import com.intellij.ui.treeStructure.SimpleTreeStructure;
import com.intellij.ui.treeStructure.treetable.ListTreeTableModelOnColumns;
import com.intellij.ui.treeStructure.treetable.TreeColumnInfo;
import com.intellij.ui.treeStructure.treetable.TreeTable;
import com.intellij.ui.treeStructure.treetable.TreeTableTree;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

/**
 * Created by bronti on 03.05.17.
 */
//public class ValgrindConsoleView extends DuplexConsoleView<ConsoleView, ConsoleView> {
//    public ValgrindConsoleView(final Project project, ConsoleView consoleView) {
//        super(consoleView, TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole());
//    }
//}

public class ValgrindConsoleView implements ConsoleView {

    private final JBSplitter mainPanel;
    private final Project project;
    private final ConsoleView console;

//    private static final int CONSOLE_COLUMN_MIN_WIDTH = 300;
//    private static final int ERRORS_COLUMN_MIN_WIDTH  = 300;

    public ValgrindConsoleView(final Project project, ConsoleView console) {
        this.project = project;
        this.console = console;
        mainPanel = new JBSplitter();
        mainPanel.setFirstComponent(console.getComponent());
        JTextArea text = new JTextArea("Ololo!!!");
        mainPanel.setSecondComponent(text);
    }

    @Override
    public JComponent getComponent() {
        return mainPanel;
    }

    @Override
    public void dispose() {}

    @Override
    public void print(@NotNull String s, @NotNull ConsoleViewContentType contentType) {}

    @Override
    public void clear() {}

    @Override
    public void scrollTo(int offset) {}

    @Override
    public void attachToProcess(ProcessHandler processHandler) {}

    @Override
    public void setOutputPaused(boolean value) {}

    @Override
    public boolean isOutputPaused() {
        return false;
    }

    @Override
    public boolean hasDeferredOutput() {
        return false;
    }

    @Override
    public void performWhenNoDeferredOutput(@NotNull Runnable runnable) {}

    @Override
    public void setHelpId(@NotNull String helpId) {}

    @Override
    public void addMessageFilter(@NotNull Filter filter) {}

    @Override
    public void printHyperlink(@NotNull String hyperlinkText, HyperlinkInfo info) {}

    @Override
    public int getContentSize() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return false;
    }

    @NotNull
    @Override
    public AnAction[] createConsoleActions() {
        return AnAction.EMPTY_ARRAY;
    }

    @Override
    public void allowHeavyFilters() {}

    @Override
    public JComponent getPreferredFocusableComponent() {
        return console.getPreferredFocusableComponent();
    }
}
