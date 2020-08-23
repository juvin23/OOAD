package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


import connection.Connector;

public class TaskRequest {
	private UUID id;
	private UUID workerID;
	private UUID supervisorID;
	private String title;
	private String note;
	
	
	
	
	//constructor
	public TaskRequest(UUID id, UUID workerID, UUID supervisorID, String title, String note) {
		super();
		this.id = id;
		this.workerID = workerID;
		this.supervisorID = supervisorID;
		this.title = title;
		this.note = note;
	}
	
	
	
	
	public static TaskRequest getTaskRequest(String id) {
		String query = "SELECT * from task_requests where id = ?";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			String trID = rs.getString("id");
			String workerID = rs.getString("workerID");
			String supervisorID = rs.getString("supervisorID");
			String title = rs.getString("title");
			String note = rs.getString("note");
			
			return new TaskRequest(UUID.fromString(trID), UUID.fromString(workerID), UUID.fromString(supervisorID), title, note);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	//setter getter
	public UUID getWorkerID() {
		return workerID;
	}
	
	public void setWorkerID(UUID workerID) {
		this.workerID = workerID;
	}
	public UUID getSupervisorID() {
		return supervisorID;
	}
	public void setSupervisorID(UUID supervisorID) {
		this.supervisorID = supervisorID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	
	
	
}
