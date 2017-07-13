package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhugongyi on 2017/7/12.
 */
public class Receiver {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9001);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("获取来自" + new String(socket.getInetAddress().getHostAddress()) + socket.getPort() + "的连接");
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                String str = inputStream.readUTF();
                Object o = inputStream.readObject();
                boolean b = inputStream.readBoolean();
                short i = inputStream.readShort();
                System.out.println(i);
                System.out.println(o);
                System.out.println(str);
                System.out.println(b);
                socket.close();
            } catch (Exception e) {

            }
        }
    }

}
