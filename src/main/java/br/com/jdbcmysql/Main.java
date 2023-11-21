package br.com.jdbcmysql;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.jdbcmysql.DAO.ConnectionFactory;
import br.com.jdbcmysql.DAO.PersonDAO;
import br.com.jdbcmysql.DAO.UserDAO;
import br.com.jdbcmysql.model.Person;
import br.com.jdbcmysql.model.User;

public class Main {
    public static void main(String[] args) {

        PersonDAO personDao = new PersonDAO();
        UserDAO userDao = new UserDAO();

        //many records
        //personDao.dropAllTables();

        personDao.createTblperson();
        userDao.createTbluser();

        Person p = new Person();
        User u = new User();

        /*p.setFullName("Diego Rodrigues");
        p.setEmail("diegor@gmail.com");
        p.setTelephone("1199999999");
        personDao.registerPerson(p,"123");*/

        for(Person person : PersonDAO.listPerson()) {
            System.out.println(person.toString());
        }

        for(User user : userDao.listUsers()) {
            System.out.println(user.toString());
        }

        //test login acess
        u.setName("Diego336566974");
        u.setPassword("123");

        System.out.println(userDao.loginAcess(u));

    }
}