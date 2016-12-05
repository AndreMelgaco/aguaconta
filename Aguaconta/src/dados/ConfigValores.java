package dados;

import java.math.BigDecimal;

public class ConfigValores {

	private int codUnidadeConsumo;
	private int mesConta;
	private int anoConta;
	private int faixas;
	private BigDecimal valorUnico;
	private BigDecimal valorUnit;

	public ConfigValores(int codUnidadeConsumo, int mesConta, int anoConta, int faixas, BigDecimal valorUnico,
			BigDecimal valorUnit) {
		super();
		this.codUnidadeConsumo = codUnidadeConsumo;
		this.mesConta = mesConta;
		this.anoConta = anoConta;
		this.faixas = faixas;
		this.valorUnico = valorUnico;
		this.valorUnit = valorUnit;
	}

	public int getCodUnidadeConsumo() {
		return codUnidadeConsumo;
	}

	public void setCodUnidadeConsumo(int codUnidadeConsumo) {
		this.codUnidadeConsumo = codUnidadeConsumo;
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

	public int getFaixas() {
		return faixas;
	}

	public void setFaixas(int faixas) {
		this.faixas = faixas;
	}

	public BigDecimal getValorUnico() {
		return valorUnico;
	}

	public void setValorUnico(BigDecimal valorUnico) {
		this.valorUnico = valorUnico;
	}

	public BigDecimal getValorUnit() {
		return valorUnit;
	}

	public void setValorUnit(BigDecimal valorUnit) {
		this.valorUnit = valorUnit;
	}

	@Override
	public String toString() {
		return "ConfigValores [codUnidadeConsumo=" + codUnidadeConsumo + ", mesConta=" + mesConta + ", anoConta="
				+ anoConta + ", faixas=" + faixas + ", valorUnico=" + valorUnico + ", valorUnit="
				+ valorUnit + "]";
	}

}