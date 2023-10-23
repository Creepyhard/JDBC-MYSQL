package br.com.jdbcmysql;

import br.com.jdbcmysql.DAO.ConnectionFactory;
import br.com.jdbcmysql.DAO.UserDAO;
import br.com.jdbcmysql.model.User;

import java.sql.Connection;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {

       UserDAO dao = new UserDAO();

        for(User users : dao.listUsers()) {
            System.out.println(users.toString());
        }

       /* Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        User u = new User();
        u.setInclusion(timestamp);
        u.setName("Sofia Rosa");
        u.setPassword("321");
        u.setUserActive(1);

        new UserDAO().registerUser(u);

        UserDAO dao2 = new UserDAO();

        for(User users : dao2.listUsers()) {
            System.out.println(users.toString());
            System.out.println(users + "sem");
        }*/
    }
}