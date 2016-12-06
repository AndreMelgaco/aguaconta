package usuario;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import banco.OutrosItensDAO;
import dados.OutrosItens;
import tablemodel.OutrosItensTableModel;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class OutrosItensView extends JDialog implements TableModelListener {

	private JTable tbOutrosItens;
	private static OutrosItensTableModel modelo;

	public OutrosItensView(int codEndInst) throws Exception {
		setModal(true);
		setTitle("Outros Itens");
		setBounds(100, 100, 592, 266);
		getContentPane().setLayout(null);

		modelo = new OutrosItensTableModel();

		tbOutrosItens = new JTable();
		tbOutrosItens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (modelo.getRowCount() > 0) {
						int linha = tbOutrosItens.getSelectedRow();
						OutrosItens outros = modelo.getItens(linha);
						try {
							new AddOutrosItens(outros, false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		tbOutrosItens.setModel(modelo);
		tbOutrosItens.setFillsViewportHeight(true);
		tbOutrosItens.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbOutrosItens.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbOutrosItens.getColumnModel().getColumn(1).setPreferredWidth(273);
		tbOutrosItens.getColumnModel().getColumn(2).setPreferredWidth(65);
		tbOutrosItens.getColumnModel().getColumn(3).setPreferredWidth(65);
		tbOutrosItens.getColumnModel().getColumn(4).setPreferredWidth(100);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 556, 170);
		getContentPane().add(scrollPane);
		scrollPane.add(tbOutrosItens);
		scrollPane.setViewportView(tbOutrosItens);

		JButton btnAddOutrosItens = new JButton("Adicionar Outros Itens");
		btnAddOutrosItens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					OutrosItens outros = new OutrosItens(0, codEndInst, 0, "", 0, 0, null, null);
					new AddOutrosItens(outros, true);
					listaOutrosItens();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddOutrosItens.setBounds(10, 192, 170, 23);
		getContentPane().add(btnAddOutrosItens);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listaOutrosItens();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnAtualizar.setBounds(468, 189, 98, 26);
		getContentPane().add(btnAtualizar);

		listaOutrosItens();
		setVisible(true);

	}

	private void listaOutrosItens() throws Exception {
		if (modelo.getRowCount() > 0) {
			modelo.limpaLista();
		}
		try {
			modelo.adicionaLista(OutrosItensDAO.listarOutrosItens());
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
