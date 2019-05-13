package tech.edwardvan.basedesignpattern.principle.singleresponsibility;

/**
 * 单一职责原则
 * <p>
 * 方法职责不单一
 *
 * @author EdwardVan
 */
public class SingleResponsibilityExample5 {
    /**
     * 更新用户姓名和地址
     */
    private void updateUserInfo(String userName, String address) {
    }

    /**
     * 更新用户信息
     */
    private void updateUserInfo(String userName, String... properties) {
    }
}
