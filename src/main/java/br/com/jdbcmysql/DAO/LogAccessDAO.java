package br.com.jdbcmysql.DAO;

import br.com.jdbcmysql.model.LogAccess;
import br.com.jdbcmysql.model.User;

import java.util.List;

public interface LogAccessDAO {

    int thereWereChanges(User u);
    List<LogAccess> listLogAccess();
    void createTblLogAccess();

}
