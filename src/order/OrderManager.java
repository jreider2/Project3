/**
 * 
 */
package order;

import java.util.ArrayList;

import dao.OrderDAO;
import product.Product;

/**
 * @author julianareider
 *
 */
public class OrderManager {
	
	private static OrderDAO dao = new OrderDAO();

	public OrderManager() {}
	
	
	public ArrayList<Order> getAllOrders(String customerID){
		return dao.getAllOrders(customerID);
	}
	
	public Order getOrder(String id) {
		return dao.getOrder(id);
	}
	
	public Order addOrder(String customerID, ArrayList<String> productIDs, String CreditCardNo) {
	
		Order order = dao.addOrder(customerID, productIDs, CreditCardNo);
		
		return order;
	}
	
	public void updateOrderStatus(String orderid, String status) {
		dao.updateOrderStatus(orderid, status);
	}


	public String getOrderStatus(String orderID) {
		return dao.getOrderStatus(orderID);
	}
	
	/**
	 * @return current Order status. Shipped if shipped, other status if failed to ship it
	 */
	public String shipOrder(String orderID) {
		boolean isShipped = false;
		//update status 
		isShipped = dao.updateOrderStatus(orderID,"shipped");
		//return the current status of the order
		return dao.getOrderStatus(orderID);
	}
	
	/**
	 * @return current Order status. Shipped if shipped, other status if failed to ship it
	 */
	public String cancelOrder(String orderID) {
		boolean isCanceled = false;
		//cancel by updating status
		isCanceled = dao.updateOrderStatus(orderID,"canceled");
		//return the current status of the order
		return dao.getOrderStatus(orderID);
	}

}
