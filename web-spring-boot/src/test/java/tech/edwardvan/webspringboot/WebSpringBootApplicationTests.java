package tech.edwardvan.webspringboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import tech.edwardvan.webspringboot.config.TestConfiguration;
import tech.edwardvan.webspringboot.config.TestProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebSpringBootApplicationTests {

    @Autowired
    ApplicationContext ioc;

    @Autowired
    TestConfiguration testConfiguration;

    @Test
    public void contextLoads() {
        System.out.println(testConfiguration);
    }

}
