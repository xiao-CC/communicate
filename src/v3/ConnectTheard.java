package v3;

import java.net.Socket;
import java.util.Vector;

public class ConnectTheard implements Runnable{

    private Server server;
    private volatile Vector<Socket> sockets=new Vector<>();

    public ConnectTheard(Server server) {
        this.server=server;
    }

    @Override
    public void run() {
        while (true){
            Socket socket=server.connect();
            sockets.add(socket);
            ReadThread readThread=new ReadThread(server,sockets,socket);
            new Thread(readThread).start();
        }
    }
}
