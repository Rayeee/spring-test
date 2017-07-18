package rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by zhugongyi on 2017/7/15.
 */
public class ServerTask extends RpcServer implements Runnable {

    private Socket socket = null;

    public ServerTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream input = null;
        ObjectOutputStream out = null;
        try {
            input = new ObjectInputStream(socket.getInputStream());
            String serviceName = input.readUTF();
            String methodName = input.readUTF();
            Class<?>[] parameterType = (Class<?>[]) input.readObject();
            Object[] arguments = (Object[]) input.readObject();
            Class serviceClass = registerService.get(serviceName);
            if (serviceClass == null) {
                throw new ClassNotFoundException("Class " + serviceName + "not found!");
            }
            Method method = serviceClass.getMethod(methodName, parameterType);
            Object result = method.invoke(serviceClass.newInstance(), arguments);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
