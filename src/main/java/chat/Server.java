package chat;

import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhugongyi on 2017/7/12.
 */
public class Server {

    private static Map<String, Set<Address>> clientMap = new ConcurrentHashMap<>();

    private static final int server_port = 9000;

    public static void main(String[] args) throws IOException , ClassNotFoundException{
        ServerSocket serverSocket = new ServerSocket(server_port);
        for (; ; ) {
            Socket socket = serverSocket.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            new Thread(() -> {
                try {
                    MyMessage message;
                    Object object = null;
                    try {
                        object = inputStream.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (processFirstShake(object)) {
                        System.out.println("server连接数：" + clientMap.size());
                        inputStream.close();
                        socket.close();
                    }else{
                        message = (MyMessage) object;
                        System.out.println("server get message : " + message.toString());
                        Set<Address> addresses = clientMap.get(message.getTarget());
                        if (!CollectionUtils.isEmpty(addresses)) {
                            for (Address address : addresses) {
                                String targetHost = address.getHost();
                                int targetPort = address.getPort();
                                Socket socket1 = new Socket(targetHost, targetPort);
                                ObjectOutputStream out = new ObjectOutputStream(socket1.getOutputStream());
                                out.writeObject(message);
                            }
                        }
                        inputStream.close();
                        socket.close();
                    }
                } catch (IOException e) {
                    try {
                        inputStream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }).start();

        }

    }

    //首次握手，注册用户信息（account，host，port）
    private static boolean processFirstShake(Object object) {
        if (object instanceof ShakeHand) {
            ShakeHand shakeHand = (ShakeHand) object;
            System.out.println("server 获取连接..." + shakeHand.toString());
            if (clientMap.get(shakeHand.getAccount()) == null) {
                Set<Address> set = new HashSet<>();
                set.add(new Address(shakeHand.getHost(), shakeHand.getPort()));
                clientMap.put(shakeHand.getAccount(), set);
            } else {
                for (Address address : clientMap.get(shakeHand.getAccount())) {
                    if (!shakeHand.getHost().equals(address.getHost()) || shakeHand.getPort() != address.getPort()) {
                        clientMap.get(shakeHand.getAccount()).add(new Address(shakeHand.getHost(), shakeHand.getPort()));
                    }
                }
            }
            return true;
        }
        return false;
    }

    static class Address implements Serializable {
        private String host;
        private int port;

        public Address() {

        }

        public Address(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}
