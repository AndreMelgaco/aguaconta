package usuario;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import banco.ClienteDAO;
import banco.EndInstDAO;
import dados.Cliente;

import tablemodel.EndInstTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class ClienteConsView extends JDialog {

	private JPanel contentPanePrincipal;
	private JTextField textFieldCodCliente;
	private JTextField textFieldNomeCliente;
	private JTextField textFieldCpfCnpj;
	private JTextField textFieldIdentidade;
	private JTextField textFieldEmail;
	private JTextField textFieldTelFixo;
	private JTextField textFieldCelular;
	private JTextField textFieldTelRecado;
	private JLabel labelNomeCliente;
	private JLabel lblCpf;
	private JLabel lblIdent;
	private JLabel lblEmail;
	private JLabel lblTelFixo;
	private JLabel lblCelular;
	private JLabel lblTelRecado;
	private JButton btnSalvar;
	private JButton btnNovaInstalacao;
	private JRadioButton btnPF;
	private JRadioButton btnPJ;
	private JRadioButton btnGov;
	private JTable tbEndInst;  
	private static EndInstTableModel modeloEndInst;
	private boolean cadastro;
	private JButton btnAtualizar;
	
	

	public ClienteConsView(int codCliente, boolean novo) throws Exception {
		cadastro = novo;
		setTitle("Cadastro de Clientes");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 805, 544);
		getContentPane().setLayout(null);
		
		modeloEndInst = new EndInstTableModel();
		
		tbEndInst = new JTable();
		tbEndInst.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int linha = tbEndInst.getSelectedRow();
					int codigo = modeloEndInst.getCodEndInst(linha);
					try {
						new EndInstView(codCliente, textFieldNomeCliente.getText(), codigo, false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}				
			}
		});
		tbEndInst.setModel(modeloEndInst);
		tbEndInst.setFillsViewportHeight(true);
		tbEndInst.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbEndInst.getColumnModel().getColumn(0).setPreferredWidth(35);
		tbEndInst.getColumnModel().getColumn(1).setPreferredWidth(300);
		tbEndInst.getColumnModel().getColumn(2).setPreferredWidth(80);
		tbEndInst.getColumnModel().getColumn(3).setPreferredWidth(80);
		tbEndInst.getColumnModel().getColumn(4).setPreferredWidth(150);
		tbEndInst.getColumnModel().getColumn(5).setPreferredWidth(80);
		tbEndInst.getColumnModel().getColumn(6).setPreferredWidth(35);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 284, 767, 171);
		getContentPane().add(scrollPane);
		scrollPane.add(tbEndInst);
		scrollPane.setViewportView(tbEndInst);
		
		textFieldCodCliente = new JTextField();
		textFieldCodCliente.setEditable(false);
		textFieldCodCliente.setText(String.valueOf(codCliente));
		textFieldCodCliente.setBounds(470, 12, 45, 20);
		getContentPane().add(textFieldCodCliente);
		textFieldCodCliente.setColumns(10);

		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setBounds(90, 23, 350, 20);
		getContentPane().add(textFieldNomeCliente);
		textFieldNomeCliente.setColumns(10);

		textFieldCpfCnpj = new JTextField();
		textFieldCpfCnpj.setBounds(90, 84, 126, 20);
		getContentPane().add(textFieldCpfCnpj);
		textFieldCpfCnpj.setColumns(10);

		textFieldIdentidade = new JTextField();
		textFieldIdentidade.setBounds(271, 84, 110, 20);
		getContentPane().add(textFieldIdentidade);
		textFieldIdentidade.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(90, 117, 350, 20);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);

		textFieldTelFixo = new JTextField();
		textFieldTelFixo.setBounds(90, 149, 110, 20);
		getContentPane().add(textFieldTelFixo);
		textFieldTelFixo.setColumns(10);

		textFieldCelular = new JTextField();
		textFieldCelular.setBounds(271, 149, 110, 20);
		getContentPane().add(textFieldCelular);
		textFieldCelular.setColumns(10);

		textFieldTelRecado = new JTextField();
		textFieldTelRecado.setBounds(90, 181, 350, 20);
		getContentPane().add(textFieldTelRecado);
		textFieldTelRecado.setColumns(10);

		labelNomeCliente = new JLabel("Nome:");
		labelNomeCliente.setBounds(10, 23, 63, 21);
		getContentPane().add(labelNomeCliente);

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 84, 70, 21);
		getContentPane().add(lblCpf);

		lblIdent = new JLabel("RG / I.E.:");
		lblIdent.setBounds(223, 84, 45, 21);
		getContentPane().add(lblIdent);

		lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 117, 63, 21);
		getContentPane().add(lblEmail);

		lblTelFixo = new JLabel("Telefone:");
		lblTelFixo.setBounds(10, 149, 63, 21);
		getContentPane().add(lblTelFixo);

		lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(218, 149, 78, 21);
		getContentPane().add(lblCelular);

		lblTelRecado = new JLabel("Recados:");
		lblTelRecado.setBounds(10, 182, 78, 21);
		getContentPane().add(lblTelRecado);

		// Inicio RadioButton e RadioGroup para decisão de CPF ou CNPJ
		btnPF = new JRadioButton("1 - Pessoa F\u00EDsica");
		btnPF.setSelected(true);
		btnPF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCpf.setText("CPF:");
			}
		});
		btnPF.setBounds(10, 52, 125, 24);
		getContentPane().add(btnPF);

		btnPJ = new JRadioButton("2 - Pessoa Jur\u00EDdica");
		btnPJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblCpf.setText("CNPJ:");
			}
		});
		btnPJ.setBounds(139, 52, 141, 24);
		getContentPane().add(btnPJ);
		
		btnGov = new JRadioButton("3 - Governo");
		btnGov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCpf.setText("CNPJ:");
			}
		});
		btnGov.setBounds(284, 52, 121, 24);
		getContentPane().add(btnGov);

		ButtonGroup cpfCnpjgroup = new ButtonGroup();
		cpfCnpjgroup.add(btnPJ);
		cpfCnpjgroup.add(btnPF);
		cpfCnpjgroup.add(btnGov);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String tipo;
					if (btnPJ.isSelected()) {
						tipo = "PJ";
					} else if (btnPF.isSelected()){
						tipo = "PF"; 
					} else {
						tipo = "GOV";
					}
					if(cadastro){
						inserirCliente(tipo);
						cadastro = false;
						JOptionPane.showMessageDialog(
								contentPanePrincipal,
								"Cadastro Incluido com Sucesso",
								"Inclusão Cliente",
								JOptionPane.INFORMATION_MESSAGE,
								new ImageIcon(getClass().getResource(
										"recursos/okgreen.png")));
					}else{
						alterarCliente(tipo);
						JOptionPane.showMessageDialog(
								contentPanePrincipal,
								"Cadastro Alterado com Sucesso",
								"Alteração Cadastro Cliente",
								JOptionPane.INFORMATION_MESSAGE,
								new ImageIcon(getClass().getResource(
										"recursos/okgreen.png")));
					}
					desabilita();

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
		btnSalvar.setBounds(197, 213, 99, 23);
		getContentPane().add(btnSalvar);
		
		btnNovaInstalacao = new JButton("Nova Instala\u00E7\u00E3o");
		btnNovaInstalacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new EndInstView(codCliente, textFieldNomeCliente.getText(), EndInstDAO.novoEndInst(), true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
		});
		btnNovaInstalacao.setBounds(323, 467, 126, 26);
		getContentPane().add(btnNovaInstalacao);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 253, 789, 2);
		getContentPane().add(separator);
		
		JLabel lblInstalaes = new JLabel("Instala\u00E7\u00F5es");
		lblInstalaes.setBounds(345, 256, 70, 16);
		getContentPane().add(lblInstalaes);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listaenderecoInstalacao(codCliente);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAtualizar.setBounds(679, 467, 98, 26);
		getContentPane().add(btnAtualizar);
		
		consClienteCod(codCliente);
		listaenderecoInstalacao(codCliente);
		desabilita();
		

		setVisible(true);

	}
	
	private void inserirCliente(String tipo) throws SQLException, Exception {
		try {
			if (textFieldCpfCnpj.getText().isEmpty()
					|| textFieldNomeCliente.getText().isEmpty()
					|| tipo == " ") {
				throw new Exception("Preencha os campos obrigatórios.");
			} else {
				Cliente objCliente = new Cliente(
						Integer.parseInt(textFieldCodCliente.getText()),
						textFieldNomeCliente.getText(), tipo,
						textFieldCpfCnpj.getText(),
						textFieldIdentidade.getText(),
						textFieldEmail.getText(), textFieldTelFixo.getText(),
						textFieldCelular.getText(),
						textFieldTelRecado.getText());
//				System.out.println(objCliente.toString());
				ClienteDAO.incluirCliente(objCliente);
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

	}

	protected void listaenderecoInstalacao(int codCliente) throws Exception {
		if(modeloEndInst.getRowCount() > 0){
			modeloEndInst.limpaLista();
		}
		try {
			modeloEndInst.adicionaLista(EndInstDAO.listarEndInstCliente(codCliente));
		} catch (Exception e) {
			
		}

	}

	private void alterarCliente(String tipo) throws Exception, SQLException {
		try {
			if (textFieldCpfCnpj.getText().isEmpty()
					|| textFieldNomeCliente.getText().isEmpty()
					|| tipo == " ") {
				throw new Exception("Preencha os campos obrigatórios.");
			} else {
			Cliente objCliente = new Cliente(
						Integer.parseInt(textFieldCodCliente.getText()),
						textFieldNomeCliente.getText(), tipo,
						textFieldCpfCnpj.getText(),
						textFieldIdentidade.getText(),
						textFieldEmail.getText(), textFieldTelFixo.getText(),
						textFieldCelular.getText(),
						textFieldTelRecado.getText());
				ClienteDAO.alterarCliente(objCliente);
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

	}
	
	private void consClienteCod(int codigo) throws SQLException, Exception {
		try {
			Cliente objCliente = ClienteDAO.consultaClienteCod(codigo);
			
			textFieldNomeCliente.setText(objCliente.getNome());
			if (objCliente.getTipo().equals("PJ")) {
				btnPJ.setSelected(true);
			} else if(objCliente.getTipo().equals("PF")) {
				btnPF.setSelected(true);
			}else{
				btnGov.setSelected(true);
			}
			textFieldCpfCnpj.setText(objCliente.getCpfCnpj());
			textFieldIdentidade.setText(objCliente.getIdentidade());
			textFieldEmail.setText(objCliente.getEmail());
			textFieldTelFixo.setText(objCliente.getTel_fixo());
			textFieldCelular.setText(objCliente.getCelular());
			textFieldTelRecado.setText(objCliente.getTel_recado());		
			
		} catch (Exception e) {
			
		}
		
	}
	
	private void desabilita(){
		if(cadastro){
			btnNovaInstalacao.setEnabled(false);
			btnSalvar.setText("Inserir");
		}else{
			btnNovaInstalacao.setEnabled(true);
			btnSalvar.setText("Salvar");
		}
	}

	@SuppressWarnings("unused")
	private void consultaCliente() throws Exception, SQLException {
		try {
			if (textFieldCpfCnpj.getText().isEmpty()) {
				throw new Exception("CPF/CNPJ em branco.");
			} else {
				Cliente objCliente = ClienteDAO
						.consultaClienteCpf(textFieldCpfCnpj.getText());
				textFieldCodCliente.setText(String.valueOf(objCliente.getCodCliente()));
				textFieldNomeCliente.setText(objCliente.getNome());
				System.out.println(objCliente.getTipo());
				if (objCliente.getTipo() == "PJ") {
					btnPJ.setSelected(true);
				} else if(objCliente.getTipo() == "PF") {
					btnPF.setSelected(true);
				}else{
					btnGov.setSelected(true);
				}
				textFieldIdentidade.setText(objCliente.getIdentidade());
				textFieldEmail.setText(objCliente.getEmail());
				textFieldTelFixo.setText(objCliente.getTel_fixo());
				textFieldCelular.setText(objCliente.getCelular());
				textFieldTelRecado.setText(objCliente.getTel_recado());
			}
		} catch (Exception e1) {
			throw new Exception(e1.getMessage());
		}
	}
}
