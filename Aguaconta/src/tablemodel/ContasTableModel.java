package tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.Conta;

public class ContasTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/**
	 * Essa lista armazenar� os objetos do tipo {@link Conta} atualmente
	 * exibidos na tablela.
	 */
	private List<Conta> conta;

	// ==============================================================
	// Construtores.
	// ==============================================================

	/**
	 * Constutor que simplesmente instancia o nosso {@link List} que usaremos
	 * para guardar os valores.
	 */
	public ContasTableModel() {
		// no construtor, instanciamos o List
		conta = new ArrayList<Conta>();
	}

	/**
	 * Criamos um construtor de conveni�ncia para j� popular a lista.
	 * 
	 * @param lista
	 *            a lista de UnidadeConsumo a ser adicionada.
	 */
	public ContasTableModel(List<Conta> listaConta) {
		this();
		conta.addAll(listaConta);
	}

	// ==============================================================
	// M�todos implementados.
	// ==============================================================

	@Override
	public Class<?> getColumnClass(int coluna) {
		// todas as colunas representam uma String
		return String.class;
	}

	@Override
	public int getColumnCount() {
		// esse m�todo deve retornar o n�mero de colunas. No caso, 6 (uma para o cod, um para o nome, uma para o
		// tipo, uma para o cpf/cnpj e uma para o telefone fixo e um para o celular).
		return 6;
	}

	@Override
	public String getColumnName(int coluna) {
		// vamos retornar o nome de cada coluna
		switch (coluna) {
		case 0:
			return "Mes"; // o nome da primeira coluna
		case 1:
			return "Ano"; // o nome da segunda
		case 2:
			return "Hidrometro"; // e o da terceira
		case 3:
			return "Leitura";
		case 4:
			return "Consumo";
		case 5:
			return "Valor";
		default:
			return ""; // isso nunca deve ocorrer, pois temos s� 5 colunas
		}
	}

	@Override
	public int getRowCount() {
		// retorna o n�mero de linhas, ou seja, a quantidade de entradas na
		// nossa lista.
		return conta.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// vai retornar o valor de determinada c�lula.
		// primeiro vamos pegar o Cliente da linha
		Conta contas = conta.get(linha);
		switch (coluna) {
		case 0:
			return contas.getMes(); // retornamos o codigo
		case 1:
			return contas.getAno(); // retornamos o nome
		case 2:
			return contas.getHidrometro(); // retornamos o tipo
		case 3:
			return contas.getLeituraAtual(); //retorna o cpf/cpnj
		case 4:
			return contas.getConsumo();
		case 5:
			return contas.getTotal();
		default:
			return null; // isso nunca deve ocorrer, pois temos s� 6 colunas
		}
	}
	
	public int getIdConta(int linha){
		Conta cont = conta.get(linha);
		return cont.getIdConta();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	/**
	 * Adiciona um novo {@link UnidadeConsumo} e notifica os listeners que um novo
	 * registro foi adicionado.
	 */
	public void adiciona(Conta con) {
		conta.add(con);
		// informamos os listeners que a linha (size - 1) foi adicionada
		fireTableRowsInserted(conta.size() - 1, conta.size() - 1);
	}


	/**
	 * Similar ao {@link #adiciona(Cliente)}, por�m para remover. Recebe o
	 * �ndice do cliente a ser removido. Se n�o souber o �ndice, use o m�todo
	 * {@link #getIndice(Cliente)} antes.
	 */
	public void remove(int indice) {
		conta.remove(indice);
		fireTableRowsDeleted(indice, indice);
	}

	/**
	 * Retorna o �ndice de determinado cliente.
	 */
	public int getIndice(Conta con) {
		return conta.indexOf(con);
	}

	/**
	 * Adiciona todos os clientes na lista � este modelo.
	 */
	public void adicionaLista(List<Conta> lista) {
		int i = conta.size();
		conta.addAll(lista);
		fireTableRowsInserted(i, i + lista.size());
	}

	/**
	 * Esvazia a lista.
	 */
	public void limpaLista() {
		int i = conta.size();
		conta.clear();
		fireTableRowsDeleted(0, i - 1);
	}

	

}
