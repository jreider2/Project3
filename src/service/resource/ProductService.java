/**
 * 
 */
package service.resource;

import java.util.ArrayList;
import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import service.represntation.ProductRepresentation;
import service.represntation.ProductRequest;

@WebService
public interface ProductService {
	
	public Set<ProductRepresentation> getProducts();
	public ProductRepresentation getProduct(String productId);
	public ArrayList<ProductRepresentation> searchProducts(String searchTerm);
	public ProductRepresentation createProduct(ProductRequest productRequest);
	public Response deleteProduct(String id);

}
