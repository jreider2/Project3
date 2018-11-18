package service.workflow;

import java.util.ArrayList;

import order.Order;
import order.OrderManager;
import order.OrderedItem;
import partner.Partner;
import partner.PartnerManager;
import product.Product;
import product.ProductManager;
import service.represntation.AbstractRepresentation;
import service.represntation.CustomerRepresentation;
import service.represntation.Link;
import service.represntation.OrderRepresentation;
import service.represntation.OrderRequest;
import service.represntation.PartnerRepresentation;
import service.represntation.ProductRepresentation;
import service.represntation.ProductRequest;

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
		oR.setProductsOnOrder(o.getProducts());
		return oR;
	}
	
	public ArrayList<OrderRepresentation> getOrders(String CustomerID){
		ArrayList<OrderRepresentation> arOr = new ArrayList<>();
		
		for (Order o : oM.getAllOrders(CustomerID)) {
			OrderRepresentation orTemp = new OrderRepresentation();
			orTemp.setOrderNo(o.getId());
			orTemp.setOrderStatus(o.getOrderStatus());
			
			orTemp.setProductsOnOrder(o.getProducts());
			arOr.add(orTemp);
		}
		return arOr;
	}
	
	public OrderRepresentation submitOrder(String customerID, ArrayList<OrderedItem> products, String CreditCardNo) {
		OrderRepresentation oR = new OrderRepresentation();
		ProductManager prodMan = new ProductManager();
		
		Product pr;
		for(OrderedItem oi : products) {
			pr = prodMan.getProduct(oi.getProductID());
			oi.setProductPrice(Double.toString(pr.getPrice()));			
		}
		
		Order newOrder = oM.addOrder(customerID, products, CreditCardNo);
		
		oR.setOrderNo(newOrder.getId());
		oR.setOrderStatus(newOrder.getOrderStatus());
		oR.setProductsOnOrder(newOrder.getProducts());
		oR.setCustomerID(newOrder.getCustomerID());
		return oR;
	}

	public String getAcknowledgeFulfillment(String orderId) {
		
		String status = oM.getOrderStatus(orderId);
		
		if (status.equalsIgnoreCase("fulfilled")) {
			return "OK";
		} 
		//order not fulfilled
		return "Not OK";
	}
	
	public OrderRepresentation cancelOrder(String orderID) {
		oM.cancelOrder(orderID);
		return getOrder(orderID);
	}
	
	public boolean shipOrder(String orderID) {
		return oM.shipOrder(orderID);
	}
	
	public boolean fulfillOrder(String orderID) {
		return oM.fulfillOrder(orderID);
	}
	
	/**
	 * Sets all the links appropriately, for each kind of representation based on state
	 */
	private void addLink(OrderRepresentation orderRep, String rel, String url) {
		// Set up the activities that can be performed on orders
		Link link = new Link();
		link.setRel(rel);
		link.setUrl(url);
		
		orderRep.addLink(link);
	}
	
	
}
