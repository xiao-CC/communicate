package v3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ReadThread implements Runnable{

    private Server server;
    private Socket socket;
    private ArrayList<Socket> sockets;
    private MsgTool msgTool=new MsgTool();

    public ReadThread(Server server, ArrayList<Socket> sockets, Socket socket) {
        this.server = server;
        this.socket = socket;
        this.sockets = sockets;
    }

    @Override
    public void run() {

        while (true){
            try {

                InputStream is=socket.getInputStream();
                int outMsg=msgTool.readInt(is);
                System.out.println(outMsg);
                for (int i=0;i<sockets.size();i++){
                    if (sockets.get(i)!=socket){
                        OutputStream os=sockets.get(i).getOutputStream();
                        msgTool.writeInt(os,outMsg);
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
