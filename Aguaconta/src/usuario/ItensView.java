package usuario;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import banco.ItensDAO;
import tablemodel.ItensTableModel;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ItensView extends JDialog implements TableModelListener {

	private JTable tbItens;
	private static ItensTableModel modelo;

	public ItensView() throws Exception {
		setModal(true);
		setTitle("Cadastro de Itens");
		setBounds(100, 100, 465, 266);
		getContentPane().setLayout(null);

		modelo = new ItensTableModel();

		tbItens = new JTable();
		tbItens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (modelo.getRowCount() > 0) {
						int linha = tbItens.getSelectedRow();
						int codigo = modelo.getItens(linha);
						try {
							new AddItens(codigo, false);
							listaItens();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		tbItens.setModel(modelo);
		tbItens.setFillsViewportHeight(true);
		tbItens.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbItens.getColumnModel().getColumn(0).setPreferredWidth(80);
		tbItens.getColumnModel().getColumn(1).setPreferredWidth(350);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 433, 170);
		getContentPane().add(scrollPane);
		scrollPane.add(tbItens);
		scrollPane.setViewportView(tbItens);

		JButton btnAdicionarItens = new JButton("Adicionar Item");
		btnAdicionarItens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					new AddItens(ItensDAO.novoItem(), true);
					listaItens();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdicionarItens.setBounds(10, 192, 170, 23);
		getContentPane().add(btnAdicionarItens);

		listaItens();
		setVisible(true);

	}

	private void listaItens() throws Exception {
		if (modelo.getRowCount() > 0) {
			modelo.limpaLista();
		}
		try {
			modelo.adicionaLista(ItensDAO.listarItens());
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
}
