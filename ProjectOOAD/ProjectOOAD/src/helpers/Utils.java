package helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;



public class Utils {
	static Scanner sc = new Scanner(System.in);
	
	public static String ScanStr() {
		String ret = sc.nextLine();
		
		return ret;
	}
	
	public static boolean Validate(String username, String password, 
				String role, String DOB, 
					String Address, String telp) {
		boolean ret = true;
		
		if(username.length() < 5 || username.length() > 15 ) {
			System.out.println("Username is not valid");
			ret = false;
		}
		
		if(!isDateValid(DOB)) {
			System.out.println("DOB is not Valid");
			ret = false;
		}
		
		return ret;
	}
	
	
	public static boolean isDateValid(String date){
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

}
