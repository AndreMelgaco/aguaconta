package usuario;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import banco.HidroEndInstDAO;
import banco.HidrometrosDAO;
import dados.HidroEndInst;
import dados.Hidrometros;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.swing.JTextArea;

public class AddHidroEndInst extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField tfCodHidrometro;
	private JTextArea tfObservacoes;
	private JLabel lblCodInstalao;
	private JLabel lblDtAtivao;
	private JLabel lblDtDesativao;
	private JTextField tfCodInst;
	private JFormattedTextField tfDtAtivacao;
	private JFormattedTextField tfDtDesativacao;
	private DateFormat df;

	public AddHidroEndInst(int cod_endinst, String codigo, boolean cadastro) {
		setModal(true);
		setTitle("Cadastro Hidrometros");
		setBounds(100, 100, 341, 353);
		getContentPane().setLayout(null);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!tfCodHidrometro.getText().isEmpty() && !tfDtAtivacao.getText().isEmpty()) {

						if (cadastro) {
							salvarHidrometro();
						} else {
							atualizarHidrometro();
						}

					} else {
						System.out.println("Nao salvou");
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
		lblCodUnidadeConsumo.setBounds(10, 60, 106, 14);
		getContentPane().add(lblCodUnidadeConsumo);

		tfCodHidrometro = new JTextField();
		tfCodHidrometro.setBounds(117, 57, 160, 20);
		getContentPane().add(tfCodHidrometro);
		tfCodHidrometro.setColumns(10);

		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es: ");
		lblObservaes.setBounds(10, 155, 82, 16);
		getContentPane().add(lblObservaes);

		tfObservacoes = new JTextArea();
		tfObservacoes.setToolTipText("");
		tfObservacoes.setWrapStyleWord(true);
		tfObservacoes.setBounds(10, 182, 303, 74);
		getContentPane().add(tfObservacoes);

		lblCodInstalao = new JLabel("Cod. Instala\u00E7\u00E3o:");
		lblCodInstalao.setBounds(10, 23, 106, 16);
		getContentPane().add(lblCodInstalao);

		lblDtAtivao = new JLabel("Dt. Ativa\u00E7\u00E3o:");
		lblDtAtivao.setBounds(10, 97, 106, 16);
		getContentPane().add(lblDtAtivao);

		lblDtDesativao = new JLabel("Dt. Desativa\u00E7\u00E3o:");
		lblDtDesativao.setBounds(10, 124, 106, 16);
		getContentPane().add(lblDtDesativao);

		tfCodInst = new JTextField();
		tfCodInst.setEditable(false);
		tfCodInst.setBounds(117, 21, 58, 20);
		getContentPane().add(tfCodInst);
		tfCodInst.setColumns(10);

		df = new SimpleDateFormat("dd/MM/yyyy");
		tfDtAtivacao = new JFormattedTextField(df);
		tfDtAtivacao.setBounds(117, 95, 114, 20);
		getContentPane().add(tfDtAtivacao);
		tfDtAtivacao.setColumns(10);

		tfDtDesativacao = new JFormattedTextField(df);
		;
		tfDtDesativacao.setBounds(117, 122, 114, 20);
		getContentPane().add(tfDtDesativacao);
		tfDtDesativacao.setColumns(10);

		if (!cadastro) {
			carregaHidrometro(cod_endinst, codigo);
			tfCodHidrometro.setEnabled(false);
		}

		tfCodInst.setText(Integer.toString(cod_endinst));
		setVisible(true);

	}

	protected void atualizaTabHidrometros(String codHidrometro, String situacao) throws SQLException {
		Hidrometros hidrometro = new Hidrometros(codHidrometro, situacao, "");
		HidrometrosDAO.atualizarSituacao(hidrometro);
		
	}

	public void carregaHidrometro(int codEndInst, String codHidrometro) {
		try {
			HidroEndInst hidro = HidroEndInstDAO.buscarHidro_EndInst(codEndInst, codHidrometro);

			tfCodHidrometro.setText(hidro.getCodHidrometro());
			tfObservacoes.setText(hidro.getObservacao());
			tfCodInst.setText(Integer.toString(hidro.getcodEndInst()));
			tfDtAtivacao.setValue(hidro.getDtAtivacao());
			tfDtDesativacao.setValue(hidro.getDtDesativacao());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarHidrometro() throws Exception {
		Hidrometros hidrometro = HidrometrosDAO.buscarHidrometro(tfCodHidrometro.getText());

		if (HidroEndInstDAO.consultaHidro_EndInst(Integer.parseInt(tfCodInst.getText()))
				&& hidrometro.getSituacao().equals("Disponivel")) {

			Date ativ = new Date(df.parse(tfDtAtivacao.getText()).getTime());
			HidroEndInst hidro = new HidroEndInst(Integer.parseInt(tfCodInst.getText()), tfCodHidrometro.getText(),
					ativ, null, tfObservacoes.getText());
			try {
				HidroEndInstDAO.incluirHidro_EndInst(hidro);
				atualizaTabHidrometros(tfCodHidrometro.getText(), "Instalado");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Hidrometro informado indisponível para instalação.");
		}
	}

	public void atualizarHidrometro() throws Exception, SQLException {
		Date desativ = null;

		if (!HidroEndInstDAO.consultaHidro_EndInst(Integer.parseInt(tfCodInst.getText()))) {

			if (!tfDtDesativacao.getText().isEmpty()) {
				desativ = new Date(df.parse(tfDtDesativacao.getText()).getTime());
			}

			Date ativ = new Date(df.parse(tfDtAtivacao.getText()).getTime());
			HidroEndInst hidro = new HidroEndInst(Integer.parseInt(tfCodInst.getText()), tfCodHidrometro.getText(),
					ativ, desativ, tfObservacoes.getText());

			try {
				HidroEndInstDAO.atualizarHidro_EndInst(hidro);
				
				if(!tfDtDesativacao.getText().isEmpty()){
					atualizaTabHidrometros(tfCodHidrometro.getText(), "Disponivel");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
