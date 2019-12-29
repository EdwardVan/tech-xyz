package tech.edwardvan.rbacspringsecuritycore.validate.code.sms;


import lombok.extern.slf4j.Slf4j;

/**
 * 默认的短信验证码发送
 *
 * @author EdwardVan
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        log.info("向手机" + mobile + "发送短信验证码" + code);
    }

}
