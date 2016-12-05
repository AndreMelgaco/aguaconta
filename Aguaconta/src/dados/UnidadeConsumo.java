package dados;

public class UnidadeConsumo {

	private int codUnidConsumo;
	private String nomeUnid;
	private String Cidade;
	private String UF;
	
	
	public UnidadeConsumo(int codUnidConsumo, String nomeUnid, String cidade,
			String uF) {
		super();
		this.codUnidConsumo = codUnidConsumo;
		this.nomeUnid = nomeUnid;
		Cidade = cidade;
		UF = uF;
	}


	public int getCodUnidConsumo() {
		return codUnidConsumo;
	}


	public void setCodUnidConsumo(int codUnidConsumo) {
		this.codUnidConsumo = codUnidConsumo;
	}


	public String getNomeUnid() {
		return nomeUnid;
	}


	public void setNomeUnid(String nomeUnid) {
		this.nomeUnid = nomeUnid;
	}


	public String getCidade() {
		return Cidade;
	}


	public void setCidade(String cidade) {
		Cidade = cidade;
	}


	public String getUF() {
		return UF;
	}


	public void setUF(String uF) {
		UF = uF;
	}
	
	
}
