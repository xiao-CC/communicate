package v2;

import java.io.IOException;
import java.net.Socket;

public class ReadThread implements Runnable{

    private Server server;
    private Socket socket;

    public ReadThread(Server server, Socket socket) {
        this.server=server;
        this.socket=socket;
    }

    @Override
    public void run() {
        while (true){
            try {
                server.readMsg(socket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
