package models;

//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import com.mysql.jdbc.PreparedStatement;

import connection.Connector;

public class Notification {
	private UUID id;
	private UUID userID;
	private String message;
	private Timestamp readAt;
	
	
	//constructor

	public Notification(UUID id, UUID userID, String message) {
		super();
		this.id = id;
		this.userID = userID;
		this.message = message;
		//this.readAt = readAt;
		this.readAt = null;
	}
	
	
//	public static Notification getNotification(String id) {
//		
//		
//		return null;
//	}
//	
	
	
	//get satu notif ga ada di class diagram
	
	public static Notification get(String id) {
	String query = "SELECT * from notifications where id = ?";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			String notifId = rs.getString("id");
			String userID = rs.getString("userID");
			String message = rs.getString("message");
		
			
			return new Notification(UUID.fromString(notifId), UUID.fromString(userID), message);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	public Notification save() {
		String query = "INSERT INTO notifications VALUES(?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, this.id.toString());
			ps.setString(2, this.userID.toString());
			ps.setString(3, this.message);
			
			
			//ResultSet rs = 
			ps.executeQuery();
			//rs.next();
			System.out.println("Successfuly inserted!!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this;
	}
	

	
	public ArrayList<Notification>getAllUnread(String userID){
		String query = "SELECT * FROM notifications";
		ArrayList<Notification> unreadNotif = new ArrayList<Notification>(); 
		
		try {
			PreparedStatement ps;
			ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String user = rs.getString("userID");
				String message = rs.getString("message");
				String readAt = rs.getTimestamp("readAt").toString();
				if(readAt != null) {
					rs.next();
				}
				Notification temp = new Notification(UUID.fromString(id), UUID.fromString(user), message);
				unreadNotif.add(temp);
			}
			return unreadNotif;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	public Notification update() {
		String query = "UPDATE notifications SET"
				+ " readAt = ?";
		this.setReadAt(new Timestamp(System.currentTimeMillis()));
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MI:SS");
			String insert = sdf.format(this.getReadAt()).toString();
			ps.setString(1, insert);
			ps.execute();
			
			return this;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	
	//setter getter
	public UUID getUserID() {
		return userID;
	}

	public void setUserID(UUID userID) {
		this.userID = userID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getReadAt() {
		return readAt;
	}
	public void setReadAt(Timestamp readAt) {
		this.readAt = readAt;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	
}
