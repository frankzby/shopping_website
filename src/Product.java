
public class Product{

	
	int pId=0;
	String pname = "";
	float price=0 ;
	boolean addCart = false;
	boolean cancelled = false;

	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean isAddCart() {
		return addCart;
	}
	public void setAddCart(boolean addCart) {
		this.addCart = addCart;
	}
	public boolean isCancelled() {
		return cancelled;
	}
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}
