package v3;

import java.net.Socket;
import java.util.ArrayList;

public class ConnectTheard implements Runnable{

    private Server server;
    private volatile ArrayList<Socket> sockets=new ArrayList<>();

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
