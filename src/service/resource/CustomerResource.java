package service.resource;

import java.util.Set;

//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
//
//import com.company.hr.service.representation.EmployeeRepresentation;
//import com.company.hr.service.representation.EmployeeRequest;
//import com.company.hr.service.workflow.EmployeeActivity;

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

@Path("/customerservice/")
public class CustomerResource implements CustomerService {

	public CustomerResource() {
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customer")
	////@Cacheable(cc="public, maxAge=3600") example for caching
	public Set<CustomerRepresentation> getCustomers() {
		System.out.println("GET METHOD Request for all employees .............");
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.getCustomers();	
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/{customerId}")
	public CustomerRepresentation getCustomer(@PathParam("customerId") String id) { 
		System.out.println("GET METHOD Request from Client with employeeRequest String ............." + id);
		CustomerActivity empActivity = new CustomerActivity();
		return empActivity.getCustomer(id);
	}
	
	/**
	 * Register & create customer profile. 
	 */
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/customer")
	public CustomerRepresentation createCustomer(CustomerRequest  customerRequest) {
		System.out.println("POST METHOD Request from Client with ............." + customerRequest.getFirstName() + "  " + customerRequest.getLastName());
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.registerNewCustomer(customerRequest.getFirstName(), customerRequest.getLastName(), customerRequest.getStreet(), customerRequest.getAptno(), customerRequest.getCity(), customerRequest.getZipcode(), customerRequest.getState() );
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/{customerId}")
	public Response deleteCustomer(@PathParam("customerId") String id) {
		
		//TODO Edit 
		
		System.out.println("Delete METHOD Request from Client with employeeRequest String ............." + id);
		CustomerActivity empActivity = new CustomerActivity();
		String res = empActivity.deleteCustomer(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}

}
