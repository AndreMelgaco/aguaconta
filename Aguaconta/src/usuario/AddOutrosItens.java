package usuario;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import banco.ItensDAO;
import banco.OutrosItensDAO;
import dados.Itens;
import dados.OutrosItens;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddOutrosItens extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final Locale LOCAL = new Locale("pt", "BR");
	private JLabel lblCodItem;
	private JLabel lblDescrio;
	private JTextField tfCodItem;
	private JTextField tfDescItem;
	private JTextField tfQteParcelas;
	private JFormattedTextField tfValor;
	private DecimalFormat df;
	private JLabel lblObservaes;
	private int codEndInst;
	private JTextArea taObservacoes;

	public AddOutrosItens(OutrosItens outros, boolean cadastro) {
		setModal(true);
		setTitle("Cadastro Itens");
		setBounds(100, 100, 527, 263);
		getContentPane().setLayout(null);

		codEndInst = outros.getCodEndInst();

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BigDecimal valor = (BigDecimal) df.parse(tfValor.getText());

					OutrosItens item = new OutrosItens(0, codEndInst, Integer.parseInt(tfCodItem.getText()),
							tfDescItem.getText(), 1, Integer.parseInt(tfQteParcelas.getText()), valor,
							taObservacoes.getText());
					if (cadastro) {
						salvarOutroItem(item);
					} else {
						atualizarOutroItem(item);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				dispose();
			}
		});
		btnSalvar.setBounds(200, 186, 98, 26);

		getContentPane().add(btnSalvar);

		lblCodItem = new JLabel("Cod. Item:");
		lblCodItem.setBounds(10, 33, 73, 14);
		getContentPane().add(lblCodItem);

		lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(10, 58, 73, 16);
		getContentPane().add(lblDescrio);

		tfCodItem = new JTextField();
		tfCodItem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					try {
						Itens item = ItensDAO.buscarItens(Integer.parseInt(tfCodItem.getText()));
						tfDescItem.setText(item.getDescItem());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		tfCodItem.setBounds(93, 30, 58, 20);
		getContentPane().add(tfCodItem);
		tfCodItem.setColumns(10);

		tfDescItem = new JTextField();
		tfDescItem.setEditable(false);
		tfDescItem.setBounds(93, 58, 408, 20);
		getContentPane().add(tfDescItem);
		tfDescItem.setColumns(10);

		JLabel lblQteParcelas = new JLabel("Qte. Parcelas:");
		lblQteParcelas.setBounds(10, 97, 89, 14);
		getContentPane().add(lblQteParcelas);

		tfQteParcelas = new JTextField();
		tfQteParcelas.setBounds(93, 94, 58, 20);
		getContentPane().add(tfQteParcelas);
		tfQteParcelas.setColumns(10);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(185, 96, 43, 16);
		getContentPane().add(lblValor);

		df = new DecimalFormat("¤ #,##0.00", new DecimalFormatSymbols(LOCAL));
		df.setParseBigDecimal(true);
		tfValor = new JFormattedTextField(df);
		tfValor.setBounds(224, 94, 74, 20);
		getContentPane().add(tfValor);
		tfValor.setColumns(10);

		lblObservaes = new JLabel("Observa\u00E7\u00F5es:");
		lblObservaes.setBounds(10, 141, 89, 16);
		getContentPane().add(lblObservaes);

		taObservacoes = new JTextArea();
		taObservacoes.setBounds(93, 123, 406, 54);
		getContentPane().add(taObservacoes);

		carregaOutroItem(outros);
		setVisible(true);

	}

	public void carregaOutroItem(OutrosItens outros) {
		try {
			tfCodItem.setText(Integer.toString(outros.getCodItem()));
			tfDescItem.setText(outros.getDescItem());
			tfQteParcelas.setText(Integer.toString(outros.getParcelaFinal()));
			tfValor.setValue(outros.getValor());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarOutroItem(OutrosItens item) throws Exception {

		try {
			OutrosItensDAO.incluirOutrosItens(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizarOutroItem(OutrosItens item) throws Exception, SQLException {

		try {
			OutrosItensDAO.atualizarOutrosItens(item);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
