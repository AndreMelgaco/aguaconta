package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.OutrosItens;

public class OutrosItensDAO {

	public static List<OutrosItens> listarOutrosItens() throws SQLException {
		try {
			PreparedStatement objCons = Banco.objCon.prepareStatement("SELECT * FROM ITENS");
			ResultSet resposta = objCons.executeQuery();
			List<OutrosItens> itens = new ArrayList<OutrosItens>();

			while (resposta.next()) {
				OutrosItens item = new OutrosItens(resposta.getInt("COD_OUTROS"), resposta.getInt("COD_END_INST"), resposta.getInt("COD_ITEM"),
						resposta.getString("DESC_ITEM"), resposta.getInt("PARCELA_ATUAL"),
						resposta.getInt("PARCELA_FINAL"), resposta.getBigDecimal("VALOR"), resposta.getString("OBSERVACOES"));
				itens.add(item);
			}
			return itens;

		} catch (Exception e) {
			throw new SQLException("Erro");
		}

	}

	public static void incluirOutrosItens(OutrosItens objItens) throws SQLException {

		PreparedStatement objInsert = Banco.objCon.prepareStatement(
				"INSERT INTO OUTROS_ITENS (COD_END_INST, COD_ITEM, DESC_ITEM, PARCELA_ATUAL, PARCELA_FINAL, VALOR, OBSERVACOES) VALUES (?, ?, ?, ?, ?, ?, ?)");

		objInsert.setInt(1, objItens.getCodEndInst());
		objInsert.setInt(2, objItens.getCodItem());
		objInsert.setString(3, objItens.getDescItem());
		objInsert.setInt(4, objItens.getParcelaAtual());
		objInsert.setInt(5, objItens.getParcelaFinal());
		objInsert.setBigDecimal(6, objItens.getValor());
		objInsert.setString(7, objItens.getObservacoes());
		objInsert.executeUpdate();
	}

	public static void atualizarOutrosItens(OutrosItens objItens) throws SQLException {

		PreparedStatement objUpdate = Banco.objCon
				.prepareStatement("UPDATE OUTROS_ITENS SET PARCELA_ATUAL = ?, PARCELA_FINAL = ?, VALOR = ?, OBSERVACOES = ? WHERE COD_OUTROS = ?");

		objUpdate.setInt(1, objItens.getParcelaAtual());
		objUpdate.setInt(2, objItens.getParcelaFinal());
		objUpdate.setBigDecimal(3, objItens.getValor());
		objUpdate.setString(4, objItens.getObservacoes());
		objUpdate.setInt(5, objItens.getCodigo());
		objUpdate.executeUpdate();
	}
	
	public static void deletarOutrosItens(OutrosItens objItens) throws SQLException {

		PreparedStatement objDelete = Banco.objCon
				.prepareStatement("DELETE FROM OUTROS_ITENS WHERE COD_OUTROS = ?");

		objDelete.setInt(1, objItens.getCodigo());
		objDelete.executeUpdate();
	}

	public static boolean buscaItens(int codigo) throws SQLException {
		try {
			PreparedStatement objCons = Banco.objCon.prepareStatement("SELECT * FROM OUTROS_ITENS WHERE COD_OUTROS = ?");
			objCons.setInt(1, codigo);
			ResultSet resposta = objCons.executeQuery();

			if (resposta.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

	}

	public static OutrosItens buscarOutrosItens(int codOutros) throws Exception, SQLException {
		try {
			PreparedStatement objCons = Banco.objCon.prepareStatement("SELECT * FROM OUTROS_ITENS WHERE COD_OUTROS = ? ");
			objCons.setInt(1, codOutros);
			ResultSet resposta = objCons.executeQuery();
			if (resposta.next()) {
				return new OutrosItens(resposta.getInt("COD_OUTROS"), resposta.getInt("COD_END_INST"), resposta.getInt("COD_ITEM"),
						resposta.getString("DESC_ITEM"), resposta.getInt("PARCELA_ATUAL"),
						resposta.getInt("PARCELA_FINAL"), resposta.getBigDecimal("VALOR"), resposta.getString("OBSERVACOES"));
			} else {
				return new OutrosItens(0, 0, 0, "", 0, 0, null, "");
			}
		} catch (Exception e) {
			throw new SQLException("Erro no buscarOutrosItens");
		}
	}

	public static int novoOutrosItens() throws SQLException {
		PreparedStatement objCons = Banco.objCon.prepareStatement("select MAX(COD_ITEM) as Maximo from ITENS");
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return objResult.getInt("Maximo") + 1;
		} else {
			throw new SQLException("");
		}

	}
}
