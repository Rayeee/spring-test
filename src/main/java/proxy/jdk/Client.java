package proxy.jdk;

import proxy.UserService;
import proxy.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * Created by zhugongyi on 2017/7/10.
 */
public class Client {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserHandler userHandler = new UserHandler(userService);
        UserService proxyInstance = (UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), userHandler);
        System.out.println(proxyInstance.getClass().getName());
        proxyInstance.query("Cloud");
    }

}
