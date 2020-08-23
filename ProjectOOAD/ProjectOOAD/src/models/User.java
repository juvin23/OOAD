package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.UUID;


import connection.Connector;


public class User {
	private UUID id;
	private String username;
	private String password;
	private String role;
	private String address;
	private Date DOB;
	private String telp;
	
	public static User instance;
	
	/*
	  `id` char(36) NOT NULL,
	  `username` varchar(20) NOT NULL,
	  `password` varchar(100) NOT NULL,
	  `role` varchar(10) NOT NULL,
	  `address` varchar(100) NOT NULL,
	  `DOB` date NOT NULL,
	  `telp` varchar(15) NOT NULL
	 */

	
	//constructor
	public User(UUID id, String username, String password, String role, String address, Date dOB, String telp) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.address = address;
		DOB = dOB;
		this.telp = telp;
	}

	
	public User() {
		
	}
	
	
	public User create(String username, String password, String role, String address, Date dob, String telp) {
		User u = new User(UUID.randomUUID(), username, password, role, address, DOB, telp);
		
		return u.Save();
	}
	
	public User Save() {
	
		String query = "INSERT INTO users (id, username, password, role, address, DOB, telp) VALUES (? , ? , ? , ? , ? , ? , ?)";
		try {
			PreparedStatement ps = Connector.getConnection().prepareStatement(query);
			
			ps.setString(1, this.id.toString());
			ps.setString(2, this.username);
			ps.setString(3, this.password);
			ps.setString(4, this.role);
			ps.setString(5, this.address);
			ps.setDate(6, DOB);
			ps.setString(7, this.telp);
			ps.execute();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return this;
	}

	public User Update() {
		String query = "UPDATE Users SET username = ?, password = ?, role = ?, address = ?, telp = ? WHERE id = ?";
		try {
			PreparedStatement ps =  (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			ps.setString(1, this.username);
			ps.setString(2, this.password);
			ps.setString(3, this.role);
			ps.setString(4, this.address);
			ps.setString(5, this.telp);
			ps.setString(6, this.id.toString());
			
			ps.execute();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return this;
	}
	
	public String delete(){
		String query = "DELETE FROM users WHERE id = " + this.id;
		try {
			Statement statement = Connector.getConnection().createStatement();
			statement.execute(query);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return this.id.toString();
		}
		
		return null;
	}
	
	public static User get(String id) {
		String query = "SELECT * FROM USER WHERE ID = ?";
		try {
			
			PreparedStatement ps =  (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String userID = rs.getString("id");
			String username= rs.getString("username");
			String password =rs.getString("password");
			String role = rs.getString("role");
			String address = rs.getString("address");
			Date dOB = rs.getDate("DOB");
			String telp = rs.getString("telp");
			
			
			return new User(UUID.fromString(userID), username, password, role, address, dOB, telp);
			
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
		return null;	
	}
	
	public static User getByUname(String uname) {
		String query = "SELECT * FROM Users WHERE username = ?";
		try {
			PreparedStatement ps =  (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next()) return null;
			String userID = rs.getString("id");
			String username= rs.getString("username");
			String password =rs.getString("password");
			String role = rs.getString("role");
			String address = rs.getString("address");
			Date dOB = rs.getDate("DOB");
			String telp = rs.getString("telp");
			User u = new User(UUID.fromString(userID), username, password, role, address, dOB, telp);
			System.out.println(u);
			System.out.println(u.getPassword());
			
			return u;
			}catch (SQLException e){
				System.out.println(e.getMessage());
			}
		return null;	
	}
	
	public static ArrayList<User> getAll() {
		String query = "SELECT * FROM users";
		try {
			ArrayList<User> ret = new ArrayList<>();
			PreparedStatement ps =  (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			
			while(rs.next()) {
				String userID = rs.getString("id");
				String username= rs.getString("username");
				String password =rs.getString("password");
				String role = rs.getString("role");
				String address = rs.getString("address");
				Date DOB = rs.getDate("DOB");
				String telp = rs.getString("telp");
				User u = new User(UUID.fromString(userID), username, password, role, address, DOB, telp);
				ret.add(u);
			}
			return ret;
			
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return null;	
	}
	
	public static ArrayList<User> getUserByRole(String Role) {
		String query = "SELECT * FROM USER WHERE role = ?";
		try {
			ArrayList<User> ret = new ArrayList<>();
			PreparedStatement ps =  (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			
			ps.setString(1, Role);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String userID = rs.getString("id");
				String username= rs.getString("username");
				String password =rs.getString("password");
				String role = rs.getString("role");
				String address = rs.getString("address");
				Date DOB = rs.getDate("DOB");
				String telp = rs.getString("telp");
				User u = new User(UUID.fromString(userID), username, password, role, address, DOB, telp);
				
				ret.add(u);
			}
			return ret;
			
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return null;	
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	//setter and getter
	public String getUsername() {
		return username;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getAddress() {
		return address;
	}
	
	public Date getDOB() {
		return DOB;
	}
	
	public String getTelp() {
		return telp;
	}
	
	public UUID getUserID() {
		return id;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public String getPassword() {
		return this.password;
	}
	
}
