package tech.edwardvan.webspringboot.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * 自定义Properties方法参数解析器
 * <p>
 * 原理:
 * {@link InvocableHandlerMethod#getMethodArgumentValues(NativeWebRequest, ModelAndViewContainer, Object...)}
 *
 * @author EdwardVan
 */
public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Properties.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String contentType = request.getHeader("Content-Type");
        MediaType mediaType = MediaType.parseMediaType(contentType);

        // 获取字符编码
        Charset charset = mediaType.getCharset();
        // 当 charset 不存在时,使用 UTF-8
        charset = charset == null ? Charset.forName("UTF-8") : charset;

        //请求输入字节流
        ServletInputStream inputStream = request.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        Properties properties = new Properties();
        properties.load(inputStreamReader);
        return properties;
    }
}
