package com.hostmdy.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.crypto.PasswordEncoder;
import com.hostmdy.crypto.PasswordValidator;

public class UserDAO {
	
	private final DataSource dataSource;
	
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public UserDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	private void close() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public User getUserByEmail(String email) {
		User user = null;
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where email='"+email+"';");
			
			while (rs.next()) {
				user = new User(
						rs.getInt("id"),
						rs.getString("username"), 
						rs.getString("email"), 
						rs.getString("password"), 
						rs.getString("role"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return user;
	}
	
	public boolean isValidUser(String email,String originalPassword) {
		User user = getUserByEmail(email);
		boolean valid = false;
		
		if(user != null) {
			String storedPassword = user.getPassword();
			try {
				valid = PasswordValidator.validatePassword(originalPassword, storedPassword);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return valid;
	}
	
	public int createUser(User user) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement(
					"INSERT INTO `user` "
					+ "(`username`, `email`, `password`, `role`) "
					+ "VALUES (?,?,?,?);"
					);
			pStmt.setString(1,user.getUsername());
			pStmt.setString(2,user.getEmail());
			
			String securedPassword = null;
			try {
				System.out.println(user.getPassword()+"!!!!!!!!!!!!!!!");
				securedPassword = PasswordEncoder.encode(user.getPassword());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pStmt.setString(3,securedPassword);
			pStmt.setString(4,user.getRole());
			
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowEffected;
	}

	public List<User> getResultList() {
		List<User> users = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user");
			
			while (rs.next()) {
				users.add( new User(
						rs.getInt("id"),
						rs.getString("username"), 
						rs.getString("email"), 
						"*****", 
						rs.getString("role")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return users;
	}

	public User getResultById(int id) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where id='"+id+"';");
			
			while (rs.next()) {
				user = new User(
						rs.getInt("id"),
						rs.getString("username"), 
						rs.getString("email"), 
						rs.getString("password"), 
						rs.getString("role"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return user;
	}

	public int deleteResult(int id) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("delete from user where id = ?;");
			pStmt.setInt(1,id);
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowEffected;
	}
	
	

}
