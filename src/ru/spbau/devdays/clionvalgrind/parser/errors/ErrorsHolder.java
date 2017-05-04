package ru.spbau.devdays.clionvalgrind.parser.errors;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorsHolder {
    private List<Error> errorList = new ArrayList<Error>();

    public void add(Error error) {
        errorList.add(error);
    }

    public int size() {
        return errorList.size();
    }

    public Error get(int i) {
        return errorList.get(i);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (errorList.size() == 0) {
            sb.append("Valgrind Memory Analyzer finished, 0 issues were found");
        }
        for (Error anErrorList : errorList) {
            sb.append(anErrorList.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
