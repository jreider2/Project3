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
	
	/**
	 * 
	 */
	public Configurables() {
		
		// TODO Auto-generated constructor stub
	}

}
