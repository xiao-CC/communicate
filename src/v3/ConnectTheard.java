package v3;

import java.net.Socket;
import java.util.Vector;

public class ConnectTheard implements Runnable{

    private Server server;
    private Vector<Client> clients;
    private Vector<Socket> sockets=new Vector<>();

    public ConnectTheard(Server server,Vector<Client> clients) {
        this.server=server;
        this.clients = clients;
    }

    @Override
    public void run() {
        while (true){
            Socket socket=server.connect();
            sockets.add(socket);
            if (socket!=null){
                for (int i=0;i<sockets.size();i++){
                    ReadThread readThread=new ReadThread(server,sockets.get(i));
                    new Thread(readThread).start();
                }
            }
        }
    }

    public Vector<Client> getClients() {
        return clients;
    }
    public void setClients(Vector<Client> clients) {
        this.clients = clients;
    }
}
