package br.com.jdbcmysql.DAO;

import br.com.jdbcmysql.model.Person;
import br.com.jdbcmysql.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void createTbluser() {
        try {
            String sql = //"DROP TABLE IF EXISTS tbluser" +
                         "CREATE TABLE jdbc_mysql.tbluser ("     +
                         "id INT NOT NULL AUTO_INCREMENT UNIQUE,"+
                         "inclusion TIMESTAMP NOT NULL,"         +
                         "alteration TIMESTAMP NULL,"            +
                         "name VARCHAR(20) NOT NULL UNIQUE,"     +
                         "userActive INT NOT NULL,"              +
                         "password VARCHAR(50) NOT NULL,"        +
                         "idLevelAcess INT NOT NULL,"            +
                         "idPerson VARCHAR(45) NOT NULL,"        +
                         "PRIMARY KEY (id),"                     +
                         "CONSTRAINT idPerson\n"                 +
                         "FOREIGN KEY (id)\n"                    +
                         "REFERENCES jdbc_mysql.tblperson(id)\n" +
                         "ON DELETE NO ACTION\n"                 +
                         "ON UPDATE NO ACTION)";

            String sql2 = "SELECT 1 FROM tbluser limit 1";

            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement ps2 = con.prepareStatement(sql2);

            try {
                ps2.executeQuery();
            } catch (Exception ex) {
                ps.execute();
            }
            ps.close();
            ps2.close();
            System.out.println("tbluser created");
        } catch (Exception e) {
            System.out.println("error create table tbluser");
            throw new RuntimeException(e);
        }
    }

    public List<User> listUsers() {
        List<User> users = new ArrayList<User>();

        try {
            String sql = "SELECT * FROM tbluser";

            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setInclusion(rs.getTimestamp("inclusion"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setUserActive(rs.getInt("useractive"));
                //u.setPersonal();

                users.add(u);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("erro");
            throw new RuntimeException(e);
        }
        return users;
    }

    /*public void registerUserWithoutPerson(User u) {

        String sql = "INSERT INTO tbluser (INCLUSION, NAME, USER_ACTIVE, PASSWORD) VALUES (?,?,?,?)";

        try {
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setTimestamp(1, u.getInclusion());
            ps.setString(2, u.getName());
            ps.setInt(3, u.getUserActive());
            ps.setString(4, u.getPassword());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public void registerUserWithPerson(String password) {

        String sqlInsertUser = "INSERT INTO tbluser (INCLUSION, NAME, USERACTIVE, PASSWORD, IDPERSON, IDLEVELACESS)" +
                                "VALUES (?,?,?,?,?,?)";
        String sqlReturnPerson = "SELECT * FROM tblperson order by id desc limit 1";

        int pID = 0;
        String pFullName = "";
        User u = new User();

        try {
            Person p = new Person();
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement psUser = con.prepareStatement(sqlInsertUser);

            PreparedStatement psRetUser = con.prepareStatement(sqlReturnPerson);
            ResultSet rs = psRetUser.executeQuery();

            while(rs.next()) {
                pID = rs.getInt("id");
                pFullName = rs.getString("fullName");
            }
            System.out.println(pID);
            System.out.println(pFullName);
            System.out.println(u.getNameUser(pFullName));

            psRetUser.close();

            psUser.setTimestamp(1, u.getInclusion());
            psUser.setString(2,u.getNameUser(pFullName));
            psUser.setInt(3, 1);
            psUser.setString(4, password);
            psUser.setInt(5, pID);
            psUser.setInt(6, 1);

            psUser.execute();
            psUser.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
