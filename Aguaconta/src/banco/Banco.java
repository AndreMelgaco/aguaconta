package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {

	/**
	 * Método para abrir conexão com o banco de dados
	 */
	public static Connection objCon;

	public static void abrirConexao() throws SQLException {
		DriverManager
				.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		objCon = DriverManager.getConnection(
				"jdbc:sqlserver://localhost:1433;databaseName=aguaconta", "sa",
				"Unique18");
	}

	// Fim do método de abertura de conexão

	/**
	 * Método para fechar conexão com Banco de Dados
	 */
	public static void finalizaConexao() throws SQLException {
		objCon.close();
	}

	// Fim do método de fechamento de conexão com o banco
	
	
	
}
