package com.greatlearning.javapersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CrudServicesDAO {
	List<User> userList = null;
	List<String> queryList = null;

	public CrudServicesDAO() {
		queryList = new ArrayList<String>();
		queryList.add("update users set fname=? where id=?");
		queryList.add("update users set lname=? where id=?");
		queryList.add("update users set email=? where id=?");
	}

	public void registerUser(Connection conn, int id, String fName, String lName, String email) throws Exception {
		String query = "Insert into Users (id,fname,lname,email) values (?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, id);
		pstmt.setString(2, fName);
		pstmt.setString(3, lName);
		pstmt.setString(4, email);
		System.out.println(pstmt.executeUpdate() + "User Inserted Successully");
	}

	public void displayData(Statement stmt) throws Exception {
		String query = "select id,fname,lname,email from Users";
		System.out.println("List of Users are:");
		ResultSet rs = stmt.executeQuery(query);
		int counter = 0;
		userList = new ArrayList<User>();
		while (rs.next()) {
			User user = new User(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("email"));
			userList.add(user);
			counter++;
		}
		System.out.println("Total Users = " + counter);
		userList.forEach(user -> System.out.println(user.toString()));

	}

	public void delete(Connection conn, int id) throws Exception {
		String query = "delete from Users where id=?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, id);
		System.out.println(pstmt.executeUpdate() + " User with id= " + id + " Deleted Successully");
	}

	public void update(Connection conn, int id, String name, int option) throws Exception {
		String query = queryList.get(option - 1);
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(2, id);
		pstmt.setString(1, name);
		System.out.println(pstmt.executeUpdate() + " Updated Successully");

	}

}
