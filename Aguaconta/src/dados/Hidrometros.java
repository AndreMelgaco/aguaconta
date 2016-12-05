package dados;

public class Hidrometros {

	private String codHidrometro;
	private String situacao;
	private String Observacao;

	public Hidrometros(String codHidrometro, String situacao, String observacao) {
		this.codHidrometro = codHidrometro;
		this.situacao = situacao;
		Observacao = observacao;
	}

	public String getCodHidrometro() {
		return codHidrometro;
	}

	public void setCodHidrometro(String codHidrometro) {
		this.codHidrometro = codHidrometro;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getObservacao() {
		return Observacao;
	}

	public void setObservacao(String observacao) {
		Observacao = observacao;
	}

	@Override
	public String toString() {
		return "Hidrometro [codHidrometro=" + codHidrometro + ", situacao=" + situacao + ", Observacao=" + Observacao
				+ "]";
	}

}
