package proxy;

import java.io.Serializable;

/**
 * Created by zhugongyi on 2017/7/10.
 */
public class TUser implements Serializable{

    private String name;

    private Integer gender;

    private Integer age;

    public TUser() {

    }

    public TUser(String name, Integer gender, Integer age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TUser{");
        sb.append("name='").append(name).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
