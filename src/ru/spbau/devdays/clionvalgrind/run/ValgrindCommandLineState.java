package ru.spbau.devdays.clionvalgrind.run;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.ColoredProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import org.jetbrains.annotations.NotNull;
import ru.spbau.devdays.clionvalgrind.results.ValgrindExecutionResult;

/**
 * Created by bronti on 02.05.17.
 */
public class ValgrindCommandLineState extends CommandLineState {

    private GeneralCommandLine commandLine;

    public ValgrindCommandLineState(ExecutionEnvironment executionEnvironment, GeneralCommandLine commandLine)
    {
        super(executionEnvironment);
        this.commandLine = commandLine;
    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        ColoredProcessHandler procHandler = new ColoredProcessHandler(commandLine);
        ProcessTerminatedListener.attach(procHandler);
        return procHandler;
    }

    @NotNull
    @Override
    public ExecutionResult execute(@NotNull Executor executor, @NotNull ProgramRunner runner) throws ExecutionException {
//        return BringConsoleToFrontExecutionResult(super.execute(executor, runner), getEnvironment(), executor);
        return new ValgrindExecutionResult(super.execute(executor, runner));
    }

}



