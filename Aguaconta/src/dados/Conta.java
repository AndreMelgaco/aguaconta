package dados;

import java.math.BigDecimal;
import java.sql.Date;

public class Conta {
	
	private int idConta;
	private int codCliente;
	private int codEndInst;
	private String hidrometro;
	private int mes;
	private int ano;
	private Date dtLeitura;
	private Date dtVencimento;
	private int leituraAnterior;
	private int leituraAtual;
	private int consumo;
	private String observacoes;
	private BigDecimal total;
	
	public Conta(int idConta, int codCliente, int codEndInst, String hidrometro, int mes, int ano, Date dtLeitura,
			Date dtVencimento, int leituraAnterior, int leituraAtual, int consumo, String observacoes, BigDecimal total) {
		super();
		this.idConta = idConta;
		this.codCliente = codCliente;
		this.codEndInst = codEndInst;
		this.hidrometro = hidrometro;
		this.mes = mes;
		this.ano = ano;
		this.dtLeitura = dtLeitura;
		this.dtVencimento = dtVencimento;
		this.leituraAnterior = leituraAnterior;
		this.leituraAtual = leituraAtual;
		this.consumo = consumo;
		this.observacoes = observacoes;
		this.total = total;
	}
	
	public int getIdConta() {
		return idConta;
	}

	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public int getCodEndInst() {
		return codEndInst;
	}

	public void setCodEndInst(int codEndInst) {
		this.codEndInst = codEndInst;
	}

	public String getHidrometro() {
		return hidrometro;
	}

	public void setHidrometro(String hidrometro) {
		this.hidrometro = hidrometro;
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

	public Date getDtLeitura() {
		return dtLeitura;
	}

	public void setDtLeitura(Date dtLeitura) {
		this.dtLeitura = dtLeitura;
	}

	public Date getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public int getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(int leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public int getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(int leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public int getConsumo() {
		return consumo;
	}

	public void setConsumo(int consumo) {
		this.consumo = consumo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	

}
