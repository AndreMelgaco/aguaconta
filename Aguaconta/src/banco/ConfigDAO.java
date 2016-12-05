package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import dados.ConfigGeral;
import dados.ConfigValores;

public class ConfigDAO {

	public static ConfigGeral retornaConfigAtual(int codigo) throws Exception {
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM CONFIG_GERAL WHERE COD_UNID_CONSUMO = ?");
			objCons.setInt(1, codigo);
			ResultSet objResult = objCons.executeQuery();
			if (objResult.next()) {
				return new ConfigGeral(objResult.getInt("COD_UNID_CONSUMO"), objResult.getDate("DATA_VENCIMENTO"),
						objResult.getDate("DATA_LEITURA"), objResult.getInt("MES_CONTA"), objResult.getInt("ANO_CONTA"),
						objResult.getFloat("JUROS"), objResult.getFloat("MULTA"), objResult.getString("TEXTO_CONTA"));

			} else {
				Date dt = new Date(0);
				float f = 0;
				return new ConfigGeral(0, dt, dt, 0, 0, f, f, "A");
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static ConfigGeral retornaConfig(int codigo) throws Exception {
		try {
			PreparedStatement objCons = Banco.objCon.prepareStatement(
					"SELECT * FROM CONFIG_GERAL WHERE COD_UNID_CONSUMO = ?");
			objCons.setInt(1, codigo);
			ResultSet objResult = objCons.executeQuery();
			if (objResult.next()) {
				return new ConfigGeral(objResult.getInt("COD_UNID_CONSUMO"), objResult.getDate("DATA_VENCIMENTO"),
						objResult.getDate("DATA_LEITURA"), objResult.getInt("MES_CONTA"), objResult.getInt("ANO_CONTA"),
						objResult.getFloat("JUROS"), objResult.getFloat("MULTA"), objResult.getString("TEXTO_CONTA"));

			} 
			return null;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static ConfigGeral retornaConfigOld(int codigo, int mes, int ano) throws Exception {
		try {
			PreparedStatement objCons = Banco.objCon.prepareStatement(
					"SELECT * FROM CONFIG_GERAL WHERE COD_UNID_CONSUMO = ? AND MES_CONTA = ? AND ANO_CONTA = ?");
			objCons.setInt(1, codigo);
			objCons.setInt(2, mes);
			objCons.setInt(3, ano);
			ResultSet objResult = objCons.executeQuery();
			if (objResult.next()) {
				return new ConfigGeral(objResult.getInt("COD_UNID_CONSUMO"), objResult.getDate("DATA_VENCIMENTO"),
						objResult.getDate("DATA_LEITURA"), objResult.getInt("MES_CONTA"), objResult.getInt("ANO_CONTA"),
						objResult.getFloat("JUROS"), objResult.getFloat("MULTA"), objResult.getString("TEXTO_CONTA"));

			}  else {
				PreparedStatement objCons2 = Banco.objCon.prepareStatement(
						"SELECT * FROM CONFIG_GERAL_OLD WHERE COD_UNID_CONSUMO = ? AND MES_CONTA = ? AND ANO_CONTA = ?");
				objCons2.setInt(1, codigo);
				objCons2.setInt(2, mes);
				objCons2.setInt(3, ano);
				ResultSet objResult2 = objCons2.executeQuery();
				if (objResult2.next()) {
					return new ConfigGeral(objResult2.getInt("COD_UNID_CONSUMO"), objResult2.getDate("DATA_VENCIMENTO"),
							objResult2.getDate("DATA_LEITURA"), objResult2.getInt("MES_CONTA"),
							objResult2.getInt("ANO_CONTA"), objResult2.getFloat("JUROS"), objResult2.getFloat("MULTA"),
							objResult2.getString("TEXTO_CONTA"));
				} else {
					Date dt = new Date(0);
					float f = 0;
					return new ConfigGeral(0, dt, dt, 0, 0, f, f, "A");
				}
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static void atualizaConfig(ConfigGeral cg) throws Exception, SQLException {
		try {
			PreparedStatement objCons = Banco.objCon.prepareStatement(
					"SELECT * FROM CONFIG_GERAL WHERE COD_UNID_CONSUMO = ? AND MES_CONTA = ? AND ANO_CONTA = ?");
			objCons.setInt(1, cg.getCodUnidadeConsumo());
			objCons.setInt(2, cg.getMesConta());
			objCons.setInt(3, cg.getAnoConta());
			ResultSet objResult = objCons.executeQuery();
			if (objResult.next()) {
				atualizaConfigAtual(cg);
			} else {
				PreparedStatement objCons2 = Banco.objCon.prepareStatement(
						"SELECT * FROM CONFIG_GERAL WHERE COD_UNID_CONSUMO = ? AND MES_CONTA = ? AND ANO_CONTA = ?");
				objCons2.setInt(1, cg.getCodUnidadeConsumo());
				objCons2.setInt(2, cg.getMesConta());
				objCons2.setInt(3, cg.getAnoConta());
				ResultSet objResult2 = objCons2.executeQuery();
				if (objResult2.next()) {
					atualizaConfigOld(cg);
				} else {
					novaConfig(cg);
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static void atualizaConfigAtual(ConfigGeral cg) throws Exception {
		try {
			PreparedStatement objUpdate = Banco.objCon.prepareStatement(
					"UPDATE CONFIG_GERAL SET DATA_VENCIMENTO = ?, DATA_LEITURA = ?, MES_CONTA = ?, ANO_CONTA = ?, JUROS = ?, MULTA = ?, TEXTO_CONTA = ? WHERE COD_UNID_CONSUMO = ?");

			objUpdate.setDate(1, cg.getDataVencimento());
			objUpdate.setDate(2, cg.getDataLeitura());
			objUpdate.setInt(3, cg.getMesConta());
			objUpdate.setInt(4, cg.getAnoConta());
			objUpdate.setFloat(5, cg.getJuros());
			objUpdate.setFloat(6, cg.getMulta());
			objUpdate.setString(7, cg.getTexto_conta());
			objUpdate.setInt(8, cg.getCodUnidadeConsumo());
			objUpdate.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static void atualizaConfigOld(ConfigGeral cg) throws Exception {
		try {
			PreparedStatement objUpdate = Banco.objCon.prepareStatement(
					"UPDATE CONFIG_GERAL_OLD SET DIA_VENCIMENTO = ?, MES_VENCIMENTO = ?, ANO_VENCIMENTO = ?, DIA_LEITURA = ?, MES_LEITURA = ?, ANO_LEITURA = ?, JUROS = ?, MULTA = ?, TEXTO_CONTA = ? WHERE COD_UNID_CONSUMO = ?");

			objUpdate.setDate(1, cg.getDataVencimento());
			objUpdate.setDate(2, cg.getDataLeitura());
			objUpdate.setInt(3, cg.getMesConta());
			objUpdate.setInt(4, cg.getAnoConta());
			objUpdate.setFloat(5, cg.getJuros());
			objUpdate.setFloat(6, cg.getMulta());
			objUpdate.setString(7, cg.getTexto_conta());
			objUpdate.setInt(8, cg.getCodUnidadeConsumo());
			objUpdate.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static void novaConfig(ConfigGeral cg) throws Exception {
		try {
			PreparedStatement objInsert = Banco.objCon.prepareStatement(
					"INSERT INTO CONFIG_GERAL (COD_UNID_CONSUMO, DATA_VENCIMENTO, DATA_LEITURA, MES_CONTA, ANO_CONTA, JUROS, MULTA, TEXTO_CONTA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

			objInsert.setInt(1, cg.getCodUnidadeConsumo());
			objInsert.setDate(2, cg.getDataVencimento());
			objInsert.setDate(3, cg.getDataLeitura());
			objInsert.setInt(4, cg.getMesConta());
			objInsert.setInt(5, cg.getAnoConta());
			objInsert.setFloat(6, cg.getJuros());
			objInsert.setFloat(7, cg.getMulta());
			objInsert.setString(8, cg.getTexto_conta());
			objInsert.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static ArrayList<String> buscaUnidConsumo() throws Exception {
		try {
			PreparedStatement objCons = Banco.objCon.prepareStatement("SELECT * FROM UNID_CONSUMO");
			ResultSet objResult = objCons.executeQuery();
			ArrayList<String> resultado = new ArrayList<String>();

			while (objResult.next()) {
				String item = objResult.getString("NOME_UNID");
				resultado.add(item);
			}

			return resultado;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public static boolean configUnidConsumo(int codigo) throws Exception {
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM CONFIG_GERAL WHERE COD_UNID_CONSUMO = ?");
			objCons.setInt(1, codigo);
			ResultSet objResult = objCons.executeQuery();
			if (objResult.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static ConfigValores retornaFaixa(int codigo, int faixa) throws Exception {
		try {
			ConfigValores cv = new ConfigValores(0, 0, 0, 0, null, null);
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM CONFIG_VALORES WHERE COD_UNID_CONSUMO = ? AND FAIXA = ?");
			objCons.setInt(1, codigo);
			objCons.setInt(2, faixa);
			ResultSet objResult = objCons.executeQuery();

			if (objResult.next()) {
				cv.setCodUnidadeConsumo(objResult.getInt("COD_UNID_CONSUMO"));
				cv.setMesConta(objResult.getInt("MES_CONTA"));
				cv.setAnoConta(objResult.getInt("ANO_CONTA"));
				cv.setFaixas(objResult.getInt("FAIXA"));
				cv.setValorUnico(objResult.getBigDecimal("VALOR_UNICO"));
				cv.setValorUnit(objResult.getBigDecimal("VALOR_UNIT"));

			}
			return cv;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static List<ConfigValores> listarFaixas(int codigo) throws Exception, SQLException {
		List<ConfigValores> listacv = new ArrayList<ConfigValores>();
		PreparedStatement objCons = Banco.objCon
				.prepareStatement("SELECT * FROM CONFIG_VALORES WHERE COD_UNID_CONSUMO = ? ORDER BY FAIXA");
		objCons.setInt(1, codigo);
		ResultSet objResult = objCons.executeQuery();
		while (objResult.next()) {
			ConfigValores cv = new ConfigValores(objResult.getInt("COD_UNID_CONSUMO"), objResult.getInt("MES_CONTA"),
					objResult.getInt("ANO_CONTA"), objResult.getInt("FAIXA"), objResult.getBigDecimal("VALOR_UNICO"),
					objResult.getBigDecimal("VALOR_UNIT"));

			listacv.add(cv);
		}
		return listacv;
	}

	public static void atualizaFaixas(ConfigValores cv) throws Exception {
		System.out.println(cv.getCodUnidadeConsumo() + " " + cv.getFaixas() );
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM CONFIG_VALORES WHERE COD_UNID_CONSUMO = ? AND FAIXA = ?");
			objCons.setInt(1, cv.getCodUnidadeConsumo());
			objCons.setInt(2, cv.getFaixas());
			ResultSet objResult = objCons.executeQuery();

			if (objResult.next()) {
				PreparedStatement objUpdate = Banco.objCon.prepareStatement(
						"UPDATE CONFIG_VALORES SET VALOR_UNICO = ?, VALOR_UNIT = ? WHERE COD_UNID_CONSUMO = ? AND FAIXA = ?");
				objUpdate.setBigDecimal(1, cv.getValorUnico());
				objUpdate.setBigDecimal(2, cv.getValorUnit());
				objUpdate.setInt(3, cv.getCodUnidadeConsumo());
				objUpdate.setInt(4, cv.getFaixas());
				objUpdate.executeUpdate();
			} else {
				PreparedStatement objInsert = Banco.objCon.prepareStatement(
						"INSERT INTO CONFIG_VALORES (COD_UNID_CONSUMO, MES_CONTA, ANO_CONTA, FAIXA, VALOR_UNICO, VALOR_UNIT) VALUES (?, ?, ?, ?, ?, ?)");
				objInsert.setInt(1, cv.getCodUnidadeConsumo());
				objInsert.setInt(2, cv.getMesConta());
				objInsert.setInt(3, cv.getAnoConta());
				objInsert.setInt(4, cv.getFaixas());
				objInsert.setBigDecimal(5, cv.getValorUnico());
				objInsert.setBigDecimal(6, cv.getValorUnit());
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
