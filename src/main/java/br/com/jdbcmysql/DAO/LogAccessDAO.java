package br.com.jdbcmysql.DAO;

import br.com.jdbcmysql.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public interface LogAccessDAO {

    long thereWereChanges(User u);
}
