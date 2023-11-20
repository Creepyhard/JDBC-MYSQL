package br.com.jdbcmysql.DAO;

import br.com.jdbcmysql.model.Person;
import br.com.jdbcmysql.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    public void createTblperson() {
        try {
            String sql = //"DROP TABLE IF EXISTS tblperson" +
                         "CREATE TABLE tblperson ("          +
                         "id INT AUTO_INCREMENT NOT NULL,"   +
                         "inclusion TIMESTAMP NOT NULL,"     +
                         "alteration TIMESTAMP,"             +
                         "fullName VARCHAR(100) NOT NULL,"   +
                         "email VARCHAR(100) NOT NULLUNIQUE,"+
                         "telephone VARCHAR(20) NOT NULL,"   +
                         "position INT null,"                +
                         "PRIMARY KEY (id))";

            String sql2 = "SELECT 1 FROM tblperson limit 1";

            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement ps2 = con.prepareStatement(sql2);

            try {
                ps2.executeQuery();
                System.out.println("aqui");
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
            PreparedStatement psPerson = con.prepareStatement(sqlInsertPerson);

            psPerson.setTimestamp(1, p.getInclusion());
            psPerson.setString(2, p.getFullName());
            psPerson.setString(3, p.getEmail());
            psPerson.setString(4, p.getTelephone());

            String as2 = p.getFullName();
            System.out.println(as2);
            psPerson.execute();
            psPerson.close();

            UserDAO userDao = new UserDAO();
            userDao.registerUserWithPerson(password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
