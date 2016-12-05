package usuario;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import dados.Conta;
import tablemodel.ItensContaTableModel;
import banco.BancoConta;
import banco.ContaDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSeparator;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class ContaView extends JDialog {
	private JPanel contentPanePrincipal;
	private JTextField tfCodCliente;
	private JTextField tfHidrometro;
	private JTextField tfLeituraAnterior;
	private JTextField tfLeituraAtual;
	private JTextField tfConsumo;
	private JTextField tfDescricao;
	private JTextField tfTotal;
	private JFormattedTextField tfDtLeitura;
	private JFormattedTextField tfDtVencimento;
	private JTextField tfCodEndInst;
	private JTextField tfMes;
	private JTextField tfAno;
	private JTable tbItens;
	private static ItensContaTableModel modeloItensConta;
	private boolean cadastro;

	/**
	 * Create the dialog.
	 * @throws Exception 
	 */
	public ContaView(int codCliente, int codInst, int codConta, boolean novo) throws Exception {
		cadastro = novo;
		setTitle("Cadastro de Leitura");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 568, 528);
		getContentPane().setLayout(null);

		modeloItensConta = new ItensContaTableModel();

		tbItens = new JTable();
		tbItens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// int linha = tbContas.getSelectedRow();
					// int codigo = modeloContas.getIdConta(linha);
					// colocar tela de detalhe de conta
				}
			}
		});
		tbItens.setModel(modeloItensConta);
		tbItens.setFillsViewportHeight(true);
		tbItens.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbItens.getColumnModel().getColumn(0).setPreferredWidth(40);
		tbItens.getColumnModel().getColumn(1).setPreferredWidth(250);
		tbItens.getColumnModel().getColumn(2).setPreferredWidth(90);
		tbItens.getColumnModel().getColumn(3).setPreferredWidth(70);
		tbItens.getColumnModel().getColumn(4).setPreferredWidth(90);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 145, 542, 171);
		getContentPane().add(scrollPane);
		scrollPane.add(tbItens);
		scrollPane.setViewportView(tbItens);

		JLabel lblCodCliente = new JLabel("Cod. Cliente:");
		lblCodCliente.setBounds(10, 12, 70, 19);
		getContentPane().add(lblCodCliente);

		JLabel lblHidrometro = new JLabel("Hidrometro:");
		lblHidrometro.setBounds(10, 50, 70, 19);
		getContentPane().add(lblHidrometro);

		JLabel lblMes = new JLabel("M\u00EAs:");
		lblMes.setBounds(256, 12, 32, 19);
		getContentPane().add(lblMes);

		JLabel lblLeituraAnterior = new JLabel("Leitura Anterior:");
		lblLeituraAnterior.setBounds(10, 90, 86, 19);
		getContentPane().add(lblLeituraAnterior);

		JLabel lblLeituraAtual = new JLabel("Leitura Atual:");
		lblLeituraAtual.setBounds(163, 90, 70, 19);
		getContentPane().add(lblLeituraAtual);

		JLabel lblConsumo = new JLabel("Consumo:");
		lblConsumo.setBounds(306, 90, 53, 19);
		getContentPane().add(lblConsumo);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setBounds(10, 403, 53, 19);
		getContentPane().add(lblDescricao);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(403, 331, 53, 19);
		getContentPane().add(lblTotal);

		JLabel lblLeitura = new JLabel("Data Leitura:");
		lblLeitura.setBounds(167, 50, 66, 19);
		getContentPane().add(lblLeitura);
		
		JLabel lblCodEndInst = new JLabel("Cod. End. Inst.:");
		lblCodEndInst.setBounds(123, 12, 86, 19);
		getContentPane().add(lblCodEndInst);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setBounds(337, 14, 32, 14);
		getContentPane().add(lblAno);
		
		JLabel lblVencimento = new JLabel("Data Vencimento:");
		lblVencimento.setBounds(328, 50, 85, 19);
		getContentPane().add(lblVencimento);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 120, 851, 2);
		getContentPane().add(separator);

		tfCodCliente = new JTextField();
		tfCodCliente.setEditable(false);
		tfCodCliente.setText(String.valueOf(codCliente));
		tfCodCliente.setBounds(79, 11, 39, 20);
		getContentPane().add(tfCodCliente);
		tfCodCliente.setColumns(10);

		tfHidrometro = new JTextField();
		tfHidrometro.setEditable(false);
		tfHidrometro.setColumns(10);
		tfHidrometro.setBounds(67, 49, 86, 20);
		getContentPane().add(tfHidrometro);

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		tfDtLeitura = new JFormattedTextField(df);
		tfDtLeitura.setColumns(10);
		tfDtLeitura.setBounds(229, 49, 81, 20);
		getContentPane().add(tfDtLeitura);

		tfLeituraAnterior = new JTextField();
		tfLeituraAnterior.setEditable(false);
		tfLeituraAnterior.setColumns(10);
		tfLeituraAnterior.setBounds(94, 90, 59, 20);
		getContentPane().add(tfLeituraAnterior);

		tfLeituraAtual = new JTextField();
		tfLeituraAtual.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					int leituraAtual = Integer.parseInt(tfLeituraAtual.getText());
					int leituraAnterior = Integer.parseInt(tfLeituraAnterior.getText());
					if (leituraAtual >= leituraAnterior) {
						calcula();
					} else {
						JOptionPane.showMessageDialog(contentPanePrincipal,
								"Leitura Atual não pode ter o valor menor que a Leitura Anterior.",
								"Leitura Atual inválida.", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		tfLeituraAtual.setColumns(10);
		tfLeituraAtual.setBounds(229, 90, 59, 20);
		getContentPane().add(tfLeituraAtual);

		tfConsumo = new JTextField();
		tfConsumo.setEditable(false);
		tfConsumo.setColumns(10);
		tfConsumo.setBounds(354, 89, 59, 20);
		getContentPane().add(tfConsumo);

		tfDescricao = new JTextField();
		tfDescricao.setColumns(10);
		tfDescricao.setBounds(67, 390, 482, 45);
		getContentPane().add(tfDescricao);

		tfTotal = new JTextField();
		tfTotal.setEditable(false);
		tfTotal.setColumns(10);
		tfTotal.setBounds(459, 331, 86, 20);
		getContentPane().add(tfTotal);

		tfDtVencimento = new JFormattedTextField(df);
		tfDtVencimento.setBounds(423, 49, 86, 20);
		getContentPane().add(tfDtVencimento);
		tfDtVencimento.setColumns(10);

		tfCodEndInst = new JTextField();
		tfCodEndInst.setEditable(false);
		tfCodEndInst.setText(String.valueOf(codInst));
		tfCodEndInst.setBounds(202, 11, 44, 20);
		getContentPane().add(tfCodEndInst);
		tfCodEndInst.setColumns(10);

		tfMes = new JTextField();
		tfMes.setBounds(283, 11, 39, 20);
		getContentPane().add(tfMes);
		tfMes.setColumns(10);

		tfAno = new JTextField();
		tfAno.setBounds(364, 11, 44, 20);
		getContentPane().add(tfAno);
		tfAno.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (cadastro) {
						cadastrarConta();
					} else {
						alterarConta();
						JOptionPane.showMessageDialog(contentPanePrincipal, "Conta Salva", "Alteração Visita",
								JOptionPane.INFORMATION_MESSAGE,
								new ImageIcon(getClass().getResource("recursos/okgreen.png")));
					}
					cadastro = false;
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnSalvar.setBounds(229, 446, 98, 26);
		getContentPane().add(btnSalvar);


		JButton btnAdicionarItens = new JButton("Adicionar Itens");
		btnAdicionarItens.setBounds(10, 327, 106, 23);
		getContentPane().add(btnAdicionarItens);

		carregarConta(codConta);
		
		setVisible(true);

	}

	protected void alterarConta() {
		// TODO Auto-generated method stub

	}

	protected void cadastrarConta() {
		// TODO Auto-generated method stub

	}

	protected void calcula() {

	}

	protected void carregarConta(int idConta) throws Exception {
		try {
			Conta objConta = ContaDAO.consultaConta(idConta);
			tfMes.setText(Integer.toString(objConta.getMes()));
			tfAno.setText(Integer.toString(objConta.getAno()));
			tfHidrometro.setText(objConta.getHidrometro());
			tfDtLeitura.setValue(objConta.getDtLeitura());
			tfDtVencimento.setValue(objConta.getDtVencimento());
			tfLeituraAnterior.setText(Integer.toString(objConta.getLeituraAnterior()));
			tfLeituraAtual.setText(Integer.toString(objConta.getLeituraAtual()));
			tfConsumo.setText(Integer.toString(objConta.getConsumo()));
			tfDescricao.setText(objConta.getObservacoes());
	//		tfTotal.setText(Float.toString(objConta.getTotal()));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	protected void buscarContaAnterior(int mes, int ano) throws Exception {
		if (mes == 1) {
			mes = 12;
			ano = ano - 1;
		} else {
			mes = mes - 1;
		}
		try {
			Conta objConta = BancoConta.contaAtual(Integer.parseInt(tfCodCliente.getText()), tfHidrometro.getText(),
					mes, ano);
			tfLeituraAnterior.setText(Integer.toString(objConta.getLeituraAtual()));
		} catch (Exception e) {
			tfLeituraAnterior.setText("0");
		}

	}
	/*

	protected void cadastraConta(int mes) throws Exception, SQLException {
		try {
			Conta objConta = new Conta(Integer.parseInt(tfCodCliente.getText()), tfHidrometro.getText(), mes,
					Integer.parseInt(textFieldAno.getText()), tfDtLeitura.getText(), tfDtVencimento.getText(),
					Integer.parseInt(tfLeituraAnterior.getText()), Integer.parseInt(tfLeituraAtual.getText()),
					Integer.parseInt(tfConsumo.getText()), Float.parseFloat(textFieldValor.getText()),
					Float.parseFloat(textFieldOutrosValores.getText()), tfDescricao.getText(),
					Float.parseFloat(tfTotal.getText()));
			BancoConta.cadastrarConta(objConta);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}*/
}
