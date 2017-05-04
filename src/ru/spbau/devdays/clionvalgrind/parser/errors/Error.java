package ru.spbau.devdays.clionvalgrind.parser.errors;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Error {
    // todo: add proper toString
    // todo: доступ к errorList?

    private String kind;
    private List<ErrorNode> errorNodeList = new ArrayList<ErrorNode>();

    public Error() {}
    public Error(String kind) {
        this.kind = kind;
    }

    public void add(ErrorNode error) {
        errorNodeList.add(error);
    }
    public int size() {
        return errorNodeList.size();
    }

    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }

    public DefaultMutableTreeNode getTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(kind);
        for (ErrorNode anErrorNode: errorNodeList) {
            root.add(anErrorNode.getTree());
        }
        return root;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(kind);
        for (ErrorNode anErrorNodeList : errorNodeList) {
            sb.append(System.lineSeparator());
            sb.append(anErrorNodeList.toString());
        }
        return sb.toString();
    }

}
