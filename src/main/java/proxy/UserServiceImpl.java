package proxy;

/**
 * Created by zhugongyi on 2017/7/10.
 */
public class UserServiceImpl implements UserService{

    @Override
    public TUser query(String name) {
        TUser user = new TUser(name, 1, 20);
        System.out.println(user);
        return user;
    }
}
