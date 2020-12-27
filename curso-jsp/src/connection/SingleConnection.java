package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String banco = "jdbc:mysql://localhost/curso_jsp?useTimezone=true&serverTimezone=UTC";
	private static String password = "mysql";
	private static String user = "root";
	private static Connection connection = null;

	static {
		conectar();
	}

	public SingleConnection() {
		conectar();
	}

	private static void conectar() {

		try {

			if (connection == null) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados!");
		}
	}

	public static Connection getConnection() {
		return connection;
	}
}
