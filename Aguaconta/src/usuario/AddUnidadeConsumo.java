package usuario;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import banco.UnidConsumoDAO;
import dados.UnidadeConsumo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddUnidadeConsumo extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField tfNome;
	private JTextField tfCidade;
	private JTextField tfEstado;
	private JTextField tfCodigo;

	public AddUnidadeConsumo() {
		setTitle("Cadastro Unidade de Consumo");
		setBounds(100, 100, 335, 259);
		getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 53, 46, 14);
		getContentPane().add(lblNome);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 84, 55, 16);
		getContentPane().add(lblCidade);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 114, 55, 16);
		getContentPane().add(lblEstado);

		tfNome = new JTextField();
		tfNome.setBounds(62, 50, 210, 20);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);

		tfCidade = new JTextField();
		tfCidade.setBounds(62, 81, 210, 20);
		getContentPane().add(tfCidade);
		tfCidade.setColumns(10);

		tfEstado = new JTextField();
		tfEstado.setBounds(62, 112, 46, 20);
		getContentPane().add(tfEstado);
		tfEstado.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarUnidadeConsumo();
				dispose();
			}
		});
		btnSalvar.setBounds(105, 171, 98, 26);
		getContentPane().add(btnSalvar);

		JLabel lblCodUnidadeConsumo = new JLabel("C\u00F3digo:");
		lblCodUnidadeConsumo.setBounds(10, 23, 46, 14);
		getContentPane().add(lblCodUnidadeConsumo);

		tfCodigo = new JTextField();
		tfCodigo.setBounds(62, 19, 46, 20);
		getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);

		setVisible(true);

	}

	public void salvarUnidadeConsumo() {
		UnidadeConsumo uc = new UnidadeConsumo(Integer.parseInt(tfCodigo.getText()), tfNome.getText(),
				tfCidade.getText(), tfEstado.getText());

		try {
			UnidConsumoDAO.IncluirUnidConsumo(uc);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
