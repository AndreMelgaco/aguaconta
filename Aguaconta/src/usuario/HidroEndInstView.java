package usuario;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import banco.HidroEndInstDAO;
import tablemodel.HidroEndInstTableModel;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class HidroEndInstView extends JDialog implements TableModelListener {

	private JTable tbHidroEndInst;
	private static HidroEndInstTableModel modelo;

	public HidroEndInstView(int codEndInst) throws Exception {
		setModal(true);
		setTitle("Hidrometros");
		setBounds(100, 100, 592, 266);
		getContentPane().setLayout(null);

		modelo = new HidroEndInstTableModel();

		tbHidroEndInst = new JTable();
		tbHidroEndInst.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (modelo.getRowCount() > 0) {
						int linha = tbHidroEndInst.getSelectedRow();
						String codigo = modelo.getHidrometro(linha);
						try {
							new AddHidroEndInst(codEndInst, codigo, false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		tbHidroEndInst.setModel(modelo);
		tbHidroEndInst.setFillsViewportHeight(true);
		tbHidroEndInst.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbHidroEndInst.getColumnModel().getColumn(0).setPreferredWidth(150);
		tbHidroEndInst.getColumnModel().getColumn(1).setPreferredWidth(100);
		tbHidroEndInst.getColumnModel().getColumn(2).setPreferredWidth(100);
		tbHidroEndInst.getColumnModel().getColumn(3).setPreferredWidth(204);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 556, 170);
		getContentPane().add(scrollPane);
		scrollPane.add(tbHidroEndInst);
		scrollPane.setViewportView(tbHidroEndInst);

		JButton btnAdicionarHidrometro = new JButton("Adicionar Hidrometro");
		btnAdicionarHidrometro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					new AddHidroEndInst(codEndInst, "", true);
					listaHidrometros();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdicionarHidrometro.setBounds(10, 192, 170, 23);
		getContentPane().add(btnAdicionarHidrometro);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listaHidrometros();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnAtualizar.setBounds(468, 189, 98, 26);
		getContentPane().add(btnAtualizar);

		listaHidrometros();
		setVisible(true);

	}

	private void listaHidrometros() throws Exception {
		if (modelo.getRowCount() > 0) {
			modelo.limpaLista();
		}
		try {
			modelo.adicionaLista(HidroEndInstDAO.listarHidro_EndInst());
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
