package service.workflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import common.Configurables;
import partner.Partner;
import product.Product;
import product.ProductManager;
import service.represntation.PartnerRepresentation;
import service.represntation.ProductRepresentation;
import service.represntation.Link;

/**
 * This class' responsibility is to manage the workflow of accessing/creating/updating/deleting resources 
 *
 */
public class ProductActivity {

	private static ProductManager productManager = new ProductManager();
	private Configurables configurables = new Configurables();
	
	public ProductActivity() {}

	public Set<ProductRepresentation> getProducts() {
		
		Set<Product> products = new HashSet<>();
		Set<ProductRepresentation> productRepresentations = new HashSet<>();
		products = productManager.getAllProducts();
		
		for (Product p : products) {
			ProductRepresentation productRepresentation = new ProductRepresentation();
			productRepresentation.setId(p.getId());
			productRepresentation.setName(p.getName());
			productRepresentation.setDescription(p.getDescription()); 
			productRepresentation.setPrice(p.getPrice());
			productRepresentation.setProductOwnerID(p.getProductOwner().getId()); //TODO make sure that product owner is guaranteed to have an ID!!
			productRepresentations.add(productRepresentation);//now add this representation in the list
			addLink(productRepresentation, "buy", configurables.BUY_URL);
		}
		
		return productRepresentations;
	}
	
	public ArrayList<ProductRepresentation> searchProducts(String searchTerm) {
		
		ArrayList<Product> products = new ArrayList<>();
		ArrayList<ProductRepresentation> productRepresentations = new ArrayList<>();
		products = productManager.searchForProduct(searchTerm);
		
		for (Product p : products) {
			ProductRepresentation productRepresentation = new ProductRepresentation();
			productRepresentation.setId(p.getId());
			productRepresentation.setName(p.getName());
			productRepresentation.setDescription(p.getDescription()); 
			productRepresentation.setPrice(p.getPrice());
			productRepresentation.setProductOwnerID(p.getProductOwner().getId()); //TODO make sure that product owner is guaranteed to have an ID!!
			//Add this representation in the list
			productRepresentations.add(productRepresentation);
			//Add Link to representation:
			addLink(productRepresentation, "buy", configurables.BUY_URL);
			addLink(productRepresentation, "search", configurables.SEARCH_URL );//Note that this url is missing a search term! (we don't know the user wants)
		}
		
		return productRepresentations;
	}
	
	public ProductRepresentation getProduct(String id) {
		
		Product product = productManager.getProduct(id);
		
		ProductRepresentation productRep = new ProductRepresentation();
		productRep.setId(product.getId());
		productRep.setName(product.getName());
		productRep.setDescription(product.getDescription());
		productRep.setPrice(product.getPrice());
		productRep.setProductOwnerID(product.getProductOwner().getId());//TODO make sure that product owner is guaranteed to have an ID!!
		
		// Add the links
		addLink(productRep, "buy", configurables.BUY_URL);
		addLink(productRep, "search", configurables.SEARCH_URL);//Note that this url is missing a search term! (we don't know the user wants)
		
		return productRep;
	}
	
	public ProductRepresentation createProduct(String name, String description, double price, String productOwnerPartnerID) {
		
		Product p = productManager.addProduct(name, description, (int)price, productOwnerPartnerID);
		
		ProductRepresentation productRepresentation = new ProductRepresentation();
		productRepresentation.setId(p.getId());
		productRepresentation.setName(p.getName());
		productRepresentation.setDescription(p.getDescription());
		productRepresentation.setPrice(p.getPrice());
		productRepresentation.setProductOwnerID(p.getProductOwner().getId());
		
		return productRepresentation;
	}
	
	public String deleteProduct(String id) {
		
		productManager.deleteProduct(id);
		
		return "OK";
	}
	
	/**
	 * Sets all the links appropriately, for each kind of representation based on state
	 */
	private void addLink(ProductRepresentation productRep, String rel, String url) {
		// Set up the activities that can be performed on orders
		Link link = new Link();
		link.setRel(rel);
		link.setUrl(url);
		
		productRep.addLink(link);
	}
	
}
