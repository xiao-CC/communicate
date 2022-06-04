package v3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UIListen implements ActionListener{

    private Client client;
    private Server server;
    private JTextField textField;
    private JTextArea textArea;

    public UIListen(JTextField textField,JTextArea textArea) {
        this.textField = textField;
        this.textArea=textArea;
    }
    public UIListen(Client client, JTextField textField, JTextArea textArea) {
        this.client = client;
        this.textField = textField;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg=textField.getText();
        textArea.append(client.getName()+":"+msg+"\n");
        try {
            client.writeMsg(Integer.parseInt(msg));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        textField.setText("");  //清空内容
    }
}
