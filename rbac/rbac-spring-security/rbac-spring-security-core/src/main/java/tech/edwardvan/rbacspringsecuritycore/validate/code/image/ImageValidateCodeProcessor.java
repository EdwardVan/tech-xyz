package tech.edwardvan.rbacspringsecuritycore.validate.code.image;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import tech.edwardvan.rbacspringsecuritycore.validate.code.AbstractValidateCodeProcessor;

/**
 * 图片验证码处理器
 *
 * @author EdwardVan
 */
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageValidateCode> {

    /**
     * 发送图形验证码，将其写到响应中
     */
    @Override
    protected void send(ServletWebRequest servletWebRequest, ImageValidateCode validateCode) throws Exception {
        ImageIO.write(validateCode.getImage(), "JPEG", servletWebRequest.getResponse().getOutputStream());
    }
}
