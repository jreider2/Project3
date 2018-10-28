package service.workflow;

import java.util.ArrayList;

import order.Order;
import order.OrderManager;
import order.OrderedItem;
import partner.Partner;
import partner.PartnerManager;
import product.Product;
import product.ProductManager;
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
		oR.setProductsOnOrder(getProductsOnOrder(o));
		return oR;
	}
	
	public ArrayList<OrderRepresentation> getOrders(String CustomerID){
		ArrayList<OrderRepresentation> arOr = new ArrayList<>();
		
		for (Order o : oM.getAllOrders(CustomerID)) {
			OrderRepresentation orTemp = new OrderRepresentation();
			orTemp.setOrderNo(o.getId());
			orTemp.setOrderStatus(o.getOrderStatus());
			
			orTemp.setProductsOnOrder(getProductsOnOrder(o));
			arOr.add(orTemp);
		}
		return arOr;
	}
	
	private ArrayList<ProductRequest> getProductsOnOrder(Order o){
		ProductRequest prTemp;
		ArrayList<ProductRequest> tempProducts = new ArrayList<ProductRequest>();
		for (Product productOnOrder : o.getItems()) {
			prTemp = new ProductRequest();
			prTemp.setDescription(productOnOrder.getDescription());
			prTemp.setPrice(productOnOrder.getPrice());
			prTemp.setId(productOnOrder.getId());
			prTemp.setName(productOnOrder.getName());
			prTemp.setProductOwnerID(productOnOrder.getProductOwner().getId());
			tempProducts.add(prTemp);
		}
		return tempProducts;
	}
	
	public OrderRepresentation submitOrder(String customerID, ArrayList<OrderedItem> products, String CreditCardNo) {
		OrderRepresentation oR = new OrderRepresentation();
		ProductRepresentation pRep = new ProductRepresentation();
		PartnerManager pm = new PartnerManager();
		ProductManager prodMan = new ProductManager();
		PartnerRepresentation partnerRep = new PartnerRepresentation();
		ArrayList<ProductRepresentation> productsOnOrder = new ArrayList<>();
		
		Product pr;
		for(OrderedItem oi : products) {
			pr = prodMan.getProduct(oi.getProductID());
			pRep.setId(pr.getId());
			pRep.setPrice(pr.getPrice());
			pRep.setQuantityOnOrder(oi.getQtyOnOrder());
			//don't set all properties because we don't need them all
			
			productsOnOrder.add(pRep);
		}
		
		Order newOrder = oM.addOrder(customerID, productsOnOrder, CreditCardNo);
		
		oR.setOrderNo(newOrder.getId());
		oR.setOrderStatus(newOrder.getOrderStatus());
		oR.setProductsOnOrder(getProductsOnOrder(newOrder));
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
	
	public boolean cancelOrder(String orderID) {
		return oM.cancelOrder(orderID);
	}
	
	
}
