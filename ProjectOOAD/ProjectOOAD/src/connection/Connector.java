package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import helpers.Env;

public class Connector {
	private static Connection connection;
	
	public Connector() {
		try {
			connection = DriverManager.getConnection("jdbc:"+ Env.DRIVER +"://"+Env.HOST+":"+ Env.PORT+"/"+Env.DB+"?" + Env.Timezone, Env.username, Env.password);
			System.out.println("jdbc:"+ Env.DRIVER +"://"+Env.HOST+":"+ Env.PORT+"/" +Env.DB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	public final static Connection getConnection() {
		if(connection == null) {
			new Connector();
		}
		return connection;
	}
}
