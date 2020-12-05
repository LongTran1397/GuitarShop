package org.group02.guitarshop.service;

import org.group02.guitarshop.entity.User;

public interface UserService {
    User findUserByEmail(String email);

    void saveUser(User user);
}