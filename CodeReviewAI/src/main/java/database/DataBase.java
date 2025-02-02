package database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DataBase {
	
	private String url="jdbc:mysql://localhost:3306/codereview";
	private String userName="root";
	private String password="shreyash@2005";
	
	private Connection connection=null;
	
	public DataBase(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Drivers Loaded Successfully!!!!");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			connection=DriverManager.getConnection(url,userName,password);
			System.out.println("Connection Established Successfully!!!!");

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int addUser(String name,String email,String pass) {
		
		String query="INSERT INTO users (name,email,password) values(?,?,?);";
		
		try {
			PreparedStatement pt=connection.prepareStatement(query);
			pt.setString(1, name);
			pt.setString(2, email);
			pt.setString(3, pass);
			int rs=pt.executeUpdate();
			System.out.println("User added successfully!!!!");
			return rs;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
			
		return 0;
	}
	
	public boolean checkUser(String email,String pass) {
		
		String query="SELECT * FROM users WHERE email=? and password=?;";
		
		try {
			PreparedStatement pt=connection.prepareStatement(query);
			pt.setString(1, email);
			pt.setString(2, pass);
			ResultSet set=pt.executeQuery();
			System.out.println("User is Present");
			return set.next();
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
