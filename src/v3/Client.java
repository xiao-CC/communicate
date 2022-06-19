package v3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    private Socket socket;
    private String name;
    private UI ui;
    private MsgTool msgTool=new MsgTool();

    public Client() throws IOException {
        this.init("127.0.0.1",12345);
    }

    public void init(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        ui=new UI("客户端",this);
    }

    public void writeMsg(int msg) throws IOException {
        OutputStream os = socket.getOutputStream();
        msgTool.writeInt(os,msg);
    }

    public void readMsg() throws IOException {
        InputStream is=socket.getInputStream();
        while (true){
            int outMsg=msgTool.readInt(is);
            ui.getTextArea().append("用户1"+":"+outMsg+"\n");
        }
    }

    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws IOException {
        Client client=new Client();
        client.readMsg();
    }
}