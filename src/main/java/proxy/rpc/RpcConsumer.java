package proxy.rpc;

import proxy.UserService;

/**
 * Created by zhugongyi on 2017/7/11.
 */
public class RpcConsumer {

    public static void main(String[] args) throws Exception {
        UserService userService = RpcFramework.refer(UserService.class, "127.0.0.1", 9000);
        System.out.println(userService.query("Cloud"));
    }

}
