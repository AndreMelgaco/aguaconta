package dados;

import java.sql.Date;

public class ConfigGeral {

	private int codUnidadeConsumo;
	private Date dataVencimento;
	private Date dataLeitura;
	private int mesConta;
	private int anoConta;
	private float juros;
	private float multa;
	private String texto_conta;
	
	
	public ConfigGeral(int codUnidadeConsumo, Date dataVencimento, Date dataLeitura, int mesConta,
			int anoConta, float juros, float multa, String texto_conta) {
		this.codUnidadeConsumo = codUnidadeConsumo;
		this.dataVencimento = dataVencimento;
		this.dataLeitura = dataLeitura;
		this.mesConta = mesConta;
		this.anoConta = anoConta;
		this.juros = juros;
		this.multa = multa;
		this.texto_conta = texto_conta;
	}


	public int getCodUnidadeConsumo() {
		return codUnidadeConsumo;
	}

	public void setCodUnidadeConsumo(int codUnidadeConsumo) {
		this.codUnidadeConsumo = codUnidadeConsumo;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	public Date getDataLeitura() {
		return dataLeitura;
	}


	public void setDataLeitura(Date dataLeitura) {
		this.dataLeitura = dataLeitura;
	}


	public int getMesConta() {
		return mesConta;
	}


	public void setMesConta(int mesConta) {
		this.mesConta = mesConta;
	}


	public int getAnoConta() {
		return anoConta;
	}


	public void setAnoConta(int anoConta) {
		this.anoConta = anoConta;
	}
	

	public float getJuros() {
		return juros;
	}


	public void setJuros(float juros) {
		this.juros = juros;
	}


	public float getMulta() {
		return multa;
	}


	public void setMulta(float multa) {
		this.multa = multa;
	}

	
	public String getTexto_conta() {
		return texto_conta;
	}


	public void setTexto_conta(String texto_conta) {
		this.texto_conta = texto_conta;
	}


	@Override
	public String toString() {
		return "ConfigGeral [dataVencimento=" + dataVencimento + ", dataLeitura=" + dataLeitura
				+ ", mesConta=" + mesConta + ", anoConta=" + anoConta
				+ ", juros=" + juros + ", multa=" + multa + "]";
	}
	
	
	
	
	
}
