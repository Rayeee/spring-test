package proxy.rpc;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by zhugongyi on 2017/7/11.
 */
public class TestSocket {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket[] sockets = new Socket[100];
        for (int i = 0; i < 51; i++) {
            sockets[i] = new Socket("127.0.0.1", 9000);
            System.out.println("第" + (i + 1) + "次连接成功");
        }
        Thread.sleep(3000);
        for (int i = 0; i < 100; i++) {
            sockets[i].close();      //断开连接
        }
    }

}
