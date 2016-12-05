package usuario;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import banco.UnidConsumoDAO;
import tablemodel.UnidConsumoTableModel;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CadastroUnidadeConsumo extends JDialog implements TableModelListener {

	private JTable table;
	private static UnidConsumoTableModel modelo;
	
	public CadastroUnidadeConsumo() {
		setTitle("Cadastro de Unidade de Consumo");
		setBounds(100, 100, 592, 300);
		getContentPane().setLayout(null);
		
		modelo = new UnidConsumoTableModel();
		
		// aqui está a grande jogada!
		modelo.addTableModelListener((TableModelListener) this);
		try {
			modelo.adicionaLista(UnidConsumoDAO.listarUnidConsumo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		table = new JTable();
		table.setModel(modelo);
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 556, 170);
		getContentPane().add(scrollPane);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		
		JButton btnAdicionarUnidade = new JButton("Adicionar Unidade");
		btnAdicionarUnidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddUnidadeConsumo();
				atualizaTabela();
			}
		});
		btnAdicionarUnidade.setBounds(10, 215, 170, 23);
		getContentPane().add(btnAdicionarUnidade);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizaTabela();
			}
		});
		btnAtualizar.setBounds(466, 181, 98, 26);
		getContentPane().add(btnAtualizar);
		
		
		setVisible(true);
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// quando a estrutura de dados muda, imprimimos a informação. Fazer a
				// persistência no banco seria o comportamento realista desse método.
				@SuppressWarnings("unused")
				String tipo;
				switch (e.getType()) {
				case TableModelEvent.DELETE:
					tipo = "DELETE";
					break;
				case TableModelEvent.INSERT:
					tipo = "INSERT";
					break;
				case TableModelEvent.UPDATE:
					tipo = "UPDATE";
					break;
				default:
					tipo = "?";
					break;
				}
	}
	
	public static void atualizaTabela(){
		if(modelo.getRowCount() > 0){
			modelo.limpaLista();
		}
		try {
			modelo.adicionaLista(UnidConsumoDAO.listarUnidConsumo());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
