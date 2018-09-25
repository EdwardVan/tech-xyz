package tech.edwardvan.webssmannotation.service;


import tech.edwardvan.webssmannotation.model.User;

public interface IUserService {

    public void testTransactional();

    User getUserById(Integer id);
}
