package service.resource;

import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import service.represntation.CustomerRepresentation;
import service.represntation.CustomerRequest;

@WebService
public interface CustomerService {
	
	public Set<CustomerRepresentation> getCustomers();
	public CustomerRepresentation getCustomer(String customerId);
	public CustomerRepresentation createCustomer(CustomerRequest customerRequest);
	public Response deleteCustomer(String customerId);

}
