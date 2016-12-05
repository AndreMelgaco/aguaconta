package usuario;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import banco.ContaDAO;
import banco.EndInstDAO;
import banco.UnidConsumoDAO;
import dados.EndInst;
import dados.UnidadeConsumo;
import tablemodel.ContasTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class EndInstView extends JDialog {
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
	private JTable tbContas;
	private static ContasTableModel modeloContas;
	private boolean cadastro;
	private JButton btnHidrometros;
	private JButton btnEndCobranca;
	private JButton btnSalvar;
	private JButton btnNovaConta;

	/**
	 * Create the dialog.
	 * 
	 * @throws Exception
	 */
	public EndInstView(int codCliente, String nome, int codEndInst, boolean novo) throws Exception {
		cadastro = novo;
		setTitle("Cadastro Endere\u00E7o de Instala\u00E7\u00E3o");
		setModal(true);
		setBounds(100, 100, 664, 553);

		modeloContas = new ContasTableModel();

		tbContas = new JTable();
		tbContas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (modeloContas.getRowCount() > 0) {
						int linha = tbContas.getSelectedRow();
						int codigo = modeloContas.getIdConta(linha);
						try {
							new ContaView(codCliente, codEndInst, codigo, false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		tbContas.setModel(modeloContas);
		tbContas.setFillsViewportHeight(true);
		tbContas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbContas.getColumnModel().getColumn(0).setPreferredWidth(35);
		tbContas.getColumnModel().getColumn(1).setPreferredWidth(45);
		tbContas.getColumnModel().getColumn(2).setPreferredWidth(110);
		tbContas.getColumnModel().getColumn(3).setPreferredWidth(80);
		tbContas.getColumnModel().getColumn(4).setPreferredWidth(80);
		tbContas.getColumnModel().getColumn(5).setPreferredWidth(80);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(106, 293, 437, 171);
		getContentPane().add(scrollPane);
		scrollPane.add(tbContas);
		scrollPane.setViewportView(tbContas);

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
		tfCodCliente.setBounds(104, 13, 50, 20);
		tfCodCliente.setText(String.valueOf(codCliente));
		tfCodCliente.setEditable(false);
		getContentPane().add(tfCodCliente);
		tfCodCliente.setColumns(10);

		tfNomeCliente = new JTextField();
		tfNomeCliente.setBounds(167, 13, 294, 21);
		tfNomeCliente.setText(nome);
		tfNomeCliente.setEditable(false);
		getContentPane().add(tfNomeCliente);
		tfNomeCliente.setColumns(10);

		tfEndereco = new JTextField();
		tfEndereco.setBounds(104, 88, 357, 20);
		tfEndereco.setColumns(10);
		getContentPane().add(tfEndereco);

		tfGleba = new JTextField();
		tfGleba.setBounds(104, 122, 44, 20);
		tfGleba.setColumns(10);
		getContentPane().add(tfGleba);

		tfCidade = new JTextField();
		tfCidade.setEditable(false);
		tfCidade.setBounds(104, 190, 199, 20);
		tfCidade.setColumns(10);
		getContentPane().add(tfCidade);

		tfChacara = new JTextField();
		tfChacara.setBounds(212, 122, 44, 20);
		tfChacara.setColumns(10);
		getContentPane().add(tfChacara);

		tfUf = new JTextField();
		tfUf.setEditable(false);
		tfUf.setBounds(341, 190, 44, 20);
		tfUf.setColumns(10);
		getContentPane().add(tfUf);

		tfCodEndInst = new JTextField();
		tfCodEndInst.setBounds(104, 57, 50, 19);
		tfCodEndInst.setEditable(false);
		tfCodEndInst.setText(String.valueOf(codEndInst));
		getContentPane().add(tfCodEndInst);
		tfCodEndInst.setColumns(10);

		JLabel lblCodEndInst = new JLabel("Cod. End Inst.:");
		lblCodEndInst.setBounds(10, 59, 79, 16);
		getContentPane().add(lblCodEndInst);

		btnHidrometros = new JButton("Hidrometros");
		btnHidrometros.setBounds(491, 87, 142, 23);
		btnHidrometros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new HidroEndInstView(codEndInst);
				} catch (Exception e1) {
					limparCampos();
					String erro = e1.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro, "Erro", JOptionPane.OK_OPTION);

				}
			}
		});
		getContentPane().add(btnHidrometros);

		tfCodUnidCons = new JTextField();
		tfCodUnidCons.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					try {
						buscaUnidadeConsumo(Integer.parseInt(tfCodUnidCons.getText()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(contentPanePrincipal, "Unidade Consumo não encontrada",
								"Endereço Instalação", JOptionPane.WARNING_MESSAGE);
						limparUnidConsumo();
					}
				}
			}
		});
		tfCodUnidCons.setBounds(104, 156, 44, 20);
		getContentPane().add(tfCodUnidCons);
		tfCodUnidCons.setColumns(10);

		tfNomeUnidCons = new JTextField();
		tfNomeUnidCons.setEditable(false);
		tfNomeUnidCons.setBounds(165, 156, 296, 20);
		getContentPane().add(tfNomeUnidCons);
		tfNomeUnidCons.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 42, 644, 2);
		getContentPane().add(separator);

		btnEndCobranca = new JButton("End. Cobran\u00E7a");
		btnEndCobranca.setBounds(491, 56, 142, 23);
		btnEndCobranca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					enderecoCobranca(codEndInst);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		getContentPane().add(btnEndCobranca);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 258, 644, 2);
		getContentPane().add(separator_1);

		JLabel lblContas = new JLabel("Contas");
		lblContas.setBounds(283, 265, 55, 16);
		getContentPane().add(lblContas);

		btnNovaConta = new JButton("Nova Conta");
		btnNovaConta.setBounds(267, 476, 98, 26);
		getContentPane().add(btnNovaConta);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (cadastro) {
						inserirEndInst();
						cadastro = false;
						JOptionPane.showMessageDialog(contentPanePrincipal,
								"Endereço de Cobrança cadastrado com sucesso.", "Cadastro Endereço de Cobrança",
								JOptionPane.INFORMATION_MESSAGE,
								new ImageIcon(getClass().getResource("recursos/okgreen.png")));
					} else {
						alterarEndInst();
						JOptionPane.showMessageDialog(contentPanePrincipal,
								"Endereço de Cobrança cadastrado com sucesso.", "Cadastro Endereço de Cobrança",
								JOptionPane.INFORMATION_MESSAGE,
								new ImageIcon(getClass().getResource("recursos/okgreen.png")));
					}
					desabilita();
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
		btnSalvar.setBounds(252, 220, 98, 26);
		getContentPane().add(btnSalvar);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					listaContas(codEndInst);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnAtualizar.setBounds(538, 476, 98, 26);
		getContentPane().add(btnAtualizar);

		consultaEndInst(codEndInst);
		listaContas(codEndInst);
		desabilita();

		setVisible(true);
	}

	private void alterarEndInst() throws SQLException {
		try {
			if (tfEndereco.getText().isEmpty() || tfNomeUnidCons.getText().isEmpty()) {
				throw new Exception("Preencha os campos obrigatórios.");
			} else {
				EndInst objEndInst = new EndInst(Integer.parseInt(tfCodEndInst.getText()),
						Integer.parseInt(tfCodCliente.getText()), tfEndereco.getText(), tfGleba.getText(),
						tfChacara.getText(), Integer.parseInt(tfCodUnidCons.getText()), tfNomeUnidCons.getText(),
						tfCidade.getText(), tfUf.getText());
				EndInstDAO.alterarEndInst(objEndInst);
			}
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}

	}

	private void inserirEndInst() throws Exception {
		try {
			if (tfEndereco.getText().isEmpty() || tfNomeUnidCons.getText().isEmpty()) {
				throw new Exception("Preencha os campos obrigatórios.");
			} else {
				EndInst objEndInst = new EndInst(Integer.parseInt(tfCodEndInst.getText()),
						Integer.parseInt(tfCodCliente.getText()), tfEndereco.getText(), tfGleba.getText(),
						tfChacara.getText(), Integer.parseInt(tfCodUnidCons.getText()), tfNomeUnidCons.getText(),
						tfCidade.getText(), tfUf.getText());
				EndInstDAO.incluirEndInst(objEndInst);
			}
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
	}

	private void listaContas(int codEndInst) throws Exception {
		if (modeloContas.getRowCount() > 0) {
			modeloContas.limpaLista();
		}
		try {
			modeloContas.adicionaLista(ContaDAO.listarContas(codEndInst));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void consultaEndInst(int codEndInst) throws Exception {
		try {
			EndInst objEndInst = EndInstDAO.consultaEndInst(codEndInst);
			tfEndereco.setText(objEndInst.getEndereco());
			tfGleba.setText(objEndInst.getGleba());
			tfChacara.setText(objEndInst.getChacara());
			tfCodUnidCons.setText(Integer.toString(objEndInst.getCodUnidadeConsumo()));
			tfNomeUnidCons.setText(objEndInst.getUnidadeConsumo());
			tfCidade.setText(objEndInst.getCidade());
			tfUf.setText(objEndInst.getUf());
		} catch (Exception e) {

		}

	}

	private void enderecoCobranca(int codEndInst) throws Exception {
		try {
			new JDialogEndCob(codEndInst);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public void desabilita() {
		if (cadastro) {
			btnNovaConta.setEnabled(false);
			btnHidrometros.setEnabled(false);
			btnEndCobranca.setEnabled(false);
			btnSalvar.setText("Inserir");
		} else {
			btnNovaConta.setEnabled(true);
			btnHidrometros.setEnabled(true);
			btnEndCobranca.setEnabled(true);
			btnSalvar.setText("Salvar");
		}
	}

	public void habilitaCampos() {
		tfEndereco.setEditable(true);
		tfGleba.setEditable(true);
		tfChacara.setEditable(true);
		// textFieldUnidConsumo.setEditable(true);
		tfCidade.setEditable(true);
		tfUf.setEditable(true);
	}

	public void buscaUnidadeConsumo(int codUnid) throws Exception {
		try {
			UnidadeConsumo unidCons = UnidConsumoDAO.buscarUnidadeConsumo(codUnid);
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
	}

	private void limparUnidConsumo() {
		tfCidade.setText("");
		tfUf.setText("");
		tfNomeUnidCons.setText("");
	}
}
