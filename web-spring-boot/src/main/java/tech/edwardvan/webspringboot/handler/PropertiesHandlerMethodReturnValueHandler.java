package tech.edwardvan.webspringboot.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import tech.edwardvan.webspringboot.converter.PropertiesHttpMessageConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * 自定义Properties方法返回值解析器
 *
 * @author EdwardVan
 */
public class PropertiesHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Properties.class.equals(returnType.getMethod().getReturnType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        // 强制装换
        Properties properties = (Properties) returnValue;
        // 复用 PropertiesHttpMessageConverter
        PropertiesHttpMessageConverter converter = new PropertiesHttpMessageConverter();

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String contentType = request.getHeader("Content-Type");
        // 获取请求头 Content-Type 中的媒体类型
        MediaType mediaType = MediaType.parseMediaType(contentType);

        // 获取 Servlet Response 对象
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        HttpOutputMessage message = new ServletServerHttpResponse(response);
        // 通过 PropertiesHttpMessageConverter 输出
        converter.write(properties, mediaType, message);
        // 告知 Spring Web MVC 当前请求已经处理完毕
        mavContainer.setRequestHandled(true);
    }
}
