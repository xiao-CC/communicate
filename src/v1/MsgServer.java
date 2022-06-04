package v1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MsgServer {

    private ServerSocket serverSocket;
    private Socket socket;
    private ServerUi serverUi;
    private SerLisen serLisen;

    public MsgServer() {
        this.connection();
        serLisen =new SerLisen(this);
        serverUi=new ServerUi(serLisen);
    }

    public void connection(){
        try {
            serverSocket=new ServerSocket(54321);
            socket=serverSocket.accept();
            System.out.println("正在监听...");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void telnet(){
        try {
            InputStream is=socket.getInputStream();
            //OutputStream os=socket.getOutputStream();
            while (true){
                int msg=is.read();
                System.out.println((char) msg);
            }
            //is.close();
            //os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendInt(int value){
        OutputStream os= null;  //这里是用socket的输出流，而不是用serversocket的
        try {
            os = socket.getOutputStream();
            int b24=(value>>24)&0xFF;
            int b16=(value>>16)&0xFF;
            int b8=(value>>8)&0xFF;
            int b0=(value>>0)&0xFF;
            os.write(b24);
            os.write(b16);
            os.write(b8);
            os.write(b0);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Socket getSocket(){
        return socket;
    }
    public void sendSring(String msg){
        int len=msg.getBytes().length;
        OutputStream os= null;
        try {
            if (len>255){
                System.out.println("字数限制在255以内");
            }else {
                os = socket.getOutputStream();
                os.write(len);
                os.write(msg.getBytes()); //根据源码注释，这里会将String类型转为byte[]，然后进行输出
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
