package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.EndInst;

public class EndInstDAO {

	// Consultando Endere�o de Instala��o pelo codigo da instala��o
	public static List<EndInst> listarEndInstCliente(int codCliente) throws Exception, SQLException {
		List<EndInst> endInst = new ArrayList<EndInst>();
		PreparedStatement objCons = Banco.objCon
				.prepareStatement("SELECT * FROM END_INSTALACAO WHERE COD_CLIENTE = ?");
		objCons.setInt(1, codCliente);
		ResultSet objResult = objCons.executeQuery();
		while (objResult.next()) {
			EndInst inst = new EndInst(objResult.getInt("COD_END_INST"), objResult.getInt("COD_CLIENTE"),
					objResult.getString("ENDERECO"), objResult.getString("GLEBA"), objResult.getString("CHACARA"),
					objResult.getInt("COD_UNID_CONSUMO"), objResult.getString("NOME_UNID"), objResult.getString("CIDADE"),
					objResult.getString("UF"));
			
			endInst.add(inst);
		} 
		return endInst;
	}

	// Fim do m�todo para consultar endere�o de instala��o ativa pelo numero do
	// hidrometro
	
	// Consultando Endere�o de Instala��o pelo codigo da instala��o
		public static EndInst consultaEndInst(int codEndInst) throws Exception, SQLException {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM END_INSTALACAO WHERE COD_END_INST = ?");
			objCons.setInt(1, codEndInst);
			ResultSet objResult = objCons.executeQuery();
			if (objResult.next()) {
				return new EndInst(objResult.getInt("COD_END_INST"), objResult.getInt("COD_CLIENTE"),
						objResult.getString("ENDERECO"), objResult.getString("GLEBA"), objResult.getString("CHACARA"),
						objResult.getInt("COD_UNID_CONSUMO"), objResult.getString("NOME_UNID"), objResult.getString("CIDADE"),
						objResult.getString("UF"));
			} else
				throw new SQLException("C�digo da Instala��o inexistente para o Cliente Informado.");
		}

		// Fim do m�todo para consultar endere�o de instala��o ativa pelo numero do
		// hidrometro

		// Cadastrando Endere�o de Instala��o
		public static void incluirEndInst(EndInst objEndInst) throws SQLException {

			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT COD_CLIENTE FROM END_INSTALACAO WHERE COD_END_INST = ?");
			objCons.setInt(1, objEndInst.getCodEndInst());
			ResultSet resposta = objCons.executeQuery();
			if (resposta.next()) {
				throw new SQLException("Este C�digo de Instala��o j� est� cadastrado para o cliente "
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

		// Fim do m�todo para cadastrar novo endere�o de instala��o

		public static void alterarEndInst(EndInst objEndInst) throws SQLException {
			PreparedStatement objCons = Banco.objCon.prepareStatement("SELECT COD_CLIENTE FROM END_INSTALACAO WHERE COD_END_INST = ?");
			objCons.setInt(1, objEndInst.getCodEndInst());
			ResultSet resposta = objCons.executeQuery();
			if (resposta.next()) {
				int codCliente = resposta.getInt("COD_CLIENTE");
				if (objEndInst.getCodCliente() != codCliente) {
					throw new SQLException("Altera��o Inv�lida. C�digo do Endere�o de Instala��o n�o pertence ao cliente.");
				}

			} else {
				throw new SQLException("C�digo do endere�o de instala��o n�o encontrado.");
			}

			PreparedStatement objUpdate = Banco.objCon.prepareStatement(
					"UPDATE END_INSTALACAO SET ENDERECO = ?, GLEBA = ?, CHACARA = ?, COD_UNID_CONSUMO = ?, NOME_UNID = ?, CIDADE = ?, UF = ? WHERE COD_END_INST = ?");
			objUpdate.setString(1, objEndInst.getEndereco());
			objUpdate.setString(2, objEndInst.getGleba());
			objUpdate.setString(3, objEndInst.getChacara());
			objUpdate.setInt(4, objEndInst.getCodUnidadeConsumo());
			objUpdate.setString(5, objEndInst.getUnidadeConsumo());
			objUpdate.setString(6, objEndInst.getCidade());
			objUpdate.setString(7, objEndInst.getUf());
			objUpdate.setInt(8, objEndInst.getCodEndInst());
			
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
						objResult.getInt("COD_UNID_CONSUMO"), objResult.getString("NOME_UNID"), objResult.getString("CIDADE"),
						objResult.getString("UF"));
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
						objResult.getInt("COD_UNID_CONSUMO"), objResult.getString("NOME_UNID"), objResult.getString("CIDADE"),
						objResult.getString("UF"));
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
						objResult.getInt("COD_UNID_CONSUMO"), objResult.getString("NOME_UNID"), objResult.getString("CIDADE"),
						objResult.getString("UF"));
			} else
				return new EndInst(0,0,"","","",0,"","","");
		}
		
		public static int novoEndInst() throws SQLException {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("select MAX(COD_END_INST) as MAXIMO from END_INSTALACAO");
			ResultSet objResult = objCons.executeQuery();
			if (objResult.next()) {
				return objResult.getInt("MAXIMO")+1;
			} else {
				throw new SQLException("");
			}

		}
	
}
