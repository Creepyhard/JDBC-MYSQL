package br.com.jdbcmysql.DAO;

import br.com.jdbcmysql.model.User;

public interface LogAccessDAO {

    int thereWereChanges(User u);
}
