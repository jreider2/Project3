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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.rs.security.cors.LocalPreflight;

import service.represntation.OrderRepresentation;
import service.represntation.OrderRequest;
import service.workflow.OrderActivity;

/*@CrossOriginResourceSharing(
		allowAllOrigins = true,
		allowCredentials = true,
		allowHeaders = {
				"'Accept': 'application/json'",
				"'Accept': 'application/xml'",
				"'Content-Type':'application/json'",
				"'Content-Type':'application/xml'"
		}
)*/
@Path("/orderService/")
public class OrderResource implements OrderService {
	@Context
	private HttpHeaders headers;
	
	private OrderActivity oA;
	
	public OrderResource() {
		oA = new OrderActivity();
	}
	
	@GET
	@Path("/orders/{customerID}")
	@LocalPreflight
	public Response options(@PathParam("customerID") String customerNumber) {
		String origin = headers.getRequestHeader("Origin").get(0);
		
		return Response.ok()
							.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "GET")
							.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
							.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "http://localhost:63342")
							.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS, "Content-Type")
							.build();
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Consumes({"application/xml", "application/json"})
	@Path("/orders/{customerID}")
	public ArrayList<OrderRepresentation> getOrders(@PathParam("customerID") String customerNumber) {
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
	
	/*@POST
	@Path("/order/neworder")
	@LocalPreflight
	public Response options() {
		String origin = headers.getRequestHeader("Origin").get(0);
		
		return Response.ok()
							.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "POST")
							.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
							.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "http://localhost:63342")
							.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS, "Content-Type")
							.build();
	}*/
	@POST
	@Produces({"application/xml", "application/json"})
	@Consumes({"application/xml", "application/json"})
	@Path("/order/neworder")
	public OrderRepresentation placeOrder(OrderRequest oR) {
		return oA.submitOrder(oR.getCustomerId(), oR.getItems(), oR.getCcNo());
		//NOTE TO TA: The returned OrderRepresentation is only expected to have ProductRepresentations with 3 fields filled out (the rest are unnecessary and expected to be null)
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
