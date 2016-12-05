package usuario;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

import banco.ConfigDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JDialogConfigValores extends JDialog {
	private JTextField tfAteUm;
	private JTextField tfAteDois;
	private JTextField tfAteTres;
	private JTextField tfAteQuatro;
	private JTextField tfAteCinco;
	private JTextField tfValorDois;
	private JTextField tfValorUm;
	private JTextField tfValorTres;
	private JTextField tfValorQuatro;
	private JTextField tfValorCinco;
	private JButton btnSalvar;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JDialogConfigValores() {
		setResizable(false);
		setTitle("Faixa de Valores");
		setBounds(100, 100, 281, 420);
		getContentPane().setLayout(null);

		JLabel lblFaixaUm = new JLabel("Faixa 1:");
		lblFaixaUm.setBounds(10, 192, 46, 14);
		getContentPane().add(lblFaixaUm);

		JLabel lblAte = new JLabel("At\u00E9:");
		lblAte.setHorizontalAlignment(SwingConstants.CENTER);
		lblAte.setBounds(86, 118, 30, 16);
		getContentPane().add(lblAte);

		JLabel lblFaixaDois = new JLabel("Faixa 2:");
		lblFaixaDois.setBounds(10, 224, 46, 14);
		getContentPane().add(lblFaixaDois);

		JLabel lblFaixaTres = new JLabel("Faixa 3:");
		lblFaixaTres.setBounds(10, 256, 46, 14);
		getContentPane().add(lblFaixaTres);

		JLabel lblFaixaQuatro = new JLabel("Faixa 4:");
		lblFaixaQuatro.setBounds(10, 288, 46, 14);
		getContentPane().add(lblFaixaQuatro);

		JLabel lblFaixaCinco = new JLabel("Faixa 5:");
		lblFaixaCinco.setBounds(10, 320, 46, 14);
		getContentPane().add(lblFaixaCinco);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(155, 118, 55, 16);
		getContentPane().add(lblValor);

		JLabel lblUnidadeConsumo = new JLabel("Unidade Consumo:");
		lblUnidadeConsumo.setBounds(10, 16, 106, 16);
		getContentPane().add(lblUnidadeConsumo);

		JLabel lblMnimo = new JLabel("M\u00EDnimo:");
		lblMnimo.setBounds(10, 159, 55, 16);
		getContentPane().add(lblMnimo);

		tfAteUm = new JTextField();
		tfAteUm.setColumns(10);
		tfAteUm.setBounds(86, 189, 30, 20);
		getContentPane().add(tfAteUm);

		tfAteDois = new JTextField();
		tfAteDois.setColumns(10);
		tfAteDois.setBounds(86, 221, 30, 20);
		getContentPane().add(tfAteDois);

		tfAteTres = new JTextField();
		tfAteTres.setColumns(10);
		tfAteTres.setBounds(86, 253, 30, 20);
		getContentPane().add(tfAteTres);

		tfAteQuatro = new JTextField();
		tfAteQuatro.setColumns(10);
		tfAteQuatro.setBounds(86, 285, 30, 20);
		getContentPane().add(tfAteQuatro);

		tfAteCinco = new JTextField();
		tfAteCinco.setColumns(10);
		tfAteCinco.setBounds(86, 317, 30, 20);
		getContentPane().add(tfAteCinco);

		tfValorDois = new JTextField();
		tfValorDois.setBounds(154, 221, 69, 20);
		getContentPane().add(tfValorDois);
		tfValorDois.setColumns(10);

		tfValorUm = new JTextField();
		tfValorUm.setColumns(10);
		tfValorUm.setBounds(154, 189, 69, 20);
		getContentPane().add(tfValorUm);

		tfValorTres = new JTextField();
		tfValorTres.setColumns(10);
		tfValorTres.setBounds(154, 253, 69, 20);
		getContentPane().add(tfValorTres);

		tfValorQuatro = new JTextField();
		tfValorQuatro.setColumns(10);
		tfValorQuatro.setBounds(154, 285, 69, 20);
		getContentPane().add(tfValorQuatro);

		tfValorCinco = new JTextField();
		tfValorCinco.setColumns(10);
		tfValorCinco.setBounds(154, 317, 69, 20);
		getContentPane().add(tfValorCinco);

		textField = new JTextField();
		textField.setBounds(86, 157, 30, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(155, 157, 69, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox cbUnidConsumo = new JComboBox();
		try {
			cbUnidConsumo.setModel(new DefaultComboBoxModel(buscaUnidConsumo()
					.toArray()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cbUnidConsumo.setBounds(119, 12, 144, 25);
		getContentPane().add(cbUnidConsumo);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(79, 353, 98, 26);
		getContentPane().add(btnSalvar);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigo = cbUnidConsumo.getSelectedIndex() + 1;
				carregaDados(codigo);
			}
		});
		btnOk.setBounds(79, 61, 98, 26);
		getContentPane().add(btnOk);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 102, 275, 14);
		getContentPane().add(separator);

		setVisible(true);

	}

	protected void carregaDados(int codigo) {
		// TODO Auto-generated method stub
		
	}

	private List<String> buscaUnidConsumo() throws Exception {
		try {
			List<String> temp = ConfigDAO.buscaUnidConsumo();

			return temp;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	
	
	
}
