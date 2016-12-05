package tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.ConfigValores;

public class ConfigValoresTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Essa lista armazenará os objetos do tipo {@link Cliente} atualmente
	 * exibidos na tablela.
	 */
	private List<ConfigValores> configValores;

	// ==============================================================
	// Construtores.
	// ==============================================================

	/**
	 * Constutor que simplesmente instancia o nosso {@link List} que usaremos
	 * para guardar os valores.
	 */
	public ConfigValoresTableModel() {
		// no construtor, instanciamos o List
		configValores = new ArrayList<ConfigValores>();
	}

	/**
	 * Criamos um construtor de conveniência para já popular a lista.
	 * 
	 * @param lista
	 *            a lista de UnidadeConsumo a ser adicionada.
	 */
	public ConfigValoresTableModel(List<ConfigValores> lista) {
		this();
		configValores.addAll(lista);
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
		return 3;
	}

	@Override
	public String getColumnName(int coluna) {
		// vamos retornar o nome de cada coluna
		switch (coluna) {
		case 0:
			return "Faixa"; // o nome da primeira coluna
		case 1:
			return "Valor Unico"; // o nome da segunda
		case 2:
			return "Valor Unit"; // e o da terceira
		default:
			return ""; // isso nunca deve ocorrer, pois temos só 4 colunas
		}
	}

	public int getConfigValores(int linha){
		ConfigValores config = configValores.get(linha);
		return config.getFaixas();
	}
	
	@Override
	public int getRowCount() {
		// retorna o número de linhas, ou seja, a quantidade de entradas na
		// nossa lista.
		return configValores.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// vai retornar o valor de determinada célula.
		ConfigValores config = configValores.get(linha);
		// façamos um switch
		switch (coluna) {
		case 0:
			return config.getFaixas(); // retornamos o codigo
		case 1:
			return config.getValorUnico(); // retornamos o nome
		case 2:
			return config.getValorUnit(); // retornamos a cidade
		default:
			return null; // isso nunca deve ocorrer, pois temos só 4 colunas
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// nesse caso, todas as células são editáveis
		return false;
	}

	/*
	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
		// aqui devemos atualizar o valor de nossos Clientes
		// vemos em qual linha ele está
		Hidro_EndInst hidro = hidrometros.get(linha);
		// e vemos o que será atualizado
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
		// é importante notificar os listeners a cada alteração
		fireTableDataChanged();
	}

*/

	// ==============================================================
	// Até aqui seria o mínimo necessário para um TableModel funcional, mas
	// ainda não há métodos para adicionar, remover ou resgatar objetos. Vamos
	// criá-los.
	// ==============================================================

	/**
	 * Adiciona um novo {@link UnidadeConsumo} e notifica os listeners que um novo
	 * registro foi adicionado.
	 */
	public void adiciona(ConfigValores uc) {
		configValores.add(uc);
		// informamos os listeners que a linha (size - 1) foi adicionada
		fireTableRowsInserted(configValores.size() - 1, configValores.size() - 1);
	}

	/**
	 * Similar ao {@link #adiciona(Cliente)}, porém para remover. Recebe o
	 * índice do cliente a ser removido. Se não souber o índice, use o método
	 * {@link #getIndice(Cliente)} antes.
	 */
	public void remove(int indice) {
		configValores.remove(indice);
		fireTableRowsDeleted(indice, indice);
	}

	/**
	 * Retorna o índice de determinado cliente.
	 */
	public int getIndice(ConfigValores uc) {
		return configValores.indexOf(uc);
	}

	/**
	 * Adiciona todos os clientes na lista à este modelo.
	 */
	public void adicionaLista(List<ConfigValores> lista) {
		int i = configValores.size();
		configValores.addAll(lista);
		fireTableRowsInserted(i, i + lista.size());
	}

	/**
	 * Esvazia a lista.
	 */
	public void limpaLista() {
		int i = configValores.size();
		configValores.clear();
		fireTableRowsDeleted(0, i - 1);
	}

	

}
