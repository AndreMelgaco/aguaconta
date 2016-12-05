package usuario;

import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import dados.EndCob;
import banco.EndCobDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JDialogEndCob extends JDialog {

	private JPanel contentPanePrincipal;
	private JTextField tfCodEndInst;
	private JTextField tfEndereco;
	private JTextField tfNumero;
	private JTextField tfBairro;
	private JTextField tfCidade;
	private JTextField TfCep;
	private JTextField tfComplemento;
	private JTextField tfUF;
	private JButton btnSalvar;
	private boolean cadastro;
	private int codEndCob;

	/**
	 * Create the dialog.
	 * 
	 * @throws Exception
	 * @throws SQLException
	 */
	public JDialogEndCob(int codigo) throws SQLException, Exception {
		setTitle("Endere\u00E7o de Cobran\u00E7a");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 559, 320);
		getContentPane().setLayout(null);

		JLabel lblCodEndInst = new JLabel("Cod. End. Inst:");
		lblCodEndInst.setBounds(10, 24, 86, 20);
		getContentPane().add(lblCodEndInst);

		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(10, 68, 86, 16);
		getContentPane().add(lblEndereco);

		JLabel lblNumero = new JLabel("N\u00BA:");
		lblNumero.setBounds(482, 68, 22, 16);
		getContentPane().add(lblNumero);

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setBounds(10, 96, 86, 16);
		getContentPane().add(lblComplemento);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 124, 86, 16);
		getContentPane().add(lblBairro);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 148, 86, 16);
		getContentPane().add(lblCidade);

		JLabel lblUf = new JLabel("UF:");
		lblUf.setBounds(255, 148, 32, 16);
		getContentPane().add(lblUf);

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(10, 176, 86, 16);
		getContentPane().add(lblCep);

		tfCodEndInst = new JTextField();
		tfCodEndInst.setText(Integer.toString(codigo));
		tfCodEndInst.setEditable(false);
		tfCodEndInst.setBounds(95, 24, 42, 20);
		getContentPane().add(tfCodEndInst);
		tfCodEndInst.setColumns(10);

		tfEndereco = new JTextField();
		tfEndereco.setColumns(10);
		tfEndereco.setBounds(95, 66, 369, 20);
		getContentPane().add(tfEndereco);

		tfNumero = new JTextField();
		tfNumero.setColumns(10);
		tfNumero.setBounds(502, 66, 42, 20);
		getContentPane().add(tfNumero);

		tfBairro = new JTextField();
		tfBairro.setColumns(10);
		tfBairro.setBounds(95, 122, 155, 20);
		getContentPane().add(tfBairro);

		tfCidade = new JTextField();
		tfCidade.setColumns(10);
		tfCidade.setBounds(95, 146, 155, 20);
		getContentPane().add(tfCidade);

		TfCep = new JTextField();
		TfCep.setColumns(10);
		TfCep.setBounds(95, 174, 78, 20);
		getContentPane().add(TfCep);

		tfComplemento = new JTextField();
		tfComplemento.setColumns(10);
		tfComplemento.setBounds(95, 94, 155, 20);
		getContentPane().add(tfComplemento);

		tfUF = new JTextField();
		tfUF.setColumns(10);
		tfUF.setBounds(276, 146, 42, 20);
		getContentPane().add(tfUF);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (cadastro) {
						cadastrarEndCob();
						JOptionPane.showMessageDialog(contentPanePrincipal,
								"Endereço de Cobrança cadastrado com sucesso.", "Cadastro Endereço de Cobrança",
								JOptionPane.INFORMATION_MESSAGE,
								new ImageIcon(getClass().getResource("recursos/okgreen.png")));
					} else {
						alterarEndCob();
						JOptionPane.showMessageDialog(contentPanePrincipal,
								"Endereço de Cobrança alterado com sucesso.", "Cadastro Endereço de Cobrança",
								JOptionPane.INFORMATION_MESSAGE,
								new ImageIcon(getClass().getResource("recursos/okgreen.png")));
					}
					cadastro = false;
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
		btnSalvar.setBounds(211, 229, 148, 26);
		getContentPane().add(btnSalvar);

		preencheEndCob(codigo);

		setVisible(true);

	}

	protected void alterarEndCob() throws SQLException {
		try {
			EndCob objEndCob = new EndCob(codEndCob, Integer.parseInt(tfCodEndInst.getText()), tfEndereco.getText(),
					Integer.parseInt(tfNumero.getText()), tfComplemento.getText(), tfBairro.getText(),
					tfCidade.getText(), tfUF.getText(), TfCep.getText());
			EndCobDAO.alterarEndCob(objEndCob);
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

	}

	protected void cadastrarEndCob() throws SQLException {
		try {
			EndCob objEndCob = new EndCob(codEndCob, Integer.parseInt(tfCodEndInst.getText()), tfEndereco.getText(),
					Integer.parseInt(tfNumero.getText()), tfComplemento.getText(), tfBairro.getText(),
					tfCidade.getText(), tfUF.getText(), TfCep.getText());
			EndCobDAO.incluirEndCob(objEndCob);
		} catch (SQLException e1) {
			throw new SQLException(e1.getMessage());
		}

	}

	private void preencheEndCob(int codigo) {
		try {
			EndCob objEndCob = EndCobDAO.consultaEndCobCliente(codigo);
			tfEndereco.setText(objEndCob.getEndereco());
			tfNumero.setText(Integer.toString(objEndCob.getNumero()));
			tfComplemento.setText(objEndCob.getComplemento());
			tfBairro.setText(objEndCob.getBairro());
			tfCidade.setText(objEndCob.getCidade());
			tfUF.setText(objEndCob.getEstado());
			TfCep.setText(objEndCob.getCEP());
			codEndCob = objEndCob.getcodEndCob();
			btnSalvar.setText("Salvar");
		} catch (Exception e) {
			cadastro = true;
			btnSalvar.setText("Inserir");
			codEndCob = 0;
		}

	}

}
