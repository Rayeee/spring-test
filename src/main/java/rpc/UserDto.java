package rpc;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhugongyi on 2017/7/15.
 */
public class UserDto implements Serializable {

    private Integer id;

    private String name;

    private Integer gender;

    private Date birthday;

    public UserDto() {

    }

    public UserDto(Integer id, String name, Integer gender, Date birthday) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", birthday=").append(birthday);
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
