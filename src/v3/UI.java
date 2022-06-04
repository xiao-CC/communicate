package v3;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {

    private Server server;
    private Client client;
    private JTextArea textArea;
    private JTextField textField;

    public UI(String title,Server server) throws HeadlessException {
        this.server = server;
        this.init(title);
    }
    public UI(String title,Client client) throws HeadlessException {
        this.client=client;
        this.init(title);
    }

    private void init(String title){
        this.setTitle(title);
        this.setSize(600,600);
        this.setLayout(null);
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        textArea=new JTextArea();
        textField=new JTextField();
        Button button=new Button();
        textArea.setBounds(10,10,560,400);
        textField.setBounds(10,420,560,80);
        button.setBounds(480,510,80,30);
        button.setLabel("send");

        this.add(textArea);
        this.add(textField);
        this.add(button);
        this.setVisible(true);

        UIListen listen=new UIListen(client,textField,textArea);
        button.addActionListener(listen);
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
