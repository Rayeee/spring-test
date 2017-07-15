package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by zhugongyi on 2017/7/12.
 */
public class ClientMay {

    private static final String remote_host = "127.0.0.1";

    private static final int remote_port = 9000;

    private static final String account = "May";

    private static final String my_host = "127.0.0.1";

    private static final int my_port = 9200;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(my_port);

        firstShake();

        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            for (; ; ) {
                Socket socket = null;
                try {
                    socket = new Socket(remote_host, remote_port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ObjectOutputStream outputStream = null;
                try {
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String content = scanner.nextLine();
                if ("quit".equals(content)) {
                    //todo 加上服务端清除连接
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
                MyMessage message = new MyMessage(UUID.randomUUID().toString(), content, account, "Cloud");
                try {
                    outputStream.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                Socket accept = null;
                try {
                    accept = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ObjectInputStream inputStream = null;
                try {
                    inputStream = new ObjectInputStream(accept.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MyMessage message = null;
                try {
                    message = (MyMessage) inputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("获取" + message.getSource() +"的消息" + message.getContent());
            }
        }).start();
    }

    public static void firstShake() throws IOException {
        //首次握手
        Socket firstShake = new Socket("127.0.0.1", 9000);
        ObjectOutputStream o1 = new ObjectOutputStream(firstShake.getOutputStream());
        o1.writeObject(new ShakeHandDto(account, my_host, my_port));
        o1.flush();
        o1.close();
        firstShake.close();
    }

}
