package rpc;

import java.util.Date;

/**
 * Created by zhugongyi on 2017/7/15.
 */
public class UserServiceImpl implements UserService {

    @Override
    public UserDto queryUser(String name) {
        UserDto dto = new UserDto();
        dto.setId(1);
        dto.setBirthday(new Date());
        dto.setGender(1);
        dto.setName("Cloud");
        return dto;
    }
}
