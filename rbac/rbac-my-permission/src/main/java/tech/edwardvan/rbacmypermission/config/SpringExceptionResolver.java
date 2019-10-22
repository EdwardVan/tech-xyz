package tech.edwardvan.rbacmypermission.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import tech.edwardvan.rbacmypermission.common.ServerResponse;
import tech.edwardvan.rbacmypermission.exception.ParamException;
import tech.edwardvan.rbacmypermission.exception.PermissionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 *
 * @author EdwardVan
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) {
        String url = request.getRequestURL().toString();
        ModelAndView mv;
        String defaultMsg = "System error";

        // 这里我们要求项目中所有请求json数据,都使用.json结尾
        if (url.endsWith(".json")) {
            if (ex instanceof PermissionException || ex instanceof ParamException) {
                ServerResponse error = ServerResponse.error(ex.getMessage());
                mv = new ModelAndView("jsonView", error.toMap());
            } else {
                log.error("unknown json exception, url:" + url, ex);
                ServerResponse error = ServerResponse.error(defaultMsg);
                mv = new ModelAndView("jsonView", error.toMap());
            }
        }
        // 这里我们要求项目中所有请求page页面,都使用.page结尾
        else if (url.endsWith(".page")) {
            log.error("unknown page exception, url:" + url, ex);
            ServerResponse error = ServerResponse.error(defaultMsg);
            mv = new ModelAndView("error", error.toMap());
        } else {
            log.error("unknow exception, url:" + url, ex);
            ServerResponse error = ServerResponse.error(defaultMsg);
            mv = new ModelAndView("jsonView", error.toMap());
        }
        return mv;
    }
}