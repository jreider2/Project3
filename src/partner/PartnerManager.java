/**
 * 
 */
package partner;

import java.util.Set;

import dao.OrderDAO;
import dao.PartnerDAO;
import dao.ProductDAO;
import order.Order;
import order.OrderedItem;
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
		Product p = new Product();
		for (OrderedItem oI: o.getProducts()) {
			p = new Product();
			p = productDao.getProduct(oI.getProductID());
			p.getProductOwner().recieveOrder(o);
			p.getProductOwner().recievePayment();
		}
		//debugging use only
		System.out.println("Order " + o.getId() + " pushed to partner " + p.getProductOwner().getId());
		
		//Update DB once partners receive order
		oDAO.updateOrderStatus(o.getId(), "PushedProductToPartner");
		
		return true;
	}

	
	
	
}
