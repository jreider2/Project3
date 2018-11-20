/**
 * 
 */
package common;

/**
 * @author julianareider
 *
 */
public class Configurables {
	public static final String LOCALHOST = "http://localhost:8081/";
	public static final String AWSHOST = "awshostURLhere";
	public static final String STATUS_URL = "http://localhost:8081/order/orderService/status/{orderID}";
	public static final String CANCEL_URL = "http://localhost:8081/order/orderService/order/cancelledorder/{orderID}";
	public static final String SEARCH_URL = "http://localhost:8081/productservice/products/searchresults/{searchterm}";
	public static final String BUY_URL = "http://localhost:8081/order/orderService/order/neworder";
	public static final String DELETE_PROFILE = "http://localhost:8081/customerservice//customers/{customerId}";
	
	//Order Resource
	private String getCustomerOrdersURL;
	private String getOrderURL;
	private String getAcknowledgementURL;
	private String shipOrderURL;
	private String fulfillOrderURL;
	
	//CreditCard Resource
	private String getCustomerCreditCardsURL;
	private String getCustomerCardURL;
	private String deleteCreditCardURL;
	private String newCreditCardURL;
	
	/**
	 * 
	 */
	public Configurables() {
		
		// TODO Auto-generated constructor stub
	}

	// We need these conditions because when this runs in AWS it needs to return the correct URL.
	// this uses the ? conditional operator. It reads: (condition result, generates true or false) ? (if true) return expression 1 : (if false) return expression 2
	// http://www.cafeaulait.org/course/week2/43.html
	public String getGetCustomerOrdersURL(String hostname) {
		
		return ((hostname.toLowerCase().contains("localhost")) ? LOCALHOST : AWSHOST) + getCustomerOrdersURL;
	}

	public String getGetOrderURL(String hostname) {
		return ((hostname.toLowerCase().contains("localhost")) ? LOCALHOST : AWSHOST) + getOrderURL;
	}

	public String getGetAcknowledgementURL(String hostname) {
		return ((hostname.toLowerCase().contains("localhost")) ? LOCALHOST : AWSHOST) + getAcknowledgementURL;
	}

	public String getGetStatusURL(String hostname) {
		return ((hostname.toLowerCase().contains("localhost")) ? LOCALHOST : AWSHOST) + STATUS_URL;
	}

	public String getCancelOrderURL(String hostname) {
		return ((hostname.toLowerCase().contains("localhost")) ? LOCALHOST : AWSHOST) + CANCEL_URL;
	}

	public String getPlaceOrderURL(String hostname) {
		return ((hostname.toLowerCase().contains("localhost")) ? LOCALHOST  : AWSHOST) + BUY_URL;
	}

	public String getShipOrderURL(String hostname) {
		return ((hostname.toLowerCase().contains("localhost")) ? LOCALHOST : AWSHOST) + shipOrderURL;
	}

	public String getFulfillOrderURL(String hostname) {
		return ((hostname.toLowerCase().contains("localhost")) ? LOCALHOST  : AWSHOST) +  fulfillOrderURL;
	}

	public String getGetCustomerCreditCardsURL(String hostname) {
		return ((hostname.toLowerCase().contains("localhost")) ? LOCALHOST : AWSHOST) + getCustomerCreditCardsURL;
	}

	public String getGetCustomerCardURL(String hostname) {
		return ((hostname.toLowerCase().contains("localhost")) ? LOCALHOST : AWSHOST) + getCustomerCardURL;
	}


	public String getDeleteCreditCardURL(String hostname) {
		return ((hostname.toLowerCase().contains("localhost")) ? LOCALHOST : AWSHOST) + deleteCreditCardURL;
	}

	public String getNewCreditCardURL(String hostname) {
		return ((hostname.toLowerCase().contains("localhost")) ? LOCALHOST : AWSHOST) + newCreditCardURL;
	}
}
