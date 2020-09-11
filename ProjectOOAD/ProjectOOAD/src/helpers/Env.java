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
	
	public static String[] SEARCHBY = {"Search by", "Title", "Supervisor Name", "Worker Name"};
	public static String[] SORTDIRECTION  = {"Sort direction", "Ascending", "Descending"};
	public static String[] SORTBY = {"Sort by", "Title", "Supervisor Name", "Worker Name", "Submitted", "Approve Date"};
	public static String[] TASKATRI = {"TaskId", "Supervisor", "Worker", "Title", "Revision Count", "Score", "Submitted", "Approved At", "Note"};
	public static String[] UPDATETASK = {"Id", "Username"};
	public static String[] TASKFORM = {"Username"};
}
