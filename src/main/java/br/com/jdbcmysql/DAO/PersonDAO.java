package br.com.jdbcmysql.DAO;

import br.com.jdbcmysql.model.Person;
import br.com.jdbcmysql.model.User;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    public void createTblperson() {
        try {
            String sql = "CREATE TABLE tblperson ("              +
                         "id INT AUTO_INCREMENT NOT NULL UNIQUE,"+
                         "inclusion TIMESTAMP NOT NULL,"         +
                         "alteration TIMESTAMP,"                 +
                         "fullName VARCHAR(100) NOT NULL,"       +
                         "email VARCHAR(100) NOT NULL UNIQUE,"   +
                         "telephone VARCHAR(20) NOT NULL,"       +
                         "position INT null,"                    +
                         "PRIMARY KEY (id));";

            String sql2 = "SELECT 1 FROM tblperson limit 1";

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

            System.out.println("tblperson created");
        } catch (Exception e) {
            System.out.println("error create table tblperson");
            throw new RuntimeException(e);
        }
    }

    public static List<Person> listPerson() {
        List<Person> person = new ArrayList<>();

        try{
            String sql = "SELECT * FROM tblperson";

            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setInclusion(rs.getTimestamp("inclusion"));
                p.setFullName(rs.getString("fullName"));
                p.setEmail(rs.getString("email"));
                p.setTelephone(rs.getString("telephone"));
                /* p.getCargo(rs.getInt("cargo")); */

                person.add(p);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("erro");
            throw new RuntimeException(e);
        }
        return person;
    }

    public void registerPerson(Person p, String password) {

        String sqlInsertPerson  = "INSERT INTO tblperson (INCLUSION, FULLNAME, EMAIL, TELEPHONE) VALUES (?,?,?,?)";

        try {
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement psPerson = con.prepareStatement(sqlInsertPerson, Statement.RETURN_GENERATED_KEYS);

            psPerson.setTimestamp(1, p.getInclusion());
            psPerson.setString(2, p.getFullName());
            psPerson.setString(3, p.getEmail());
            psPerson.setString(4, p.getTelephone());

            psPerson.executeUpdate();
            //psPerson.execute();

            ResultSet rs = psPerson.getGeneratedKeys();

            int idPerson = 0;
            if (rs.next()) {
                idPerson = rs.getInt(1);
            }

            //we send the password because  on the registration screen the user enters their personal data and password together.
            psPerson.close();
            UserDAO userDao = new UserDAO();
            userDao.registerUserWithPerson(idPerson, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropAllTables() {
        String sql = "DROP TABLE IF EXISTS tbluser;";
        String sql2 = "DROP TABLE IF EXISTS tblperson;";

        try {
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement ps2 = con.prepareStatement(sql2);

            ps.execute();
            ps2.execute();
            ps.close();
            System.out.println("deleted all tables");
        } catch (Exception e) {
            System.out.println("Error delete tables");
        }
    }
}
