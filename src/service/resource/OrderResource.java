package service.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import service.represntation.OrderRepresentation;
import service.workflow.OrderActivity;

@Path("/orderService/")
public class OrderResource implements OrderService {
	
	private OrderActivity oA;
	
	public OrderResource() {
		oA = new OrderActivity();
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Consumes({"application/xml", "application/json"})
	@Path("/orders")
	public ArrayList<OrderRepresentation> getOrders(@QueryParam("customerID") String customerNumber) {
		return oA.getOrders(customerNumber);
	}
		
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path ("/order")
	public OrderRepresentation getOrder(@QueryParam("orderID") String orderNo) {
		return oA.getOrder(orderNo);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/order/fulfillmentAcknowledgement/{orderId}")
	public Response getAcknowledgeFulfillment(@PathParam("orderId") String orderId) { 
		System.out.println("GET METHOD Request Order ID: ............." + orderId);
		OrderActivity orderActivity = new OrderActivity();
		String res = orderActivity.getAcknowledgeFulfillment(orderId);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
	@GET
	@Produces({"application/xml", "application/json"})
	@Path("/order/status")
	public String getOrderStatus(@QueryParam("orderID") String orderID) {
		OrderActivity oA = new OrderActivity();
		OrderRepresentation oR = oA.getOrder(orderID);
		
		System.out.println("GET Method for order status:.........." + oR.getOrderNo() + ": " + oR.getOrderStatus());
		return oR.getOrderStatus();
	}
	
}
