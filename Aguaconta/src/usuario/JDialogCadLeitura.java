package usuario;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import dados.Conta;
import banco.ClienteDAO;
import banco.BancoConta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JSeparator;
import java.awt.Window.Type;

@SuppressWarnings("serial")
public class JDialogCadLeitura extends JDialog {
	private JPanel contentPanePrincipal;
	private JTextField tfHidrometro;
	private JTextField tfLeituraAtual;
	int mesLeitura, anoLeitura, mesVencimento, anoVencimento;
	String dataLeitura = "";
	String dataVencimento = "";
	private JTextField tfDtLeitura;
	private JTextField tfDtVencimento;

	/**
	 * Create the dialog.
	 */
	public JDialogCadLeitura() {
		setType(Type.POPUP);
		setAutoRequestFocus(false);
		setResizable(false);
		setTitle("Cadastro de Leitura");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 299, 239);
		getContentPane().setLayout(null);

		JLabel lblHidrometro = new JLabel("Hidrometro:");
		lblHidrometro.setBounds(53, 93, 82, 16);
		getContentPane().add(lblHidrometro);

		JLabel lblLeituraAtual = new JLabel("Leitura Atual:");
		lblLeituraAtual.setBounds(53, 121, 82, 16);
		getContentPane().add(lblLeituraAtual);

		tfHidrometro = new JTextField();
		tfHidrometro.setBounds(132, 89, 114, 20);
		getContentPane().add(tfHidrometro);
		tfHidrometro.setColumns(10);

		tfLeituraAtual = new JTextField();
		tfLeituraAtual.setBounds(132, 119, 114, 20);
		getContentPane().add(tfLeituraAtual);
		tfLeituraAtual.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					salvarConta();
				} catch (SQLException e2) {
					String erro = e2.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro,
							"Erro", JOptionPane.OK_OPTION);
				} catch (Exception e1) {
					String erro = e1.getMessage();
					JOptionPane.showMessageDialog(contentPanePrincipal, erro,
							"Erro", JOptionPane.OK_OPTION);
				}

			}
		});
		btnSalvar.setBounds(98, 162, 98, 26);
		getContentPane().add(btnSalvar);
		
		JLabel lblDataLeitura = new JLabel("Data Leitura:");
		lblDataLeitura.setBounds(53, 12, 82, 16);
		getContentPane().add(lblDataLeitura);
		
		JLabel lblDataVencimento = new JLabel("Data Vencimento:");
		lblDataVencimento.setBounds(53, 40, 100, 16);
		getContentPane().add(lblDataVencimento);
		
		tfDtLeitura = new JTextField();
		tfDtLeitura.setEditable(false);
		tfDtLeitura.setBounds(164, 10, 82, 20);
		getContentPane().add(tfDtLeitura);
		tfDtLeitura.setColumns(10);
		
		tfDtVencimento = new JTextField();
		tfDtVencimento.setEditable(false);
		tfDtVencimento.setColumns(10);
		tfDtVencimento.setBounds(164, 38, 82, 20);
		getContentPane().add(tfDtVencimento);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 68, 293, 13);
		getContentPane().add(separator);

		setVisible(true);

	}

	protected void salvarConta() throws Exception {

		try {

			int codCliente = ClienteDAO
					.consultaEndInstHidrometro(tfHidrometro.getText());

			Conta objConta = buscarContaAnterior(codCliente);

			Conta objConta2 = new Conta(
					codCliente,
					tfHidrometro.getText(),
					mesVencimento,
					anoVencimento,
					dataLeitura,
					dataVencimento,
					objConta.getLeituraAtual(),
					Integer.parseInt(tfLeituraAtual.getText()),
					Integer.parseInt(tfLeituraAtual.getText()) - objConta.getLeituraAtual(),
					100,
					0,
					"",
					100);
			BancoConta.cadastrarConta(objConta2);
			JOptionPane.showMessageDialog(contentPanePrincipal,
					"Brasil sil sil", "Certinho", JOptionPane.OK_OPTION);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanePrincipal, e, "errado",
					JOptionPane.OK_OPTION);
			;
		}

	}

	protected Conta buscarContaAnterior(int codCliente) throws Exception {

		try {
			Conta objConta = (BancoConta.contaAnterior(codCliente,
					tfHidrometro.getText()));
			return objConta;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

}
