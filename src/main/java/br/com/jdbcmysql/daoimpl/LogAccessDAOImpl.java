package br.com.jdbcmysql.daoimpl;

import br.com.jdbcmysql.DAO.ConnectionFactory;
import br.com.jdbcmysql.DAO.LogAccessDAO;
import br.com.jdbcmysql.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class LogAccessDAOImpl implements LogAccessDAO {

    public long thereWereChanges(User u) {
        String sqlLoginTrue = "INSERT INTO TBLLOGACCESS (INCLUSION, USERALTERATION, IPACESS, USERMASTER)" +
                "VALUES (?,?,?,?)";
        try {
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement psLogAcess = con.prepareStatement(sqlLoginTrue, Statement.RETURN_GENERATED_KEYS);

            psLogAcess.setTimestamp(1, u.getAlteration());
            psLogAcess.setLong(2, u.getId());
            psLogAcess.setString(3, u.getIpAcess());
            psLogAcess.setString(4, u.getNameMaster());

            psLogAcess.execute();
            psLogAcess.close();

            return 1;
        } catch (Exception e) {
            return 2;
        }
    }
}

