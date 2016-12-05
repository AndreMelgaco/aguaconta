package usuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;

import dados.ConfigGeral;
import tablemodel.ConfigValoresTableModel;
import banco.ConfigDAO;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class ConfigView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanePrincipal;
	private JFormattedTextField tfDataVenc;
	private JFormattedTextField tfDataLeitura;
	private JTextField tfJuros;
	private JTextField tfMulta;
	private JTextField tfTextoConta;
	private JTable tbConfig;
	private static ConfigValoresTableModel modelo;
	private JTextField tfMes;
	private JTextField tfAno;
	private DateFormat df;
	private JButton btnAtualizar;
	private JButton btnNovaFaixa;
	private JButton btnSalvar;
	private int codigo;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ConfigView() {
		setTitle("Configura\u00E7\u00F5es Gerais");
		setBounds(100, 100, 555, 426);
		getContentPane().setLayout(null);

		modelo = new ConfigValoresTableModel();

		tbConfig = new JTable();
		tbConfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (modelo.getRowCount() > 0) {
						int linha = tbConfig.getSelectedRow();
						int faixa = modelo.getConfigValores(linha);
						try {
							new AddFaixas(codigo, faixa, Integer.parseInt(tfMes.getText()), Integer.parseInt(tfAno.getText()), false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		tbConfig.setModel(modelo);
		tbConfig.setFillsViewportHeight(true);
		tbConfig.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbConfig.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbConfig.getColumnModel().getColumn(1).setPreferredWidth(100);
		tbConfig.getColumnModel().getColumn(2).setPreferredWidth(100);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(272, 81, 256, 203);
		getContentPane().add(scrollPane);
		scrollPane.add(tbConfig);
		scrollPane.setViewportView(tbConfig);

		JLabel lblDataDeVencimento = new JLabel("Data de Vencimento:");
		lblDataDeVencimento.setBounds(12, 84, 117, 16);
		getContentPane().add(lblDataDeVencimento);

		JLabel lblDataDeLeitura = new JLabel("Data de Leitura:");
		lblDataDeLeitura.setBounds(12, 111, 96, 16);
		getContentPane().add(lblDataDeLeitura);

		df = new SimpleDateFormat("dd/MM/yyyy");
		tfDataVenc = new JFormattedTextField(df);
		tfDataVenc.setEditable(false);
		tfDataVenc.setBounds(139, 82, 96, 20);
		getContentPane().add(tfDataVenc);
		tfDataVenc.setColumns(10);

		tfDataLeitura = new JFormattedTextField(df);
		tfDataLeitura.setEditable(false);
		tfDataLeitura.setColumns(10);
		tfDataLeitura.setBounds(139, 109, 96, 20);
		getContentPane().add(tfDataLeitura);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 142, 260, 10);
		getContentPane().add(separator);

		JLabel lblJuros = new JLabel("Juros:");
		lblJuros.setBounds(31, 163, 40, 16);
		getContentPane().add(lblJuros);

		JLabel lblMulta = new JLabel("Multa:");
		lblMulta.setBounds(139, 163, 40, 16);
		getContentPane().add(lblMulta);

		JLabel label = new JLabel("%");
		label.setBounds(113, 163, 28, 16);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("%");
		label_1.setBounds(219, 165, 28, 16);
		getContentPane().add(label_1);

		tfJuros = new JTextField();
		tfJuros.setEditable(false);
		tfJuros.setBounds(75, 163, 33, 20);
		getContentPane().add(tfJuros);
		tfJuros.setColumns(10);

		tfMulta = new JTextField();
		tfMulta.setEditable(false);
		tfMulta.setColumns(10);
		tfMulta.setBounds(180, 163, 33, 20);
		getContentPane().add(tfMulta);

		JComboBox cbCondRegiao = new JComboBox();
		try {
			cbCondRegiao.setModel(new DefaultComboBoxModel(buscaCondominios().toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		cbCondRegiao.setBounds(139, 8, 237, 25);
		getContentPane().add(cbCondRegiao);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					atualizaCampos();
				} catch (Exception e) {
					String erro = e.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro, "Cliente não cadastrado.",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSalvar.setBounds(219, 347, 98, 26);
		getContentPane().add(btnSalvar);

		JLabel lblTextoDaConta = new JLabel("Texto da Conta:");
		lblTextoDaConta.setBounds(10, 203, 88, 16);
		getContentPane().add(lblTextoDaConta);

		tfTextoConta = new JTextField();
		tfTextoConta.setEditable(false);
		tfTextoConta.setBounds(0, 230, 248, 75);
		getContentPane().add(tfTextoConta);
		tfTextoConta.setColumns(10);

		JLabel lblUnidConsumo = new JLabel("Unidade Consumo:");
		lblUnidConsumo.setBounds(12, 12, 117, 16);
		getContentPane().add(lblUnidConsumo);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				codigo = cbCondRegiao.getSelectedIndex() + 1;
				carregaDados(codigo);
				btnSalvar.setEnabled(true);
			}
		});
		btnOk.setBounds(219, 43, 98, 26);
		getContentPane().add(btnOk);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 73, 665, 10);
		getContentPane().add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 190, 260, 2);
		getContentPane().add(separator_3);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(260, 73, 10, 263);
		getContentPane().add(separator_1);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(0, 336, 665, 10);
		getContentPane().add(separator_4);

		JLabel lblMs = new JLabel("M\u00EAs:");
		lblMs.setBounds(425, 12, 40, 16);
		getContentPane().add(lblMs);

		JLabel lblAno = new JLabel("Ano:");
		lblAno.setBounds(425, 43, 40, 16);
		getContentPane().add(lblAno);

		tfMes = new JTextField();
		tfMes.setEditable(false);
		tfMes.setBounds(466, 10, 40, 20);
		getContentPane().add(tfMes);
		tfMes.setColumns(10);

		tfAno = new JTextField();
		tfAno.setEditable(false);
		tfAno.setBounds(466, 41, 40, 20);
		getContentPane().add(tfAno);
		tfAno.setColumns(10);

		btnNovaFaixa = new JButton("Nova Faixa");
		btnNovaFaixa.setEnabled(false);
		btnNovaFaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new AddFaixas(codigo, 0, Integer.parseInt(tfMes.getText()), Integer.parseInt(tfAno.getText()), true);
					listaFaixas(codigo);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNovaFaixa.setBounds(272, 298, 98, 26);
		getContentPane().add(btnNovaFaixa);

		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setEnabled(false);
		btnAtualizar.setBounds(440, 298, 88, 26);
		getContentPane().add(btnAtualizar);

		setVisible(true);

	}

	private void listaFaixas(int codigo) throws Exception {
		if (modelo.getRowCount() > 0) {
			modelo.limpaLista();
		}
		try {
			modelo.adicionaLista(ConfigDAO.listarFaixas(codigo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void carregaDados(int codigo) {
		System.out.println(codigo);
		try {
			ConfigGeral cg = ConfigDAO.retornaConfig(codigo);
			tfDataVenc.setValue(cg.getDataVencimento());
			tfDataLeitura.setValue(cg.getDataLeitura());
			tfMes.setText(Integer.toString(cg.getMesConta()));
			tfAno.setText(Integer.toString(cg.getAnoConta()));
			tfJuros.setText(Float.toString(cg.getJuros()));
			tfMulta.setText(Float.toString(cg.getMulta()));
			tfTextoConta.setText(cg.getTexto_conta());

			listaFaixas(codigo);

			tfDataVenc.setEditable(true);
			tfDataLeitura.setEditable(true);
			tfJuros.setEditable(true);
			tfMulta.setEditable(true);
			tfTextoConta.setEditable(true);
			btnAtualizar.setEnabled(true);
			btnNovaFaixa.setEnabled(true);
			btnSalvar.setEnabled(true);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private List<String> buscaCondominios() throws Exception {
		try {
			List<String> temp = ConfigDAO.buscaUnidConsumo();

			return temp;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	protected void atualizaCampos() {
		try {

			if (tfDataVenc.getText().isEmpty() || tfDataLeitura.getText().isEmpty() || tfMes.getText().isEmpty()
					|| tfAno.getText().isEmpty() || tfJuros.getText().isEmpty() || tfMulta.getText().isEmpty()
					|| tfTextoConta.getText().isEmpty()) {
				throw new Exception("Campos em Branco");

			} else {

				Date venc = new Date(df.parse(tfDataVenc.getText()).getTime());
				Date leit = new Date(df.parse(tfDataLeitura.getText()).getTime());

				ConfigGeral cg = new ConfigGeral(codigo, venc, leit, Integer.parseInt(tfMes.getText()),
						Integer.parseInt(tfAno.getText()), Float.parseFloat(tfJuros.getText()),
						Float.parseFloat(tfMulta.getText()), tfTextoConta.getText());

				System.out.println(cg.toString());

				ConfigDAO.atualizaConfig(cg);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
