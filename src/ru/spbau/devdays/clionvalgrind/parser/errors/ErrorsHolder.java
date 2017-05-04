package ru.spbau.devdays.clionvalgrind.parser.errors;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorsHolder {
    //todo: make ErrorsHolder implement Iterable (!!)
    // (я пока сделала errorList пабликом, т к торопилась. потом,
    // когда ErrorsHolder будет итерабл (или как-то еще конвертироваться в стрим) надо будет поменять
    public List<Error> errorList = new ArrayList<Error>();

    public void add(Error error) {
        errorList.add(error);
    }

    public int size() {
        return errorList.size();
    }

    public Error get(int i) {
        return errorList.get(i);
    }

    void print(OutputStream os) throws IOException {
        for (Error anErrorList : errorList) {
            anErrorList.print(os);
            os.write(System.lineSeparator().getBytes());
        }
    }
}
