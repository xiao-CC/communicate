package v3;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class WriteTheard implements Runnable{
    private Client client;
    private String msg;

    public WriteTheard(String msg) {
        this.msg = msg;
    }
    public WriteTheard(Client client, String msg) {
        this.client = client;
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            client.writeMsg(Integer.parseInt(msg));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
