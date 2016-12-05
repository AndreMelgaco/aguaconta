package usuario;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class JDialogConsCliente extends JDialog {
	private JTextField tfCpfCnpj;
	private JTextField tfNome;
	private JTable table;

	public JDialogConsCliente() {
		setAlwaysOnTop(true);
		setModal(true);
		setTitle("Consulta de Clientes");
		setBounds(100, 100, 559, 403);
		getContentPane().setLayout(null);

		JLabel lblEscolhaOTipo = new JLabel("Escolha o tipo de Consulta:");
		lblEscolhaOTipo.setBounds(10, 11, 192, 14);
		getContentPane().add(lblEscolhaOTipo);

		tfCpfCnpj = new JTextField();
		tfCpfCnpj.setBounds(100, 37, 114, 20);
		getContentPane().add(tfCpfCnpj);
		tfCpfCnpj.setColumns(10);

		tfNome = new JTextField();
		tfNome.setBounds(100, 63, 114, 20);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		JCheckBox ckbxCpfcnpj = new JCheckBox("CPF/CNPJ:");
		ckbxCpfcnpj.setBounds(8, 33, 84, 24);
		getContentPane().add(ckbxCpfcnpj);
		
		JCheckBox ckbxNome = new JCheckBox("Nome:");
		ckbxNome.setBounds(8, 61, 84, 24);
		getContentPane().add(ckbxNome);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(226, 46, 98, 26);
		getContentPane().add(btnBuscar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 95, 543, 10);
		getContentPane().add(separator);
		
		table = new JTable();
		table.setBounds(10, 118, 521, 233);
		getContentPane().add(table);
		
		final JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 80, 783, 312);
		getContentPane().add(scrollPane);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		
		setVisible(true);
	}
}
