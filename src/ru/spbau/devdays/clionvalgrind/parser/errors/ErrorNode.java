package ru.spbau.devdays.clionvalgrind.parser.errors;

import java.util.List;

public class ErrorNode {

    private String what;
    private List<String> funcList;
    private List<String> dirList;
    private List<Integer> lineNumber;

    public ErrorNode(String what, List<String> funcList,
                     List<String> dirList, List<Integer> lineNumber) {
        this.what = what;
        this.funcList = funcList;
        this.dirList = dirList;
        this.lineNumber = lineNumber;
    }
}
