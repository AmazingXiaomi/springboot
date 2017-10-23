package springboot;

import com.xiaomi.spring.web.TestController;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaolai on 2017/10/23.
 */
public class AppTestDemo {
    @Test
    public void testSayHello() {
        assertEquals("Hello,World!",new TestController().test());
    }
}
