import org.junit.Test;

/**
 * Created by zhugongyi on 2017/5/9.
 */
public class RedisTest extends BaseTest{

    @Test
    public void test01(){
        redisClient.set("ttt", "111");
    }

}
