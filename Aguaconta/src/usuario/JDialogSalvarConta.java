package usuario;

import javax.swing.JDialog;
import javax.swing.JTextPane;

import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class JDialogSalvarConta extends JDialog {

	/**
	 * Create the dialog.
	 */
	public JDialogSalvarConta() {
		setTitle("Confirma\u00E7\u00E3o");
		setBounds(100, 100, 348, 172);
		getContentPane().setLayout(null);
		
		JTextPane txtpnJExisteUma = new JTextPane();
		txtpnJExisteUma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnJExisteUma.setBackground(SystemColor.control);
		txtpnJExisteUma.setEditable(false);
		txtpnJExisteUma.setText("J\u00E1 existe uma conta cadastrada com estes dados. \r\n\r\n\r\n            Deseja mesmo alterar esta conta?");
		txtpnJExisteUma.setBounds(10, 0, 314, 82);
		getContentPane().add(txtpnJExisteUma);
		
		JButton btnSim = new JButton("Sim");
		btnSim.setBounds(54, 101, 89, 23);
		getContentPane().add(btnSim);
		
		JButton btnNo = new JButton("N\u00E3o");
		btnNo.setActionCommand("Cancel");
		btnNo.setBounds(196, 101, 89, 23);
		getContentPane().add(btnNo);

	}
}
