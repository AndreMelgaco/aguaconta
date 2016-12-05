package usuario;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import banco.ConfigDAO;
import dados.ConfigValores;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.JFormattedTextField;

public class AddFaixas extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final Locale LOCAL = new Locale("pt","BR");
	private JLabel lblUnidConsumo;
	private JLabel lblFaixa;
	private JLabel lblValornico;
	private JLabel lblValorUnitrio;
	private JTextField tfUnidConsumo;
	private JTextField tfFaixa;
	private JFormattedTextField ftfValorUnico;
	private JFormattedTextField tfValorUnit;
	private DecimalFormat df;

	public AddFaixas(int codUnidConsumo, int faixa, int mes, int ano, boolean cadastro) {
		setModal(true);
		setTitle("Cadastro Faixas");
		setBounds(100, 100, 238, 214);
		getContentPane().setLayout(null);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					salvarFaixa(mes, ano);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				dispose();
			}
		});
		btnSalvar.setBounds(4, 114, 98, 26);

		getContentPane().add(btnSalvar);

		lblUnidConsumo = new JLabel("Unid Consumo:");
		lblUnidConsumo.setBounds(10, 11, 92, 14);
		getContentPane().add(lblUnidConsumo);

		lblFaixa = new JLabel("Faixa .................:");
		lblFaixa.setBounds(10, 36, 92, 16);
		getContentPane().add(lblFaixa);

		lblValornico = new JLabel("Valor \u00DAnico .....:");
		lblValornico.setBounds(10, 61, 92, 16);
		getContentPane().add(lblValornico);

		lblValorUnitrio = new JLabel("Valor Unit\u00E1rio .:");
		lblValorUnitrio.setBounds(9, 87, 98, 16);
		getContentPane().add(lblValorUnitrio);

		tfUnidConsumo = new JTextField();
		tfUnidConsumo.setEditable(false);
		tfUnidConsumo.setBounds(103, 8, 75, 20);
		getContentPane().add(tfUnidConsumo);
		tfUnidConsumo.setColumns(10);

		tfFaixa = new JTextField();
		tfFaixa.setBounds(103, 34, 75, 20);
		getContentPane().add(tfFaixa);
		tfFaixa.setColumns(10);

		df = new DecimalFormat("¤ #,###.00", new DecimalFormatSymbols(LOCAL));
		df.setParseBigDecimal(true);
		tfValorUnit = new JFormattedTextField(df);
		tfValorUnit.setBounds(103, 85, 75, 20);
		getContentPane().add(tfValorUnit);
		tfValorUnit.setColumns(10);

		ftfValorUnico = new JFormattedTextField(df);
		ftfValorUnico.setBounds(103, 59, 75, 18);
		getContentPane().add(ftfValorUnico);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					excluirFaixa(mes, ano);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dispose();
			}
		});
		btnExcluir.setBounds(147, 142, 75, 23);
		getContentPane().add(btnExcluir);

		carregaFaixa(codUnidConsumo, faixa);
		setVisible(true);

	}

	public void carregaFaixa(int codigo, int faixa) {
		try {
			ConfigValores cv = ConfigDAO.retornaFaixa(codigo, faixa);
			tfUnidConsumo.setText(Integer.toString(codigo));
			tfFaixa.setText(Integer.toString(cv.getFaixas()));
			tfValorUnit.setValue(cv.getValorUnit());
			ftfValorUnico.setValue(cv.getValorUnico());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarFaixa(int mes, int ano) throws Exception {
		BigDecimal valorUnit = (BigDecimal) df.parse(tfValorUnit.getText());
		BigDecimal valorUnico = (BigDecimal) df.parse(ftfValorUnico.getText());

		ConfigValores cv = new ConfigValores(Integer.parseInt(tfUnidConsumo.getText()),
				mes, ano, Integer.parseInt(tfFaixa.getText()), valorUnico, valorUnit);

		try {
			ConfigDAO.atualizaFaixas(cv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluirFaixa(int mes, int ano) throws Exception {
		BigDecimal valorUnit = (BigDecimal) df.parse(tfValorUnit.getText());
		BigDecimal valorUnico = (BigDecimal) df.parse(ftfValorUnico.getText());

		ConfigValores cv = new ConfigValores(Integer.parseInt(tfUnidConsumo.getText()),
				mes, ano, Integer.parseInt(tfFaixa.getText()), valorUnico, valorUnit);

		try {
			ConfigDAO.excluirFaixa(cv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
