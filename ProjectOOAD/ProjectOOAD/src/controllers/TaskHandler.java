package controllers;

import java.util.ArrayList;
import java.util.UUID;

import models.Task;

public class TaskHandler {

	public TaskHandler() {
		// TODO Auto-generated constructor stub
		
	}
	
	public Task getTask(String taskID) {
		return Task.getTask(taskID);
	}
	
	public ArrayList<Task> getAll(){
		return Task.getAllTask();
	}
	
	public Task CreateTask(String title, String supervisorID, String workerID, String note) {
		if(Task.getByTitle(title) != null) {
			System.out.println("Task already Exist");
			return null;
		}
		
		Task task = Task.create(title, UUID.fromString(supervisorID), UUID.fromString(workerID), note); 
		
		return task.save();
	}
	
	public Task updateTask(String taskID, String title, String workerID, String supervisorID, Integer score, String note) {
		Task task = Task.getTask(taskID);
		task.setTitle(title);
		task.setWorkerID(UUID.fromString(workerID));
		task.setSupervisorID(UUID.fromString(supervisorID));
		task.setScore(score);
		task.setNote(note);
		task.update();
		return task;
	}
	
	public void deleteTask(String taskID) {
		Task task = Task.getTask(taskID);
		task.delete();
	}
	
	public Task approveTask(String taskID, Integer score) {
		getAll();
		Task task = Task.getTask(taskID);
		task.setScore(score);
		task.update();
		return task;
	}
	
	public Task requestTaskRevision(String taskID) {
		getAll();
		Task task = Task.getTask(taskID);
		Integer temp = task.getRevisionCount();
		task.setRevisionCount(temp++);
		task.update();
		return task;
	}
	
	public Task submitTask(String taskID) {
		getAll();
		Task task = Task.getTask(taskID);
		task.setIsSubmitted(true);
		task.update();
		return task;
	}
	
	public ArrayList<Task> searchTask(String query){
		return Task.search(query);
	}
	
	public static ArrayList<Task> sortTask(String sortBy, String direction){
		return Task.sort(sortBy, direction);
	}
}
