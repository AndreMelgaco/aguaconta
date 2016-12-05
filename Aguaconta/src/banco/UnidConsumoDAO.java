package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.UnidadeConsumo;

public class UnidConsumoDAO {

	public static List<UnidadeConsumo> listarUnidConsumo() throws SQLException {
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM UNID_CONSUMO");
			ResultSet resposta = objCons.executeQuery();
			List<UnidadeConsumo> unidConsumo = new ArrayList<UnidadeConsumo>();

			while (resposta.next()) {
				UnidadeConsumo uc = new UnidadeConsumo(
						resposta.getInt("COD_UNID_CONSUMO"),
						resposta.getString("NOME_UNID"),
						resposta.getString("CIDADE"), resposta.getString("UF"));
				unidConsumo.add(uc);
			}
			return unidConsumo;

		} catch (Exception e) {
			throw new SQLException("Erro");
		}

	}

	public static void IncluirUnidConsumo(UnidadeConsumo objUnidConsumo)
			throws SQLException {

		PreparedStatement objInsert = Banco.objCon
				.prepareStatement("INSERT INTO UNID_CONSUMO (COD_UNID_CONSUMO, NOME_UNID, CIDADE, UF) VALUES (?, ?, ?, ?)");

		objInsert.setInt(1, objUnidConsumo.getCodUnidConsumo());
		objInsert.setString(2, objUnidConsumo.getNomeUnid());
		objInsert.setString(3, objUnidConsumo.getCidade());
		objInsert.setString(4, objUnidConsumo.getUF());
		objInsert.executeUpdate();
	}

	public static boolean buscaCodUnidConsumo(int codigo) throws SQLException {
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM UNID_CONSUMO WHERE COD_UNID_CONSUMO = ?");
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

	public static UnidadeConsumo buscarUnidadeConsumo(int codUnid) throws Exception, SQLException {
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM UNID_CONSUMO WHERE COD_UNID_CONSUMO = ? ");
			objCons.setInt(1, codUnid);
			ResultSet resposta = objCons.executeQuery();
			if (resposta.next()) {
				return new UnidadeConsumo(resposta.getInt("COD_UNID_CONSUMO"), resposta.getString("NOME_UNID"),
						resposta.getString("CIDADE"), resposta.getString("UF"));
			} else {
				throw new Exception("Unidade de Consumo não encontrada.");
			}
		} catch (Exception e) {
			throw new SQLException("Erro no buscarUnidadeConsumo");
		}
	}
}
