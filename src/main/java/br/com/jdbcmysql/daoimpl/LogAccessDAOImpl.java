package br.com.jdbcmysql.daoimpl;

import br.com.jdbcmysql.DAO.ConnectionFactory;
import br.com.jdbcmysql.DAO.LogAccessDAO;
import br.com.jdbcmysql.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LogAccessDAOImpl implements LogAccessDAO {

    public int thereWereChanges(User u) {
        String sqlLoginTrue = "INSERT INTO TBLLOGACCESS (INCLUSION, USERALTERATION, IPACESS, USERMASTER)" +
                "VALUES (?,?,?,?)";
        int idInclusion = 0;
        
        try {
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement psLogAccess = con.prepareStatement(sqlLoginTrue, Statement.RETURN_GENERATED_KEYS);

            psLogAccess.setTimestamp(1, u.getAlteration());
            psLogAccess.setLong(2, u.getId());
            psLogAccess.setString(3, u.getIpAcess());
            psLogAccess.setString(4, u.getNameMaster());

            psLogAccess.executeUpdate();
            psLogAccess.close();
            
            ResultSet rs = psLogAccess.getGeneratedKeys();
            
            if (rs.next()) {
                idInclusion = rs.getInt(1);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idInclusion;
    }
}

