package tech.edwardvan.rbacspringsecuritycore.validate.code.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import tech.edwardvan.rbacspringsecuritycore.properties.SecurityConstants;
import tech.edwardvan.rbacspringsecuritycore.validate.code.AbstractValidateCodeProcessor;
import tech.edwardvan.rbacspringsecuritycore.validate.code.ValidateCode;

/**
 * 短信验证码处理器
 *
 * @author EdwardVan
 */
@Component
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest servletWebRequest, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(servletWebRequest.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}
