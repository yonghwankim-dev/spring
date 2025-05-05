package com.nemo.aop.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import com.nemo.aop.user.domain.User;

public class UserDao {
	private final DataSource dataSource;

	public UserDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users";

		try(
			Connection con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
			while (rs.next()){
				long id = rs.getLong("id");
				String name = rs.getString("name");
				String level = rs.getString("level");
				users.add(new User(id, name, level));
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Failed to fetch users", e);
		}
		return users;
	}
}
