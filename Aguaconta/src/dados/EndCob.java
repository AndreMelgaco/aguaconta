package dados;

public class EndCob {

	private int codEndCob;
	private int codEndInst;
	private String endereco;
	private int numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String CEP;

	public EndCob(int codEndCob, int codEndInst, String endereco, int numero, String complemento, String bairro,
			String cidade, String estado, String cEP) {
		super();
		this.codEndCob = codEndCob;
		this.codEndInst = codEndInst;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		CEP = cEP;
	}

	/**
	 * @return the codEndCob
	 */
	public int getcodEndCob() {
		return codEndCob;
	}

	/**
	 * @param codEndCob
	 *            the codEndCob to set
	 */
	public void setcodEndCob(int codEndCob) {
		this.codEndCob = codEndCob;
	}

	/**
	 * @return the codEndInst
	 */
	public int getcodEndInst() {
		return codEndInst;
	}

	/**
	 * @param codEndInst
	 *            the codEndInst to set
	 */
	public void setcodEndInst(int codEndInst) {
		this.codEndInst = codEndInst;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 *            the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @param complemento
	 *            the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro
	 *            the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade
	 *            the cidade to set
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the cEP
	 */
	public String getCEP() {
		return CEP;
	}

	/**
	 * @param cEP
	 *            the cEP to set
	 */
	public void setCEP(String cEP) {
		CEP = cEP;
	}

}
