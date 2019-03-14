package tech.edwardvan.webssmannotation.config;

import org.springframework.beans.factory.FactoryBean;
import tech.edwardvan.webssmannotation.model.User;

/**
 * User工厂类
 *
 * @author EdwardVan
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setUsername("UserFactoryBean");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
