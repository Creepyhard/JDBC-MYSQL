package br.com.jdbcmysql;

import br.com.jdbcmysql.daoimpl.PersonDAOImpl;
import br.com.jdbcmysql.daoimpl.UserDAOImpl;
import br.com.jdbcmysql.model.Person;
import br.com.jdbcmysql.model.User;

public class Main {
    public static void main(String[] args) {

        PersonDAOImpl personDao = new PersonDAOImpl();
        UserDAOImpl userDao = new UserDAOImpl();

        //many records
        //personDao.dropAllTables();

        personDao.createTblperson();
        userDao.createTbluser();

        Person p = new Person();
        User u = new User();

       /* p.setFullName("Diego Rodrigues");
        p.setEmail("diegor@gmail.com");
        p.setTelephone("1199999999");
        personDao.registerPerson(p,"123");*/

        p.setFullName("Cm Yoshi");
        p.setEmail("CmYoshi@gmail.com");
        p.setTelephone("1155555555");
        personDao.registerPerson(p,"cmyoshi");

      /*  p.setFullName("Cne Claris");
        p.setEmail("CneClaris@gmail.com");
        p.setTelephone("1166666666");
        personDao.registerPerson(p,"cneClaris");*/

        /*  p.setFullName("Cny lyv");
        p.setEmail("Cnylyv@gmail.com");
        p.setTelephone("1166666666");
        personDao.registerPerson(p,"Cny lyvd");*/
        for(Person person : PersonDAOImpl.listPerson()) {
            System.out.println(person.toString());
        }

        for(User user : userDao.listUsers()) {
            System.out.println(user.toString());
        }

        //test login acess
        /*u.setName("Diego336566974'--");
        u.setPassword("123");*/

       // System.out.println(userDao.loginAcess(u));

    }
}