package service.workflow;

import java.util.ArrayList;

import common.Configurables;
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
	private Configurables urls = new Configurables();
	
	public OrderActivity() {
		oM = new OrderManager();
	}
	
	public OrderRepresentation getOrder(String OrderNo) {
		
		Order o = oM.getOrder(OrderNo);
		OrderRepresentation oR = new OrderRepresentation();
		
		oR.setOrderNo(o.getId());
		oR.setOrderStatus(o.getOrderStatus());
		
		for (OrderedItem orderedItem: o.getProducts()) {//oR.setProductsOnOrder(o.getProducts());
			ProductRepresentation productRepresentation = new ProductRepresentation();
			productRepresentation.setId(orderedItem.getProductID());
			productRepresentation.setPrice(Double.parseDouble(orderedItem.getProductPrice()));
			productRepresentation.setQuantityOnOrder(orderedItem.getQtyOnOrder());
			oR.addProductRepresentation(productRepresentation);
		}
		 
		String orderId = o.getId();
		
		addLink(oR, "cancel", urls.CANCEL_URL.replace("{orderID}", orderId) );
		addLink(oR, "checkStatus", urls.STATUS_URL.replace("{orderID}", orderId));
		addLink(oR, "search", urls.SEARCH_URL); //NOTE: this URL is missing the search term
		
		return oR;
	}
	
	public ArrayList<OrderRepresentation> getOrders(String CustomerID){
		ArrayList<OrderRepresentation> arOr = new ArrayList<>();
		
		for (Order o : oM.getAllOrders(CustomerID)) {
			OrderRepresentation orTemp = new OrderRepresentation();
			orTemp.setOrderNo(o.getId());
			orTemp.setOrderStatus(o.getOrderStatus());
			
			for (OrderedItem orderedItem: o.getProducts()) {
				ProductRepresentation productRepresentation = new ProductRepresentation();
				productRepresentation.setId(orderedItem.getProductID());
				productRepresentation.setPrice(Double.parseDouble(orderedItem.getProductPrice()));
				productRepresentation.setQuantityOnOrder(orderedItem.getQtyOnOrder());
				orTemp.addProductRepresentation(productRepresentation);
			}
			//orTemp.setProductsOnOrder(o.getProducts());
			arOr.add(orTemp);
		}
		return arOr;
	}
	
	public OrderRepresentation submitOrder(String customerID, ArrayList<ProductRequest> products, String CreditCardNo) {
		
		OrderRepresentation oR = new OrderRepresentation();
		ProductManager prodMan = new ProductManager();
		ArrayList<OrderedItem> orderedItemList = new ArrayList<>();
		Product pr;
		
		//for each product request create an ordered list item 
		for(ProductRequest p : products) {
			OrderedItem oi = new OrderedItem();
			oi.setProductID(p.getId());
			oi.setQtyOnOrder(p.getQuantityOnOrder());
			oi.setProductPrice(Double.toString(p.getPrice()));
			pr = prodMan.getProduct(oi.getProductID());
			//add item to the collection
			orderedItemList.add(oi);
		}
		
		//create new order using ordered item list
		Order newOrder = oM.addOrder(customerID, orderedItemList, CreditCardNo);
		//add to database and get id back
		String orderId = newOrder.getId();
		
		//begin to create representation of order
		oR.setOrderNo(newOrder.getId());
		oR.setOrderStatus(newOrder.getOrderStatus());
		
		//for each ordered item make a prod rep
		for (OrderedItem orderedItem: orderedItemList) {
			ProductRepresentation productRepresentation = new ProductRepresentation();
			productRepresentation.setId(orderedItem.getProductID());
			productRepresentation.setPrice(Double.parseDouble(orderedItem.getProductPrice()));
			productRepresentation.setQuantityOnOrder(orderedItem.getQtyOnOrder());
			oR.addProductRepresentation(productRepresentation);
		}
		
		oR.setCustomerID(newOrder.getCustomerID());
		
		//add the links to representation
		addLink(oR, "checkStatus", urls.STATUS_URL.replace("{orderID}", orderId));
		addLink(oR, "cancel Order", urls.CANCEL_URL.replace("{orderID}", orderId));
		addLink(oR, "search", urls.SEARCH_URL); //NOTE: this URL is missing the search term
		
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
		OrderRepresentation orderRep = getOrder(orderID);
		//this is how I think we should do it. Rather than having a function to do it in each class.
		orderRep.addLink(new Link("search", urls.SEARCH_URL, "application/xml, application/json"));
		
		//addLink(orderRep, "search", urls.SEARCH_URL);//NOTE: this URL missing searchTerm!
		addLink(orderRep, "checkStatus", urls.STATUS_URL.replace("{orderID}", orderID));
		
		
		return orderRep;
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
		link.setMediaType("application/xml, application/json");
		
		orderRep.addLink(link);
	}
	
	
}
