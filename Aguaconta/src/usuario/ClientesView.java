package usuario;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JTable;

import banco.ClienteDAO;
import tablemodel.ClienteTableModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ClientesView extends JDialog {

	private JTable tbCliente;  
	private static ClienteTableModel modelo;

	private JTextField tfBuscaPorCpf;
	private JTextField tfBuscaPorNome;

	public ClientesView() throws Exception {
		setTitle("Cadastro de Clientes");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 747, 465);
		getContentPane().setLayout(null);

		modelo = new ClienteTableModel();
//		modelo.addTableModelListener((TableModelListener) this);
		try {
			modelo.adicionaLista(ClienteDAO.listarCliente("", ""));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tbCliente = new JTable();
		tbCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int linha = tbCliente.getSelectedRow();
					int codigo = modelo.getCodCliente(linha);
					try {
						new ClienteConsView(codigo,false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		tbCliente.setModel(modelo);
		tbCliente.setFillsViewportHeight(true);
		tbCliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbCliente.getColumnModel().getColumn(0).setPreferredWidth(35);
		tbCliente.getColumnModel().getColumn(1).setPreferredWidth(260);
		tbCliente.getColumnModel().getColumn(2).setPreferredWidth(88);
		tbCliente.getColumnModel().getColumn(3).setPreferredWidth(120);
		tbCliente.getColumnModel().getColumn(4).setPreferredWidth(100);
		tbCliente.getColumnModel().getColumn(5).setPreferredWidth(100);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 92, 707, 322);
		getContentPane().add(scrollPane);
		scrollPane.add(tbCliente);
		scrollPane.setViewportView(tbCliente);
		
		JLabel lblBuscarPorCpf = new JLabel("Buscar por CPF...:");
		lblBuscarPorCpf.setBounds(12, 12, 112, 16);
		getContentPane().add(lblBuscarPorCpf);

		JLabel lblBuscarPorNome = new JLabel("Buscar por Nome:");
		lblBuscarPorNome.setBounds(12, 40, 112, 16);
		getContentPane().add(lblBuscarPorNome);

		tfBuscaPorCpf = new JTextField();
		tfBuscaPorCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER){
					try {
						if(modelo.getRowCount() > 0){
							modelo.limpaLista();
						}
						tfBuscaPorNome.setText("");
						carregaLista();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		tfBuscaPorCpf.setBounds(116, 10, 114, 20);
		getContentPane().add(tfBuscaPorCpf);
		tfBuscaPorCpf.setColumns(10);

		tfBuscaPorNome = new JTextField();
		tfBuscaPorNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER){
					try {
						if(modelo.getRowCount() > 0){
							modelo.limpaLista();
						}
						tfBuscaPorCpf.setText("");
						carregaLista();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		tfBuscaPorNome.setBounds(116, 40, 220, 20);
		getContentPane().add(tfBuscaPorNome);
		tfBuscaPorNome.setColumns(10);
		
		JButton btnNovoCliente = new JButton("Novo Cliente");
		btnNovoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int codigo = ClienteDAO.novoCliente();
					new ClienteConsView(codigo,true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNovoCliente.setBounds(544, 7, 119, 26);
		getContentPane().add(btnNovoCliente);

		setVisible(true);

	}
	
	public void carregaLista() throws SQLException, Exception{
		modelo.adicionaLista(ClienteDAO.listarCliente(tfBuscaPorCpf.getText(), tfBuscaPorNome.getText()));
	}
}