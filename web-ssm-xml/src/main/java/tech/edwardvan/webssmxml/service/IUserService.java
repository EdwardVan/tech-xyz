package tech.edwardvan.webssmxml.service;


import tech.edwardvan.webssmxml.model.User;

public interface IUserService {

    public void testTransactional();

    User getUserById(Integer id);
}
