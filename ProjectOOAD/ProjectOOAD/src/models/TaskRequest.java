package models;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.List;
import java.util.UUID;

import com.mysql.jdbc.PreparedStatement;

import connection.Connector;
//import controllers.TaskRequestHandler;
public class TaskRequest {
	private UUID id;
	private UUID workerID;
	private UUID supervisorID;
	private String title;
	private String note;
	
	public TaskRequest(UUID id, UUID workerID, UUID supervisorID, String title, String note) {
		super();
		this.id = id;
		this.workerID = workerID;
		this.supervisorID = supervisorID;
		this.title = title;
		this.note = note;
	}

	public static TaskRequest get(String id) {
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
	
	public static ArrayList<TaskRequest> getAll(String userID){
		String query = "SELECT * FROM task_requests";
		ArrayList<TaskRequest> taskRequestList = new ArrayList<TaskRequest>();
		
		try {
			PreparedStatement ps;
			ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String trID = rs.getString("id");
				String workerID = rs.getString("workerID");
				String supervisorID = rs.getString("supervisorID");
				String title = rs.getString("title");
				String note = rs.getString("note");
				
				TaskRequest temp = new TaskRequest(UUID.fromString(trID), UUID.fromString(workerID), UUID.fromString(supervisorID), title, note);
				taskRequestList.add(temp);
				
			}
			return taskRequestList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ps.setString(1, id);
		return null;
		
	}
	
	public UUID delete() {
		String query = "DELETE FROM task_requests"
				+ "WHERE = ?";

		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			ps.setString(1, this.id.toString());
			ps.executeUpdate();
			
			System.out.println("Successfully Deleted!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		return this.id;
	}
	
	public TaskRequest save() {
		
		String query = "INSERT INTO task_requests VALUES(?, ?, ? , ?, ?)";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, this.id.toString());
			ps.setString(2, this.workerID.toString());
			ps.setString(3, this.supervisorID.toString());
			ps.setString(4, this.title);
			ps.setString(5, this.note); 
			ps.executeUpdate();
			
			System.out.println("Successfuly inserted!!");
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
		return this;
	}
	 
	public TaskRequest update() {
		String query = "UPDATE task_requests SET"
				+ " workerID = ?, supervisorID = ?, title = ?, note = ?";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, this.workerID.toString());
			ps.setString(2, this.supervisorID.toString());
			ps.setString(3, title);
			ps.setString(4, note);
			
			
			ps.executeUpdate();
			
			return this;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
