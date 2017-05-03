package ru.spbau.devdays.clionvalgrind.parser.errors;

import java.util.ArrayList;
import java.util.List;

public class Error {

    private String kind;
    private List<ErrorNode> errorList = new ArrayList<ErrorNode>();

    public Error() {}

    public Error(String kind) {
        this.kind = kind;
    }

    public void add(ErrorNode error) {
        errorList.add(error);
    }

    public int size() {
        return errorList.size();
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
