package com.cinema.pharmacie.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    T get(String id) throws SQLException;

    List<T> getAll() throws SQLException;

    int insert(T t) throws SQLException;

    int update(T t) throws SQLException;

    int delete(T t) throws SQLException;
}