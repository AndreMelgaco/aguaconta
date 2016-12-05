package tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.ItensConta;

public class ItensContaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/**
	 * Essa lista armazenará os objetos do tipo {@link Conta} atualmente
	 * exibidos na tablela.
	 */
	private List<ItensConta> itens;

	// ==============================================================
	// Construtores.
	// ==============================================================

	/**
	 * Constutor que simplesmente instancia o nosso {@link List} que usaremos
	 * para guardar os valores.
	 */
	public ItensContaTableModel() {
		// no construtor, instanciamos o List
		itens = new ArrayList<ItensConta>();
	}

	/**
	 * Criamos um construtor de conveniência para já popular a lista.
	 * 
	 * @param lista
	 *            a lista de UnidadeConsumo a ser adicionada.
	 */
	public ItensContaTableModel(List<ItensConta> listaItensConta) {
		this();
		itens.addAll(listaItensConta);
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
		return 5;
	}

	@Override
	public String getColumnName(int coluna) {
		// vamos retornar o nome de cada coluna
		switch (coluna) {
		case 0:
			return "Cod."; // o nome da primeira coluna
		case 1:
			return "Descrição"; // o nome da segunda
		case 2:
			return "Valor"; // e o da terceira
		case 3:
			return "Qtde.";
		case 4:
			return "Total";
		default:
			return ""; // isso nunca deve ocorrer, pois temos só 5 colunas
		}
	}

	@Override
	public int getRowCount() {
		// retorna o número de linhas, ou seja, a quantidade de entradas na
		// nossa lista.
		return itens.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// vai retornar o valor de determinada célula.
		// primeiro vamos pegar o Cliente da linha
		ItensConta item = itens.get(linha);
		switch (coluna) {
		case 0:
			return item.getCodItem(); // retornamos o codigo
		case 1:
			return item.getDescItem(); // retornamos o nome
		case 2:
			return item.getValor(); // retornamos o tipo
		case 3:
			return item.getQtdItem(); //retorna o cpf/cpnj
		case 4:
			return item.getTotal();
		default:
			return null; // isso nunca deve ocorrer, pois temos só 6 colunas
		}
	}
	
	public int getIdConta(int linha){
		ItensConta item = itens.get(linha);
		return item.getIdConta();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	/**
	 * Adiciona um novo {@link UnidadeConsumo} e notifica os listeners que um novo
	 * registro foi adicionado.
	 */
	public void adiciona(ItensConta item) {
		itens.add(item);
		// informamos os listeners que a linha (size - 1) foi adicionada
		fireTableRowsInserted(itens.size() - 1, itens.size() - 1);
	}


	/**
	 * Similar ao {@link #adiciona(Cliente)}, porém para remover. Recebe o
	 * índice do cliente a ser removido. Se não souber o índice, use o método
	 * {@link #getIndice(Cliente)} antes.
	 */
	public void remove(int indice) {
		itens.remove(indice);
		fireTableRowsDeleted(indice, indice);
	}

	/**
	 * Retorna o índice de determinado cliente.
	 */
	public int getIndice(ItensConta item) {
		return itens.indexOf(item);
	}

	/**
	 * Adiciona todos os clientes na lista à este modelo.
	 */
	public void adicionaLista(List<ItensConta> lista) {
		int i = itens.size();
		itens.addAll(lista);
		fireTableRowsInserted(i, i + lista.size());
	}

	/**
	 * Esvazia a lista.
	 */
	public void limpaLista() {
		int i = itens.size();
		itens.clear();
		fireTableRowsDeleted(0, i - 1);
	}

	

}
