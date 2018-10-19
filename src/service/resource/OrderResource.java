package service.resource;

import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import service.represntation.OrderRepresentation;
import service.workflow.OrderActivity;

@Path("/orderService/")
public class OrderResource implements OrderService {
	
	private OrderActivity oA;
	
	public OrderResource() {
		oA = new OrderActivity();
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/orders")
	public ArrayList<OrderRepresentation> getOrders(String customerNumber) {
		return oA.getOrders(customerNumber);
	}
		
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path ("/order")
	public OrderRepresentation getOrder(String orderNo) {
		return oA.getOrder(orderNo);
	}		
}
