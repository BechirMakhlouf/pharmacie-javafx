package com.cinema.pharmacie.dao;

import com.cinema.pharmacie.model.Profil;
import com.cinema.pharmacie.model.User;
import com.cinema.pharmacie.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfilDAO implements DAO<Profil> {

    private final UserDAO userDAO;

    public ProfilDAO() {
        this.userDAO = new UserDAO();
    }

    public Profil get(String id) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Profils WHERE user_code = ?");
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            User user = userDAO.get(resultSet.getString("user_code"));
            return new Profil(user);
        }

        return null;
    }

    @Override
    public List<Profil> getAll() throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Profils");

        List<Profil> profils = new ArrayList<>();
        while (resultSet.next()) {
            User user = userDAO.get(resultSet.getString("user_code"));
            profils.add(new Profil(user));
        }

        return profils;
    }

    @Override
    public int insert(Profil profil) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Profils (user_code) VALUES (?)");
        statement.setString(1, profil.getUser().getCode());

        return statement.executeUpdate();
    }

    @Override
    public int update(Profil profil) throws SQLException {
        throw new UnsupportedOperationException("ilya pas des proprietes en Profil");
    }

    @Override
    public int delete(Profil profil) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Profils WHERE user_code = ?");
        statement.setString(1, profil.getUser().getCode());

        return statement.executeUpdate();
    }
}