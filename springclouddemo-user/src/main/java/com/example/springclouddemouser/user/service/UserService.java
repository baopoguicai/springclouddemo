package com.example.springclouddemouser.user.service;

import com.example.springclouddemouser.user.entity.User;
import com.example.springclouddemouser.user.entity.UserElement;

public interface UserService {
    User findUserById(Long id);

    void registerUser(User user) throws Exception;

    UserElement login(User user);

}
