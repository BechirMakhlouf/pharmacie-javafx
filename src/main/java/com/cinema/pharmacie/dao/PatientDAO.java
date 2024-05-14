package com.cinema.pharmacie.dao;

import com.cinema.pharmacie.model.Patient;
import com.cinema.pharmacie.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO implements DAO<Patient> {

    @Override
    public Patient get(String id) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Patients WHERE code = ?");
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Patient(
                    resultSet.getString("code"),
                    resultSet.getString("nom"),
                    resultSet.getString("numTel")
            );
        }

        return null;
    }

    @Override
    public List<Patient> getAll() throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Patients");

        List<Patient> patients = new ArrayList<>();
        while (resultSet.next()) {
            patients.add(new Patient(
                    resultSet.getString("code"),
                    resultSet.getString("nom"),
                    resultSet.getString("numTel")
            ));
        }

        return patients;
    }

    @Override
    public int insert(Patient patient) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Patients (code, nom, numTel) VALUES (?, ?, ?)");
        statement.setString(1, patient.getCode());
        statement.setString(2, patient.getNom());
        statement.setString(3, patient.getNumTel());

        return statement.executeUpdate();
    }

    @Override
    public int update(Patient patient) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Patients SET nom = ?, numTel = ? WHERE code = ?");
        statement.setString(1, patient.getNom());
        statement.setString(2, patient.getNumTel());
        statement.setString(3, patient.getCode());

        return statement.executeUpdate();
    }

    @Override
    public int delete(Patient patient) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Patients WHERE code = ?");
        statement.setString(1, patient.getCode());

        return statement.executeUpdate();
    }
}