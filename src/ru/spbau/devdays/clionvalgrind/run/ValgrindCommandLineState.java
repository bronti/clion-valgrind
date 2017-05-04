package ru.spbau.devdays.clionvalgrind.run;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.ColoredProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import ru.spbau.devdays.clionvalgrind.results.ValgrindRunConsoleBuilder;

/**
 * Created by bronti on 02.05.17.
 */
public class ValgrindCommandLineState extends CommandLineState {

    private GeneralCommandLine commandLine;

    // todo: can change?
    private String pathToXml;

    public ValgrindCommandLineState(ExecutionEnvironment executionEnvironment, String pathToXml, GeneralCommandLine commandLine)
    {
        super(executionEnvironment);
        this.commandLine = commandLine;
        this.pathToXml = pathToXml;
    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        Project project = getEnvironment().getProject();

        ColoredProcessHandler process = new ColoredProcessHandler(commandLine);

        setConsoleBuilder(new ValgrindRunConsoleBuilder(project, process, pathToXml));
        ProcessTerminatedListener.attach(process, project);
        return process;
    }
}



