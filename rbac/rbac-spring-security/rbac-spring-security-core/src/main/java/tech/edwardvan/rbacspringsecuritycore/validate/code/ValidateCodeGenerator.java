package tech.edwardvan.rbacspringsecuritycore.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器接口
 * <p>
 * BeanName命名规范:xxxValidateCodeGenerator
 * 举例:imageValidateCodeGenerator
 *
 * @author EdwardVan
 */
public interface ValidateCodeGenerator {

    /**
     * 生成校验码
     *
     * @param servletWebRequest 请求对象
     * @return 校验码封装类
     */
    ValidateCode generate(ServletWebRequest servletWebRequest);

}
