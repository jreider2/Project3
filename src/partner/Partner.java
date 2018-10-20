/**
 * 
 */
package partner;

import java.io.Serializable;
import java.util.ArrayList;

import order.Order;
import order.OrderManager;
import product.Product;

/**
 * @author julianareider
 *
 */
public class Partner implements Serializable {
	
	private static final long serialVersionUID = 1L; //Saw this in Zewdie example, does this help with ID numbers?
	private String id;
	private String companyName;
	private ArrayList<Product> products;
	private String userName;
	private String password;

	public Partner() {
		this.products = new ArrayList<>();
	}
	
	public Partner(String companyName) {
		this.companyName = companyName;
		this.products = new ArrayList<>();
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
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the products
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	

	public void addProduct(Product p) {
		this.products.add(p);
	}
	

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean recieveOrder(Order o) {
		System.out.println("Order recieved. Order is: " + o.getItems().get(0).toString());
		System.out.println("New Order status: " + "pushedToPartner");
		return true;
	}
	
	public void recievePayment() {
		System.out.println(this.getCompanyName() + " recieved payment for order");
		//System.out.println("Company with ID " + this.getId() + " recieved payment for order");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Partner Company Name: " + companyName;
	}

}
