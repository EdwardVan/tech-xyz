package tech.edwardvan.rbacspringsecuritycore.validate.code.sms;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import tech.edwardvan.rbacspringsecuritycore.properties.SpringSecurityProperties;
import tech.edwardvan.rbacspringsecuritycore.validate.code.ValidateCode;
import tech.edwardvan.rbacspringsecuritycore.validate.code.ValidateCodeGenerator;


/**
 * 短信验证码生成器
 *
 * @author EdwardVan
 */
@Component
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SpringSecurityProperties springSecurityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest servletWebRequest) {
        String code = RandomStringUtils.randomNumeric(springSecurityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, springSecurityProperties.getCode().getSms().getExpireIn());
    }

}
