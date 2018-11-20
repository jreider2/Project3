package service.resource;

import java.util.Set;

import service.represntation.CustomerRepresentation;
import service.represntation.CustomerRequest;
import service.workflow.CustomerActivity;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.CacheControl;

@Path("/")
public class CustomerResource implements CustomerService {

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
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customerAuthentication")
	public CustomerRepresentation loginCustomer(CustomerRequest  customerRequest) { 
		System.out.println("GET METHOD Request from Client for user Authentication .............");
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.loginCustomer(customerRequest.getUserName(), customerRequest.getPassword());
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
