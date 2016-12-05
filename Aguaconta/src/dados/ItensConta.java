package dados;

import java.math.BigDecimal;

public class ItensConta {

	private int idConta;
	private int seqItem;
	private int mes;
	private int ano;
	private int codItem;
	private String descItem;
	private BigDecimal valor;
	private int qtdItem;
	private BigDecimal total;

	public ItensConta(int idConta, int seqItem, int mes, int ano, int codItem, String descItem, BigDecimal valor, int qtdItem,
			BigDecimal total) {
		super();
		this.idConta = idConta;
		this.seqItem = seqItem;
		this.mes = mes;
		this.ano = ano;
		this.codItem = codItem;
		this.descItem = descItem;
		this.valor = valor;
		this.qtdItem = qtdItem;
		this.total = total;
	}

	public int getIdConta() {
		return idConta;
	}

	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	
	public int getseqItem() {
		return seqItem;
	}

	public void setseqItem(int seqItem) {
		this.seqItem = seqItem;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getCodItem() {
		return codItem;
	}

	public void setCodItem(int codItem) {
		this.codItem = codItem;
	}

	public String getDescItem() {
		return descItem;
	}

	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getQtdItem() {
		return qtdItem;
	}

	public void setQtdItem(int qtdItem) {
		this.qtdItem = qtdItem;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Itens_Conta [idConta=" + idConta + ", seqItem=" + seqItem + ", mes=" + mes + ", ano=" + ano + ", codItem=" + codItem
				+ ", descItem=" + descItem + ", valor=" + valor + ", qtdItem=" + qtdItem + ", total=" + total + "]";
	}

}
