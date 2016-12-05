package tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.Cliente;
import dados.EndInst;

public class EndInstTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/**
	 * Essa lista armazenará os objetos do tipo {@link EndInst} atualmente
	 * exibidos na tablela.
	 */
	private List<EndInst> endInst;

	// ==============================================================
	// Construtores.
	// ==============================================================

	/**
	 * Constutor que simplesmente instancia o nosso {@link List} que usaremos
	 * para guardar os valores.
	 */
	public EndInstTableModel() {
		// no construtor, instanciamos o List
		endInst = new ArrayList<EndInst>();
	}

	/**
	 * Criamos um construtor de conveniência para já popular a lista.
	 * 
	 * @param lista
	 *            a lista de UnidadeConsumo a ser adicionada.
	 */
	public EndInstTableModel(List<EndInst> listaEndInst) {
		this();
		endInst.addAll(listaEndInst);
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
		return 7;
	}

	@Override
	public String getColumnName(int coluna) {
		// vamos retornar o nome de cada coluna
		switch (coluna) {
		case 0:
			return "Cod."; // o nome da primeira coluna
		case 1:
			return "Endereço"; // o nome da segunda
		case 2:
			return "Gleba"; // e o da terceira
		case 3:
			return "Chacara";
		case 4:
			return "Unidade Consumo";
		case 5:
			return "Cidade";
		case 6:
			return "UF";
		default:
			return ""; // isso nunca deve ocorrer, pois temos só 5 colunas
		}
	}
	
	public int getCodEndInst(int linha){
		EndInst inst = endInst.get(linha);
		return inst.getCodEndInst();
	}

	@Override
	public int getRowCount() {
		// retorna o número de linhas, ou seja, a quantidade de entradas na
		// nossa lista.
		return endInst.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// vai retornar o valor de determinada célula.
		// primeiro vamos pegar o Cliente da linha
		EndInst inst = endInst.get(linha);
		switch (coluna) {
		case 0:
			return inst.getCodEndInst(); // retornamos o codigo
		case 1:
			return inst.getEndereco(); // retornamos o nome
		case 2:
			return inst.getGleba(); // retornamos o tipo
		case 3:
			return inst.getChacara(); //retorna o cpf/cpnj
		case 4:
			return inst.getUnidadeConsumo();
		case 5:
			return inst.getCidade();
		case 6:
			return inst.getUf();
		default:
			return null; // isso nunca deve ocorrer, pois temos só 6 colunas
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	/**
	 * Adiciona um novo {@link UnidadeConsumo} e notifica os listeners que um novo
	 * registro foi adicionado.
	 */
	public void adiciona(EndInst ei) {
		endInst.add(ei);
		// informamos os listeners que a linha (size - 1) foi adicionada
		fireTableRowsInserted(endInst.size() - 1, endInst.size() - 1);
	}


	/**
	 * Similar ao {@link #adiciona(Cliente)}, porém para remover. Recebe o
	 * índice do cliente a ser removido. Se não souber o índice, use o método
	 * {@link #getIndice(Cliente)} antes.
	 */
	public void remove(int indice) {
		endInst.remove(indice);
		fireTableRowsDeleted(indice, indice);
	}

	/**
	 * Retorna o índice de determinado cliente.
	 */
	public int getIndice(EndInst inst) {
		return endInst.indexOf(inst);
	}

	/**
	 * Adiciona todos os clientes na lista à este modelo.
	 */
	public void adicionaLista(List<EndInst> lista) {
		int i = endInst.size();
		endInst.addAll(lista);
		fireTableRowsInserted(i, i + lista.size());
	}

	/**
	 * Esvazia a lista.
	 */
	public void limpaLista() {
		int i = endInst.size();
		endInst.clear();
		fireTableRowsDeleted(0, i - 1);
	}

	

}
