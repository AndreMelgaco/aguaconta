package tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.UnidadeConsumo;

public class UnidConsumoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Essa lista armazenar� os objetos do tipo {@link Cliente} atualmente
	 * exibidos na tablela.
	 */
	private List<UnidadeConsumo> unidConsumo;

	// ==============================================================
	// Construtores.
	// ==============================================================

	/**
	 * Constutor que simplesmente instancia o nosso {@link List} que usaremos
	 * para guardar os valores.
	 */
	public UnidConsumoTableModel() {
		// no construtor, instanciamos o List
		unidConsumo = new ArrayList<UnidadeConsumo>();
	}

	/**
	 * Criamos um construtor de conveni�ncia para j� popular a lista.
	 * 
	 * @param lista
	 *            a lista de UnidadeConsumo a ser adicionada.
	 */
	public UnidConsumoTableModel(List<UnidadeConsumo> lista) {
		this();
		unidConsumo.addAll(lista);
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
		// esse m�todo deve retornar o n�mero de colunas. No caso, 4 (uma para o codigo, uma para o
		// nome, uma para a cidade e uma para o UF).
		return 4;
	}

	@Override
	public String getColumnName(int coluna) {
		// vamos retornar o nome de cada coluna
		switch (coluna) {
		case 0:
			return "C�digo"; // o nome da primeira coluna
		case 1:
			return "Unidade Consumo"; // o nome da segunda
		case 2:
			return "Cidade"; // e o da terceira
		case 3:
			return "UF";
		default:
			return ""; // isso nunca deve ocorrer, pois temos s� 4 colunas
		}
	}

	@Override
	public int getRowCount() {
		// retorna o n�mero de linhas, ou seja, a quantidade de entradas na
		// nossa lista.
		return unidConsumo.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// vai retornar o valor de determinada c�lula. A linha representa a
		// posi��o da Unidade de Consumo na nossa lista e a coluna vai ser: 1 - codigo, 2 -
		// nome, 3 - cidade e 4 - UF
		// primeiro vamos pegar o Cliente da linha
		UnidadeConsumo uc = unidConsumo.get(linha);
		// fa�amos um switch
		switch (coluna) {
		case 0:
			return uc.getCodUnidConsumo(); // retornamos o codigo
		case 1:
			return uc.getNomeUnid(); // retornamos o nome
		case 2:
			return uc.getCidade(); // retornamos a cidade
		case 3:
			return uc.getUF(); //retorna a UF
		default:
			return null; // isso nunca deve ocorrer, pois temos s� 4 colunas
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// nesse caso, todas as c�lulas s�o edit�veis
		return false;
	}

	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
		// aqui devemos atualizar o valor de nossos Clientes
		// vemos em qual linha ele est�
		UnidadeConsumo uc = unidConsumo.get(linha);
		// e vemos o que ser� atualizado
		switch (coluna) {
		case 0:
			uc.setCodUnidConsumo(Integer.parseInt(valor.toString())); // mudamos o codigo
			break;
		case 1:
			uc.setNomeUnid(valor.toString()); // mudamos o nome
			break;
		case 2:
			uc.setCidade(valor.toString()); // a cidade
			break;
		case 3:
			uc.setUF(valor.toString()); //e o estado
		}
		// � importante notificar os listeners a cada altera��o
		fireTableDataChanged();
	}

	// ==============================================================
	// At� aqui seria o m�nimo necess�rio para um TableModel funcional, mas
	// ainda n�o h� m�todos para adicionar, remover ou resgatar objetos. Vamos
	// cri�-los.
	// ==============================================================

	/**
	 * Adiciona um novo {@link UnidadeConsumo} e notifica os listeners que um novo
	 * registro foi adicionado.
	 */
	public void adiciona(UnidadeConsumo uc) {
		unidConsumo.add(uc);
		// informamos os listeners que a linha (size - 1) foi adicionada
		fireTableRowsInserted(unidConsumo.size() - 1, unidConsumo.size() - 1);
	}

	/**
	 * Similar ao {@link #adiciona(Cliente)}, por�m para remover. Recebe o
	 * �ndice do cliente a ser removido. Se n�o souber o �ndice, use o m�todo
	 * {@link #getIndice(Cliente)} antes.
	 */
	public void remove(int indice) {
		unidConsumo.remove(indice);
		fireTableRowsDeleted(indice, indice);
	}

	/**
	 * Retorna o �ndice de determinado cliente.
	 */
	public int getIndice(UnidadeConsumo uc) {
		return unidConsumo.indexOf(uc);
	}

	/**
	 * Adiciona todos os clientes na lista � este modelo.
	 */
	public void adicionaLista(List<UnidadeConsumo> lista) {
		int i = unidConsumo.size();
		unidConsumo.addAll(lista);
		fireTableRowsInserted(i, i + lista.size());
	}

	/**
	 * Esvazia a lista.
	 */
	public void limpaLista() {
		int i = unidConsumo.size();
		unidConsumo.clear();
		fireTableRowsDeleted(0, i - 1);
	}

	

}
