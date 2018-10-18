/**
 * 
 */
package customer;

import java.io.Serializable;
import java.util.ArrayList;
import order.Order;

/**
 * @author julianareider
 *
 */
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L; //Saw this in Zewdie example, does this help with ID numbers?
	private String id;
	private String lastName;
	private String firstName;
	private Address address;
	private CreditCard card;
	private String phone;
	private ArrayList<Order> orders;
	private String userName;
	private String password;
	private String email;
	
	/**
	 * Constructor
	 */
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String e) {
		email = e; 
	}

	//GETTERS AND SETTERS
	
	/**
	 * @return the id
	 */
	public String getGid() {
		return id;
	}

	/**
	 * @param newId the id to set
	 */
	public void setGid(String newId) {
		this.id = newId;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the card
	 */
	public CreditCard getCard() {
		return card;
	}

	/**
	 * @param card the card to set
	 */
	public void setCard(CreditCard card) {
		this.card = card;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the orders
	 */
	public ArrayList<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
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
	
	//OTHER METHODS
	
	

}
