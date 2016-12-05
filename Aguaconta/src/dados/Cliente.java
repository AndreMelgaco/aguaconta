package dados;

public class Cliente {
	
	
	private int codCliente;
	private String nome;
	private String tipo;
	private String cpfCnpj;
	private String identidade;
	private String email;
	private String tel_fixo;
	private String celular;
	private String tel_recado;
	
	
	public Cliente(int codCliente, String nome, String tipo, String cpfCnpj, String identidade,
			String email, String tel_fixo, String celular, String tel_recado) {
		super();
		this.codCliente = codCliente;
		this.nome = nome;
		this.tipo = tipo;
		this.cpfCnpj = cpfCnpj;
		this.identidade = identidade;
		this.email = email;
		this.tel_fixo = tel_fixo;
		this.celular = celular;
		this.tel_recado = tel_recado;
	}


	/**
	 * @return the codCliente
	 */
	public int getCodCliente() {
		return codCliente;
	}


	/**
	 * @param codCliente the codCliente to set
	 */
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}


	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}


	/**
	 * @param nome the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}


	/**
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}


	/**
	 * @return the identidade
	 */
	public String getIdentidade() {
		return identidade;
	}


	/**
	 * @param identidade the identidade to set
	 */
	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the tel_fixo
	 */
	public String getTel_fixo() {
		return tel_fixo;
	}


	/**
	 * @param tel_fixo the tel_fixo to set
	 */
	public void setTel_fixo(String tel_fixo) {
		this.tel_fixo = tel_fixo;
	}


	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}


	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}


	/**
	 * @return the tel_recado
	 */
	public String getTel_recado() {
		return tel_recado;
	}


	/**
	 * @param tel_recado the tel_recado to set
	 */
	public void setTel_recado(String tel_recado) {
		this.tel_recado = tel_recado;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cliente [codCliente=" + codCliente + ", nome=" + nome
				+ ", tipo=" + tipo +", cpf=" + cpfCnpj + ", identidade=" + identidade + ", email="
				+ email + ", tel_fixo=" + tel_fixo + ", celular=" + celular
				+ ", tel_recado=" + tel_recado + ", resp_recado="
				+ "]";
	}

}
