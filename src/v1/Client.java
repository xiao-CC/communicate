package v1;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {

    private Socket client;
    private ClientUi ui;

    public Client() {
        this.setSocket();
        ui=new ClientUi();
        this.receiveUi(ui);
    }

    public void setSocket(){
        try {
            client=new Socket("localhost",54321);
            System.out.println("服务端端口："+client.getPort());
            System.out.println("客户端口："+client.getLocalPort());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void receiveUi(ClientUi ui){
        Graphics g=ui.getGraphics();
        int x;
        int y;
        try {
            InputStream is = client.getInputStream();
            while (true){
                x=is.read();
                y=is.read();
                g.setColor(Color.blue);
                g.fillOval(x,y,20,20);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveInt(){
        int vel=0;
        try {
            InputStream is = client.getInputStream();
            int[] msg=new int[4];
            System.out.println("开始读消息:");
            for (int i=0;i<4;i++){
                msg[i]=is.read();
            }
            for (int i=0;i<4;i++){
                vel=vel+msg[i]<<(3-i)*8;
            }
            System.out.println(vel);
            client.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveString(){
        try {
            InputStream is = client.getInputStream();
            int len=is.read();
            byte[] bytes=new byte[len];
            is.read(bytes);//从输入流中读取到bytes中，具体见源码注释
            String msgStr=new String(bytes);
            System.out.println(msgStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
