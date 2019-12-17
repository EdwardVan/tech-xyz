
package tech.edwardvan.rbacspringsecuritycore.validate.code;

import tech.edwardvan.rbacspringsecuritycore.properties.SecurityConstants;

/**
 * 校验码类型
 *
 * @author EdwardVan
 */

public enum ValidateCodeType {

    /**
     * 图片验证码
     */
    IMAGE(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE),
    /**
     * 短信验证码
     */
    SMS(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS);


    /**
     * 请求参数名称
     */
    private String paramName;

    ValidateCodeType(String paramName) {
        this.paramName = paramName;
    }

    /**
     * 校验时从请求中获取的参数的名字
     */
    public String getParamNameOnValidate() {
        return paramName;
    }

}
