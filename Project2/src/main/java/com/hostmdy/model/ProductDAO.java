package com.hostmdy.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ProductDAO {
	
	private DataSource dataSource;
	
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public ProductDAO(DataSource dataSource) {
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
	
	public List<Product> getResultList(){
		List<Product> resultList = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from product;");
			
			while (rs.next()) {
				resultList.add(new Product(
						rs.getInt("id"), 
						rs.getString("productName"), 
						rs.getInt("qty"), 
						rs.getString("description"), 
						rs.getDouble("sellingPrice"), 
						rs.getBoolean("discount"),
						rs.getString("imageName"),
						rs.getString("brandName")
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return resultList;
		
	}
	
	public Product getResult(int id){
		Product result = null;
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from product where id='"+id+"';");
			
			while (rs.next()) {
				result = new Product(
						rs.getInt("id"), 
						rs.getString("productName"), 
						rs.getInt("qty"), 
						rs.getString("description"), 
						rs.getDouble("sellingprice"), 
						rs.getBoolean("discount"),
						rs.getString("imageName"), 
						rs.getString("brandName"));
						
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
		
	}
	
	public int createProduct(Product result) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(
					"INSERT INTO `product`"
					+"(`productName`, `qty`, `description`, `sellingprice`, `discount`, `imageName`, `brandName`)"
					+"VALUES (?,?,?,?,?,?,?);"
					
					);
			pStmt.setString(1,result.getProductName());
			pStmt.setInt(2,result.getQty());
			pStmt.setString(3,result.getDescription());
			pStmt.setDouble(4,result.getSellingprice());
			pStmt.setBoolean(5,result.isDiscount());
			pStmt.setString(6, result.getImageName());
			pStmt.setString(7, result.getBrandName());
			
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowEffected;
	}
	
	public int updateResult(Product result) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement(
					"UPDATE `product` SET "
					+ "`productName` = ?, "
					+ "`qty` = ?, "
					+ "`description` = ?, "
					+ "`sellingprice` = ?, "
					+ "`discount` = ?, "
					+ "`imageName`=?,"
					+"`brandName`=? WHERE (`id` = ?);"
					);
			pStmt.setString(1,result.getProductName());
			pStmt.setInt(2,result.getQty());
			pStmt.setString(3,result.getDescription());
			pStmt.setDouble(4,result.getSellingprice());
			pStmt.setBoolean(5,result.isDiscount());
			pStmt.setString(6, result.getImageName());
			pStmt.setString(7, result.getBrandName());
			pStmt.setInt(8,result.getId());
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
			pStmt = connection.prepareStatement("delete from product where id = ?;");
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

	public List<Product> getResult(String colName, String value) {
		
		List<Product> resultList = new ArrayList<>();
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
				resultList.add(new Product
						(rs.getInt("id"), 
						rs.getString("productName"), 
						rs.getInt("qty"), 
						rs.getString("description"), 
						rs.getDouble("sellingprice"), 
						rs.getBoolean("discount"),
						rs.getString("imageName"), 
						rs.getString("brandName")
						));
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
