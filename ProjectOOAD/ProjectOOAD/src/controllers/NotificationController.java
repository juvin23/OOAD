package controllers;

import java.util.ArrayList;
import java.util.UUID;

import models.Notification;
import models.User;

public class NotificationController {
	public NotificationController() {
		// TODO Auto-generated constructor stub
	}
	//create
	public static Notification createNotification(UUID userID, String message) {
		
		Notification ntf = new Notification(UUID.randomUUID(),userID, message);
		
		return ntf.save();
	}
	//getAllNotification
	public ArrayList<Notification> getAllNotification(){
		User user = User.get("id");
		return Notification.getAll(user.getUserID().toString());
	}
	
	public void readAllNotification(UUID userID) {
		ArrayList<Notification> ntfList = new ArrayList<Notification>();
		ntfList = Notification.getAll(userID.toString());
		for(Notification ntf: ntfList) {
			ntf.update();
		}
		System.out.println("Marked All read");
	}
	
}
