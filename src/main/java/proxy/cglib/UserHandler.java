package proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhugongyi on 2017/7/10.
 */
public class UserHandler implements MethodInterceptor{

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("-----" + methodProxy.getSuperName());
        System.out.println(method.getName());
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("invoke-----" + invoke);
        return invoke;
    }
}
