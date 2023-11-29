package br.com.jdbcmysql.daoimpl;

import br.com.jdbcmysql.DAO.ConnectionFactory;
import br.com.jdbcmysql.DAO.LogAccessDAO;
import br.com.jdbcmysql.model.LogAccess;
import br.com.jdbcmysql.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LogAccessDAOImpl implements LogAccessDAO {

    public void createTblLogAccess() {
        try {
            String sql = "CREATE TABLE jdbc_mysql.tblLogAccess ("+
                         "id INT NOT NULL AUTO_INCREMENT UNIQUE,"+
                         "inclusion TIMESTAMP NOT NULL,"         +
                         "alteration TIMESTAMP NULL,"            +
                         "userAlteration INT NULL,"              +
                         "ipAccess VARCHAR(15) NOT NULL,"        +
                         "userMaster VARCHAR(50),"               +
                         "PRIMARY KEY (id));";

            String sql2 = "SELECT 1 FROM tbluser limit 1";

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
            System.out.println("tblLogAccess created");
        } catch (Exception e) {
            System.out.println("error create table tblLogAccess");
            throw new RuntimeException(e);
        }
    }

    public int thereWereChanges(User u) {
        String sqlLoginTrue = "INSERT INTO TBLLOGACCESS (INCLUSION, USERALTERATION, IPACCESS, USERMASTER)" +
                "VALUES (?,?,?,?)";
        int idInclusion = 0;
        
        try {
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement psLogAccess = con.prepareStatement(sqlLoginTrue, Statement.RETURN_GENERATED_KEYS);

            psLogAccess.setTimestamp(1, u.getAlteration());
            psLogAccess.setLong(2, u.getId());
            psLogAccess.setString(3, u.getIpAccess());
            psLogAccess.setString(4, u.getNameMaster());

            psLogAccess.executeUpdate();
            ResultSet rs = psLogAccess.getGeneratedKeys();
            
            if (rs.next()) {
                idInclusion = rs.getInt(1);
            }
            psLogAccess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idInclusion;
    }

    public List<LogAccess> listLogAccess() {
        List<LogAccess> users = new ArrayList<LogAccess>();

        try {
            String sql = "SELECT * FROM tblLogAccess";

            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                LogAccess logAccess = new LogAccess();
                logAccess.setId(rs.getInt("id"));
                logAccess.setInclusion(rs.getTimestamp("inclusion"));
                logAccess.setInclusion(rs.getTimestamp("alteration"));
                logAccess.setUserInclusion(rs.getInt("userAlteration"));
                logAccess.setIpAccess(rs.getString("ipAccess"));
                logAccess.setUserMaster(rs.getString("userMaster"));

                users.add(logAccess);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("erro");
            throw new RuntimeException(e);
        }
        return users;
    }
}

