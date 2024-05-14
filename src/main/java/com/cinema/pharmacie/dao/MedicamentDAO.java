package com.cinema.pharmacie.dao;

import com.cinema.pharmacie.model.Medicament;
import com.cinema.pharmacie.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentDAO implements DAO<Medicament> {

    @Override
    public Medicament get(int id) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Medicaments WHERE codeMed = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Medicament(
                    resultSet.getString("codeMed"),
                    resultSet.getString("nomMed"),
                    resultSet.getDouble("prixMed"),
                    resultSet.getInt("Qte"),
                    resultSet.getString("TypeMed")
            );
        }

        return null;
    }

    @Override
    public List<Medicament> getAll() throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Medicaments");

        List<Medicament> medicaments = new ArrayList<>();
        while (resultSet.next()) {
            medicaments.add(new Medicament(
                    resultSet.getString("codeMed"),
                    resultSet.getString("nomMed"),
                    resultSet.getDouble("prixMed"),
                    resultSet.getInt("Qte"),
                    resultSet.getString("TypeMed")
            ));
        }

        return medicaments;
    }

    @Override
    public int save(Medicament medicament) throws SQLException {
        return insert(medicament);
    }

    @Override
    public int insert(Medicament medicament) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Medicaments (codeMed, nomMed, prixMed, Qte, TypeMed) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, medicament.getCodeMed());
        statement.setString(2, medicament.getNomMed());
        statement.setDouble(3, medicament.getPrixMed());
        statement.setInt(4, medicament.getQte());
        statement.setString(5, medicament.getTypeMed());

        return statement.executeUpdate();
    }

    @Override
    public int update(Medicament medicament) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Medicaments SET nomMed = ?, prixMed = ?, Qte = ?, TypeMed = ? WHERE codeMed = ?");
        statement.setString(1, medicament.getNomMed());
        statement.setDouble(2, medicament.getPrixMed());
        statement.setInt(3, medicament.getQte());
        statement.setString(4, medicament.getTypeMed());
        statement.setString(5, medicament.getCodeMed());

        return statement.executeUpdate();
    }

    @Override
    public int delete(Medicament medicament) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Medicaments WHERE codeMed = ?");
        statement.setString(1, medicament.getCodeMed());

        return statement.executeUpdate();
    }
}