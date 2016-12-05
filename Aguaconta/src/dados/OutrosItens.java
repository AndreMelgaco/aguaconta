package dados;

import java.math.BigDecimal;

public class OutrosItens {

	private int codigo;
	private int codEndInst;
	private int codItem;
	private String descItem;
	private int parcelaAtual;
	private int parcelaFinal;
	private BigDecimal valor;
	private String observacoes;

	public OutrosItens(int codigo, int codEndInst, int codItem, String descItem, int parcelaAtual, int parcelaFinal, BigDecimal valor, String observacoes) {
		this.codigo = codigo;
		this.codEndInst = codEndInst;
		this.codItem = codItem;
		this.descItem = descItem;
		this.parcelaAtual = parcelaAtual;
		this.parcelaFinal = parcelaFinal;
		this.valor = valor;
		this.observacoes = observacoes;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodEndInst() {
		return codEndInst;
	}

	public void setCodEndInst(int codEndInst) {
		this.codEndInst = codEndInst;
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

	public int getParcelaAtual() {
		return parcelaAtual;
	}

	public void setParcelaAtual(int parcelaAtual) {
		this.parcelaAtual = parcelaAtual;
	}

	public int getParcelaFinal() {
		return parcelaFinal;
	}

	public void setParcelaFinal(int parcelaFinal) {
		this.parcelaFinal = parcelaFinal;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Override
	public String toString() {
		return "OutrosItens [codEndInst=" + codEndInst + ", codItem=" + codItem + ", parcelaAtual=" + parcelaAtual
				+ ", parcelaFinal=" + parcelaFinal + ", valor=" + valor + "]";
	}

}