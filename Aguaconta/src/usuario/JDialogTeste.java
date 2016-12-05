package usuario;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import banco.Banco;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class JDialogTeste extends JDialog {
	private JPanel contentPanePrincipal;
	private JFormattedTextField textField;
	private DateFormat df;

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public JDialogTeste() throws SQLException {
		setTitle("Cadastro de Hidrometro");
		setBounds(100, 100, 255, 185);
		getContentPane().setLayout(null);

		JLabel lblCodHidrometro = new JLabel("Cod. Hidrometro:");
		lblCodHidrometro.setBounds(10, 34, 94, 14);
		getContentPane().add(lblCodHidrometro);

		df = new SimpleDateFormat("MM/dd/yyyy");
		textField = new JFormattedTextField(df);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					System.out.println("funciona");
				}
			}
		});
		textField.setBounds(94, 31, 127, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cadHidrometro();
					JOptionPane.showMessageDialog(contentPanePrincipal, "Hidrometro cadastrado com sucesso.",
							"Cadastro de Hidrometro", JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon(getClass().getResource("recursos/okgreen.png")));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnCadastrar.setBounds(71, 98, 89, 23);
		getContentPane().add(btnCadastrar);

		cadHidrometro();

	}

	protected void cadHidrometro() throws SQLException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse("20/10/2015", formatter);
//		LocalDate data2 = LocalDate.parse("", formatter);
		data = data.minusDays(60);
		System.out.println(data);

	}
}
