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
	public static final String STATUS_URL = "order/orderService/status/{orderID}";
	public static final String CANCEL_URL = "order/orderService/order/cancelledorder/{orderID}";
	public static final String SEARCH_URL = "http://localhost:8081/productservice/products/searchresults/{searchterm}";
	public static final String BUY_URL = "order/orderService/order/neworder";
	public static final String DELETE_PROFILE = "http://localhost:8081/customerservice//customers/{customerId}";
	
	//Order Resource
	private String getCustomerOrdersURL = "order/orderService/orders/{customerID}";
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

	
	public String getGetCustomerOrdersURL(String hostname) {
		
		return getHostURL(hostname) + getCustomerOrdersURL;
	}

	public String getGetOrderURL(String hostname) {
		return getHostURL(hostname) + getOrderURL;
	}

	public String getGetAcknowledgementURL(String hostname) {
		return getHostURL(hostname) + getAcknowledgementURL;
	}

	public String getGetStatusURL(String hostname) {
		return getHostURL(hostname) + STATUS_URL;
	}

	public String getCancelOrderURL(String hostname) {
		return getHostURL(hostname) + CANCEL_URL;
	}

	public String getPlaceOrderURL(String hostname) {
		return getHostURL(hostname) + BUY_URL;
	}

	public String getShipOrderURL(String hostname) {
		return getHostURL(hostname) + shipOrderURL;
	}

	public String getFulfillOrderURL(String hostname) {
		return getHostURL(hostname) +  fulfillOrderURL;
	}

	public String getGetCustomerCreditCardsURL(String hostname) {
		return getHostURL(hostname) + getCustomerCreditCardsURL;
	}

	public String getGetCustomerCardURL(String hostname) {
		return getHostURL(hostname) + getCustomerCardURL;
	}


	public String getDeleteCreditCardURL(String hostname) {
		return getHostURL(hostname) + deleteCreditCardURL;
	}

	public String getNewCreditCardURL(String hostname) {
		return getHostURL(hostname) + newCreditCardURL;
	}
	
	// We need these conditions because when this runs in AWS it needs to return the correct URL.
		// this uses the ? conditional operator. It reads: (condition result, generates true or false) ? (if true) return expression 1 : (if false) return expression 2
		// http://www.cafeaulait.org/course/week2/43.html
	public String getHostURL(String hostname) {
		return ((hostname.toLowerCase().contains("localhost")) ? LOCALHOST : AWSHOST);
	}
}
