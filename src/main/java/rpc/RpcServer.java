package rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhugongyi on 2017/7/15.
 */
public class RpcServer {

    private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    protected static Map<String, Class> registerService = new ConcurrentHashMap<>();

    private int port;

    public RpcServer(int port) {
        this.port = port;
    }

    public RpcServer() {

    }

    public void register(Class interface0, Class impl){
        registerService.put(interface0.getName(), impl);
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(this.port);
        try {
            while (true) {
                executor.execute(new ServerTask(serverSocket.accept()));
            }
        } finally {
            serverSocket.close();
        }
    }

    public void stop() throws InterruptedException {
        executor.shutdown();
        while (!executor.awaitTermination(10, TimeUnit.SECONDS)) ;
    }
}
