package ru.spbau.devdays.clionvalgrind.parser.errors;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;
import java.io.OutputStream;
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

    List<Integer> getLineNumber() {
        return lineNumber;
    }

    public DefaultMutableTreeNode getTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(what);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < funcList.size(); i++) {
            sb.append(funcList.get(i));
            sb.append(" in ");
            sb.append(dirList.get(i));
            if (!lineNumber.get(i).equals(-1)) {
                sb.append(':');
                sb.append(lineNumber.get(i));
            }
            root.add(new DefaultMutableTreeNode(sb.toString()));
            sb.setLength(0);
        }
        return root;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t");
        sb.append(what);
        for (int i = 0; i < funcList.size(); i++) {
            sb.append(System.lineSeparator());
            sb.append("\t\t");
            sb.append(funcList.get(i));
            sb.append(" in ");
            sb.append(dirList.get(i));
            if (!lineNumber.get(i).equals(-1)) {
                sb.append(':');
                sb.append(lineNumber.get(i));
            }
        }
        return sb.toString();
    }
}
