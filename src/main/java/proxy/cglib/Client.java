package proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import proxy.UserService;
import proxy.UserServiceImpl;

/**
 * Created by zhugongyi on 2017/7/10.
 */
public class Client {

    public static void main(String[] args) {
        UserHandler handler = new UserHandler();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(handler);
        UserService userService = (UserService) enhancer.create();
        userService.query("Cloud");
    }

}
