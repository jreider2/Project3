package service.workflow;

import java.util.ArrayList;

import order.Order;
import order.OrderManager;
import service.represntation.OrderRepresentation;

public class OrderActivity {
	private OrderManager oM;
	public OrderActivity() {
		oM = new OrderManager();
	}
	
	public OrderRepresentation getOrder(String OrderNo) {
		
		Order o = oM.getOrder(OrderNo);
		OrderRepresentation oR = new OrderRepresentation();
		
		oR.setOrderNo(o.getId());
		oR.setOrderStatus(o.getOrderStatus());
		oR.setProductsOnOrder(o.getItems());
		return oR;
	}
	
	public ArrayList<OrderRepresentation> getOrders(String CustomerID){
		ArrayList<OrderRepresentation> arOr = new ArrayList<>();
		
		for (Order o : oM.getAllOrders(CustomerID)) {
			OrderRepresentation orTemp = new OrderRepresentation();
			orTemp.setOrderNo(o.getId());
			orTemp.setOrderStatus(o.getOrderStatus());
			orTemp.setProductsOnOrder(o.getItems());
			arOr.add(orTemp);
		}
		return arOr;
	}
	
	public Order submitOrder(String customerID, ArrayList<String> productIDs, String CreditCardNo) {
		return oM.addOrder(customerID, productIDs, CreditCardNo);
	}

	public String getAcknowledgeFulfillment(String orderId) {
		
		String status = oM.getOrderStatus(orderId);
		
		if (status.equalsIgnoreCase("fulfilled")) {
			return "OK";
		} 
		//order not fulfilled
		return "Not OK";
	}
	
	public boolean cancelOrder(String orderID) {
		return oM.cancelOrder(orderID);
	}
	
	
}
