/**
 * 
 */
package common;

/**
 * @author julianareider
 *
 */
public class Configurables {
	
	public static final String STATUS_URL = "http://localhost:8081/order/orderService/status/{orderID}";
	public static final String CANCEL_URL = "http://localhost:8081/order/orderService/order/cancelledorder/{orderID}";
	public static final String SEARCH_URL = "http://localhost:8081/productservice/products/searchresults/{searchterm}";
	public static final String BUY_URL = "http://localhost:8081/order/orderService/order/neworder";
	public static final String DELETE_PROFILE = "http://localhost:8081/customerservice//customers/{customerId}";
	
	//Order Resource
	private String getCustomerOrdersURL;
	private String getOrderURL;
	private String getAcknowledgementURL;
	private String getStatusURL;
	private String cancelOrderURL;
	private String placeOrderURL;
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
	// this uses the ? conditional operator. It reads: (condition result, true or false) ? (if true) return expression 1 : (if false) return expression 2
	// http://www.cafeaulait.org/course/week2/43.html
	public String getGetCustomerOrdersURL(String hostname) {
		
		return (hostname.toLowerCase().contains("localhost")) ? "http://localhost:8081/" + getCustomerOrdersURL : "awsServicehost" + getCustomerOrdersURL;
	}

	public String getGetOrderURL(String hostname) {
		return (hostname.toLowerCase().contains("localhost")) ? "http://localhost:8081/" + getOrderURL : "awsServicehost" + getOrderURL;
	}

	public String getGetAcknowledgementURL(String hostname) {
		return (hostname.toLowerCase().contains("localhost")) ? "http://localhost:8081/" + getAcknowledgementURL : "awsServiceHost" + getAcknowledgementURL;
	}

	public String getGetStatusURL(String hostname) {
		return (hostname.toLowerCase().contains("localhost")) ? "http://localhost:8081/" + getStatusURL : "aws" + getStatusURL;
	}

	public String getCancelOrderURL(String hostname) {
		return (hostname.toLowerCase().contains("localhost")) ? "http://localhost:8081/" + cancelOrderURL : "aws" + cancelOrderURL;
	}

	public String getPlaceOrderURL(String hostname) {
		return (hostname.toLowerCase().contains("localhost")) ? "http://localhost:8081/" + placeOrderURL  : "aws" + placeOrderURL;
	}

	public String getShipOrderURL(String hostname) {
		return (hostname.toLowerCase().contains("localhost")) ? "http://localhost:8081/" + shipOrderURL : "aws" + shipOrderURL;
	}

	public String getFulfillOrderURL(String hostname) {
		return (hostname.toLowerCase().contains("localhost")) ? "http://localhost:8081/" + fulfillOrderURL  : "aws" +  fulfillOrderURL;
	}

	public String getGetCustomerCreditCardsURL(String hostname) {
		return (hostname.toLowerCase().contains("localhost")) ? "http://localhost:8081/" + getCustomerCreditCardsURL : "aws" + getCustomerCreditCardsURL;
	}

	public String getGetCustomerCardURL(String hostname) {
		return (hostname.toLowerCase().contains("localhost")) ? "http://localhost:8081/" + getCustomerCardURL : "aws" + getCustomerCardURL;
	}


	public String getDeleteCreditCardURL(String hostname) {
		return (hostname.toLowerCase().contains("localhost")) ? "http://localhost:8081/" + deleteCreditCardURL : "aws" + deleteCreditCardURL;
	}

	public String getNewCreditCardURL(String hostname) {
		return (hostname.toLowerCase().contains("localhost")) ? "http://localhost:8081/" + newCreditCardURL : "aws" + newCreditCardURL;
	}


	
	
}
