package v3;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class ReadThread implements Runnable{

    private Server server;
    private Socket socket;
    private Vector<Socket> sockets;

    public ReadThread(Server server, Vector<Socket> sockets, Socket socket) {
        this.server = server;
        this.socket = socket;
        this.sockets = sockets;
    }

    @Override
    public void run() {

        while (true){
            try {
                Integer msg=server.readMsg(socket);
                for (int i=0;i<sockets.size();i++){
                    if (sockets.get(i)!=socket){
                        server.transmit(sockets.get(i),msg);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
