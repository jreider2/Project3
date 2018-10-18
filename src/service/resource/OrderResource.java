package service.resource;

import java.util.ArrayList;

import javax.ws.rs.Path;

import service.represntation.OrderRepresentation;
import service.workflow.OrderActivity;

@Path("/orderService/")
public class OrderResource implements OrderService {
	
	private OrderActivity oA;
	
	public OrderResource() {
		oA = new OrderActivity();
	}
	
	//@POST with customer number
	//@Path("/orders")
	//@Produces({"application/xml" , "application/json"})
	public ArrayList<OrderRepresentation> getOrders(String customerNumber) {
		return oA.getOrders(customerNumber);
	}
		
	//@POST with order number
	//@Path ("/order")
	//@Produces({"application/xml" , "application/json"})
	public OrderRepresentation getOrder(String orderNo) {
		return oA.getOrder(orderNo);
	}		
}
