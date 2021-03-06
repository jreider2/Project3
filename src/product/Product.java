/**
 * 
 */
package product;

import java.io.Serializable;

import partner.Partner;
import service.represntation.ProductRepresentation;

/**
 * @author julianareider
 *
 */
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L; //Saw this in Zewdie example, does this help with ID numbers?
	private String id;
	private String name;
	private String description;
	private double price;
	private Partner productOwner;

	
	public void initialize(ProductRepresentation pR) {
		this.id = pR.getId();
		this.name = pR.getName();
		this.description = pR.getDescription();
		this.price = pR.getPrice();
	}


	/**
	 * Constructor
	 */
	public Product() {
		// TODO Auto-generated constructor stub
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
	 * @return the name of the product
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param distription the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the productOwner
	 */
	public Partner getProductOwner() {
		return productOwner;
	}

	/**
	 * @param productOwner the productOwner to set
	 */
	public void setProductOwner(Partner productOwner) {
		this.productOwner = productOwner;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [name= " + name + ", productOwner= " + productOwner + "]";
	}
	
	


}
