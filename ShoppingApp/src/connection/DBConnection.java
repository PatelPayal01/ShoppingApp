package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection makeConnection() throws Exception {
		String username = "testuser";
		String psswd = "123";
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", username, psswd);
	}
}
