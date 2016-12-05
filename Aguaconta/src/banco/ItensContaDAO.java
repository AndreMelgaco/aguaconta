package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Conta;
import dados.EndInst;

public class ItensContaDAO {

	// Consultando Endereço de Instalação pelo codigo da instalação
	public static List<Conta> listarContas(int codEndInst) throws Exception, SQLException {
		List<Conta> contas = new ArrayList<Conta>();
		PreparedStatement objCons = Banco.objCon
				.prepareStatement("SELECT * FROM CONTA WHERE COD_END_INST = ? ORDER BY ANO DESC, MES DESC");
		objCons.setInt(1, codEndInst);
		ResultSet objResult = objCons.executeQuery();
		while (objResult.next()) {
			Conta conta = new Conta(objResult.getInt("ID_CONTA"), objResult.getInt("COD_END_INST"),
					objResult.getInt("COD_CLIENTE"), objResult.getString("HIDROMETRO"), objResult.getInt("MES"),
					objResult.getInt("ANO"), objResult.getDate("DT_LEITURA"), objResult.getDate("DT_VENCIMENTO"),
					objResult.getInt("LEITURA_ANTERIOR"), objResult.getInt("LEITURA_ATUAL"),
					objResult.getInt("CONSUMO"), objResult.getString("OBSERVACOES"), objResult.getBigDecimal("Total"));

			contas.add(conta);
		}
		return contas;
	}

