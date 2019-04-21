package tech.edwardvan.webspringboot.service;


import tech.edwardvan.webspringboot.model.User;

public interface IUserService {

    User getUserById(Integer id);
}
