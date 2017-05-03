package ru.spbau.devdays.clionvalgrind.run;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.ColoredProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.NotNull;
import ru.spbau.devdays.clionvalgrind.results.ValgrindRunConsoleBuilder;

/**
 * Created by bronti on 02.05.17.
 */
public class ValgrindCommandLineState extends CommandLineState {

    private GeneralCommandLine commandLine;
    private String pathToXml;

    public ValgrindCommandLineState(ExecutionEnvironment executionEnvironment, String pathToXml, GeneralCommandLine commandLine)
    {
        super(executionEnvironment);
        this.commandLine = commandLine;
        this.pathToXml = pathToXml;
        setConsoleBuilder(new ValgrindRunConsoleBuilder(executionEnvironment.getProject()));
    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        ColoredProcessHandler procHandler = new ColoredProcessHandler(commandLine);
        ProcessTerminatedListener.attach(procHandler);
        return procHandler;
    }
}



