package v3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
    private ArrayList<Socket> sockets;
    private ArrayList<Client> clients;
    private UI ui;
    MsgTool msgTool=new MsgTool();

    public Server() throws IOException {
        this.init(12345);
    }
    public void init(int port) throws IOException {
        ui=new UI("服务端",this);
        serverSocket=new ServerSocket(port);    //新建个服务器，占用一个端口
    }

    public Socket connect(){
        Socket socket = null;
        try {
            socket=serverSocket.accept ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return socket;
    }

    public static void main(String[] args) throws IOException {
        Server server;
        try {
            server = new Server();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ConnectTheard connects = new ConnectTheard(server);
        new Thread(connects).start();
    }
}
