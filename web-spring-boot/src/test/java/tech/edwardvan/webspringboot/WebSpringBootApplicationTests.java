package tech.edwardvan.webspringboot;

import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
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
import tech.edwardvan.webspringboot.model.User;

import java.io.IOException;
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

    @Autowired
    JestClient jestClient;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRabbitMQ() throws IOException {
        User user = new User();
        user.setUsername("Edward");
        user.setPassword("123456");
        user.setEmail("test@qq.com");
        rabbitTemplate.convertAndSend("testExchange","testKey",user);
    }

    @Test
    public void testElasticsearch() throws IOException {
        User user = new User();
        user.setUsername("Edward");
        user.setPassword("123456");
        user.setEmail("test@qq.com");

        Index indexBuild = new Index.Builder(user).index("test").type("user").id("1").build();
        DocumentResult indexBuildResult = jestClient.execute(indexBuild);
        System.out.println("jestClient:"+indexBuildResult.getId());

        String queryJson = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"username\" : \"Edward\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        Search searchBuild = new Search.Builder(queryJson).addIndex("test").addType("user").build();
        SearchResult searchBuildResult = jestClient.execute(searchBuild);
        System.out.println("jestClient:"+searchBuildResult.getJsonString());
    }

}
