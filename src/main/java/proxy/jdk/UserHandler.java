package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhugongyi on 2017/7/10.
 */
public class UserHandler implements InvocationHandler {

    private Object subject;

    public UserHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before...");
        Object invoke = method.invoke(subject, args);
        System.out.println("after...");
        return invoke;

    }
}
