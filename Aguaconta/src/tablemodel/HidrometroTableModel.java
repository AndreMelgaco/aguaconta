package tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.Hidrometros;

public class HidrometroTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Essa lista armazenará os objetos do tipo {@link Cliente} atualmente
	 * exibidos na tablela.
	 */
	private List<Hidrometros> hidrometros;

	// ==============================================================
	// Construtores.
	// ==============================================================

	/**
	 * Constutor que simplesmente instancia o nosso {@link List} que usaremos
	 * para guardar os valores.
	 */
	public HidrometroTableModel() {
		// no construtor, instanciamos o List
		hidrometros = new ArrayList<Hidrometros>();
	}

	/**
	 * Criamos um construtor de conveniência para já popular a lista.
	 * 
	 * @param lista
	 *            a lista de UnidadeConsumo a ser adicionada.
	 */
	public HidrometroTableModel(List<Hidrometros> lista) {
		this();
		hidrometros.addAll(lista);
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
			return "Código"; // o nome da primeira coluna
		case 1:
			return "Situação"; // o nome da segunda
		case 2:
			return "Observação"; // e o da terceira
		default:
			return ""; // isso nunca deve ocorrer, pois temos só 4 colunas
		}
	}

	public String getHidrometro(int linha){
		Hidrometros hidro = hidrometros.get(linha);
		return hidro.getCodHidrometro();
	}
	
	@Override
	public int getRowCount() {
		// retorna o número de linhas, ou seja, a quantidade de entradas na
		// nossa lista.
		return hidrometros.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// vai retornar o valor de determinada célula.
		Hidrometros hidro = hidrometros.get(linha);
		// façamos um switch
		switch (coluna) {
		case 0:
			return hidro.getCodHidrometro(); // retornamos o codigo
		case 1:
			return hidro.getSituacao(); // retornamos o nome
		case 2:
			return hidro.getObservacao(); // retornamos a cidade
		default:
			return null; // isso nunca deve ocorrer, pois temos só 4 colunas
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// nesse caso, todas as células são editáveis
		return false;
	}

	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
		// aqui devemos atualizar o valor de nossos Clientes
		// vemos em qual linha ele está
		Hidrometros hidro = hidrometros.get(linha);
		// e vemos o que será atualizado
		switch (coluna) {
		case 0:
			hidro.setCodHidrometro(valor.toString()); // mudamos o codigo
			break;
		case 1:
			hidro.setSituacao(valor.toString());; // mudamos o nome
			break;
		case 2:
			hidro.setObservacao(valor.toString()); // a cidade
			break;
		}
		// é importante notificar os listeners a cada alteração
		fireTableDataChanged();
	}

	// ==============================================================
	// Até aqui seria o mínimo necessário para um TableModel funcional, mas
	// ainda não há métodos para adicionar, remover ou resgatar objetos. Vamos
	// criá-los.
	// ==============================================================

	/**
	 * Adiciona um novo {@link UnidadeConsumo} e notifica os listeners que um novo
	 * registro foi adicionado.
	 */
	public void adiciona(Hidrometros uc) {
		hidrometros.add(uc);
		// informamos os listeners que a linha (size - 1) foi adicionada
		fireTableRowsInserted(hidrometros.size() - 1, hidrometros.size() - 1);
	}

	/**
	 * Similar ao {@link #adiciona(Cliente)}, porém para remover. Recebe o
	 * índice do cliente a ser removido. Se não souber o índice, use o método
	 * {@link #getIndice(Cliente)} antes.
	 */
	public void remove(int indice) {
		hidrometros.remove(indice);
		fireTableRowsDeleted(indice, indice);
	}

	/**
	 * Retorna o índice de determinado cliente.
	 */
	public int getIndice(Hidrometros uc) {
		return hidrometros.indexOf(uc);
	}

	/**
	 * Adiciona todos os clientes na lista à este modelo.
	 */
	public void adicionaLista(List<Hidrometros> lista) {
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
