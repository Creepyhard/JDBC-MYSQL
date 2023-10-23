package br.com.jdbcmysql.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private String url = "jdbc:mysql://localhost:3306/jdbc_mysql";
    private String user = "root";
    private String password = "teste980";

    private Connection con;

    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch ( SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
