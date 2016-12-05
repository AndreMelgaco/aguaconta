package dados;

public class Itens {

	private int coditem;
	private String descItem;
	
	public Itens(int coditem, String descItem) {
		this.coditem = coditem;
		this.descItem = descItem;
	}

	public int getCoditem() {
		return coditem;
	}

	public void setCoditem(int coditem) {
		this.coditem = coditem;
	}

	public String getDescItem() {
		return descItem;
	}

	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}

	@Override
	public String toString() {
		return "Itens [coditem=" + coditem + ", descItem=" + descItem + "]";
	}

	
}