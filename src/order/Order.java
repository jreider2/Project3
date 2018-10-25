/**
 * 
 */
package order;

import java.io.Serializable;
import java.util.ArrayList;

import dao.OrderDAO;
import product.Product;
import java.math.BigDecimal;

/**
 * @author julianareider
 *
 */
public class Order implements Serializable {
	
	//private static final long serialVersionUID = 1L;
	private String id;
	private ArrayList<Product> products;
	private String orderStatus;
	private String customerID;
	private String creditCardNo;
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	private BigDecimal orderTotal;

	/**
	 * 
	 */
	public Order() {
		// TODO Auto-generated constructor stub
		this.products = new ArrayList<Product>(); //TODO does this need to be in database?
	}
	
	public String getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(String s) {
		customerID = s;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}
	
	public void setCreditCardNo(String s) {
		creditCardNo = s;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the items
	 */
	public ArrayList<Product> getItems() {
		return products;
	}


	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<Product> items) {
		this.products = items;
	}
	
	public void addProduct(Product p) {
		this.products.add(p);
	}


	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}


	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + ", products=" + products + "]";
	}
	
	

}
