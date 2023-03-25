package com.hostmdy.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class PetDAO {
	
	private DataSource dataSource;
	
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public PetDAO(DataSource dataSource) {
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
	
	public List<Pet> getResultList(){
		List<Pet> resultList = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from pet;");
			
			while (rs.next()) {
				resultList.add(new Pet(
						rs.getInt("id"), 
						rs.getString("petName"), 
						rs.getInt("petAge"), 
						rs.getString("ownerName"), 
						rs.getDouble("ownerAge"), 
						rs.getBoolean("purchasedStatus")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return resultList;
		
	}
	
	public Pet getResult(int id){
		Pet result = null;
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from pet where id='"+id+"';");
			
			while (rs.next()) {
				result = new Pet(
						rs.getInt("id"), 
						rs.getString("petName"), 
						rs.getInt("petAge"), 
						rs.getString("ownerName"), 
						rs.getDouble("ownerAge"), 
						rs.getBoolean("purchasedStatus"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
		
	}
	
	public int createResult(Pet result) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(
					"INSERT INTO `pet` "
					+ "(`petName`, `petAge`, `ownerName`, `ownerAge`, `purchasedStatus`) "
					+ "VALUES (?,?,?,?,?);"
					);
			pStmt.setString(1,result.getPetName());
			pStmt.setInt(2,result.getPetAge());
			pStmt.setString(3,result.getOwnerName());
			pStmt.setDouble(4,result.getOwnerAge());
			pStmt.setBoolean(5,result.isPurchasedStatus());
			
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowEffected;
	}
	
	public int updateResult(Pet result) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement(
					"UPDATE `pet` SET "
					+ "`petName` = ?, "
					+ "`petAge` = ?, "
					+ "`ownerName` = ?, "
					+ "`ownerAge` = ?, "
					+ "`purchasedStatus` = ? WHERE (`id` = ?);"
					);
			pStmt.setString(1,result.getPetName());
			pStmt.setInt(2,result.getPetAge());
			pStmt.setString(3,result.getOwnerName());
			pStmt.setDouble(4,result.getOwnerAge());
			pStmt.setBoolean(5,result.isPurchasedStatus());
			pStmt.setInt(6,result.getId());
			
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	
	public int deleteResult(int id) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("delete from pet where id = ?;");
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

	public List<Pet> getResult(String colName, String value) {
		
		List<Pet> resultList = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement(
					"select * from pet"
					+ " WHERE (? = ?);"
					);
			
			pStmt.setString(1,colName);
			pStmt.setString(2,value);
			//System.out.println(pStmt.toString()+"1111111111");
			rs = pStmt.executeQuery();
			while (rs.next()) {
				resultList.add(new Pet(
						rs.getInt("id"), 
						rs.getString("petName"), 
						rs.getInt("petAge"), 
						rs.getString("ownerName"), 
						rs.getDouble("ownerAge"), 
						rs.getBoolean("purchasedStatus")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return resultList;
	}
	
	

}
