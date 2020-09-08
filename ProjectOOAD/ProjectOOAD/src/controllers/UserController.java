package controllers;

import java.util.ArrayList;
import java.sql.Date;
import java.util.UUID;

import javax.swing.JOptionPane;

import helpers.Env;
import helpers.Utils;
import models.User;

public class UserController {
	
	private static UserController uc;
	
	public static UserController getInstance() {
		if(uc == null) {
			uc = new UserController();
		}
		return uc;
	}

	public UserController() {
	}

	
	public User UserLogin(String uname, String password) throws Exception {
		User user = this.getByUname(uname);
		
		if(user == null) {
			throw new Exception();
		}else if(!user.getPassword().equals(password)) {
			System.out.println("/" + user.getPassword() + "/" + password + "/");
			throw new Exception();
		}
		return user;
	}
	
	
	public User getUser(String userID) {
		return User.get(userID);
	}
	
	public User getByUname(String name) {
		return User.getByUname(name);
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
			JOptionPane.showMessageDialog(null, "User Already Exist");
			return null;
		}
		
		Date dob = null;
		dob = Date.valueOf(DOB);
	
		User u = new User(UUID.randomUUID(), username, password, role, Address,dob , telp);
		u.Save();
		
		return u;
	}
	
	public boolean checkpass(User u, String pass) {
		
		if(u.getPassword() == pass) return true;
		return false;
	}
	
	public String deleteUser(String ID) {
		User u = User.get(ID);
		if(u == null) return null;
		else if(u.delete()) JOptionPane.showMessageDialog(null, "Deleted");
		return ID;
	}
	
	public User UpdateProfile(String Usermame, String Password, String Role, Date DOB, String Address, String telp) {
		User u = User.getByUname(Usermame);
		u.setAddress(Address);
		u.setDOB(DOB);
		u.setTelp(telp);
		return u.Update();
	}
	
 	public User resetPassword(String uuid){
 		User u = User.get(uuid);
		String newpass = u.getDOB().toString();
		u.setPassword(newpass); 
		
		return u.Update();
	}
	
	public User changePassword(String oldPass, String newPass) {
		if(Env.user.checkPassword(oldPass)) return null;
		
		Env.user.setPassword(newPass);
		
		return Env.user.Update();
	}
	

}
