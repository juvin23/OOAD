package controllers;

import java.util.ArrayList;
import java.sql.Date;
import java.util.UUID;

import helpers.Utils;
import models.User;

public class UserController {

	public UserController() {
		
	}
	
	public User getUser(String userID) {
		return User.get(userID);
	}
	
	public  ArrayList<User> getAllUser(){
		return User.getAll();
	}
	
	public ArrayList<User> getUserByRole(String Role){
		return User.getUserByRole(Role);
	}
	
	// RETURN NULL IF NOT SUCCESS
	public User createUser(String username, String password, 
							String role, String DOB, 
								String Address, String telp) {
		
		if(!Utils.Validate(username, password, role, DOB, Address, telp)) return null; // not successfully createuser
		if(User.getByUname(username) != null) {
			System.out.println("Username Already Exist");
			return null;
		}
		
		Date dob = null;
		dob = Date.valueOf(DOB);
	
		User u = new User(UUID.randomUUID(), username, password, role, Address,dob , telp);
		u.Save();
		
		return u;
	}
	
	public User login(String uname, String password){
		User user = User.getByUname(uname);
		if(user == null || user.getPassword() != password) return null;
	
		return user;
	}
	
	public boolean checkpass(User u, String pass) {
		if(u.getPassword() == pass) return true;
		return false;
	}
	
	public void deleteUser(String ID) {
		User u = User.get(ID);
		if(u == null) System.out.println("ID does not exist!");
		else if(u.delete()) System.out.println("deleted");
		else System.out.println("Cannot perform action");
		return;
	}
	
	public User UpdateProfile(String Usermame, String Password, String Role, Date DOB, String Address, String telp) {
		User u = null;
		return u;
	}
	
 	public User resetPassword(String userID){
		User.instance = User.get(userID);
		String newpass = User.instance.getDOB().toString();
		User.instance.setPassword(newpass);
		
		return User.instance;
	}
	
	public User changePassword(String oldPass, String newPass) {
		if(User.instance.getPassword() != oldPass) return null;
		
		User.instance.setPassword(newPass);
		
		return User.instance;
	}
	

}
