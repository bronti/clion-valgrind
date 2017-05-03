package ru.spbau.devdays.clionvalgrind.parser.errors;

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
}
