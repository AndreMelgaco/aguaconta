package usuario;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import banco.ClienteDAO;
import dados.EndInst;
import dados.UnidadeConsumo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class zzzEndInstCad extends JDialog {
	private JPanel contentPanePrincipal;
	private JTextField tfCodCliente;
	private JTextField tfEndereco;
	private JTextField tfGleba;
	private JTextField tfCidade;
	private JTextField tfChacara;
	private JTextField tfUf;
	private JTextField tfCodEndInst;
	private JTextField tfNomeCliente;
	private JTextField tfCodUnidCons;
	private JTextField tfNomeUnidCons;
	private String codUnidAnt;

	/**
	 * Create the dialog.
	 * 
	 * @throws Exception
	 */
	public zzzEndInstCad(int codigo, String nome) throws Exception {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Cadastro Endere\u00E7o de Instala\u00E7\u00E3o");
		setModal(true);
		setBounds(100, 100, 485, 379);
		getContentPane().setLayout(null);

		JLabel lblCodCliente = new JLabel("Cod. Cliente:");
		lblCodCliente.setBounds(10, 12, 70, 22);
		getContentPane().add(lblCodCliente);

		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(10, 87, 70, 22);
		getContentPane().add(lblEndereco);

		JLabel lblNumero = new JLabel("N\u00BA / Gleba: ");
		lblNumero.setBounds(10, 121, 60, 22);
		getContentPane().add(lblNumero);

		JLabel lblUnidConsumo = new JLabel("Unid. Consumo:");
		lblUnidConsumo.setBounds(10, 155, 89, 22);
		getContentPane().add(lblUnidConsumo);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 189, 70, 22);
		getContentPane().add(lblCidade);

		JLabel lblChacara = new JLabel("Chacara:");
		lblChacara.setBounds(155, 121, 60, 22);
		getContentPane().add(lblChacara);

		JLabel lblUf = new JLabel("UF:");
		lblUf.setBounds(312, 189, 26, 22);
		getContentPane().add(lblUf);

		tfCodCliente = new JTextField();
		tfCodCliente.setText(Integer.toString(codigo));
		tfCodCliente.setEditable(false);
		tfCodCliente.setBounds(98, 13, 50, 20);
		getContentPane().add(tfCodCliente);
		tfCodCliente.setColumns(10);

		tfNomeCliente = new JTextField();
		tfNomeCliente.setText(nome);
		tfNomeCliente.setEditable(false);
		tfNomeCliente.setBounds(155, 13, 294, 21);
		getContentPane().add(tfNomeCliente);
		tfNomeCliente.setColumns(10);

		tfEndereco = new JTextField();
		tfEndereco.setColumns(10);
		tfEndereco.setBounds(98, 88, 351, 20);
		getContentPane().add(tfEndereco);

		tfGleba = new JTextField();
		tfGleba.setColumns(10);
		tfGleba.setBounds(98, 122, 44, 20);
		getContentPane().add(tfGleba);

		tfCidade = new JTextField();
		tfCidade.setEditable(false);
		tfCidade.setColumns(10);
		tfCidade.setBounds(98, 190, 199, 20);
		getContentPane().add(tfCidade);

		tfChacara = new JTextField();
		tfChacara.setColumns(10);
		tfChacara.setBounds(206, 122, 44, 20);
		getContentPane().add(tfChacara);

		tfUf = new JTextField();
		tfUf.setEditable(false);
		tfUf.setColumns(10);
		tfUf.setBounds(341, 190, 44, 20);
		getContentPane().add(tfUf);

		tfCodEndInst = new JTextField();
		tfCodEndInst.setEditable(false);
		novoEndInst();
		tfCodEndInst.setBounds(98, 57, 50, 19);
		getContentPane().add(tfCodEndInst);
		tfCodEndInst.setColumns(10);

		JButton btnInserirEndInst = new JButton("Inserir");
		btnInserirEndInst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cadastrarEndInst();
					JOptionPane.showMessageDialog(contentPanePrincipal,
							"Endereço de Instalação cadastrado com sucesso.", "Cadastro Endereço de Instalação",
							JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon(getClass().getResource("recursos/okgreen.png")));
					limparCampos();
					novoEndInst();
				} catch (SQLException e1) {
					String erro = e1.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro, "SQL Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e2) {
					String erro = e2.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro, "Campo Obrigatório não Preenchido",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnInserirEndInst.setBounds(181, 241, 132, 23);
		getContentPane().add(btnInserirEndInst);

		JLabel lblCodEndInst = new JLabel("Cod. End Inst.:");
		lblCodEndInst.setBounds(10, 59, 79, 16);
		getContentPane().add(lblCodEndInst);

		tfCodUnidCons = new JTextField();
		tfCodUnidCons.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!tfCodUnidCons.getText().isEmpty() && tfCodUnidCons.getText() != codUnidAnt) {
					codUnidAnt = tfCodUnidCons.getText();
					try {
						buscaUnidadeConsumo(Integer.parseInt(tfCodUnidCons.getText()));
					} catch (Exception e1) {
						tfNomeUnidCons.setText("");
						tfCidade.setText("");
						tfUf.setText("");
						String erro = e1.getMessage();
						JOptionPane.showMessageDialog(contentPanePrincipal, erro, "Erro", JOptionPane.OK_OPTION);

					}
				}
			}
		});
		tfCodUnidCons.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER && !tfCodUnidCons.getText().isEmpty()
						&& tfCodUnidCons.getText() != codUnidAnt) {
					codUnidAnt = tfCodUnidCons.getText();
					try {
						buscaUnidadeConsumo(Integer.parseInt(tfCodUnidCons.getText()));
					} catch (Exception e1) {
						tfNomeUnidCons.setText("");
						tfCidade.setText("");
						tfUf.setText("");
						String erro = e1.getMessage();
						JOptionPane.showMessageDialog(contentPanePrincipal, erro, "Erro", JOptionPane.OK_OPTION);
					}
				}
			}
		});
		tfCodUnidCons.setBounds(98, 156, 44, 20);
		getContentPane().add(tfCodUnidCons);
		tfCodUnidCons.setColumns(10);

		tfNomeUnidCons = new JTextField();
		tfNomeUnidCons.setEditable(false);
		tfNomeUnidCons.setBounds(155, 156, 296, 20);
		getContentPane().add(tfNomeUnidCons);
		tfNomeUnidCons.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 42, 596, 2);
		getContentPane().add(separator);

		setVisible(true);
	}

	protected void novoEndInst() throws SQLException {

		tfCodEndInst.setText(String.valueOf(ClienteDAO.novoEndInst() + 1));

	}

	private void cadastrarEndInst() throws Exception {
		try {
			if (tfCodUnidCons.getText().isEmpty() || tfNomeUnidCons.getText().isEmpty()) {
				throw new Exception("Campos Obrigatórios em branco");
			}

			EndInst objEndInst = new EndInst(Integer.parseInt(tfCodEndInst.getText()),
					Integer.parseInt(tfCodCliente.getText()), tfEndereco.getText(), tfGleba.getText(),
					tfChacara.getText(), Integer.parseInt(tfCodUnidCons.getText()));
			ClienteDAO.incluirEndInst(objEndInst);
		} catch (SQLException e1) {
			throw new SQLException(e1.getMessage());
		}

	}

	public void buscaUnidadeConsumo(int codUnid) throws Exception {
		try {
			UnidadeConsumo unidCons = ClienteDAO.buscarUnidadeConsumo(codUnid);
			tfNomeUnidCons.setText(unidCons.getNomeUnid());
			tfCidade.setText(unidCons.getCidade());
			tfUf.setText(unidCons.getUF());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	private void limparCampos() {
		tfEndereco.setText("");
		tfGleba.setText("");
		tfCidade.setText("");
		tfChacara.setText("");
		tfUf.setText("");
		tfCodUnidCons.setText("");
		tfNomeUnidCons.setText("");
		codUnidAnt = "";
	} 
}
