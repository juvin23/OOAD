package models;

import java.sql.Timestamp;
import java.util.UUID;

public class Notification {
	private UUID id;
	private UUID userID;
	private String message;
	private Timestamp readAt;
	
	
	//constructor

	public Notification(UUID id, UUID userID, String message, Timestamp readAt) {
		super();
		this.id = id;
		this.userID = userID;
		this.message = message;
		this.readAt = readAt;
	}
	
	
//	public static Notification getNotification(String id) {
//		
//		
//		return null;
//	}
//	
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
