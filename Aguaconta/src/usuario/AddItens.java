package usuario;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import banco.ItensDAO;
import dados.Itens;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddItens extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblCodItem;
	private JLabel lblDescrio;
	private JTextField tfCodItem;
	private JTextField tfDescItem;

	public AddItens(int codigo, boolean cadastro) {
		setModal(true);
		setTitle("Cadastro Itens");
		setBounds(100, 100, 527, 175);
		getContentPane().setLayout(null);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!tfCodItem.getText().isEmpty() && !tfDescItem.getText().isEmpty()) {

						if (cadastro) {
							salvarItem();
						} else {
							atualizarItem();
						}

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				dispose();
			}
		});
		btnSalvar.setBounds(201, 89, 98, 26);

		getContentPane().add(btnSalvar);

		lblCodItem = new JLabel("Cod. Item:");
		lblCodItem.setBounds(10, 33, 73, 14);
		getContentPane().add(lblCodItem);

		lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(10, 58, 73, 16);
		getContentPane().add(lblDescrio);

		tfCodItem = new JTextField();
		tfCodItem.setEditable(false);
		tfCodItem.setBounds(82, 30, 58, 20);
		getContentPane().add(tfCodItem);
		tfCodItem.setColumns(10);

		tfDescItem = new JTextField();
		tfDescItem.setBounds(82, 58, 408, 20);
		getContentPane().add(tfDescItem);
		tfDescItem.setColumns(10);

		carregaItem(codigo);
		setVisible(true);

	}

	public void carregaItem(int codItem) {
		try {
			Itens item = ItensDAO.buscarItens(codItem);

			tfCodItem.setText(Integer.toString(item.getCoditem()));
			tfDescItem.setText(item.getDescItem());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarItem() throws Exception {

		Itens item = new Itens(Integer.parseInt(tfCodItem.getText()), tfDescItem.getText());

		try {
			ItensDAO.incluirItens(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizarItem() throws Exception, SQLException {

		Itens item = new Itens(Integer.parseInt(tfCodItem.getText()), tfDescItem.getText());

		try {
			ItensDAO.atualizarItens(item);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
