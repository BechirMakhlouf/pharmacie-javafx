package com.cinema.pharmacie.controller;

import com.cinema.pharmacie.dao.UserDAO;
import com.cinema.pharmacie.exception.BadEmailException;
import com.cinema.pharmacie.exception.IncorrectPasswordException;
import com.cinema.pharmacie.model.User;
import com.cinema.pharmacie.util.Security;

import java.sql.SQLException;

public class UserController {
    private final UserDAO userDao = new UserDAO();

    public User registerUser(String email, String password, String name, String phoneNumber) {
        String code = Security.generateRandomId();
        User newUser = new User(code, email, password, name, phoneNumber);

        try {
            userDao.insert(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newUser;
    }

    public User loginUser(String email, String password) throws  BadEmailException, IncorrectPasswordException{
        try {
            User user = userDao.get(email);
            if (Security.comparePasswords(password, user.getMp())) {
                return user;
            } else {
                throw new IncorrectPasswordException();
            }
        } catch (SQLException e) {
            throw new BadEmailException();
        }
    }
    // update user info
    public void updateUser(String code, String name, String mp) {
        try {
            User user = userDao.get(code);
            user.setNom(name);
            user.setNumTel(mp);
            userDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // delete user
    public void deleteUser(String code) {
        try {
            User user = userDao.get(code);
            userDao.delete(user);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}