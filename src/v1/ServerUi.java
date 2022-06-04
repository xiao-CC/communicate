package v1;

import javax.swing.*;

public class ServerUi extends JFrame {

    public ServerUi(SerLisen liesen) {
        this.setSize(400,400);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocation(800,100);
        this.setTitle("服务端");
        this.setVisible(true);
        this.addMouseListener(liesen);
        liesen.setG(this.getGraphics());
    }

}
