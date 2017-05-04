package ru.spbau.devdays.clionvalgrind.parser.errors;

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

    void print(OutputStream os) throws IOException {
        os.write(kind.getBytes());
        for (ErrorNode anErrorNodeList : errorNodeList) {
            os.write(System.lineSeparator().getBytes());
            anErrorNodeList.print(os);
        }
    }

}
