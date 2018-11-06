package service.workflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import partner.Partner;
import product.Product;
import product.ProductManager;
import service.represntation.PartnerRepresentation;
import service.represntation.ProductRepresentation;
import service.represntation.linkMetaData;

/**
 * This class' responsibility is to manage the workflow of accessing/creating/updating/deleting resources 
 *
 */
public class ProductActivity {

	private static ProductManager productManager = new ProductManager();
	
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
			productRepresentations.add(productRepresentation);//now add this representation in the list
			linkMetaData lmt = new linkMetaData("rel", "/link/text/here" + p.getId().toString(), "metadatastuff");
			productRepresentation.addMetaDataToLink(lmt);
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
	
}
