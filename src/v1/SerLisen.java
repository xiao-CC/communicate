package v1;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;

public class SerLisen implements MouseListener {

    int x;
    int y;
    Graphics g;
    Socket socket;
    MsgServer server;

    public void setG(Graphics g){this.g=g;}
    public SerLisen(MsgServer server) {
        //this.socket=server.getSocket();
        this.server=server;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        g.setColor(Color.blue);
        g.fillOval(x,y,20,20);

        server.sendInt(x);
        server.sendInt(y);
        /*try {
            OutputStream os=socket.getOutputStream();
            os.write(x);
            os.write(y);
            //os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/

    }

    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

}
