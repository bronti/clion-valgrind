package ru.spbau.devdays.clionvalgrind.parser.errors;

import javax.swing.tree.DefaultMutableTreeNode;
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

//    public List<Integer> getLineNumber() {
//        return
//    }

    public DefaultMutableTreeNode getTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                    "Valgrind Memory Analyzer finished, " + errorList.size()
                              + " issues were found"
        );

        for (Error anError: errorList) {
            root.add(anError.getTree());
        }
        return root;
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
