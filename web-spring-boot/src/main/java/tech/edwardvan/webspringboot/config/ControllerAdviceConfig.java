package tech.edwardvan.webspringboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 切面通知配置
 *
 * @author EdwardVan
 */
@ControllerAdvice
@Slf4j
public class ControllerAdviceConfig {

    @ModelAttribute("acceptLanguage")
    public String acceptLanguage(@RequestHeader(value = "Accept-Language", required = false) String acceptLanguage) {
        return acceptLanguage;
    }

    @ModelAttribute("jsessionId")
    public String jsessionId(@CookieValue(value = "JSESSIONID", required = false) String jsessionId) {
        return jsessionId;
    }

    /**
     * 全局异常处理器
     */
    @ExceptionHandler
    @ResponseBody
    String handleException(Exception e) {
        log.error(e.getMessage());
        return "Exception:" + e.getMessage();
    }
}
