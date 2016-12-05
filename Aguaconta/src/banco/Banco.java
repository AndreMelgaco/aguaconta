package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {

	/**
	 * M�todo para abrir conex�o com o banco de dados
	 */
	public static Connection objCon;

	public static void abrirConexao() throws SQLException {
		DriverManager
				.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		objCon = DriverManager.getConnection(
				"jdbc:sqlserver://localhost:1433;databaseName=aguaconta", "sa",
				"Unique18");
	}

	// Fim do m�todo de abertura de conex�o

	/**
	 * M�todo para fechar conex�o com Banco de Dados
	 */
	public static void finalizaConexao() throws SQLException {
		objCon.close();
	}

	// Fim do m�todo de fechamento de conex�o com o banco
	
	
	
}
