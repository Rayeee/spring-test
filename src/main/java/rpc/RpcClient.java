package rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Created by zhugongyi on 2017/7/15.
 */
public class RpcClient {

    public static <T> T getProxy(Class<?> serviceInterface, String host, int port) {
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class[]{serviceInterface}, (proxy, method, args) -> {
            Socket socket = null;
            ObjectOutputStream out = null;
            ObjectInputStream input = null;
            try {
                socket = new Socket(host, port);
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeUTF(serviceInterface.getName());
                out.writeUTF(method.getName());
                out.writeObject(method.getParameterTypes());
                out.writeObject(args);
                input = new ObjectInputStream(socket.getInputStream());
                return input.readObject();
            } catch (Exception e) {

            } finally {
                socket.close();
                input.close();
                out.close();
            }
            return null;
        });
    }

}
