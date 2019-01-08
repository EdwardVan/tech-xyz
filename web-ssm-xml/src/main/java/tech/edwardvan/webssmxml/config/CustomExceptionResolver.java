package tech.edwardvan.webssmxml.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import tech.edwardvan.webssmxml.common.ResponseCode;
import tech.edwardvan.webssmxml.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 *
 * @author EdwardVan
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        logger.error(e.getMessage());
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(new ServerResponse(ResponseCode.ERROR.getCode(), e.getMessage()));
            //设置响应头
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return modelAndView;
    }
}
