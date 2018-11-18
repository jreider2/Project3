package service.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import service.represntation.OrderRepresentation;
import service.represntation.OrderRequest;
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
	public OrderRepresentation getAcknowledgeFulfillment(@PathParam("orderId") String orderId) { 
		System.out.println("GET METHOD Request Order ID: ............." + orderId);
		OrderRepresentation oR = oA.getOrder(orderId);
		return oR;
	}
	
	
	@GET
	@Produces({"application/xml", "application/json"})
	@Path("/status/{orderID}")
	public OrderRepresentation getOrderStatus(@PathParam("orderID") String orderID) {
		OrderRepresentation oR = oA.getOrder(orderID);
		System.out.println("GET Method for order status:.........." + oR.getOrderNo() + ": " + oR.getOrderStatus());
		return oR;
	}
	
	@DELETE
	@Produces({"application/xml", "application/json"})
	@Path("/order/cancelledorder/{orderID}")
	public OrderRepresentation cancelOrder(@PathParam("orderID") String orderID) {
		OrderRepresentation oR = oA.cancelOrder(orderID);
		return oR;
	}
	
	@POST
	@Produces({"application/json", "application/xml"})
	@Consumes({"application/json", "application/xml"})
	@Path("/order/neworder")
	public OrderRepresentation placeOrder(OrderRequest oR) {
		return oA.submitOrder(oR.getCustomerId(), oR.getItems(), oR.getCcNo());
	}
	
	@PUT
	@Produces({"application/json", "application/xml"})
	@Consumes({"application/json", "application/xml"})
	@Path("/shippedOrder")
	public boolean shipOrder(String orderID) {
		return oA.shipOrder(orderID);
	} 
	
	@PUT
	@Produces({"application/json", "application/xml"})
	@Consumes({"application/json", "application/xml"})
	@Path("/fulfilledOrder")
	public boolean fulfillOrder(String orderID) {
		return oA.fulfillOrder(orderID);
	} 
	
}
