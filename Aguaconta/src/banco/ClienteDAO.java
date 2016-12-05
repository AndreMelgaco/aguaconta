package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dados.Cliente;

public class ClienteDAO {
	
	// M�todo para inserir novo cliente
	public static void incluirCliente(Cliente objCliente) throws SQLException {

		PreparedStatement objCons = Banco.objCon.prepareStatement("SELECT * FROM CLIENTE WHERE COD_CLIENTE = ?");
		objCons.setInt(1, objCliente.getCodCliente());
		ResultSet resposta = objCons.executeQuery();
		if (resposta.next()) {
			throw new SQLException("Este c�digo est� cadastrado para o cliente " + resposta.getString("NOME"));
		}

		PreparedStatement objInsert = Banco.objCon.prepareStatement(
				"INSERT INTO CLIENTE (NOME, TIPO, CPF_CNPJ, IDENT, EMAIL, TEL_FIXO, TEL_CELULAR, TEL_RECADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

		objInsert.setString(1, objCliente.getNome());
		objInsert.setString(2, objCliente.getTipo());
		objInsert.setString(3, objCliente.getCpfCnpj());
		objInsert.setString(4, objCliente.getIdentidade());
		objInsert.setString(5, objCliente.getEmail());
		objInsert.setString(6, objCliente.getTel_fixo());
		objInsert.setString(7, objCliente.getCelular());
		objInsert.setString(8, objCliente.getTel_recado());
		objInsert.executeUpdate();

	}

	// Fim do m�todo para inserir um novo cliente

	// Alterando Cliente
	public static void alterarCliente(Cliente objCliente) throws SQLException {

		PreparedStatement objUpdate = Banco.objCon.prepareStatement(
				"UPDATE CLIENTE SET NOME = ?, TIPO = ?, IDENT = ?, EMAIL = ?, TEL_FIXO = ?, TEL_CELULAR = ?, TEL_RECADO = ?, CPF_CNPJ = ? WHERE COD_CLIENTE = ?");
		objUpdate.setString(1, objCliente.getNome());
		objUpdate.setString(2, objCliente.getTipo());
		objUpdate.setString(3, objCliente.getIdentidade());
		objUpdate.setString(4, objCliente.getEmail());
		objUpdate.setString(5, objCliente.getTel_fixo());
		objUpdate.setString(6, objCliente.getCelular());
		objUpdate.setString(7, objCliente.getTel_recado());
		objUpdate.setString(8, objCliente.getCpfCnpj());
		objUpdate.setInt(9, objCliente.getCodCliente());
		objUpdate.executeUpdate();

	}

	// Fim do m�todo para alterar dados do cliente

	// Consultando se o cliente est� no banco de dados (codigo - Retorno Cliente)
	public static Cliente consultaClienteCadastro(int codigo) throws Exception, SQLException {
		PreparedStatement objCons = Banco.objCon.prepareStatement("SELECT * FROM CLIENTE WHERE COD_CLIENTE = ?");
		objCons.setInt(1, codigo);
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return new Cliente(objResult.getInt("COD_CLIENTE"), objResult.getString("NOME"), objResult.getString("TIPO"),
					objResult.getString("CPF_CNPJ"), objResult.getString("IDENT"), objResult.getString("EMAIL"),
					objResult.getString("TEL_FIXO"), objResult.getString("TEL_CELULAR"),
					objResult.getString("TEL_RECADO"));
		} else
			throw new SQLException("N�o existe cliente cadastrado com este c�digo.");
	}

	// Fim do m�todo para verificar se cliente est� no banco
	
	// Consultando se o cliente est� no banco de dados (codigo - Retorno Lista de Cliente)
	public static List<Cliente> listarCliente(String cpf, String nome) throws Exception, SQLException {
		String busca, filtro;
		List<Cliente> cliente = new ArrayList<Cliente>();
		if(cpf.isEmpty() && nome.isEmpty()){
			busca = "SELECT * FROM CLIENTE";
			filtro = "";
		}else if (cpf.isEmpty()){
			busca = "SELECT * FROM CLIENTE WHERE NOME LIKE ?";
			filtro = nome;
		}else{
			busca = "SELECT * FROM CLIENTE WHERE CPF_CNPJ = ?";
			filtro = cpf;
		}
		PreparedStatement objCons = Banco.objCon.prepareStatement(busca);
		if(filtro != ""){
			objCons.setString(1, filtro);
		}
		ResultSet objResult = objCons.executeQuery();
		while(objResult.next()) {
			Cliente cli = new Cliente(objResult.getInt("COD_CLIENTE"), objResult.getString("NOME"), objResult.getString("TIPO"),
					objResult.getString("CPF_CNPJ"), objResult.getString("IDENT"), objResult.getString("EMAIL"),
					objResult.getString("TEL_FIXO"), objResult.getString("TEL_CELULAR"),
					objResult.getString("TEL_RECADO"));
			
			cliente.add(cli);
		}
		return cliente;
	}

	// Fim do m�todo para verificar se cliente est� no banco

	// Consultando se o cliente est� no banco de dados cpf - Retorno Cliente.
	public static Cliente consultaClienteCpf(String cpf) throws Exception, SQLException {
		PreparedStatement objCons = Banco.objCon.prepareStatement("SELECT * FROM CLIENTE WHERE CPF_CNPJ = ?");
		objCons.setString(1, cpf);
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return new Cliente(objResult.getInt("COD_CLIENTE"), objResult.getString("NOME"), objResult.getString("TIPO"),
					objResult.getString("CPF_CNPJ"), objResult.getString("IDENT"), objResult.getString("EMAIL"),
					objResult.getString("TEL_FIXO"), objResult.getString("TEL_CELULAR"),
					objResult.getString("TEL_RECADO"));
		} else
			throw new SQLException("N�o existe cliente cadastrado com este cpf.");
	}

	// Fim do m�todo para verificar se cliente est� no banco

	// Consultando se o cliente est� no banco de dados cpf - Retorno Cliente.
	public static Cliente consultaClienteCod(int codigo) throws Exception, SQLException {
		PreparedStatement objCons = Banco.objCon.prepareStatement("SELECT * FROM CLIENTE WHERE COD_CLIENTE = ?");
		objCons.setInt(1, codigo);
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return new Cliente(objResult.getInt("COD_CLIENTE"), objResult.getString("NOME"), objResult.getString("TIPO"),
					objResult.getString("CPF_CNPJ"), objResult.getString("IDENT"), objResult.getString("EMAIL"),
					objResult.getString("TEL_FIXO"), objResult.getString("TEL_CELULAR"),
					objResult.getString("TEL_RECADO"));
		} 
		return null;
	}

	// Fim do m�todo para verificar se cliente est� no banco
	
	
	// Consultando se o cliente est� no banco de dados - Retorno bool.
	public static boolean consultaClienteBool(int codigo) throws Exception, SQLException {
		PreparedStatement objCons = Banco.objCon.prepareStatement("SELECT * FROM CLIENTE WHERE COD_CLIENTE = ?");
		objCons.setInt(1, codigo);
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return true;
		} else
			throw new Exception("N�o existe cliente cadastrado com este c�digo.");
	}

	// Fim do m�todo para verificar se cliente est� no banco

	public static int novoCliente() throws SQLException {
		PreparedStatement objCons = Banco.objCon.prepareStatement("select MAX(COD_CLIENTE) as Maximo from CLIENTE");
		ResultSet objResult = objCons.executeQuery();
		if (objResult.next()) {
			return objResult.getInt("Maximo")+1;
		} else {
			throw new SQLException("");
		}

	}
}
