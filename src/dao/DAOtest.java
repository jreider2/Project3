package dao;

import java.util.ArrayList;
import java.util.Set;

import customer.Address;
import customer.CreditCard;
import customer.CreditCardManager;
import customer.Customer;
import order.Order;
import order.OrderManager;
import partner.Partner;
import product.Product;
import product.ProductManager;

public class DAOtest {

	public DAOtest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		CustomerDAO cus = new CustomerDAO();
		
		
		Set<Customer> customers = cus.getAllCustomers();
		
		for (Customer c : customers) {
			System.out.println(c.getFirstName());
		}
		
		AddressDAO adDao = new AddressDAO();
        String a = adDao.addAddress("5", "Melrose", "Boystown", "Ohio", "32147");
		System.out.println(a);
		
		PartnerDAO partDAO = new PartnerDAO();
		Partner part = partDAO.addPartner("Speedo", "speedoUser", "passw", Integer.parseInt(a));
		
		System.out.println("Partner ID = " + part.getId());
		
		CreditCardDAO ccdao = new CreditCardDAO();
		CreditCard cctest = ccdao.getCreditCardInfo("632596587452");
		System.out.println(cctest.toString());
		
		CreditCardManager ccm = new CreditCardManager();
		CreditCard ccAddMe = new CreditCard();
		ccAddMe.setCreditCardNumber("654998746543");
		ccAddMe.setExpDate("5/55");
		ccAddMe.setcardHolderName("Test User1");
		ccAddMe.setSecurityCode("789");
		ccAddMe.setCustomerID("3");
		boolean b = ccm.Save(ccAddMe.getCreditCardNumber(), ccAddMe.getExpDate(), ccAddMe.getCardHolderName(), ccAddMe.getSecurityCode(), ccAddMe.getCustomerID());
		System.out.println(b);
		boolean b1 = ccm.Delete(ccAddMe.getId());
		System.out.println(b1);
		
		Order o = new Order();
		Product p = new Product();
		ProductManager pm = new ProductManager();
		p = pm.getProduct("6");
		o.addProduct(p);
		OrderManager om = new OrderManager();
		ArrayList<String> ap = new ArrayList<>();
		ap.add(p.getId());
		//om.addOrder("1", ap, "1111222233334444");
		
		/** Note to partner: This class is just for testing. Main functionalities are DOMOed in mainClient */
		
	}

}
