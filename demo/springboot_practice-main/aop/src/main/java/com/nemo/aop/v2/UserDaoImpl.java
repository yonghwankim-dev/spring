package com.nemo.aop.v2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

class UserDaoImpl implements UserDao {
	private final DataSource dataSource;

	public UserDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users";

		try (
			Connection con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
			while (rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("name");
				Level level = Level.valueOf(rs.getString("level"));
				users.add(new User(id, name, level));
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Failed to fetch users", e);
		}
		return users;
	}

	@Override
	public void upgradeLevel(User user) {
		String sql = "UPDATE users SET level = ? where id = ?";
		try (
			Connection con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
		) {
			ps.setString(1, user.getLevel().nextLevel().name());
			ps.setLong(2, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalStateException("Failed to update user", e);
		}
	}

	@Override
	public void add(User user) {
		String sql = "INSERT INTO users (name, level) VALUES (?, ?)";
		try (
			Connection con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
		) {
			ps.setString(1, user.getName());
			ps.setString(2, user.getLevel().name());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalStateException("Failed to insert user", e);
		}
	}

	@Override
	public void deleteAll() {
		String sql = "DELETE FROM users";
		try (
			Connection con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
		) {
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalStateException("Failed to delete all users", e);
		}
	}

	@Override
	public int getCount() {
		int result = 0;
		String sql = "SELECT count(*) FROM users";

		try (
			Connection con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Failed to fetch user count", e);
		}
		return result;
	}

	@Override
	public User get(String id) {
		User user = null;
		String sql = "SELECT * FROM users where id = ?";

		try (
			Connection con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
			if (rs.next()) {
				long idValue = rs.getLong("id");
				String name = rs.getString("name");
				Level level = Level.valueOf(rs.getString("level"));
				user = new User(idValue, name, level);
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Failed to fetch users", e);
		}
		return user;
	}
}
