import java.lang.reflect.ParameterizedType;

/**
 * Created by zhugongyi on 2017/8/8.
 */
public class FastFailTest1 extends FastFailTest0<Integer> {

    public void print() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        System.out.println();
    }

    public static void main(String[] args) {
        FastFailTest1 test1 = new FastFailTest1();
        test1.print();
    }

}
