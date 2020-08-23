package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	public Task(UUID id, UUID workerID, UUID supervisorID, String title, Integer revisionCount, Integer score,
			Boolean isSubmitted, Timestamp approvedAt, String note) {
		super();
		this.id = id;
		this.workerID = workerID;
		this.supervisorID = supervisorID;
		this.title = title;
		this.revisionCount = revisionCount;
		this.score = score;
		this.isSubmitted = isSubmitted;
		this.approvedAt = approvedAt;
		this.note = note;
	}
	
	public static Task getTask(String id) {
		String query = "SELECT * from tasks where id = ?";
		
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			
			String taskID = rs.getString("id");
			String workerID =rs.getString("workerID");
			String supervisorID =rs.getString("supervisorID");
			String title =rs.getString("title");
			Integer revisionCount = rs.getInt("revisionCount");
			Integer score = rs.getInt("score");
			Boolean isSubmitted = rs.getBoolean("isSubmitted");
			Timestamp approvedAt = rs.getTimestamp("approvedAt");
			String note = rs.getString("note");
			
			
			return new Task(UUID.fromString(taskID), UUID.fromString(workerID), UUID.fromString(supervisorID), title, revisionCount, score, isSubmitted, approvedAt, note);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
