import controllers.UserController;
import helpers.Utils;
import models.User;

public class Main {

	public Main() {
		UserController uc = new UserController();
		String uname, password;
		do{
			System.out.print("login >> "); uname = Utils.ScanStr();
			System.out.print("pass  >> ");password = Utils.ScanStr();
			User.instance = uc.login(uname, password);
		}while(User.instance == null);
		
		
	}
	
	public static void main(String[] args) {
		new Main();
		
	}
	
}
