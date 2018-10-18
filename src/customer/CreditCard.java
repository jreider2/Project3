/**
 * 
 */
package customer;

import dao.CreditCardDAO;

/**
 * @author julianareider
 *
 */
public class CreditCard {
	
	private String id;
	private String creditCardNumber;
	private String securityCode;
	private String cardHolderName;
	private String cardExpDate;
	private String customerID;

	/**
	 * 
	 */
	public CreditCard() {
		// TODO Auto-generated constructor stub
	}

	public String getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(String custNo) {
		customerID = custNo;
	}
	
	public String getExpDate() {
		return cardExpDate;
	}
	
	public void setExpDate(String expDate) {
		cardExpDate = expDate;
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
	
	public void setcardHolderName(String name) {
		cardHolderName = name;
	}
	
	public String getCardHolderName() {
		return cardHolderName;
	}

	/**
	 * @return the creditCardNumber
	 */
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	/**
	 * @param creditCardNumber the creditCardNumber to set
	 */
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	/**
	 * @return the securityCode
	 */
	public String getSecurityCode() {
		return securityCode;
	}

	/**
	 * @param securityCode the securityCode to set
	 */
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
		
	@Override
	public String toString() {
		return "Credit Card: " + creditCardNumber + " " + securityCode  + " " + cardHolderName  + " " + cardExpDate  + " " + customerID;
	}
	

}
