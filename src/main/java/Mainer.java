import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhugongyi on 2017/4/27.
 */
public class Mainer {

    private static final Logger logger = Logger.getLogger(Mainer.class);

    public static void main(String[] args) {
        logger.info("========== start =========");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        logger.info("========== end =========");
    }

}
