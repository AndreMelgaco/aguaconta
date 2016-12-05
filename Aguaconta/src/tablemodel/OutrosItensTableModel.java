package tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.OutrosItens;

public class OutrosItensTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Essa lista armazenará os objetos do tipo {@link Cliente} atualmente
	 * exibidos na tablela.
	 */
	private List<OutrosItens> itens;

	// ==============================================================
	// Construtores.
	// ==============================================================

	/**
	 * Constutor que simplesmente instancia o nosso {@link List} que usaremos
	 * para guardar os valores.
	 */
	public OutrosItensTableModel() {
		// no construtor, instanciamos o List
		itens = new ArrayList<OutrosItens>();
	}

	/**
	 * Criamos um construtor de conveniência para já popular a lista.
	 * 
	 * @param lista
	 *            a lista de UnidadeConsumo a ser adicionada.
	 */
	public OutrosItensTableModel(List<OutrosItens> lista) {
		this();
		itens.addAll(lista);
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
		// esse método deve retornar o número de colunas. No caso, 4 (uma para o codigo, uma para o
		// nome, uma para a cidade e uma para o UF).
		return 5;
	}

	@Override
	public String getColumnName(int coluna) {
		// vamos retornar o nome de cada coluna
		switch (coluna) {
		case 0:
			return "Código"; // o nome da primeira coluna
		case 1:
			return "Descrição"; // o nome da segunda
		case 2:
			return "Parcela";
		case 3:
			return "De";
		case 4:
			return "Valor";
		default:
			return ""; // isso nunca deve ocorrer, pois temos só 4 colunas
		}
	}
	
	public OutrosItens getItens(int linha){
		OutrosItens item = itens.get(linha);
		return item;
	}

	@Override
	public int getRowCount() {
		// retorna o número de linhas, ou seja, a quantidade de entradas na
		// nossa lista.
		return itens.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// vai retornar o valor de determinada célula. A linha representa a
		// posição da Unidade de Consumo na nossa lista e a coluna vai ser: 1 - codigo, 2 -
		// nome, 3 - cidade e 4 - UF
		// primeiro vamos pegar o Cliente da linha
		OutrosItens item = itens.get(linha);
		// façamos um switch
		switch (coluna) {
		case 0:
			return item.getCodItem(); // retornamos o codigo
		case 1:
			return item.getDescItem(); // retornamos o nome
		case 2:
			return item.getParcelaAtual();
		case 3:
			return item.getParcelaFinal();
		case 4:
			return item.getValor();
		default:
			return null; // isso nunca deve ocorrer, pois temos só 4 colunas
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// nesse caso, todas as células são editáveis
		return false;
	}

/*	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
		// aqui devemos atualizar o valor de nossos Clientes
		// vemos em qual linha ele está
		OutrosItens item = itens.get(linha);
		// e vemos o que será atualizado
		switch (coluna) {
		case 0:
			item.setCodItem(Integer.parseInt(valor.toString())); // mudamos o codigo
			break;
		case 1:
			item.setDescItem(valor.toString()); // mudamos o nome
			break;
		case 2:
			item.setParcelaAtual(Integer.parseInt(valor.toString()));
			break;
		case 3:
			item.setParcelaFinal(Integer.parseInt(valor.toString()));
			break;
		case 4:
			item.setValor(Integer.parseInt(valor.toString()));
			break;
		}
		// é importante notificar os listeners a cada alteração
		fireTableDataChanged();
	} */

	// ==============================================================
	// Até aqui seria o mínimo necessário para um TableModel funcional, mas
	// ainda não há métodos para adicionar, remover ou resgatar objetos. Vamos
	// criá-los.
	// ==============================================================

	/**
	 * Adiciona um novo {@link UnidadeConsumo} e notifica os listeners que um novo
	 * registro foi adicionado.
	 */
	public void adiciona(OutrosItens item) {
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
	public int getIndice(OutrosItens uc) {
		return itens.indexOf(uc);
	}

	/**
	 * Adiciona todos os clientes na lista à este modelo.
	 */
	public void adicionaLista(List<OutrosItens> lista) {
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
