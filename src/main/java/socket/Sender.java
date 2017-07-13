package socket;

import proxy.TUser;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by zhugongyi on 2017/7/12.
 */
public class Sender {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9001);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeUTF("123");
        outputStream.writeObject(new TUser("Cloud", 1, 18));
        outputStream.writeBoolean(true);
        outputStream.writeShort(22);
        outputStream.close();
        socket.close();
    }

}
