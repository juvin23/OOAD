package helpers;

import java.awt.Dimension;
import java.awt.Toolkit;

import models.User;

public class Env {
	public static User user;
	public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static String DRIVER = "mysql";
	public static String HOST = "localhost";
	public static String PORT = "3306";
	public static String DB = "Project";
	public static String Timezone = "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	
	public static String username = "root";
	public static String password = "";
	
}
