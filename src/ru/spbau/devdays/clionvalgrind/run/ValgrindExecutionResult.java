package ru.spbau.devdays.clionvalgrind.run;

import com.intellij.execution.ExecutionResult;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ExecutionConsole;
import com.intellij.openapi.actionSystem.AnAction;
import org.jetbrains.annotations.NotNull;

/**
 * Created by bronti on 02.05.17.
 */
public class ValgrindExecutionResult implements ExecutionResult {

    private ExecutionResult result;

    ValgrindExecutionResult(ExecutionResult exexutionResult) {
        this.result = exexutionResult;
    }

    @Override
    public ExecutionConsole getExecutionConsole() {
        return result.getExecutionConsole();
    }

    @NotNull
    @Override
    public AnAction[] getActions() {
        return result.getActions();
    }

    @Override
    public ProcessHandler getProcessHandler() {
        return result.getProcessHandler();
    }
}