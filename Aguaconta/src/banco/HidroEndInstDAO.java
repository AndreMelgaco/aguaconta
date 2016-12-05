package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.HidroEndInst;

public class HidroEndInstDAO {

	public static List<HidroEndInst> listarHidro_EndInst() throws SQLException {
		try {
			PreparedStatement objCons = Banco.objCon.prepareStatement("SELECT * FROM HIDRO_ENDINST");
			ResultSet resposta = objCons.executeQuery();
			List<HidroEndInst> hidrometros = new ArrayList<HidroEndInst>();

			while (resposta.next()) {
				HidroEndInst hidro = new HidroEndInst(resposta.getInt("COD_END_INST"), 
						resposta.getString("COD_HIDROMETRO"), resposta.getDate("DT_ATIVACAO"),
						resposta.getDate("DT_DESATIVACAO"), resposta.getString("OBSERVACOES"));
				hidrometros.add(hidro);
			}
			return hidrometros;

		} catch (Exception e) {
			throw new SQLException("Erro");
		}

	}

	public static void incluirHidro_EndInst(HidroEndInst objHidroEndInst) throws SQLException {

		PreparedStatement objInsert = Banco.objCon.prepareStatement(
				"INSERT INTO HIDRO_ENDINST (COD_HIDROMETRO, COD_END_INST, DT_ATIVACAO, DT_DESATIVACAO, OBSERVACOES) VALUES (?, ?, ?, ?, ?)");

		objInsert.setString(1, objHidroEndInst.getCodHidrometro());
		objInsert.setInt(2, objHidroEndInst.getcodEndInst());
		objInsert.setDate(3, objHidroEndInst.getDtAtivacao());
		objInsert.setDate(4, objHidroEndInst.getDtDesativacao());
		objInsert.setString(5, objHidroEndInst.getObservacao());
		objInsert.executeUpdate();
	}

	public static void atualizarHidro_EndInst(HidroEndInst objHidroEndInst) throws SQLException {

		PreparedStatement objUpdate = Banco.objCon.prepareStatement(
				"UPDATE HIDRO_ENDINST SET DT_ATIVACAO = ?, DT_DESATIVACAO = ?, OBSERVACOES = ? WHERE COD_END_INST = ? AND COD_HIDROMETRO = ?");

		objUpdate.setDate(1, objHidroEndInst.getDtAtivacao());
		objUpdate.setDate(2, objHidroEndInst.getDtDesativacao());
		objUpdate.setString(3, objHidroEndInst.getObservacao());
		objUpdate.setInt(4, objHidroEndInst.getcodEndInst());
		objUpdate.setString(5, objHidroEndInst.getCodHidrometro());
		objUpdate.executeUpdate();
	}

	public static boolean consultaHidro_EndInst(int codEndInst) throws Exception, SQLException {
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM HIDRO_ENDINST WHERE COD_END_INST = ?  AND DT_DESATIVACAO != ?");
			objCons.setInt(1, codEndInst);
			objCons.setString(2, " ");
			ResultSet resposta = objCons.executeQuery();
			if (resposta.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new SQLException("Já existe hidrometro ativo para a esta intalação.");
		}
	}
	
	public static HidroEndInst buscarHidro_EndInst(int codEndInst, String codHidrometro) throws Exception, SQLException {
		try {
			PreparedStatement objCons = Banco.objCon
					.prepareStatement("SELECT * FROM HIDRO_ENDINST WHERE COD_END_INST = ?  AND COD_HIDROMETRO = ?");
			objCons.setInt(1, codEndInst);
			objCons.setString(2, codHidrometro);
			ResultSet resposta = objCons.executeQuery();
			if (resposta.next()) {
				return new HidroEndInst(resposta.getInt("COD_END_INST"), resposta.getString("COD_HIDROMETRO"), 
						resposta.getDate("DT_ATIVACAO"), resposta.getDate("DT_DESATIVACAO"),
						resposta.getString("OBSERVACOES"));
			} else {
				return new HidroEndInst(codEndInst, codHidrometro, null, null, "");
			}
		} catch (SQLException e) {
			throw new SQLException("Erro no buscarHidrometro");
		}
	}
}
