package rpc;

import java.io.IOException;

/**
 * Created by zhugongyi on 2017/7/15.
 */
public class Bootstrap {

    public static void main(String[] args) throws IOException {
        RpcServer server = new RpcServer(9400);
        server.register(UserService.class, UserServiceImpl.class);
        server.start();
    }

}
