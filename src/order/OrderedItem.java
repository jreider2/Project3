package order;

public class OrderedItem {
	private String productID;
	private String qtyOnOrder;
	private String productPrice;
	
	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public OrderedItem() {}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getQtyOnOrder() {
		return qtyOnOrder;
	}

	public void setQtyOnOrder(String qtyOnOrder) {
		this.qtyOnOrder = qtyOnOrder;
	}
	
	
}
