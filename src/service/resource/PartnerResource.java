package service.resource;

import java.util.Set;

import service.represntation.OrderRequest;
import service.represntation.PartnerRepresentation;
import service.represntation.PartnerRequest;
import service.represntation.ProductRepresentation;
import service.represntation.ProductRequest;
import service.workflow.PartnerActivity;

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

@Path("/partnerService/")
public class PartnerResource implements PartnerService {

	public PartnerResource() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get existing partners
	 */
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/partner")
	//@Cacheable(cc="public, maxAge=3600") example for caching
	public Set<PartnerRepresentation> getPartner() {
		System.out.println("GET METHOD Request for all partners .............");
		PartnerActivity partnerActivity = new PartnerActivity();
		return partnerActivity.getPartners();
	}
	
	/**
	 * Get existing partner
	 */
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}")
	public PartnerRepresentation getPartner(@PathParam("partnerId") String id) { // TODO ADD THIS BACK IN @PathParam("partnerId") String id
		System.out.println("GET METHOD Request from Client with partnerRequest String ............." + id);
		PartnerActivity pActivity = new PartnerActivity();
		return pActivity.getPartner(id);
	}
	
	
	/**
	 * Register & create partner profile. 
	 */
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/partner")
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
	@Path("/partner/{partnerId}/newProduct")
	public PartnerRepresentation addProductToPartner(@PathParam("partnerId") String partnerID, ProductRequest productRequest) {
		
		//TODO add partner name to system output 
		System.out.println("PUT METHOD Request to update Partner ............." + productRequest.getName());
		PartnerActivity partnerActivity = new PartnerActivity();
		return partnerActivity.addProductToPartner(productRequest.getName(), productRequest.getDescription(), (int) productRequest.getPrice(), partnerID);
	}
	
	/**
	 * Push new Order to Partner
	 */
	@PUT
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}/pushOrder")
	public PartnerRepresentation pushOrderToPartner(@PathParam("partnerId") String partnerID, OrderRequest orderRequest) { // TODO ADD THIS BACK IN @PathParam("partnerId") String partnerID
		//TODO add partner name to system output 
		System.out.println("PUT METHOD Request to update Partner .... Push Order .............order ID: " + orderRequest.getId());
		PartnerActivity partnerActivity = new PartnerActivity();
		
		//TODO FIX ME:
		
		//return partnerActivity.pushOrderToPartner(orderRequest);
		return new PartnerRepresentation();
	}
	
	/**
	 * Delete existing partner
	 */
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}")
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
