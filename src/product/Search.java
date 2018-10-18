package product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import dao.ProductDAO;

//This class cannot be declared as an object because all it does is pass through search terms and passes back results.
public class Search {
	
	private Search() {}
	
	public static ArrayList<Product> performSearch(String searchTerm) {
		ArrayList<Product> resultProducts = new ArrayList<>();
		
		ProductDAO productDAO = new ProductDAO();
		
		
		resultProducts = productDAO.performProductSearch(searchTerm);
		
		return resultProducts;
	}

}
