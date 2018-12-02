package service.workflow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import common.Configurables;
import order.Order;
import partner.Partner;
import partner.PartnerManager;
import product.Product;
import service.represntation.Link;
import service.represntation.OrderRepresentation;
import service.represntation.OrderRequest;
import service.represntation.PartnerRepresentation;
import service.represntation.ProductRepresentation;
import service.represntation.ProductRequest;

/**
 * This class' responsibility is to manage the workflow of accessing/creating/updating/deleting resources
 *
 */
public class PartnerActivity {
	
	private static PartnerManager partnerManager = new PartnerManager();
	private Configurables urls = new Configurables();

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
		partnerRepresentation.addLink(new Link());
		//addLink(partnerRepresentation, "addProductToPartner", urls.getAddProductToPartnerURL(urls.LOCALHOST));
		addLink(partnerRepresentation, "deletePartner", urls.getDeletePartnerURL(urls.LOCALHOST));
		
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
		//addLink(partnerRepresentation, "addProductToPartner", urls.getAddProductToPartnerURL(urls.LOCALHOST));
		addLink(partnerRepresentation, "deletePartner", urls.getDeletePartnerURL(urls.LOCALHOST));
		
		return partnerRepresentation;
	}
	
	public PartnerRepresentation registerPartner(String companyName, String userName, String password, int addressID) {
		
		Partner p = partnerManager.registerNewPartner(companyName, userName, password, addressID);
		
		PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
		partnerRepresentation.setId(p.getId());
		partnerRepresentation.setCompanyName(p.getCompanyName());
		partnerRepresentation.setUserName(p.getUserName());
		partnerRepresentation.setPassword(p.getPassword());
		//addLink(partnerRepresentation, "addProductToPartner", urls.getAddProductToPartnerURL(urls.LOCALHOST));
		addLink(partnerRepresentation, "deletePartner", urls.getDeletePartnerURL(urls.LOCALHOST));
	
		
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
	
	public String deletePartner(String id) {
		//TODO Fix me in the DAO layer. 
		
		partnerManager.deletePartner(id);
		
		return "OK";
	}
	
	/**
	 * Sets all the links appropriately, for each kind of representation based on state
	 */
	private void addLink(PartnerRepresentation pRep, String rel, String url) {
		// Set up the activities that can be performed on orders
		Link link = new Link();
		link.setRel(rel);
		link.setUrl(url);
		link.setMediaType("application/xml, application/json");
		
		pRep.addLink(link);
	}

}
