package usuario;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class JDialogCadHidrometro extends JDialog {
	private JPanel contentPanePrincipal;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the dialog.
	 */
	public JDialogCadHidrometro() {
		setTitle("Cadastro de Hidrometro");
		setBounds(100, 100, 255, 185);
		getContentPane().setLayout(null);

		JLabel lblCodHidrometro = new JLabel("Cod. Hidrometro:");
		lblCodHidrometro.setBounds(10, 34, 94, 14);
		getContentPane().add(lblCodHidrometro);

		textField = new JTextField();
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

		textField_1 = new JTextField();
		textField_1.setBounds(74, 67, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		setVisible(true);

	}

	protected void cadHidrometro() {
		// TODO Auto-generated method stub

	}
}
