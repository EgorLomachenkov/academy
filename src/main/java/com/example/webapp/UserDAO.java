package com.example.webapp;

import model.User;

public interface UserDAO {

    User findUserByEmail(String email);
    boolean createUser(User user);
}
