package tech.edwardvan.rbacspringsecuritybrowser.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import tech.edwardvan.rbacspringsecuritybrowser.pojo.SimpleResponse;
import tech.edwardvan.rbacspringsecuritycore.properties.LoginSuccessReturnType;
import tech.edwardvan.rbacspringsecuritycore.properties.SpringSecurityProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理器
 * 继承自SpringSecurity默认处理器
 *
 * @author EdwardVan
 */
@Component
@Slf4j
public class BrowserAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SpringSecurityProperties springSecurityProperties;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("登录失败");

        if (LoginSuccessReturnType.JSON.equals(springSecurityProperties.getBrowser().getLoginSuccessReturnType())) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
