package br.com.jdbcmysql.DAO;

import br.com.jdbcmysql.daoimpl.UserDAOImpl;
import br.com.jdbcmysql.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface PersonDAO {

    void createTblperson();
    List<Person> listPerson();
    void registerPerson(Person p, String password);
    void dropAllTables();
}
