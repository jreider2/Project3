/**
 * 
 */
package order;

import java.math.BigDecimal;
import java.util.ArrayList;

import dao.OrderDAO;
import partner.PartnerManager;
import product.Product;
import product.ProductManager;
import service.represntation.ProductRepresentation;

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
	
	public Order addOrder(String customerID, ArrayList<OrderedItem> products, String CreditCardNo) {
	
		BigDecimal orderTotal = new BigDecimal(0);
		
		for (OrderedItem oI : products) {
			orderTotal.add(new BigDecimal(oI.getProductPrice()));
		}
		
		Order order = dao.placeOrder(customerID, products, CreditCardNo, orderTotal);
		//NOTE TO JULIANA: This is where the partners should be notified of the order.
		PartnerManager pm = new PartnerManager();
		pm.pushOrderToPartner(order);
		
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
	public boolean shipOrder(String orderID) {
		//update status 
		dao.updateOrderStatus(orderID,"shipped");
		//return the current status of the order
		String status = dao.getOrderStatus(orderID);
		if (status != "" && status != null) {
			System.out.println("Order " + orderID + " shipped");
			return true;
		} else {
			System.out.println("Order " + orderID + " not shipped ERROR!");
			return false;
		}
	}
	
	public boolean fulfillOrder(String orderID) {
		//update status 
		dao.updateOrderStatus(orderID,"fulfilled");
		//return the current status of the order
		String status = dao.getOrderStatus(orderID);
		if (status != "" && status != null) {
			System.out.println("Order " + orderID + " fulfilled");
			return true;
		} else {
			System.out.println("Order " + orderID + " not fulfilled ERROR!");
			return false;
		}
	}
	
	/**
	 * @return current Order status. Shipped if shipped, other status if failed to ship it
	 */
	public boolean cancelOrder(String orderID) {
		//cancel by updating status
		return dao.updateOrderStatus(orderID,"cancelled");
	}

}
