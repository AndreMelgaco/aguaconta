package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Hidrometros;

public class HidrometrosDAO {

	public static List<Hidrometros> listarHidrometros() throws SQLException {
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM HIDROMETROS");
			ResultSet resposta = objCons.executeQuery();
			List<Hidrometros> hidrometros = new ArrayList<Hidrometros>();

			while (resposta.next()) {
				Hidrometros hidro = new Hidrometros(
						resposta.getString("COD_HIDROMETRO"),
						resposta.getString("SITUACAO"),
						resposta.getString("OBSERVACOES"));
				hidrometros.add(hidro);
			}
			return hidrometros;

		} catch (Exception e) {
			throw new SQLException("Erro");
		}

	}
	
	public static void incluirHidrometro(Hidrometros objHidrometros)
			throws SQLException {

		PreparedStatement objInsert = Banco.objCon
				.prepareStatement("INSERT INTO HIDROMETROS (COD_HIDROMETRO, SITUACAO, OBSERVACOES) VALUES (?, ?, ?)");

		objInsert.setString(1, objHidrometros.getCodHidrometro());
		objInsert.setString(2, objHidrometros.getSituacao());
		objInsert.setString(3, objHidrometros.getObservacao());
		objInsert.executeUpdate();
	}

	public static void atualizarHidrometro(Hidrometros objHidrometros)
			throws SQLException {

		PreparedStatement objUpdate = Banco.objCon
				.prepareStatement("UPDATE HIDROMETROS SET SITUACAO = ?, OBSERVACOES = ? WHERE COD_HIDROMETRO = ?");

		objUpdate.setString(1, objHidrometros.getSituacao());
		objUpdate.setString(2, objHidrometros.getObservacao());
		objUpdate.setString(3, objHidrometros.getCodHidrometro());
		objUpdate.executeUpdate();
	}
	
	public static void atualizarSituacao(Hidrometros objHidrometros)
			throws SQLException {

		PreparedStatement objUpdate = Banco.objCon
				.prepareStatement("UPDATE HIDROMETROS SET SITUACAO = ? WHERE COD_HIDROMETRO = ?");

		objUpdate.setString(1, objHidrometros.getSituacao());
		objUpdate.setString(2, objHidrometros.getCodHidrometro());
		objUpdate.executeUpdate();
	}

	public static boolean buscaCodHidrometro(int codigo) throws SQLException {
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM HIDROMETROS WHERE COD_HIDROMETROS = ?");
			objCons.setInt(1, codigo);
			ResultSet resposta = objCons.executeQuery();
			
			if(resposta.next()){
				return true;
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

	}

	public static Hidrometros buscarHidrometro(String codHidrometro) throws Exception, SQLException {
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM HIDROMETROS WHERE COD_HIDROMETRO = ? ");
			objCons.setString(1, codHidrometro);
			ResultSet resposta = objCons.executeQuery();
			if (resposta.next()) {
				return new Hidrometros(resposta.getString("COD_HIDROMETRO"), resposta.getString("SITUACAO"),
						resposta.getString("OBSERVACOES"));
			} else {
				return new Hidrometros("", "", "");
			}
		} catch (Exception e) {
			throw new SQLException("Erro no buscarHidrometro");
		}
	}
}
