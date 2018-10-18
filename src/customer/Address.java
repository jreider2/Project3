/**
 * 
 */
package customer;

/**
 * @author julianareider
 *
 */
public class Address {
	
	private int id;
	private String aptNumber;
	private String streetAndStreetNum;
	private String city;
	private String state;
	private String zipcode;

	/**
	 * 
	 */
	public Address() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the aptNumber
	 */
	public String getAptNumber() {
		return aptNumber;
	}

	/**
	 * @param aptNumber the aptNumber to set
	 */
	public void setAptNumber(String aptNumber) {
		this.aptNumber = aptNumber;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the street and street Number 
	 */
	public String getStreet() {
		return streetAndStreetNum;
	}

	/**
	 * @param street the street and street Number to set
	 */
	public void setStreet(String street) {
		this.streetAndStreetNum = street;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip code
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zip code to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	

}
