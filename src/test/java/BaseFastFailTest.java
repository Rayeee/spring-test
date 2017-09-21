import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.redis.RedisClient;

import javax.annotation.Resource;

/**
 * Created by zhugongyi on 2017/5/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BaseFastFailTest {

    @Resource
    protected RedisClient redisClient;
}
