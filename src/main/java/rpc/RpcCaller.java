package rpc;

/**
 * Created by zhugongyi on 2017/7/15.
 */
public class RpcCaller {

    public static void main(String[] args) {
        UserService userService = RpcClient.getProxy(UserService.class, "127.0.0.1", 9400);
        UserDto dto = userService.queryUser("Cloud");
        System.out.println(dto.toString());
    }

}
