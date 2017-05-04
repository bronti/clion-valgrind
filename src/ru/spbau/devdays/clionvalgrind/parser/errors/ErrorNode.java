package ru.spbau.devdays.clionvalgrind.parser.errors;

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

    void print(OutputStream os) throws IOException {
        os.write(("\t" + what).getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < funcList.size(); i++) {
            sb.append(funcList.get(i));
            if (!lineNumber.get(i).equals(-1)) {
                sb.append(':');
                sb.append(lineNumber.get(i));
            }
            sb.append(" in ");
            sb.append(dirList.get(i));
            sb.append(System.lineSeparator());
        }
        os.write(sb.toString().getBytes());
    }
}
