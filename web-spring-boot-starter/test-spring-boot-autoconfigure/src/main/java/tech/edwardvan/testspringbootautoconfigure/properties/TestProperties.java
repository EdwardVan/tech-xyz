package tech.edwardvan.testspringbootautoconfigure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Properties对应的配置类
 * <p>
 * 如果是在某个业务逻辑中需要获取一下配置文件中的某项值,则使用@Value
 * 如果是专门编写了一个javaBean来和配置文件进行映射,则直接使用@ConfigurationProperties
 *
 * @author EdwardVan
 */
@ConfigurationProperties(prefix = "test.properties")//默认从全局配置文件中获取值
@Data
public class TestProperties {
    private String name;
    private Integer age;
    private Boolean boss;
    private Date birth;
    private Map<String, Object> maps;
    private List<Object> lists;
}
