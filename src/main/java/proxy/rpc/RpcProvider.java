package proxy.rpc;

import proxy.UserService;
import proxy.UserServiceImpl;

/**
 * Created by zhugongyi on 2017/7/11.
 */
public class RpcProvider {

    public static void main(String[] args) throws Exception {
        UserService userService = new UserServiceImpl();
        RpcFramework.export(userService, 9000);
    }

}
