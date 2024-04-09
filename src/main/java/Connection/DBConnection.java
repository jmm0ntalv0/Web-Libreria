package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	static String bd = "libreria_udemy";
	static String port = "3306";
	static String username = "root";
	static String password = "jesus";
	static String url = "jdbc:mariadb://localhost:" + port + "/" + bd;
	
	Connection connection = null;
	
	public DBConnection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);	
			
			if(connection == null) {
				System.out.println("La conexion a " + bd + " ha fallado");
			} else
				System.out.println("La conexion a " + bd + " se ha establecido");
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void desconectar() {
		connection = null;
	}
}
