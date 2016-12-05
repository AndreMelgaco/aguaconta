package tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.Cliente;

public class ClienteTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/**
	 * Essa lista armazenará os objetos do tipo {@link Cliente} atualmente
	 * exibidos na tablela.
	 */
	private List<Cliente> cliente;

	// ==============================================================
	// Construtores.
	// ==============================================================

	/**
	 * Constutor que simplesmente instancia o nosso {@link List} que usaremos
	 * para guardar os valores.
	 */
	public ClienteTableModel() {
		// no construtor, instanciamos o List
		cliente = new ArrayList<Cliente>();
	}

	/**
	 * Criamos um construtor de conveniência para já popular a lista.
	 * 
	 * @param lista
	 *            a lista de UnidadeConsumo a ser adicionada.
	 */
	public ClienteTableModel(List<Cliente> listaCliente) {
		this();
		cliente.addAll(listaCliente);
	}

	// ==============================================================
	// Métodos implementados.
	// ==============================================================

	@Override
	public Class<?> getColumnClass(int coluna) {
		// todas as colunas representam uma String
		return String.class;
	}

	@Override
	public int getColumnCount() {
		// esse método deve retornar o número de colunas. No caso, 6 (uma para o cod, um para o nome, uma para o
		// tipo, uma para o cpf/cnpj e uma para o telefone fixo e um para o celular).
		return 6;
	}

	@Override
	public String getColumnName(int coluna) {
		// vamos retornar o nome de cada coluna
		switch (coluna) {
		case 0:
			return "Cod."; // o nome da primeira coluna
		case 1:
			return "Nome Cliente"; // o nome da segunda
		case 2:
			return "Tipo Cliente"; // e o da terceira
		case 3:
			return "CPF/CNPJ";
		case 4:
			return "Telefone";
		case 5:
			return "Celular";
		default:
			return ""; // isso nunca deve ocorrer, pois temos só 6 colunas
		}
	}

	@Override
	public int getRowCount() {
		// retorna o número de linhas, ou seja, a quantidade de entradas na
		// nossa lista.
		return cliente.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// vai retornar o valor de determinada célula.
		// primeiro vamos pegar o Cliente da linha
		Cliente cli = cliente.get(linha);
		// façamos um switch
		switch (coluna) {
		case 0:
			return cli.getCodCliente(); // retornamos o codigo
		case 1:
			return cli.getNome(); // retornamos o nome
		case 2:
			return cli.getTipo(); // retornamos o tipo
		case 3:
			return cli.getCpfCnpj(); //retorna o cpf/cpnj
		case 4:
			return cli.getTel_fixo();
		case 5:
			return cli.getCelular();
		default:
			return null; // isso nunca deve ocorrer, pois temos só 6 colunas
		}
	}
	
	public int getCodCliente(int linha){
		Cliente cli = cliente.get(linha);
		return cli.getCodCliente();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	/**
	 * Adiciona um novo {@link UnidadeConsumo} e notifica os listeners que um novo
	 * registro foi adicionado.
	 */
/*	public void adiciona(UnidadeConsumo uc) {
		unidConsumo.add(uc);
		// informamos os listeners que a linha (size - 1) foi adicionada
		fireTableRowsInserted(unidConsumo.size() - 1, unidConsumo.size() - 1);
	}
*/

	/**
	 * Similar ao {@link #adiciona(Cliente)}, porém para remover. Recebe o
	 * índice do cliente a ser removido. Se não souber o índice, use o método
	 * {@link #getIndice(Cliente)} antes.
	 */
/*	public void remove(int indice) {
		unidConsumo.remove(indice);
		fireTableRowsDeleted(indice, indice);
	}
*/
	/**
	 * Retorna o índice de determinado cliente.
	 */
	public int getIndice(Cliente cli) {
		return cliente.indexOf(cli);
	}

	/**
	 * Adiciona todos os clientes na lista à este modelo.
	 */
	public void adicionaLista(List<Cliente> lista) {
		int i = cliente.size();
		cliente.addAll(lista);
		fireTableRowsInserted(i, i + lista.size());
	}

	/**
	 * Esvazia a lista.
	 */
	public void limpaLista() {
		int i = cliente.size();
		cliente.clear();
		fireTableRowsDeleted(0, i - 1);
	}

	

}
