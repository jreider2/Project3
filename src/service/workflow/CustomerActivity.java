package service.workflow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import customer.Customer;
import customer.CustomerManager;
import service.represntation.CustomerRepresentation;
import service.represntation.Link;
import service.represntation.ProductRepresentation;

/**
 * This class' responsibility is to manage the workflow of accessing/creating/updating/deleting resources
 *
 */
public class CustomerActivity {
	
	private static CustomerManager cmanager = new CustomerManager();
	
	public CustomerActivity() {
	}
	
	public Set<CustomerRepresentation> getCustomers() {
		
		Set<Customer> customers = new HashSet<Customer>();
		Set<CustomerRepresentation> employeeRepresentations = new HashSet<>();
		//employees = dao.getAllEmployees();
		customers = cmanager.getAllCustomers();
		
		Iterator<Customer> it = customers.iterator();
		while(it.hasNext()) {
          Customer customer = (Customer)it.next();
          CustomerRepresentation customerRepresentation = new CustomerRepresentation();
          customerRepresentation.setId(customer.getGid());
          customerRepresentation.setFirstName(customer.getFirstName());
          customerRepresentation.setLastName(customer.getLastName());
          
          //now add this representation in the list
          employeeRepresentations.add(customerRepresentation);
        }
		return employeeRepresentations;
	}
	
	public CustomerRepresentation getCustomer(String id) {
		
		Customer customer = cmanager.getCustomer(id);
		
		CustomerRepresentation customerRep = new CustomerRepresentation();
		customerRep.setFirstName(customer.getFirstName());
		customerRep.setLastName(customer.getLastName());
		customerRep.setId(customer.getGid());
		
		return customerRep;
	}
	
	public CustomerRepresentation registerNewCustomer(String firstName, String lastName, String street, String aptno, String city, String zipcode, String state) {
		
		Customer customer = cmanager.registerNewCustomer(firstName, lastName, street, aptno, city, zipcode, state);
		
		CustomerRepresentation cRep = new CustomerRepresentation();
		cRep.setFirstName(customer.getFirstName());
		cRep.setLastName(customer.getLastName());
		cRep.setId(customer.getGid());
		
		return cRep;
	}
	
	public String deleteCustomer(String id) {
		
		cmanager.deleteCustomer(id);
		
		return "OK";
	}
	
	/**
	 * Sets all the links appropriately, for each kind of representation based on state
	 */
	private void addLink(CustomerRepresentation customerRep, String rel, String url) {
		// Set up the activities that can be performed on orders
		Link link = new Link();
		link.setRel(rel);
		link.setUrl(url);
		
		customerRep.addLink(link);
	}
}
