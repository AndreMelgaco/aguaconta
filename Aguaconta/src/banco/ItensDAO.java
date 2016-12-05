package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Itens;

public class ItensDAO {

	public static List<Itens> listarItens() throws SQLException {
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM ITENS");
			ResultSet resposta = objCons.executeQuery();
			List<Itens> itens = new ArrayList<Itens>();

			while (resposta.next()) {
				Itens item = new Itens(
						resposta.getInt("COD_ITEM"),
						resposta.getString("DESC_ITEM"));
				itens.add(item);
			}
			return itens;

		} catch (Exception e) {
			throw new SQLException("Erro");
		}

	}
	
	public static void incluirItens(Itens objItens)
			throws SQLException {

		PreparedStatement objInsert = Banco.objCon
				.prepareStatement("INSERT INTO ITENS (COD_ITEM, DESC_ITEM) VALUES (?, ?)");

		objInsert.setInt(1, objItens.getCoditem());
		objInsert.setString(2, objItens.getDescItem());
		objInsert.executeUpdate();
	}

	public static void atualizarItens(Itens objItens)
			throws SQLException {

		PreparedStatement objUpdate = Banco.objCon
				.prepareStatement("UPDATE ITENS SET DESC_ITEM = ? WHERE COD_ITEM = ?");

		objUpdate.setString(1, objItens.getDescItem());
		objUpdate.setInt(2, objItens.getCoditem());
		objUpdate.executeUpdate();
	}

	public static boolean buscaItens(int codigo) throws SQLException {
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM ITENS WHERE COD_ITEM = ?");
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

	public static Itens buscarItens(int codItem) throws Exception, SQLException {
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM ITENS WHERE COD_ITEM = ? ");
			objCons.setInt(1, codItem);
			ResultSet resposta = objCons.executeQuery();
			if (resposta.next()) {
				return new Itens(resposta.getInt("COD_ITEM"), resposta.getString("DESC_ITEM"));
			} else {
				return new Itens(codItem, "");
			}
		} catch (Exception e) {
			throw new SQLException("Erro no buscarHidrometro");
		}
	}
	
	public static int novoItem() throws SQLException {
		PreparedStatement objCons = Banco.objCon.prepareStatement("select MAX(COD_ITEM) as Maximo from ITENS");
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return objResult.getInt("Maximo")+1;
		} else {
			throw new SQLException("");
		}

	}
}
