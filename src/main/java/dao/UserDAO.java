package dao;

import model.User;
import util.DBConnection;

import java.sql.*;

public class UserDAO {

    public boolean save(User user) throws SQLException {
        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";

        PreparedStatement ps = DBConnection.getInstance()
                .getConnection()
                .prepareStatement(sql);

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());

        return ps.executeUpdate() > 0;
    }

    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";

        PreparedStatement ps = DBConnection.getInstance()
                .getConnection()
                .prepareStatement(sql);

        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password")); // hashed
            return user;
        }

        return null;
    }
}

