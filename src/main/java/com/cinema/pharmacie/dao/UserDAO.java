package com.cinema.pharmacie.dao;

import com.cinema.pharmacie.model.User;
import com.cinema.pharmacie.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {

    @Override
    public User get(String id) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE code = ?");
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new User(
                    resultSet.getString("code"),
                    resultSet.getString("email"),
                    resultSet.getString("mp"),
                    resultSet.getString("nom"),
                    resultSet.getString("numTel")
            );
        }

        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");

        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(new User(
                    resultSet.getString("code"),
                    resultSet.getString("email"),
                    resultSet.getString("mp"),
                    resultSet.getString("nom"),
                    resultSet.getString("numTel")
            ));
        }

        return users;
    }
    @Override
    public int insert(User user) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (code, email, mp, nom, numTel) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, user.getCode());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getMp());
        statement.setString(4, user.getNom());
        statement.setString(5, user.getNumTel());

        return statement.executeUpdate();
    }

    @Override
    public int update(User user) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Users SET email = ?, mp = ?, nom = ?, numTel = ? WHERE code = ?");
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getMp());
        statement.setString(3, user.getNom());
        statement.setString(4, user.getNumTel());
        statement.setString(5, user.getCode());

        return statement.executeUpdate();
    }
    @Override
    public int delete(User user) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE code = ?");
        statement.setString(1, user.getCode());

        return statement.executeUpdate();
    }
}