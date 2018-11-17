package service.resource;

import java.util.ArrayList;
import java.util.Set;

import service.represntation.ProductRepresentation;
import service.represntation.ProductRequest;
import service.workflow.ProductActivity;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import javax.ws.rs.core.CacheControl;

@CrossOriginResourceSharing(allowAllOrigins = true)

@Path("/")
public class ProductResource implements ProductService {

	public ProductResource() {	}
 
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/products")
	public Set<ProductRepresentation> getProducts() {
		System.out.println("GET METHOD Request for all products .............");
		ProductActivity pActivity = new ProductActivity();
		return pActivity.getProducts();	
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/products/{productId}")
	public ProductRepresentation getProduct(@PathParam("productId") String id ) { 
		System.out.println("GET METHOD Request from Client with productRequest String ............." + id);
		ProductActivity pActivity = new ProductActivity();
		return pActivity.getProduct(id);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/products/searchresults/{searchterm}")
	public ArrayList<ProductRepresentation> searchProducts(@PathParam("searchterm") String searchTerm) {
		System.out.println("GET METHOD Request for all products .............");
		ProductActivity pActivity = new ProductActivity();
		return pActivity.searchProducts(searchTerm);	
	} 
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/products")
	public ProductRepresentation createProduct(ProductRequest  productRequest) {
		System.out.println("POST METHOD Request from Client with ............." + productRequest.getName() + "  " + productRequest.getDescription());
		ProductActivity pActivity = new ProductActivity();
		return pActivity.createProduct(productRequest.getName(), productRequest.getDescription(), productRequest.getPrice(), productRequest.getProductOwnerID() );
	}
	
//	TODO finish method 
  @DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/products/{productId}")
	public Response deleteProduct(@PathParam("productId") String id) {
		System.out.println("Delete METHOD Request from Client with productRequest String ............." + id);
		ProductActivity pActivity = new ProductActivity();
		String res = pActivity.deleteProduct(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
}
