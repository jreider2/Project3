package service.resource;

import java.sql.SQLException;
import java.util.Set;

import service.represntation.OrderRepresentation;
import service.represntation.OrderRequest;
import service.represntation.PartnerRepresentation;
import service.represntation.PartnerRequest;
import service.represntation.ProductRepresentation;
import service.represntation.ProductRequest;
import service.workflow.PartnerActivity;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;

@Path("/")
public class PartnerResource implements PartnerService {

	@Context private HttpServletResponse response;
	
	public PartnerResource() {	}
	
	/**
	 * Get existing partners
	 */
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/partners")
	//@Cacheable(cc="public, maxAge=3600") example for caching
	public Set<PartnerRepresentation> getPartner() {
		//TODO Incomplete. Not implemented in the PartnerDAO object. 
		System.out.println("GET METHOD Request for all partners .............");
		PartnerActivity partnerActivity = new PartnerActivity();
		return partnerActivity.getPartners();
	}
	
	/**
	 * Get existing partner
	 */
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/partners/{partnerId}")
	public PartnerRepresentation getPartner(@PathParam("partnerId") String id)  { //TODO throws SQLException
		System.out.println("GET METHOD Request from Client with partnerRequest String ............." + id);
		PartnerActivity pActivity = new PartnerActivity();
		return pActivity.getPartner(id);
		
	}
	
	
	/**
	 * Register & create partner profile. 
	 */
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/partners")
	public PartnerRepresentation createPartner(PartnerRequest  partnerRequest) {
		System.out.println("POST METHOD Request from Client with ............." + partnerRequest.getCompanyName());
		PartnerActivity partnerActivity = new PartnerActivity();
		return partnerActivity.registerPartner(partnerRequest.getCompanyName(), partnerRequest.getUserName(), partnerRequest.getPassword());
	}
	
	
	/**
	 * Add product to partner & Add product to Market place
	 */
	@PUT
	@Produces({"application/xml" , "application/json"})
	@Path("/partners/{partnerId}/newProduct")
	public PartnerRepresentation addProductToPartner(@PathParam("partnerId") String partnerID, ProductRequest productRequest) {
	
		System.out.println("PUT METHOD Request to update Partner ............." + productRequest.getName());
		PartnerActivity partnerActivity = new PartnerActivity();
		return partnerActivity.addProductToPartner(productRequest.getName(), productRequest.getDescription(), (int) productRequest.getPrice(), partnerID);
	}
	
	//NOTE TO JULIANA: I don't think this should be exposed to the client. Just done behind the scene when an order is placed.
	/**
	 * Push new Order to Partner
	 *//*
	@PUT
	@Produces({"application/xml" , "application/json"})
	@Path("/partners/orderNotification")
	public OrderRepresentation pushOrderToPartner(OrderRequest orderRequest) { // old param: @PathParam("partnerId") String partnerID
		System.out.println("PUT METHOD Request to update Partner .... Push Order .............order ID: " + orderRequest.getId());
		PartnerActivity partnerActivity = new PartnerActivity();
		return partnerActivity.pushOrderToPartner(orderRequest);
	}//TODO add partner name to system output 
*/	
	/**
	 * Delete existing partner
	 */
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/partners/{partnerId}")
	public Response deletePartner(@PathParam("partnerId") String id) { // TODO ADD THIS as parameterlist @PathParam("partnerId") String id
		System.out.println("Delete METHOD Request from Client with employeeRequest String ............." + id);
		PartnerActivity partnerActivity = new PartnerActivity();
		String res = partnerActivity.deletePartner(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}

}
