package controllers;

import java.util.ArrayList;
//import java.util.List;
import java.util.UUID;

//import models.Notification;
import models.Task;
import models.TaskRequest;
import models.User;
import views.AllTaskRequestDisplay;

public class TaskRequestHandler {
	public TaskRequestHandler() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	//gatau ini gayakin
	public ArrayList<TaskRequest> getAllTaskRequest(){
		User user = User.get("id");
		return TaskRequest.getAll(user.getUserID().toString());
	}
	
	public TaskRequest getTaskRequest(String taskRequestId) {
		return TaskRequest.get(taskRequestId);
	}
	
	
	
	// create task request
	public static TaskRequest createTaskRequest(String title, UUID supervisorID, UUID workerID, String note) {
		UUID id = UUID.randomUUID();
		TaskRequest tr =  new TaskRequest(id, workerID, supervisorID, title, note);
		
	
		return tr.save();
	}
	
	
	
	//accept task request
	public static TaskRequest acceptTaskRequest(String id) {
		TaskRequest tr = TaskRequest.get(id);
		
		
		
		if (tr == null) {
			System.out.println("Task Request not found");
			return null;
		}
		else {
			String title = tr.getTitle();
			String supervisorId = tr.getSupervisorID().toString();
			String workerId = tr.getWorkerID().toString();
			String note = tr.getNote();
			String trId = tr.delete().toString();
			Task task = Task.create(title, UUID.fromString(supervisorId), UUID.fromString(workerId), note); //do uncomment if the create already created on task class
			task.save();
			//String message = "dunno";
			//createNotification(id, message);
			
			String message = "Your task request accepted!";
			NotificationController.createNotification(UUID.fromString(id), message);
			System.out.println("Successfully TR: "+ trId +"deleted");
			System.out.println("Task Request accepted");
			
			return tr;
		}
		
	}
	
	//reject task request
	public static TaskRequest rejectTaskRequest(String id) {
		TaskRequest tr = TaskRequest.get(id);
		if(tr == null) {
			System.out.println("Not found");
			return null;
		}
		else {
//			String title = tr.getTitle();
//			String supervisorId = tr.getSupervisorID().toString();
//			String workerId = tr.getWorkerID().toString();
//			String note = tr.getNote();
//		
			String trId = tr.delete().toString();
//			
			String message = "Your task request rejected!";
			NotificationController.createNotification(UUID.fromString(id), message);
			System.out.println("Successfully TR: "+ trId +"deleted");
			return tr;
		}
		
		
	}
	
}