	public static Conta consultaConta(int idConta) throws Exception, SQLException{
		PreparedStatement objCons = Banco.objCon.prepareStatement("SELECT * FROM CONTA WHERE ID_CONTA = ?");
		objCons.setInt(1, idConta);
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return new Conta(objResult.getInt("ID_CONTA"), objResult.getInt("COD_END_INST"),
					objResult.getInt("COD_CLIENTE"), objResult.getString("HIDROMETRO"), objResult.getInt("MES"),
					objResult.getInt("ANO"), objResult.getDate("DT_LEITURA"), objResult.getDate("DT_VENCIMENTO"),
					objResult.getInt("LEITURA_ANTERIOR"), objResult.getInt("LEITURA_ATUAL"),
					objResult.getInt("CONSUMO"), objResult.getString("OBSERVACOES"), objResult.getBigDecimal("Total"));

		}else{
			return new Conta(0, 0, 0, "", 0, 0, null, null, 0, 0, 0, "", null);
		}
		
	}

	// OBS: DAQUI PRA BAIXO NÃO USEI NADA, SÓ COPIA DE ENDINSTDAO

	// Fim do método para consultar endereço de instalação ativa pelo numero do
	// hidrometro

	// Consultando Endereço de Instalação pelo codigo da instalação
	public static EndInst consultaEndInst(int codEndInst, int codCliente) throws Exception, SQLException {
		PreparedStatement objCons = Banco.objCon
				.prepareStatement("SELECT * FROM END_INSTALACAO WHERE COD_END_INST = ? AND COD_CLIENTE = ?");
		objCons.setInt(1, codEndInst);
		objCons.setInt(2, codCliente);
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return new EndInst(objResult.getInt("COD_END_INST"), objResult.getInt("COD_CLIENTE"),
					objResult.getString("ENDERECO"), objResult.getString("GLEBA"), objResult.getString("CHACARA"),
					objResult.getInt("COD_UNID_CONSUMO"), objResult.getString("NOME_UNID"),
					objResult.getString("CIDADE"), objResult.getString("UF"));
		} else
			throw new SQLException("Código da Instalação inexistente para o Cliente Informado.");
	}

	// Fim do método para consultar endereço de instalação ativa pelo numero do
	// hidrometro

	// Cadastrando Endereço de Instalação
	public static void incluirEndInst(EndInst objEndInst) throws SQLException {

		PreparedStatement objCons = Banco.objCon
				.prepareStatement("SELECT COD_CLIENTE FROM END_INSTALACAO WHERE COD_END_INST = ?");
		objCons.setInt(1, objEndInst.getCodEndInst());
		ResultSet resposta = objCons.executeQuery();
		if (resposta.next()) {
			throw new SQLException("Este Código de Instalação já está cadastrado para o cliente "
					+ resposta.getString("COD_CLIENTE") + ".");

		}

		PreparedStatement objInsert = Banco.objCon.prepareStatement(
				"INSERT INTO END_INSTALACAO (COD_END_INST, COD_CLIENTE, ENDERECO, GLEBA, CHACARA, COD_UNID_CONSUMO, NOME_UNID, CIDADE, UF) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

		objInsert.setInt(1, objEndInst.getCodEndInst());
		objInsert.setInt(2, objEndInst.getCodCliente());
		objInsert.setString(3, objEndInst.getEndereco());
		objInsert.setString(4, objEndInst.getGleba());
		objInsert.setString(5, objEndInst.getChacara());
		objInsert.setInt(6, objEndInst.getCodUnidadeConsumo());
		objInsert.setString(7, objEndInst.getUnidadeConsumo());
		objInsert.setString(8, objEndInst.getCidade());
		objInsert.setString(9, objEndInst.getUf());
		objInsert.executeUpdate();
	}

	// Fim do método para cadastrar novo endereço de instalação

	public static void alterarEndInst(EndInst objEndInst) throws SQLException {
		PreparedStatement objCons = Banco.objCon
				.prepareStatement("SELECT COD_CLIENTE FROM END_INSTALACAO WHERE COD_END_INST = ?");
		objCons.setInt(1, objEndInst.getCodEndInst());
		ResultSet resposta = objCons.executeQuery();
		if (resposta.next()) {
			int codCliente = resposta.getInt("COD_CLIENTE");
			if (objEndInst.getCodCliente() != codCliente) {
				throw new SQLException("Alteração Inválida. Código do Endereço de Instalação não pertence ao cliente.");
			}

		} else {
			throw new SQLException("Código do endereço de instalação não encontrado.");
		}

		PreparedStatement objUpdate = Banco.objCon.prepareStatement(
				"UPDATE END_INSTALACAO SET ENDERECO = ?, GLEBA = ?, CHACARA = ?, COD_UNID_CONSUMO = ?, NOME_UNID = ?, CIDADE = ?, UF = ? WHERE ID_END_INST = ? AND COD_CLIENTE = ?");
		objUpdate.setString(1, objEndInst.getEndereco());
		objUpdate.setString(2, objEndInst.getGleba());
		objUpdate.setString(3, objEndInst.getChacara());
		objUpdate.setInt(4, objEndInst.getCodUnidadeConsumo());
		objUpdate.setString(5, objEndInst.getUnidadeConsumo());
		objUpdate.setString(6, objEndInst.getCidade());
		objUpdate.setString(7, objEndInst.getUf());
		objUpdate.setInt(8, objEndInst.getCodEndInst());
		objUpdate.setInt(9, objEndInst.getCodCliente());

		objUpdate.executeUpdate();

	}

	public static EndInst consultaInstAnterior(int id, int codCliente) throws SQLException {
		PreparedStatement objCons = Banco.objCon.prepareStatement(
				"SELECT * FROM END_INSTALACAO WHERE COD_END_INST = (SELECT MAX(COD_END_INST) FROM END_INSTALACAO WHERE COD_END_INST < ? AND COD_CLIENTE = ?)");
		objCons.setInt(1, id);
		objCons.setInt(2, codCliente);
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return new EndInst(objResult.getInt("COD_END_INST"), objResult.getInt("COD_CLIENTE"),
					objResult.getString("ENDERECO"), objResult.getString("GLEBA"), objResult.getString("CHACARA"),
					objResult.getInt("COD_UNID_CONSUMO"), objResult.getString("NOME_UNID"),
					objResult.getString("CIDADE"), objResult.getString("UF"));
		} else
			throw new SQLException();
	}

	public static EndInst consultaInstPosterior(int id, int codCliente) throws SQLException {
		PreparedStatement objCons = Banco.objCon.prepareStatement(
				"SELECT * FROM END_INSTALACAO WHERE COD_END_INST = (SELECT MIN(COD_END_INST) FROM END_INSTALACAO WHERE COD_END_INST > ? AND COD_CLIENTE = ?)");
		objCons.setInt(1, id);
		objCons.setInt(2, codCliente);
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return new EndInst(objResult.getInt("COD_END_INST"), objResult.getInt("COD_CLIENTE"),
					objResult.getString("ENDERECO"), objResult.getString("GLEBA"), objResult.getString("CHACARA"),
					objResult.getInt("COD_UNID_CONSUMO"), objResult.getString("NOME_UNID"),
					objResult.getString("CIDADE"), objResult.getString("UF"));
		} else
			throw new SQLException();
	}

	public static EndInst consultaInstInicial(int id, int codCliente) throws SQLException {
		PreparedStatement objCons = Banco.objCon.prepareStatement(
				"SELECT * FROM END_INSTALACAO WHERE COD_END_INST = (SELECT MIN(COD_END_INST) FROM END_INSTALACAO WHERE COD_END_INST > ? AND COD_CLIENTE = ?)");
		objCons.setInt(1, id);
		objCons.setInt(2, codCliente);
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return new EndInst(objResult.getInt("COD_END_INST"), objResult.getInt("COD_CLIENTE"),
					objResult.getString("ENDERECO"), objResult.getString("GLEBA"), objResult.getString("CHACARA"),
					objResult.getInt("COD_UNID_CONSUMO"), objResult.getString("NOME_UNID"),
					objResult.getString("CIDADE"), objResult.getString("UF"));
		} else
			return new EndInst(0, 0, "", "", "", 0, "", "", "");
	}

	public static int novoEndInst() throws SQLException {
		PreparedStatement objCons = Banco.objCon
				.prepareStatement("select MAX(COD_END_INST) as MAXIMO from END_INSTALACAO");
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return objResult.getInt("MAXIMO");
		} else {
			throw new SQLException("");
		}

	}

}
