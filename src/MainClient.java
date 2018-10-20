import java.util.ArrayList;
import java.util.Set;

import customer.Address;
import customer.Customer;
import customer.CustomerManager;
import dao.AddressDAO;
import dao.CustomerDAO;
import dao.PartnerDAO;
import order.Order;
import order.OrderManager;
import partner.Partner;
import partner.PartnerManager;
import product.Product;
import product.ProductManager;
import java.util.ArrayList;
/**
 * 
 */

/**
 * @author julianareider
 *
 */
public class MainClient {

	/**
	 * 
	 */
	public MainClient() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// PROJECT TODOS
		// 1) Complete minimum functionality (see below) 
		// 2) Update and include UML and Database Model
		// 2) Quick Readme if time
		// 3) Deploy on AWS
		
		//*****************ADD NEW ADDRESS FUNCTIONALITY*************************************************
		AddressDAO adDao = new AddressDAO();
		String newAddressID = adDao.addAddress("5", "Melrose", "Boystown", "Ohio", "20987");
		
		//*****************REGISTER NEW PARTNER FUNCTIONALITY*********************************************
		PartnerManager partManager = new PartnerManager();
		Partner partner1 = partManager.registerNewPartner("Books a Million", "BAM username","BAM password", Integer.parseInt(newAddressID));
		System.out.println("Partner added to Database = " + partner1.toString());
		System.out.println(partner1.getCompanyName() + "'s ID = " + partner1.getId());
		
		//*****************Get PARTNER by ID FUNCTIONALITY*********************************************
		Partner partner1RetreivedFromDB = partManager.getPartner(partner1.getId());
		System.out.println("Partner retreieved from DB = " + partner1RetreivedFromDB.toString() );
		
		//*****************ADD Products to marketPlace FUNCTIONALITY****************************************
		ProductManager productManager = new ProductManager();
		Product googles = productManager.addProduct("goggles", "swim goggles", 5, partner1.getId());
		System.out.println(googles.getDescription() + " were added to the MarketPlace and the DB.");
		
		//*****************ADD Products to PARTNER FUNCTIONALITY*******************************************
		partner1 = partManager.addProductToPartner("candle", "white candle", 2, partner1.getId());
		System.out.println("Product white candle Added to BAM products: " + partner1.getProducts());
		
		//*****************Search item database by product FUNCTIONALITY*********************************************
		ProductManager prodSearch = new ProductManager();
		ArrayList<Product> searchResults = prodSearch.searchForProduct("candle");
		System.out.println("Search results for 'candle'");
		for (Product p : searchResults) {
			System.out.println(p.getName());
		}
		
		//*****************REGISTER NEW CUSTOMER FUNCTIONALITY*********************************************
		CustomerManager cm = new CustomerManager();
		boolean custRegistered = cm.registerNewCustomer("Randy", "Johnson", "4562589499", "RJRJ", "randyspass", "RJRJ@gmail.com", "321 Test Loop", "Apt 432", "Denver", "45698", "Colorado");
		System.out.println(custRegistered);
		//*****************Create order FUNCTIONALITY*********************************************
		OrderManager orderManager = new OrderManager();
		ArrayList<String> myProductIDs = new ArrayList<>();
		myProductIDs.add(googles.getId());
		String tempCustomerID = "3";
		Order myGoggleOrder = orderManager.addOrder(tempCustomerID, myProductIDs, "5555888822229999");
		System.out.println("OrderID of the newly created Order: " + myGoggleOrder.getId());
		System.out.println("Credit Card on order: " + myGoggleOrder.getCreditCardNo());
		System.out.println("Customer Order successfully put in an order for: ");
		
		for (Product p : myGoggleOrder.getItems()) {
			System.out.println(p);
		}
		
		//*****************Request Product /// Accept Credit Card payment //create order FUNCTIONALITY*********************************************
			// request to buy product > credit card payment > create Order (Order status is "ordered" (not yet shipped, partner must explicitly ship it)
		Order o2 = orderManager.addOrder("3", myProductIDs, "2100215484845847");
		System.out.println("Andrews Order: " + o2.getId());
		
		
		//*****************Push Order to PARTNER FUNCTIONALITY*********************************************
				//push Order to partner > (Status of Order changes to "pushed to Partner")  
					//Partner receives Order (receiveOrder)
							//		partManager.pushOrderToPartner(order1);
		PartnerManager pm = new PartnerManager();
		pm.pushOrderToPartner(o2);
		
		
		
		//*****************Provide ORDER status; Provide status of orders in progress FUNCTIONALITY*********************************************
		String orderID = myGoggleOrder.getId();
		String status = orderManager.getOrderStatus(orderID);
		System.out.println("This is the status of your order: " + status);

		
		//*****************Ship ORDERs FUNCTIONALITY*********************************************
			//Partner can ship existing order
			// Order status is currently "ordered" > orderManager ships order (order status changes to "shipped") 
		status = orderManager.shipOrder(orderID);
		//if (!status.equals("shipped")) {
		//	System.out.println("somthing is wrong with shipping functionality");
		//}
		System.out.println("Now This is the status of your order: " + status);
		
		//*****************Partner can ship ORDERs FUNCTIONALITY*********************************************
		
		
		
		//*****************Get Acknowledgment of Order fulfillment by partner FUNCTIONALITY*********************************************
		//with the getAckwlegment function, if order status is shipped/fulfilled, then boolean yes it is fulfilled
		
		//partner TODO
		//+ AccknowledgeShippment
		System.out.println("This is your order status: " + orderManager.getOrderStatus(o2.getId()));
		
		
		//*****************ORDER cancel FUNCTIONALITY*********************************************
			//Partner/User can cancel existing order
			// Order status is currently "ordered" ( or even "shipped"? ) > Someone cancels order (order status changes to "canceled") 

		status = orderManager.cancelOrder(orderID);
		//if (!status.equals("canceled")) {
		//	System.out.println("somthing is wrong with shipping functionality");
		//}
		System.out.println("Now This is the status of your order: " + status);
		
		
		
		

	}

}
