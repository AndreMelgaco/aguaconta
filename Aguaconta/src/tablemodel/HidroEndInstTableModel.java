package tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.HidroEndInst;

public class HidroEndInstTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Essa lista armazenar� os objetos do tipo {@link Cliente} atualmente
	 * exibidos na tablela.
	 */
	private List<HidroEndInst> hidrometros;

	// ==============================================================
	// Construtores.
	// ==============================================================

	/**
	 * Constutor que simplesmente instancia o nosso {@link List} que usaremos
	 * para guardar os valores.
	 */
	public HidroEndInstTableModel() {
		// no construtor, instanciamos o List
		hidrometros = new ArrayList<HidroEndInst>();
	}

	/**
	 * Criamos um construtor de conveni�ncia para j� popular a lista.
	 * 
	 * @param lista
	 *            a lista de UnidadeConsumo a ser adicionada.
	 */
	public HidroEndInstTableModel(List<HidroEndInst> lista) {
		this();
		hidrometros.addAll(lista);
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
			return "Dt. Ativa��o"; // o nome da segunda
		case 2:
			return "Dt. Desativa��o"; // e o da terceira
		case 3:
			return "Observa��es";
		default:
			return ""; // isso nunca deve ocorrer, pois temos s� 4 colunas
		}
	}

	public String getHidrometro(int linha){
		HidroEndInst hidro = hidrometros.get(linha);
		return hidro.getCodHidrometro();
	}
	
	@Override
	public int getRowCount() {
		// retorna o n�mero de linhas, ou seja, a quantidade de entradas na
		// nossa lista.
		return hidrometros.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// vai retornar o valor de determinada c�lula.
		HidroEndInst hidro = hidrometros.get(linha);
		// fa�amos um switch
		switch (coluna) {
		case 0:
			return hidro.getCodHidrometro(); // retornamos o codigo
		case 1:
			return hidro.getDtAtivacao(); // retornamos o nome
		case 2:
			return hidro.getDtDesativacao(); // retornamos a cidade
		case 3:
			return hidro.getObservacao();
		default:
			return null; // isso nunca deve ocorrer, pois temos s� 4 colunas
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// nesse caso, todas as c�lulas s�o edit�veis
		return false;
	}

	/*
	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
		// aqui devemos atualizar o valor de nossos Clientes
		// vemos em qual linha ele est�
		Hidro_EndInst hidro = hidrometros.get(linha);
		// e vemos o que ser� atualizado
		switch (coluna) {
		case 0:
			hidro.setCodHidrometro(valor.toString()); // mudamos o codigo
			break;
		case 1:
			hidro.setSituacao(valor.toString());; // mudamos o nome
			break;
		case 2:
			hidro.setDtDesativacao(valor.); // a cidade
			break;
		case 3:
			hidro.setObservacao(valor.toString());
		}
		// � importante notificar os listeners a cada altera��o
		fireTableDataChanged();
	}

*/

	// ==============================================================
	// At� aqui seria o m�nimo necess�rio para um TableModel funcional, mas
	// ainda n�o h� m�todos para adicionar, remover ou resgatar objetos. Vamos
	// cri�-los.
	// ==============================================================

	/**
	 * Adiciona um novo {@link UnidadeConsumo} e notifica os listeners que um novo
	 * registro foi adicionado.
	 */
	public void adiciona(HidroEndInst uc) {
		hidrometros.add(uc);
		// informamos os listeners que a linha (size - 1) foi adicionada
		fireTableRowsInserted(hidrometros.size() - 1, hidrometros.size() - 1);
	}

	/**
	 * Similar ao {@link #adiciona(Cliente)}, por�m para remover. Recebe o
	 * �ndice do cliente a ser removido. Se n�o souber o �ndice, use o m�todo
	 * {@link #getIndice(Cliente)} antes.
	 */
	public void remove(int indice) {
		hidrometros.remove(indice);
		fireTableRowsDeleted(indice, indice);
	}

	/**
	 * Retorna o �ndice de determinado cliente.
	 */
	public int getIndice(HidroEndInst uc) {
		return hidrometros.indexOf(uc);
	}

	/**
	 * Adiciona todos os clientes na lista � este modelo.
	 */
	public void adicionaLista(List<HidroEndInst> lista) {
		int i = hidrometros.size();
		hidrometros.addAll(lista);
		fireTableRowsInserted(i, i + lista.size());
	}

	/**
	 * Esvazia a lista.
	 */
	public void limpaLista() {
		int i = hidrometros.size();
		hidrometros.clear();
		fireTableRowsDeleted(0, i - 1);
	}

	

}
