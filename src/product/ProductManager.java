/**
 * 
 */
package product;

import java.util.ArrayList;
import java.util.Set;

import dao.ProductDAO;
import order.OrderedItem;
import partner.Partner;

/**
 * @author julianareider
 *
 */
public class ProductManager {
	
	private static ProductDAO dao = new ProductDAO();
	
	/**
	 * 
	 */
	public ProductManager() {
	}

	/**
	 * View all products
	 */
	public Set<Product> getAllProducts(){
		return dao.getAllProducts();
	}
	
	/**
	 * Search for product
	 */
	public Product getProduct(String id) {
		return dao.getProduct(id);
	}
	
	public ArrayList<Product> getProductList(ArrayList<OrderedItem> orderedProducts){
		return dao.getProductList(orderedProducts);
	}
	
	/**
	 * Returns null if no products match the search 
	 */
	public ArrayList<Product> searchForProduct(String search) {
		return dao.performProductSearch(search);
	}
	
	public Product addProduct(String name, String description, int price, String productOwnerPartnerID) {
		
		Product product = dao.addProduct(name, description, price, productOwnerPartnerID);
		
		return product;
	}

	public void updateProduct(int id, int price) {
		dao.updateProduct(id, price);
	}

	public void deleteProduct(String id) {
		//TODO Fix me in the DAO layer. 
		dao.deleteProduct(id);
	}

}
