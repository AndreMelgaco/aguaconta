package usuario;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import banco.ClienteDAO;
import dados.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class zzzClienteCad extends JDialog {

	private JPanel contentPanePrincipal;
	private JTextField textFieldCodCliente;
	private JTextField textFieldNomeCliente;
	private JTextField textFieldCpfCnpj;
	private JTextField textFieldIdentidade;
	private JTextField textFieldEmail;
	private JTextField textFieldTelFixo;
	private JTextField textFieldCelular;
	private JTextField textFieldTelRecado;
	private JTextField textFieldRespRecado;
	private JLabel labelCodCliente;
	private JLabel labelNomeCliente;
	private JLabel lblCpf;
	private JLabel lblIdent;
	private JLabel lblEmail;
	private JLabel lblTelFixo;
	private JLabel lblCelular;
	private JLabel lblTelRecado;
	private JLabel lblRespRecado;
	private JButton btnAlterarCadastro;
	private JButton btnEndInst;
	private JButton btnLimparCampos;
	private JButton btnNovoCliente;
	private JButton btnOk;
	private JRadioButton cpfButton;
	private JRadioButton cnpjButton;

	public zzzClienteCad() {
		setTitle("Cadastro de Clientes");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 620, 420);
		getContentPane().setLayout(null);

		textFieldCodCliente = new JTextField();
		textFieldCodCliente.setBounds(90, 26, 45, 20);
		getContentPane().add(textFieldCodCliente);
		textFieldCodCliente.setColumns(10);

		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setBounds(90, 57, 350, 20);
		getContentPane().add(textFieldNomeCliente);
		textFieldNomeCliente.setColumns(10);

		textFieldCpfCnpj = new JTextField();
		textFieldCpfCnpj.setBounds(90, 115, 126, 20);
		getContentPane().add(textFieldCpfCnpj);
		textFieldCpfCnpj.setColumns(10);

		textFieldIdentidade = new JTextField();
		textFieldIdentidade.setBounds(90, 151, 110, 20);
		getContentPane().add(textFieldIdentidade);
		textFieldIdentidade.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(90, 183, 248, 20);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);

		textFieldTelFixo = new JTextField();
		textFieldTelFixo.setBounds(90, 215, 110, 20);
		getContentPane().add(textFieldTelFixo);
		textFieldTelFixo.setColumns(10);

		textFieldCelular = new JTextField();
		textFieldCelular.setBounds(90, 247, 110, 20);
		getContentPane().add(textFieldCelular);
		textFieldCelular.setColumns(10);

		textFieldTelRecado = new JTextField();
		textFieldTelRecado.setBounds(90, 280, 110, 20);
		getContentPane().add(textFieldTelRecado);
		textFieldTelRecado.setColumns(10);

		textFieldRespRecado = new JTextField();
		textFieldRespRecado.setBounds(90, 313, 248, 20);
		getContentPane().add(textFieldRespRecado);
		textFieldRespRecado.setColumns(10);

		labelCodCliente = new JLabel("Cod. Cliente:");
		labelCodCliente.setBounds(10, 25, 78, 21);
		getContentPane().add(labelCodCliente);

		labelNomeCliente = new JLabel("Nome:");
		labelNomeCliente.setBounds(10, 56, 78, 21);
		getContentPane().add(labelNomeCliente);

		lblCpf = new JLabel("CPF/CNPJ:");
		lblCpf.setBounds(10, 115, 78, 21);
		getContentPane().add(lblCpf);

		lblIdent = new JLabel("Identidade:");
		lblIdent.setBounds(10, 148, 78, 21);
		getContentPane().add(lblIdent);

		lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 181, 78, 21);
		getContentPane().add(lblEmail);

		lblTelFixo = new JLabel("Telefone Fixo:");
		lblTelFixo.setBounds(10, 214, 78, 21);
		getContentPane().add(lblTelFixo);

		lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(10, 247, 78, 21);
		getContentPane().add(lblCelular);

		lblTelRecado = new JLabel("Recados:");
		lblTelRecado.setBounds(10, 280, 78, 21);
		getContentPane().add(lblTelRecado);

		lblRespRecado = new JLabel("Respons\u00E1vel:");
		lblRespRecado.setBounds(10, 313, 78, 21);
		getContentPane().add(lblRespRecado);

		// Inicio RadioButton e RadioGroup para decisão de CPF ou CNPJ
		cpfButton = new JRadioButton("CPF");
		cpfButton.setSelected(true);
		cpfButton.setBounds(8, 85, 55, 24);
		getContentPane().add(cpfButton);

		cnpjButton = new JRadioButton("CNPJ");
		cnpjButton.setBounds(67, 85, 56, 24);
		getContentPane().add(cnpjButton);

		ButtonGroup cpfCnpjgroup = new ButtonGroup();
		cpfCnpjgroup.add(cnpjButton);
		cpfCnpjgroup.add(cpfButton);
		// Final da configuração de seleção CPF ou CNPJ

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (cnpjButton.isSelected()) {
						cadastrarCliente(1);
					} else {
						cadastrarCliente(0);
					}
					JOptionPane.showMessageDialog(
							contentPanePrincipal,
							"Cliente cadastrado com sucesso",
							"Cadastro Cliente",
							JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon(getClass().getResource(
									"recursos/okgreen.png")));
					textFieldCodCliente.setEditable(false);
					textFieldNomeCliente.setEditable(false);
					textFieldCpfCnpj.setEditable(false);
				} catch (SQLException e1) {
					String erro = e1.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro,
							"SQL Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e2) {
					String erro = e2.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro,
							"Campo Obrigatório não Preenchido",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCadastrar.setBounds(10, 346, 91, 23);
		getContentPane().add(btnCadastrar);

		btnAlterarCadastro = new JButton("Alterar Cadastro");
		btnAlterarCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (cnpjButton.isSelected()) {
						alterarCliente(1);
					} else {
						alterarCliente(0);
					}
					JOptionPane.showMessageDialog(
							contentPanePrincipal,
							"Cadastro Alterado com Sucesso",
							"Alteração Cadastro Cliente",
							JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon(getClass().getResource(
									"recursos/okgreen.png")));
				} catch (SQLException e1) {
					String erro = e1.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro,
							"SQL Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e2) {
					String erro = e2.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro,
							"Campo Obrigatório não Preenchido",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAlterarCadastro.setBounds(113, 346, 137, 23);
		getContentPane().add(btnAlterarCadastro);

		btnEndInst = new JButton("Endere\u00E7o Instala\u00E7\u00E3o");
		btnEndInst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					enderecoInstalacao();
				} catch (Exception e1) {
					String erro = e1.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro,
							"Cliente não cadastrado.",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEndInst.setBounds(262, 346, 154, 23);
		getContentPane().add(btnEndInst);

		btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimparCampos.setBounds(455, 44, 137, 20);
		getContentPane().add(btnLimparCampos);

		btnNovoCliente = new JButton("Novo Cliente");
		btnNovoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					limparCampos();
					novoCliente();
					textFieldCodCliente.setEditable(false);
				} catch (Exception e2) {
					String erro = e2.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro,
							"Erro", JOptionPane.OK_OPTION);
				}
			}
		});
		btnNovoCliente.setBounds(455, 12, 137, 20);
		getContentPane().add(btnNovoCliente);

		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					consultaCliente();
					textFieldCodCliente.setEditable(false);
					textFieldNomeCliente.setEditable(false);
					textFieldCpfCnpj.setEditable(false);

				} catch (Exception e2) {
					String erro = e2.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro,
							"Erro", JOptionPane.OK_OPTION);
				}
			}
		});
		btnOk.setBounds(140, 26, 55, 20);
		getContentPane().add(btnOk);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					consultaClienteCpf();
					textFieldCodCliente.setEditable(false);
					textFieldNomeCliente.setEditable(false);
					textFieldCpfCnpj.setEditable(false);

				} catch (Exception e2) {
					String erro = e2.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro,
							"Erro", JOptionPane.OK_OPTION);
				}
			}
		});
		btnBuscar.setBounds(228, 115, 91, 20);
		getContentPane().add(btnBuscar);

		setVisible(true);

	}

	protected void consultaClienteCpf() throws Exception {
		try {
			if (textFieldCpfCnpj.getText().isEmpty()) {
				throw new Exception("CPF/CNPJ em branco.");
			} else {
				Cliente objCliente = ClienteDAO
						.consultaClienteCpf(textFieldCpfCnpj.getText());
				textFieldCodCliente.setText(Integer.toString(objCliente.getCodCliente()));
				textFieldNomeCliente.setText(objCliente.getNome());
				if (objCliente.getTipo() == 1) {
					cnpjButton.setSelected(true);
				} else {
					cpfButton.setSelected(true);
				}
				textFieldIdentidade.setText(objCliente.getIdentidade());
				textFieldEmail.setText(objCliente.getEmail());
				textFieldTelFixo.setText(objCliente.getTel_fixo());
				textFieldCelular.setText(objCliente.getCelular());
				textFieldTelRecado.setText(objCliente.getTel_recado());
				textFieldRespRecado.setText(objCliente.getResp_recado());
			}
		} catch (Exception e1) {
			throw new Exception(e1.getMessage());
		}

	}

	protected void novoCliente() throws SQLException {

		textFieldCodCliente
				.setText(String.valueOf(ClienteDAO.novoCliente() + 1));

	}

	// private static String format(String pattern, Object value) {
	// MaskFormatter mask;
	// try {
	// mask = new MaskFormatter(pattern);
	// mask.setValueContainsLiteralCharacters(false);
	// return mask.valueToString(value);
	// } catch (ParseException e) {
	// throw new RuntimeException(e);
	// }
	// }

	// Final do método JDialogEndInst

	protected void enderecoInstalacao() throws Exception {
		try {
			if (textFieldCodCliente.getText().isEmpty()) {
				throw new Exception("Código do cliente não pode ser vazio.");
			}
			ClienteDAO.consultaClienteBool(Integer
					.parseInt(textFieldCodCliente.getText()));
			new EndInstView(Integer.parseInt(textFieldCodCliente.getText()),textFieldNomeCliente.getText());

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	private void cadastrarCliente(int tipo) throws SQLException, Exception {
		try {
			if (textFieldCodCliente.getText().isEmpty()
					|| textFieldCpfCnpj.getText().isEmpty()
					|| textFieldNomeCliente.getText().isEmpty()) {
				throw new Exception("Preencha os campos obrigatórios.");
			} else {
				Cliente objCliente = new Cliente(
						Integer.parseInt(textFieldCodCliente.getText()),
						textFieldNomeCliente.getText(), tipo,
						textFieldCpfCnpj.getText(),
						textFieldIdentidade.getText(),
						textFieldEmail.getText(), textFieldTelFixo.getText(),
						textFieldCelular.getText(),
						textFieldTelRecado.getText(),
						textFieldRespRecado.getText());
				System.out.println(objCliente.toString());
				ClienteDAO.incluirCliente(objCliente);
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

	}

	private void alterarCliente(int tipo) throws Exception, SQLException {
		try {
			if (textFieldCodCliente.getText().isEmpty()
					|| textFieldCpfCnpj.getText().isEmpty()
					|| textFieldNomeCliente.getText().isEmpty()) {
				throw new Exception("Campos obrigatórios não preenchidos.");
			} else {
				Cliente objCliente = new Cliente(
						Integer.parseInt(textFieldCodCliente.getText()),
						textFieldNomeCliente.getText(), tipo,
						textFieldCpfCnpj.getText(),
						textFieldIdentidade.getText(),
						textFieldEmail.getText(), textFieldTelFixo.getText(),
						textFieldCelular.getText(),
						textFieldTelRecado.getText(),
						textFieldRespRecado.getText());
				ClienteDAO.alterarCliente(objCliente);
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

	}

	private void consultaCliente() throws Exception, SQLException {
		try {
			if (textFieldCodCliente.getText().isEmpty()) {
				throw new Exception("Código em branco.");
			} else {
				Cliente objCliente = ClienteDAO
						.consultaClienteCadastro(Integer
								.parseInt(textFieldCodCliente.getText()));
				textFieldNomeCliente.setText(objCliente.getNome());
				if (objCliente.getTipo() == 1) {
					cnpjButton.setSelected(true);
				} else {
					cpfButton.setSelected(true);
				}
				textFieldCpfCnpj.setText(objCliente.getCpfCnpj());
				textFieldIdentidade.setText(objCliente.getIdentidade());
				textFieldEmail.setText(objCliente.getEmail());
				textFieldTelFixo.setText(objCliente.getTel_fixo());
				textFieldCelular.setText(objCliente.getCelular());
				textFieldTelRecado.setText(objCliente.getTel_recado());
				textFieldRespRecado.setText(objCliente.getResp_recado());
			}
		} catch (Exception e1) {
			throw new Exception(e1.getMessage());
		}
	}
	
	private void limparCampos() {
		textFieldCodCliente.setText("");
		textFieldNomeCliente.setText("");
		textFieldCpfCnpj.setText("");
		textFieldIdentidade.setText("");
		textFieldEmail.setText("");
		textFieldTelFixo.setText("");
		textFieldCelular.setText("");
		textFieldTelRecado.setText("");
		textFieldRespRecado.setText("");
		textFieldCodCliente.setEditable(true);
		textFieldNomeCliente.setEditable(true);
		textFieldCpfCnpj.setEditable(true);
	}
}
