package service.workflow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import order.Order;
import partner.Partner;
import partner.PartnerManager;
import product.Product;
import service.represntation.OrderRequest;
import service.represntation.PartnerRepresentation;
import service.represntation.ProductRequest;


/**
 * This class' responsibility is to manage the workflow of accessing/creating/updating/deleting resources
 *
 */
public class PartnerActivity {
	
	private static PartnerManager partnerManager = new PartnerManager();

	public PartnerActivity() { }
	
	public Set<PartnerRepresentation> getPartners() {
		
		Set<Partner> partners = new HashSet<>();
		Set<PartnerRepresentation> partnerRepresentations = new HashSet<>();
		partners = partnerManager.getAllPartners();
		
		for (Partner p : partners) {
			PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
			partnerRepresentation.setId(p.getId());
			partnerRepresentation.setCompanyName(p.getCompanyName());
			partnerRepresentation.setUserName(p.getUserName());
			partnerRepresentation.setPassword(p.getPassword());
			partnerRepresentations.add(partnerRepresentation);//now add this representation in the list
		}
		return partnerRepresentations;
	}
	
	public PartnerRepresentation getPartner(String id) {
		
		Partner p = partnerManager.getPartner(id);
		
		PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
		partnerRepresentation.setId(p.getId());
		partnerRepresentation.setCompanyName(p.getCompanyName());
		partnerRepresentation.setUserName(p.getUserName());
		partnerRepresentation.setPassword(p.getPassword());
		
		return partnerRepresentation;
	}
	
	public PartnerRepresentation registerPartner(String companyName, String userName, String password) {
		
		int defaultAddressID = 1; //TODO Do we need to create a registerNewPartner method in partnerManager that doesn't require addressID? 
		Partner p = partnerManager.registerNewPartner(companyName, userName, password, defaultAddressID);
		
		PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
		partnerRepresentation.setId(p.getId());
		partnerRepresentation.setCompanyName(p.getCompanyName());
		partnerRepresentation.setUserName(p.getUserName());
		partnerRepresentation.setPassword(p.getPassword());
		
		return partnerRepresentation;
	}
	
	public PartnerRepresentation registerPartner(String companyName, String userName, String password, int addressID) {
		
		Partner p = partnerManager.registerNewPartner(companyName, userName, password, addressID);
		
		PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
		partnerRepresentation.setId(p.getId());
		partnerRepresentation.setCompanyName(p.getCompanyName());
		partnerRepresentation.setUserName(p.getUserName());
		partnerRepresentation.setPassword(p.getPassword());
		
		return partnerRepresentation;
	}
	
	public PartnerRepresentation addProductToPartner(String productName, String pDescription, int productPrice, String partnerID) {
		
		Partner p = partnerManager.addProductToPartner(productName, pDescription, productPrice, partnerID);
		//TODO make sure this above operation actually adds it to the database
		
		PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
		partnerRepresentation.setId(p.getId());
		partnerRepresentation.setCompanyName(p.getCompanyName());
		partnerRepresentation.setUserName(p.getUserName());
		partnerRepresentation.setPassword(p.getPassword());
		
		return partnerRepresentation;
	}
	
	public boolean pushOrderToPartner(OrderRequest oR) {
		
		//TODO test this method
		
		//the order only needs it's order Id and products attributes filled
		Order o = new Order();
		//set order ID
		o.setId(oR.getId());
		//set the products in the order
		for (ProductRequest p : oR.getProducts()) {
			//each product object needs productOwner attribute filled 
			Product currP = new Product();
			//create partner with proper ID
			Partner currPartner = new Partner();
			currPartner.setId(oR.getPartnerRep().getId());
			//set the product owner in the product
			currP.setProductOwner(currPartner);
			//add to the order
			o.addProduct(currP);
		}
		//let partnerManager push order and return status of success or failure
		return partnerManager.pushOrderToPartner(o);
	}
	
	public String deletePartner(String id) {
		
		partnerManager.deletePartner(id);
		
		return "OK";
	}

}
