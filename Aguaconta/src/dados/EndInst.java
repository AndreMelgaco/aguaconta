package dados;

public class EndInst {
	
	private int codEndInst;
	private int codCliente;
	private String endereco;
	private String gleba;
	private String chacara;
	private int codUnidadeConsumo;
	private String unidadeConsumo;
	private String cidade;
	private String uf;
	
	public EndInst(int codEndInst, int codCliente, String endereco, String gleba,
			String chacara, int codUnidadeConsumo, String unidadeConsumo, String cidade, String uf) {
		super();
		this.codEndInst = codEndInst;
		this.codCliente = codCliente;
		this.endereco = endereco;
		this.gleba = gleba;
		this.chacara = chacara;
		this.codUnidadeConsumo = codUnidadeConsumo;
		this.unidadeConsumo = unidadeConsumo;
		this.cidade = cidade;
		this.uf = uf;
	}

	public int getCodEndInst() {
		return codEndInst;
	}

	public void setCodEndInst(int codEndInst) {
		this.codEndInst = codEndInst;
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getGleba() {
		return gleba;
	}

	public void setGleba(String gleba) {
		this.gleba = gleba;
	}

	public String getChacara() {
		return chacara;
	}

	public void setChacara(String chacara) {
		this.chacara = chacara;
	}

	public int getCodUnidadeConsumo() {
		return codUnidadeConsumo;
	}

	public void setCodUnidadeConsumo(int codUnidadeConsumo) {
		this.codUnidadeConsumo = codUnidadeConsumo;
	}
	public String getUnidadeConsumo() {
		return unidadeConsumo;
	}

	public void setUnidadeConsumo(String unidadeConsumo) {
		this.unidadeConsumo = unidadeConsumo;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
