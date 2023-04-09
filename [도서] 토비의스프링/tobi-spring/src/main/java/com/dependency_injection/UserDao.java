package com.dependency_injection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(
            "INSERT INTO Users(id, name, password) VALUES(?, ?, ?)");
        stmt.setString(1, user.getId());
        stmt.setString(2, user.getName());
        stmt.setString(3, user.getPassword());

        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(
            "SELECT * FROM users WHERE id = ?");
        stmt.setString(1, id);

        ResultSet rs = stmt.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));

        rs.close();
        stmt.close();
        connection.close();
        return user;
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(
            "DELETE FROM USERS");

        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(
            "SELECT count(*) FROM users");

        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        stmt.close();
        connection.close();
        return count;
    }
}
