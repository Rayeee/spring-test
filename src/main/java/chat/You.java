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
public class You {

    private static final String name = "Cloud";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(9999);

        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            for (; ; ) {
                Socket socket = null;
                try {
                    socket = new Socket("127.0.0.1", 9999);
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
                MyMessage message = new MyMessage(UUID.randomUUID().toString(), content, name, "May");
                try {
                    outputStream.writeObject(message);
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
                System.out.println(message.toString());
            }
        }).start();
    }
}
