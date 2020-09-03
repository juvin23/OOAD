package models;

//import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import connection.Connector;

public class Task {
	private UUID id;
	private UUID workerID;
	private UUID supervisorID;
	private String title;
	private Integer revisionCount;
	private Integer score;
	private Boolean isSubmitted;
	private Timestamp approvedAt;
	private String note;
	
	//constructor
	public Task(UUID id, String title, UUID supervisorID, UUID workerID, String note) {
		super();
		this.id = id;
		this.workerID = workerID;
		this.supervisorID = supervisorID;
		this.title = title;
		this.revisionCount = 0;
		this.score = 0;
		this.isSubmitted = false;
		this.approvedAt = null;
		this.note = note;
		
	}
	
	public Task() {
		
	}
	
	public static Task getTask(String id) {
		String query = "SELECT * from tasks WHERE id = ?";
		
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			
			String taskID = rs.getString("taskID");
			String workerID =rs.getString("workerID");
			String supervisorID =rs.getString("supervisorID");
			String title =rs.getString("title");
			String note = rs.getString("note");
			
			return new Task(UUID.fromString(taskID), title, UUID.fromString(supervisorID), UUID.fromString(workerID), note);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static ArrayList<Task> getAllTask() {
		String query = "SELECT * from tasks";
		ArrayList<Task> list = new ArrayList<Task>();
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String taskID = rs.getString("taskID");
				String workerID =rs.getString("workerID");
				String supervisorID =rs.getString("supervisorID");
				String title =rs.getString("title");
				String note = rs.getString("note");
				
				Task task = new Task(UUID.fromString(taskID), title, UUID.fromString(supervisorID), UUID.fromString(workerID), note);
				list.add(task);
			}			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static Task getByTitle(String title) {
		String query = "SELECT * FROM users WHERE title = ?";
	
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, title);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String taskID = rs.getString("taskID");
				String workerID =rs.getString("workerID");
				String supervisorID =rs.getString("supervisorID");
				String titles =rs.getString("title");
				String note = rs.getString("note");
				
				Task task = new Task(UUID.fromString(taskID), titles, UUID.fromString(supervisorID), UUID.fromString(workerID), note);
				return task;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static Task create(String title, UUID supervisorID, UUID workerID, String note) {
		Task task = new Task(UUID.randomUUID(), title, supervisorID, workerID, note);
		return task.save();
	}
	
	public Task save() {
		String query = "INSERT INTO tasks() VALUES(?,?,?,?,?)";
		
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, this.getId().toString());
			ps.setString(2, this.getTitle());
			ps.setString(3, this.getSupervisorID().toString());
			ps.setString(4, this.getWorkerID().toString());
			ps.setString(5, this.getNote());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return this;
	}
	
	public Task update() {
		String query = "UPDATE task SET title = ?, supervisor_id = ?, "
				+ "worker_id = ?, note = ?"
				+ "WHERE id = ?";
		
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			ps.setString(1, this.getTitle());
			ps.setString(2, this.getSupervisorID().toString());
			ps.setString(3, this.getWorkerID().toString());
			ps.setString(4, this.getNote());
			ps.setString(5, this.id.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public UUID delete() {
		String query = "DELETE FROM task where id = ?";
		
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, id.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static ArrayList<Task> sort(String by, String direction) {
		String query = "SELECT * FROM task ORDER BY ? ?";
		PreparedStatement ps;
		ArrayList<Task> list = new ArrayList<>();
		
		try {
			ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, by);
			ps.setString(2, direction);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String taskID = rs.getString("taskID");
				String workerID =rs.getString("workerID");
				String supervisorID =rs.getString("supervisorID");
				String title =rs.getString("title");
				String note = rs.getString("note");
				
				Task task = new Task(UUID.fromString(taskID), title, UUID.fromString(supervisorID), UUID.fromString(workerID), note);
				list.add(task);
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static ArrayList<Task> search(String query) {
		
		PreparedStatement ps;
		ArrayList<Task> list = new ArrayList<>();
		
		try {
			ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String taskID = rs.getString("taskID");
				String workerID =rs.getString("workerID");
				String supervisorID =rs.getString("supervisorID");
				String title =rs.getString("title");
				String note = rs.getString("note");
				
				Task task = new Task(UUID.fromString(taskID), title, UUID.fromString(supervisorID), UUID.fromString(workerID), note);
				list.add(task);
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	//setter and getter
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
	public Integer getRevisionCount() {
		return revisionCount;
	}
	public void setRevisionCount(Integer revisionCount) {
		this.revisionCount = revisionCount;
	}
	public Boolean getIsSubmitted() {
		return isSubmitted;
	}
	public void setIsSubmitted(Boolean isSubmitted) {
		this.isSubmitted = isSubmitted;
	}
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Timestamp getApprovedAt() {
		return approvedAt;
	}
	public void setApprovedAt(Timestamp approvedAt) {
		this.approvedAt = approvedAt;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public Integer getScore() {
		return score;
	}
	
	public void setScore(Integer score) {
		this.score = score;
	}

	
}
