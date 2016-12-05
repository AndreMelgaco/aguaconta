package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dados.EndCob;

public class EndCobDAO {

	
	// Consultando Endereço de Cobrança do Cliente
	public static EndCob consultaEndCobCliente(int codigo) throws Exception, SQLException {
		PreparedStatement objCons = Banco.objCon.prepareStatement("SELECT * FROM END_COBRANCA WHERE COD_END_INST = ?");
		objCons.setInt(1, codigo);
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return new EndCob(objResult.getInt("COD_END_COB"), objResult.getInt("COD_END_INST"), objResult.getString("ENDERECO"),
					objResult.getInt("NUMERO"), objResult.getString("COMPLEMENTO"), objResult.getString("BAIRRO"),
					objResult.getString("CIDADE"), objResult.getString("SIG_UF"), objResult.getString("CEP"));
		} else
			throw new SQLException("");
	}

	// Fim do método para consultar endereço de cobrança

	// Cadastrando Endereço de Cobrança
	public static void incluirEndCob(EndCob objEndCob) throws SQLException {

		PreparedStatement objInsert = Banco.objCon.prepareStatement(
				"INSERT INTO END_COBRANCA (COD_END_INST, ENDERECO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, SIG_UF, CEP) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

		objInsert.setInt(1, objEndCob.getcodEndInst());
		objInsert.setString(2, objEndCob.getEndereco());
		objInsert.setInt(3, objEndCob.getNumero());
		objInsert.setString(4, objEndCob.getComplemento());
		objInsert.setString(5, objEndCob.getBairro());
		objInsert.setString(6, objEndCob.getCidade());
		objInsert.setString(7, objEndCob.getEstado());
		objInsert.setString(8, objEndCob.getCEP());
		objInsert.executeUpdate();

	}

	// Fim do método para inserir um novo cliente

	// Alterando Endereço de Cobrança
	public static void alterarEndCob(EndCob objEndCob) throws SQLException {

		PreparedStatement objUpdate = Banco.objCon.prepareStatement(
				"UPDATE END_COBRANCA SET ENDERECO = ?, NUMERO = ?, COMPLEMENTO = ?, BAIRRO = ?, CIDADE = ?, SIG_UF = ?, CEP = ? WHERE COD_END_COB = ?");
		objUpdate.setString(1, objEndCob.getEndereco());
		objUpdate.setInt(2, objEndCob.getNumero());
		objUpdate.setString(3, objEndCob.getComplemento());
		objUpdate.setString(4, objEndCob.getBairro());
		objUpdate.setString(5, objEndCob.getCidade());
		objUpdate.setString(6, objEndCob.getEstado());
		objUpdate.setString(7, objEndCob.getCEP());
		objUpdate.setInt(8, objEndCob.getcodEndCob());
		objUpdate.executeUpdate();

	}

	// Fim do método para alterar o endereço de cobrança
}
