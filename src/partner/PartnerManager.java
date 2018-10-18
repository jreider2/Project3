/**
 * 
 */
package partner;

import java.util.Set;

import dao.OrderDAO;
import dao.PartnerDAO;
import dao.ProductDAO;
import order.Order;
import product.Product;

/**
 * @author julianareider
 *
 */
public class PartnerManager {
	
	private static PartnerDAO dao;
	private static ProductDAO productDao;
	private static OrderDAO oDAO;
	
	/**
	 * 
	 */
	public PartnerManager() {
		dao = new PartnerDAO();
		productDao = new ProductDAO();
		oDAO = new OrderDAO();
	}

	public Partner registerNewPartner(String companyName, String userName, String password, int addressID) {		
		return addPartner(companyName, userName, password, addressID);
	}
	
	public boolean loginPartner(String companyName, String id) {
		//TODO Login partner// or find error in information given 
		return isValid(companyName, ""); // use this method
	}
	
	private boolean isValid(String companyName, String id) {
		//TODO validate user
		return false;
	}
	
	
	public Set<Partner> getAllPartners(){
		return dao.getAllPartners();
	}
	
	public Partner getPartner(String id) {
		return dao.getPartner(id);
	}
	

	public Partner addPartner(String companyName, String userName, String password, int addressID) {
		return dao.addPartner(companyName, userName, password, addressID);

	}

	public void updatePartner(String id, Product p) {
		dao.updatePartner(id, p);
	}

	public void deletePartner(String id) {
		dao.deletePartner(id);
	}
	
	public Partner addProductToPartner(String productName, String productDescription, int productPrice, String partnerId) {
		Partner partner = dao.getPartner(partnerId); //get partner from DB
		Product product = productDao.addProduct(productName, productDescription, productPrice, partnerId);
		partner.addProduct(product); // add the product
		return partner; //return updated partner
	}
	
	public boolean pushOrderToPartner(Order o) {
		
		for (Product p: o.getItems()) {
			p.getProductOwner().recieveOrder(o);
			p.getProductOwner().recievePayment();
		}
		
		//Update DB once partners receive order
		oDAO.updateOrderStatus(o.getId(), "PushedProductToPartner");
		
		return true;
	}

	
	
	
}
