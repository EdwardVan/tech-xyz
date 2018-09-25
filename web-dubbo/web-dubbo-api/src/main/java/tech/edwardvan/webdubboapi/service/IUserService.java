package tech.edwardvan.webdubboapi.service;


import tech.edwardvan.webdubboapi.model.User;

public interface IUserService {
    User getUserById(Integer id);
}
