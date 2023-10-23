package br.com.jdbcmysql.DAO;

import br.com.jdbcmysql.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public List<User> listUsers() {
        List<User> users = new ArrayList<User>();

        try {
            String sql = "SELECT * FROM tbluser";

            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setInclusion(rs.getTimestamp("inclusion"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setUserActive(rs.getInt("user_active"));

                users.add(u);
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

    public void registerUser(User u) {

        String sql = "INSERT INTO tbluser (INCLUSION, NAME, USER_ACTIVE, PASSWORD) VALUES (?,?,?,?)";

        try {
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setTimestamp(1, u.getInclusion());
            ps.setString(2, u.getName());
            ps.setInt(3, u.getUserActive());
            ps.setString(4, u.getPassword());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
