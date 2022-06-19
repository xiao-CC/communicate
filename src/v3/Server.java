package v3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {

    private ServerSocket serverSocket;
    private Vector<Socket> sockets;
    private Vector<Client> clients;
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

    public Integer readMsg(Socket socket) throws IOException {
        InputStream is=socket.getInputStream();
        int outMsg=msgTool.readInt(is);
        ui.getTextArea().append("用户1"+":"+outMsg+"\n");
        return outMsg;
    }

    public void transmit(Socket socket,Integer msg) throws IOException{
        OutputStream os=socket.getOutputStream();
        msgTool.writeInt(os,msg);
    }

    public Vector<Socket> getSockets() {
        return sockets;
    }
    public void setSockets(Vector<Socket> sockets) {
        this.sockets = sockets;
    }
    public Vector<Client> getClients() {
        return clients;
    }
    public void setClients(Vector<Client> clients) {
        this.clients = clients;
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
