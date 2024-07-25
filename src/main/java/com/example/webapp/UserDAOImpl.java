package com.example.webapp;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findUserByEmail(String email) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try (Connection conn = DBUtils.getConnection()) {

            pstmt = conn.prepareStatement(String.format("SELECT * FROM users WHERE email ='%s'", email));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setActive(rs.getString(4).equalsIgnoreCase("Y"));
                user.setPassword(rs.getString(5));
                user.setCreatedTs(rs.getTimestamp(6));
                user.setUpdatedTs(rs.getTimestamp(7));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("FindUserByEmail Exception", e);
        } finally {
            DBUtils.close(null, null, pstmt, rs);
        }

        return null;
    }

    @Override
    public boolean createUser(User user) {
        PreparedStatement pstmt = null;
        try (Connection conn = DBUtils.getConnection()) {
            pstmt = conn.prepareStatement(String.format("INSERT INTO users (name, email, password) VALUES ('%s', '%s', '%s')"
                    , user.getName(), user.getEmail(), user.getPassword()));

            return pstmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Creation User problem", e);
        } finally {
            DBUtils.close(null, null, pstmt, null);
        }


    }

    @Override
    public boolean activate(User user) {
        PreparedStatement pstmt = null;
        try (Connection conn = DBUtils.getConnection()) {
            pstmt = conn.prepareStatement("UPDATE users SET is_active = 'Y' WHERE users.email = ?");
            pstmt.setString(1,user.getEmail());
            return pstmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Creation User problem", e);
        } finally {
            DBUtils.close(null, null, pstmt, null);
        }
    }
}