package v3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MsgTool {

    private InputStream is;
    private OutputStream os;

    public void writeInt(OutputStream outputStream, int value){
        this.os=outputStream;
        try {
            int b24=(value>>24)&0xFF;
            int b16=(value>>16)&0xFF;
            int b8=(value>>8)&0xFF;
            int b0=(value>>0)&0xFF;
            os.write(b24);
            os.write(b16);
            os.write(b8);
            os.write(b0);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int readInt(InputStream inputStream){

        int vel=0;
        try {
            this.is=inputStream;
            int[] msg=new int[4];
            for (int i=0;i<4;i++){
                //开启两个客户端1和2。当先用2给服务器发送，再用1给服务器发送时，会卡到这里
                msg[i]=is.read();
            }
            for (int i=0;i<4;i++){
                vel=vel+msg[i]<<(3-i)*8;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vel;
    }

}
