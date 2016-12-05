package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dados.Conta;

public class BancoConta {
	
	public static boolean contaExiste(int codigo, String hidrometro, int mes,
			int ano) throws Exception, SQLException {
		PreparedStatement objCons = Banco.objCon
				.prepareStatement("SELECT * FROM CONTA WHERE COD_CLIENTE = ? AND HIDROMETRO = ? AND MES = ? AND ANO = ?");
		objCons.setInt(1, codigo);
		objCons.setString(2, hidrometro);
		objCons.setInt(3, mes);
		objCons.setInt(4, ano);
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return true;
		} else {
			return false;
		}
	}

	public static void cadastrarConta(Conta objConta) throws Exception,
			SQLException {
		try {
			PreparedStatement objInsert = Banco.objCon
					.prepareStatement("INSERT INTO CONTA (COD_CLIENTE, HIDROMETRO, MES, ANO, DT_LEITURA, DT_VENCIMENTO, LEITURA_ANTERIOR, LEITURA_ATUAL, CONSUMO, VALOR, OUTROS_VALORES, DESCRICAO, TOTAL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			objInsert.setInt(1, objConta.getCodCliente());
			objInsert.setString(2, objConta.getHidrometro());
			objInsert.setInt(3, objConta.getMes());
			objInsert.setInt(4, objConta.getAno());
			objInsert.setString(5, objConta.getDtLeitura());
			objInsert.setString(6, objConta.getDtVencimento());
			objInsert.setInt(7, objConta.getLeituraAnterior());
			objInsert.setInt(8, objConta.getLeituraAtual());
			objInsert.setInt(9, objConta.getConsumo());
			objInsert.setFloat(10, objConta.getValor());
			objInsert.setFloat(11, objConta.getOutrosValores());
			objInsert.setString(12, objConta.getDescricao());
			objInsert.setFloat(13, objConta.getTotal());
			objInsert.executeUpdate();
		} catch (Exception e) {
			throw new SQLException(e + "algo estranho");
		}
		
		

	}

	public static void atualizarConta(Conta objConta) throws Exception,
			SQLException {
		PreparedStatement objUpdate = Banco.objCon
				.prepareStatement("UPDATE CONTA SET COD_CLIENTE = ?, HIDROMETRO = ?, MES = ?, ANO = ?, DT_LEITURA = ?, DT_VENCIMENTO = ?, LEITURA_ANTERIOR = ?, LEITURA_ATUAL = ?, CONSUMO = ?, VALOR = ?, OUTROS_VALORES = ?, DESCRICAO = ?, TOTAL = ?");

		objUpdate.setInt(1, objConta.getCodCliente());
		objUpdate.setString(2, objConta.getHidrometro());
		objUpdate.setInt(3, objConta.getMes());
		objUpdate.setInt(4, objConta.getAno());
		objUpdate.setString(5, objConta.getDtLeitura());
		objUpdate.setString(6, objConta.getDtVencimento());
		objUpdate.setInt(7, objConta.getLeituraAnterior());
		objUpdate.setInt(8, objConta.getLeituraAtual());
		objUpdate.setInt(9, objConta.getConsumo());
		objUpdate.setFloat(10, objConta.getValor());
		objUpdate.setFloat(11, objConta.getOutrosValores());
		objUpdate.setString(12, objConta.getDescricao());
		objUpdate.setFloat(13, objConta.getTotal());
		objUpdate.executeUpdate();
	}

	public static Conta contaAtual(int codigo, String hidrometro, int mes,
			int ano) throws Exception, SQLException {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM CONTA WHERE COD_CLIENTE = ? AND HIDROMETRO = ? AND MES = ? AND ANO = ?");
			objCons.setInt(1, codigo);
			objCons.setString(2, hidrometro);
			objCons.setInt(3, mes);
			objCons.setInt(4, ano);
			ResultSet objResult = objCons.executeQuery();
			if (objResult.next()) {
				return new Conta(objResult.getInt("COD_CLIENTE"),
						objResult.getString("HIDROMETRO"),
						objResult.getInt("MES"),
						objResult.getInt("ANO"),
						objResult.getString("DT_LEITURA"),
						objResult.getString("DT_VENCIMENTO"),
						objResult.getInt("LEITURA_ANTERIOR"),
						objResult.getInt("LEITURA_ATUAL"),
						objResult.getInt("CONSUMO"),
						objResult.getFloat("VALOR"),
						objResult.getFloat("OUTROS_VALORES"),
						objResult.getString("DESCRICAO"),
						objResult.getFloat("TOTAL"));
			}else {
				throw new SQLException();
			}

	}
	

	public static Conta contaAnterior(int codigo, String hidrometro) throws Exception, SQLException {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT TOP 1 * FROM CONTA WHERE COD_CLIENTE = ? AND HIDROMETRO = ? ORDER BY ANO DESC, MES DESC");
			objCons.setInt(1, codigo);
			objCons.setString(2, hidrometro);
			ResultSet objResult = objCons.executeQuery();
			if (objResult.next()) {
				return new Conta(objResult.getInt("COD_CLIENTE"),
						objResult.getString("HIDROMETRO"),
						objResult.getInt("MES"),
						objResult.getInt("ANO"),
						objResult.getString("DT_LEITURA"),
						objResult.getString("DT_VENCIMENTO"),
						objResult.getInt("LEITURA_ANTERIOR"),
						objResult.getInt("LEITURA_ATUAL"),
						objResult.getInt("CONSUMO"),
						objResult.getFloat("VALOR"),
						objResult.getFloat("OUTROS_VALORES"),
						objResult.getString("DESCRICAO"),
						objResult.getFloat("TOTAL"));
			}else {
				return new Conta(0, "0", 0, 0, "0", "0", 0, 0, 0, 0, 0, "0", 0);
			}

	}
	

}
