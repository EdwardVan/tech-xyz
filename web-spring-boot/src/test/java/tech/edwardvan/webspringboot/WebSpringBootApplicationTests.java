package tech.edwardvan.webspringboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import tech.edwardvan.webspringboot.config.TestConfiguration;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebSpringBootApplicationTests {

    @Autowired
    ApplicationContext ioc;

    @Autowired
    TestConfiguration testConfiguration;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {
    }

}
