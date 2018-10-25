package service.resource;

import java.util.ArrayList;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import service.represntation.OrderRepresentation;

@WebService
public interface OrderService {
	
	public ArrayList<OrderRepresentation> getOrders(String customerNumber);
	public OrderRepresentation getOrder(String orderNo);
	public Response getAcknowledgeFulfillment(String orderId);
	public String getOrderStatus(String orderID);
	public boolean cancelOrder(String orderID);

}
