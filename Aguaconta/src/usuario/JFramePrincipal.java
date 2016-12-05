package usuario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import banco.Banco;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class JFramePrincipal extends JFrame {

	private JPanel contentPane;

	public JFramePrincipal() {
		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					Banco.finalizaConexao();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		setTitle("\u00C1guaConta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 100, 100);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new ClientesView();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		mnCadastros.add(mntmClientes);
		
		JMenuItem mntmUnidadeDeConsumo = new JMenuItem("Unidade de Consumo");
		mntmUnidadeDeConsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new CadastroUnidadeConsumo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JMenuItem mntmHidrometros = new JMenuItem("Hidrometros");
		mntmHidrometros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new CadastroHidrometro();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnCadastros.add(mntmHidrometros);
		
		JMenuItem mntmItens = new JMenuItem("Itens");
		mntmItens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ItensView();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		mnCadastros.add(mntmItens);
		mnCadastros.add(mntmUnidadeDeConsumo);
		
		JMenu mnConfig = new JMenu("Configura\u00E7\u00E3o");
		menuBar.add(mnConfig);
		
		JMenuItem mntmConfigGerais = new JMenuItem("Configra\u00E7\u00F5es Gerais");
		mntmConfigGerais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new ConfigView();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		mnConfig.add(mntmConfigGerais);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setSize(redimensionarFrameTotal());
		setVisible(true);
		
	}
	
	public static Dimension redimensionarFrameTotal (){
		return (
			new Dimension (
					(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
					(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50));
	}
}
