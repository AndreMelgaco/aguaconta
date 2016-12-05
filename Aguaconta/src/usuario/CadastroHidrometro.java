package usuario;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import banco.HidrometrosDAO;
import tablemodel.HidrometroTableModel;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CadastroHidrometro extends JDialog implements TableModelListener {

	private JTable tbHidrometro;
	private static HidrometroTableModel modelo;

	public CadastroHidrometro() throws Exception {
		setTitle("Cadastro de Hidrometros");
		setBounds(100, 100, 592, 266);
		getContentPane().setLayout(null);

		modelo = new HidrometroTableModel();

		tbHidrometro = new JTable();
		tbHidrometro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (modelo.getRowCount() > 0) {
						int linha = tbHidrometro.getSelectedRow();
						String codigo = modelo.getHidrometro(linha);
						try {
							new AddHidrometros(codigo, false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		tbHidrometro.setModel(modelo);
		tbHidrometro.setFillsViewportHeight(true);
		tbHidrometro.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbHidrometro.getColumnModel().getColumn(0).setPreferredWidth(150);
		tbHidrometro.getColumnModel().getColumn(1).setPreferredWidth(143);
		tbHidrometro.getColumnModel().getColumn(2).setPreferredWidth(260);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 556, 170);
		getContentPane().add(scrollPane);
		scrollPane.add(tbHidrometro);
		scrollPane.setViewportView(tbHidrometro);

		JButton btnAdicionarHidrometro = new JButton("Adicionar Hidrometro");
		btnAdicionarHidrometro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new AddHidrometros("", true);
					listaHidrometros();
				} catch (Exception e2) {
					e2.printStackTrace();
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
			modelo.adicionaLista(HidrometrosDAO.listarHidrometros());
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
