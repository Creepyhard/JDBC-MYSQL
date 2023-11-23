package br.com.jdbcmysql.DAO;

import br.com.jdbcmysql.model.User;

import java.util.List;

public interface UserDAO {

    void createTbluser();
    List<User> listUsers();
    void registerUserWithPerson(int idPerson, String password);
    String loginAcess(User user);
}
