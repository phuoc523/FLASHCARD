package com.flashcard.service;

import com.flashcard.dao.UserDAO;
import com.flashcard.model.User;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();

    public boolean login(String username, String password) {
        // TODO: add authentication logic
        return false;
    }

    public void register(User user) {
        // TODO: add registration logic
        userDAO.save(user);
    }
}
