package tech.edwardvan.webspringboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import tech.edwardvan.webspringboot.converter.PropertiesHttpMessageConverter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.Properties;

/**
 * 测试Controller
 *
 * @author EdwardVan
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    
    /**
     * 自定义MessageConverter测试
     * <p>
     * RequestBody注解决定了{@link RequestResponseBodyMethodProcessor}处理参数和返回值
     * 请求头中Content-Type为 text/properties 决定了{@link PropertiesHttpMessageConverter}处理转换
     * <p>
     * consumes:指定请求头Content-Type的MediaType
     * produces:指定响应头Content-Type的MediaType
     * 原理:{@link AbstractMessageConverterMethodProcessor#getProducibleMediaTypes(HttpServletRequest, Class, Type)}
     */
    @PostMapping(value = "/testMessageConverter", consumes = "text/properties", produces = "text/properties")
    public Object testMessageConverter(@RequestBody Properties properties) {
        return properties;
    }

}
