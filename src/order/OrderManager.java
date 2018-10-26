/**
 * 
 */
package order;

import java.math.BigDecimal;
import java.util.ArrayList;

import dao.OrderDAO;
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
	
	public Order addOrder(String customerID, ArrayList<ProductRepresentation> products, String CreditCardNo) {
	
		BigDecimal orderTotal = new BigDecimal(0);
		ArrayList<Product> arProducts = new ArrayList<>();
		Product p = new Product();
		
		for(ProductRepresentation pR : products) {
			p.initialize(pR);
			arProducts.add(p);
		}
		
		Order order = dao.placeOrder(customerID, arProducts, CreditCardNo, orderTotal);
		
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
	public boolean cancelOrder(String orderID) {
		//cancel by updating status
		return dao.updateOrderStatus(orderID,"canceled");
	}

}
