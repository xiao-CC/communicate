package v3;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private String name;

    public Client() throws IOException {
        this.init("127.0.0.1",12345);
    }

    public void init(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        UI ui=new UI("客户端",this);
    }

    public void writeMsg(int msg) throws IOException {
        OutputStream os = socket.getOutputStream();
        os.write(msg);
        os.flush();
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
    }
}