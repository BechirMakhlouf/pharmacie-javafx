package com.cinema.pharmacie.dao;

import com.cinema.pharmacie.model.PatientMed;
import com.cinema.pharmacie.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientMedDAO implements DAO<PatientMed> {
    private final PatientDAO patientDAO = new PatientDAO();
    private final MedicamentDAO medicamentDAO = new MedicamentDAO();

    @Override
    public PatientMed get(String id) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM PatientMeds WHERE CodeMed = ? AND CodePatient = ?");
        String[] ids = id.split(",");
        statement.setString(1, ids[0]);
        statement.setString(2, ids[1]);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new PatientMed(
                    resultSet.getInt("Qte"),
                    resultSet.getDate("Date"),
                    medicamentDAO.get(resultSet.getString("CodeMed")),
                    patientDAO.get(resultSet.getString("CodePatient"))
            );
        }

        return null;
    }
    @Override
public List<PatientMed> getAll() throws SQLException {
    Connection connection = Database.getInstance().getConnection();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM PatientMeds");

    List<PatientMed> patientMeds = new ArrayList<>();
    while (resultSet.next()) {
        patientMeds.add(new PatientMed(
                resultSet.getInt("Qte"),
                resultSet.getDate("Date"),
                medicamentDAO.get(resultSet.getString("CodeMed")),
                patientDAO.get(resultSet.getString("CodePatient"))
        ));
    }

    return patientMeds;
}

@Override
public int insert(PatientMed patientMed) throws SQLException {
    Connection connection = Database.getInstance().getConnection();
    PreparedStatement statement = connection.prepareStatement("INSERT INTO PatientMeds (Qte, Date, CodeMed, CodePatient) VALUES (?, ?, ?, ?)");
    statement.setInt(1, patientMed.getQte());
    statement.setDate(2, new java.sql.Date(patientMed.getDate().getTime()));
    statement.setString(3, patientMed.getMed().getCodeMed());
    statement.setString(4, patientMed.getPatient().getCode());

    return statement.executeUpdate();
}

@Override
public int update(PatientMed patientMed) throws SQLException {
    Connection connection = Database.getInstance().getConnection();
    PreparedStatement statement = connection.prepareStatement("UPDATE PatientMeds SET Qte = ?, Date = ? WHERE CodeMed = ? AND CodePatient = ?");
    statement.setInt(1, patientMed.getQte());
    statement.setDate(2, new java.sql.Date(patientMed.getDate().getTime()));
    statement.setString(3, patientMed.getMed().getCodeMed());
    statement.setString(4, patientMed.getPatient().getCode());

    return statement.executeUpdate();
}

@Override
public int delete(PatientMed patientMed) throws SQLException {
    Connection connection = Database.getInstance().getConnection();
    PreparedStatement statement = connection.prepareStatement("DELETE FROM PatientMeds WHERE CodeMed = ? AND CodePatient = ?");
    statement.setString(1, patientMed.getMed().getCodeMed());
    statement.setString(2, patientMed.getPatient().getCode());

    return statement.executeUpdate();
}
}