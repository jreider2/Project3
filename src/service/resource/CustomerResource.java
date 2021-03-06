package service.resource;

import java.util.Set;

import service.represntation.CustomerRepresentation;
import service.represntation.CustomerRequest;
import service.workflow.CustomerActivity;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.rs.security.cors.LocalPreflight;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

//@CrossOriginResourceSharing(
//		allowAllOrigins = true,
//		allowCredentials = true,
//		allowHeaders = {
//				"'Accept': 'application/json'",
//				"'Accept': 'application/xml'",
//				"'Content-Type':'application/json'",
//				"'Content-Type':'application/xml'"
//		}
//)
@Path("/")
public class CustomerResource implements CustomerService {
//	@Context
//	private HttpHeaders headers;
//	
	public CustomerResource() {
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customers")
	////@Cacheable(cc="public, maxAge=3600") example for caching
	public Set<CustomerRepresentation> getCustomers() {
		System.out.println("GET METHOD Request for all employees .............");
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.getCustomers();	
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customers/{customerId}")
	public CustomerRepresentation getCustomer(@PathParam("customerId") String id) { 
		System.out.println("GET METHOD Request from Client with employeeRequest String ............." + id);
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.getCustomer(id);
	}
	

	
	/**
	 * Register & create customer profile. 
	 */
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/customers")
	public CustomerRepresentation createCustomer(CustomerRequest  customerRequest) {
		System.out.println("POST METHOD Request from Client with ............." + customerRequest.getFirstName() + "  " + customerRequest.getLastName());
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.registerNewCustomer(customerRequest.getFirstName(), customerRequest.getLastName(), customerRequest.getStreet(), customerRequest.getAptno(), customerRequest.getCity(), customerRequest.getZipcode(), customerRequest.getState() );
	}
	
	
//	@POST
//	@Path("/customerAuthentication")
//	@LocalPreflight
//	public Response options() {
//		String origin = headers.getRequestHeader("Origin").get(0);
//		
//		return Response.ok()
//							.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "POST")
//							.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
//							.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "http://localhost:63342")
//							.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS, "Content-Type")
//							.build();
//	}
	
	/**
	 * Login for Customer
	 * @return Returns 204 No Content Status if user fails to login
	 */
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/customerAuthentication")
	@Consumes({"application/xml" , "application/json"})
	public CustomerRepresentation loginCustomer(CustomerRequest customerRequest) { 
		System.out.println("GET METHOD Request from Client for user Authentication .............");
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.loginCustomer(customerRequest.getUserName(), customerRequest.getPassword()); //returns null if failed to login
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/customers/{customerId}")
	public Response deleteCustomer(@PathParam("customerId") String id) {
		
		System.out.println("Delete METHOD Request from Client with employeeRequest String ............." + id);
		CustomerActivity customerActivity = new CustomerActivity();
		String res = customerActivity.deleteCustomer(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}

}
