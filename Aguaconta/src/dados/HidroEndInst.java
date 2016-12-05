package dados;

import java.sql.Date;

public class HidroEndInst {

	private int codEndInst;
	private String codHidrometro;
	private Date dtAtivacao;
	private Date dtDesativacao;
	private String Observacao;

	public HidroEndInst(int codEndInst, String codHidrometro, Date dtAtivacao, Date dtDesativacao, String observacao) {
		this.codEndInst = codEndInst;
		this.codHidrometro = codHidrometro;
		this.dtAtivacao = dtAtivacao;
		this.dtDesativacao = dtDesativacao;
		this.Observacao = observacao;
	}
	
	public int getcodEndInst() {
		return codEndInst;
	}

	public void setcodEndInst(int codEndInst) {
		this.codEndInst = codEndInst;
	}

	public String getCodHidrometro() {
		return codHidrometro;
	}

	public void setCodHidrometro(String codHidrometro) {
		this.codHidrometro = codHidrometro;
	}

	public Date getDtAtivacao() {
		return dtAtivacao;
	}

	public void setDtAtivacao(Date dtAtivacao) {
		this.dtAtivacao = dtAtivacao;
	}

	public Date getDtDesativacao() {
		return dtDesativacao;
	}

	public void setDtDesativacao(Date dtDesativacao) {
		this.dtDesativacao = dtDesativacao;
	}

	public String getObservacao() {
		return Observacao;
	}

	public void setObservacao(String observacao) {
		Observacao = observacao;
	}

	@Override
	public String toString() {
		return "Hidro_EndInst [codHidrometro=" + codHidrometro + ", dtAtivacao=" + dtAtivacao + ", dtDesativacao="
				+ dtDesativacao + ", Observacao=" + Observacao + "]";
	}

}