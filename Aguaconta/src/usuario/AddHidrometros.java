package usuario;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import banco.HidrometrosDAO;
import dados.Hidrometros;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class AddHidrometros extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField tfCodHidrometro;
	private JRadioButton rdbtnDisponivel;
	private JRadioButton rdbtnInstalado;
	private JRadioButton rdbtnManutencao;
	private JRadioButton rdbtnEstragado;
	private String status;
	private JTextArea tfObservacoes;

	public AddHidrometros(String codigo, boolean cadastro) {
		setTitle("Cadastro Hidrometros");
		setBounds(100, 100, 341, 353);
		getContentPane().setLayout(null);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!tfCodHidrometro.getText().isEmpty()) {

						if (rdbtnEstragado.isSelected()) {
							status = "Estragado";
						} else if (rdbtnInstalado.isSelected()) {
							status = "Instalado";
						} else if (rdbtnManutencao.isSelected()) {
							status = "Em Manutenção";
						} else {
							status = "Disponivel";
						}

						if (cadastro) {
							salvarHidrometros();
						} else {
							atualizarHidrometros();
						}

					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				dispose();
			}
		});
		btnSalvar.setBounds(109, 276, 98, 26);

		getContentPane().add(btnSalvar);

		JLabel lblCodUnidadeConsumo = new JLabel("C\u00F3d. Hidrometro: ");
		lblCodUnidadeConsumo.setBounds(10, 23, 105, 14);
		getContentPane().add(lblCodUnidadeConsumo);

		tfCodHidrometro = new JTextField();
		tfCodHidrometro.setBounds(125, 20, 160, 20);
		getContentPane().add(tfCodHidrometro);
		tfCodHidrometro.setColumns(10);

		JLabel lblStatus = new JLabel("Status: ");
		lblStatus.setBounds(10, 59, 46, 14);
		getContentPane().add(lblStatus);

		rdbtnDisponivel = new JRadioButton("1 - Dispon\u00EDvel");
		rdbtnDisponivel.setBounds(49, 55, 134, 23);
		getContentPane().add(rdbtnDisponivel);

		rdbtnInstalado = new JRadioButton("2 - Instalado");
		rdbtnInstalado.setBounds(185, 55, 134, 23);
		getContentPane().add(rdbtnInstalado);

		rdbtnManutencao = new JRadioButton("3 - Em Manuten\u00E7\u00E3o");
		rdbtnManutencao.setBounds(49, 81, 134, 23);
		getContentPane().add(rdbtnManutencao);

		rdbtnEstragado = new JRadioButton("4 - Estragado");
		rdbtnEstragado.setBounds(185, 80, 134, 24);
		getContentPane().add(rdbtnEstragado);

		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es: ");
		lblObservaes.setBounds(10, 133, 82, 16);
		getContentPane().add(lblObservaes);

		ButtonGroup statusGroup = new ButtonGroup();
		statusGroup.add(rdbtnDisponivel);
		statusGroup.add(rdbtnInstalado);
		statusGroup.add(rdbtnManutencao);
		statusGroup.add(rdbtnEstragado);

		tfObservacoes = new JTextArea();
		tfObservacoes.setToolTipText("");
		tfObservacoes.setWrapStyleWord(true);
		tfObservacoes.setBounds(10, 161, 303, 95);
		getContentPane().add(tfObservacoes);

		carregaHidrometro(codigo);
		setVisible(true);

	}
	
	public void carregaHidrometro(String codHidrometro){
		try {
			Hidrometros hidro = HidrometrosDAO.buscarHidrometro(codHidrometro);
			
			tfCodHidrometro.setText(hidro.getCodHidrometro());
			tfObservacoes.setText(hidro.getObservacao());
			String situacao = hidro.getSituacao();
			
			if (situacao.equals("Estragado")) {
				rdbtnEstragado.setSelected(true);
			} else if (situacao.equals("Disponivel")) {
				rdbtnDisponivel.setSelected(true);
			} else if (situacao.equals("Em Manutenção")) {
				rdbtnManutencao.setSelected(true);
			} else {
				rdbtnInstalado.setSelected(true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarHidrometros() {
		Hidrometros hidro = new Hidrometros(tfCodHidrometro.getText(), status, tfObservacoes.getText());
		try {
			HidrometrosDAO.incluirHidrometro(hidro);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void atualizarHidrometros() {
		Hidrometros hidro = new Hidrometros(tfCodHidrometro.getText(), status, tfObservacoes.getText());
		try {
			HidrometrosDAO.atualizarHidrometro(hidro);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
