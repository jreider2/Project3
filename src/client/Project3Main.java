package client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import order.OrderedItem;
import service.represntation.OrderRequest;
import service.represntation.PartnerRequest;
import service.represntation.ProductRequest;
import service.represntation.CustomerRequest;
import service.represntation.OrderRepresentation;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

public final class Project3Main {

	public static void main(String[] args) throws Exception {
		
		
		 //Providers
		 List<Object> providers = new ArrayList<Object>();
         JacksonJsonProvider provider = new JacksonJsonProvider();
         provider.addUntouchable(Response.class);
         providers.add(provider);
         
         /*****************************************************************************************
          * GET METHOD : Get an existing Partner
          *****************************************************************************************/
//         
//         System.out.println("GET METHOD ......................................Get partner with id 17");
//         WebClient getClient = WebClient.create("http://localhost:8081", providers);
//         
//         //Configuring the CXF logging intercepter for the outgoing message
//         WebClient.getConfig(getClient).getOutInterceptors().add(new LoggingOutInterceptor());
//         //Configuring the CXF logging intercepter for the incoming response
//         WebClient.getConfig(getClient).getInInterceptors().add(new LoggingInInterceptor());
//         
//         // set Accept and ContentType headers 
//         // set path with Partner ID = 17
//         getClient = getClient.accept("application/json").type("application/json").path("/partnerservice/partners/17");
//         
//         //The following lines are to show how to log messages without the CXF interceptors
//         String getRequestURI = getClient.getCurrentURI().toString();
//         System.out.println("Client GET METHOD Request URI:  " + getRequestURI);
//         String getRequestHeaders = getClient.getHeaders().toString();
//         System.out.println("Client GET METHOD Request Headers:  " + getRequestHeaders);
//         
//         //to see as raw XML/json
//         String response = getClient.get(String.class);
//         System.out.println("GET METHOD Response: ...." + response);
//      
//         /*****************************************************************************************
//          * POST METHOD Register a new Partner
//         *****************************************************************************************/
//         
//         System.out.println("POST METHOD ........................................Register new Partner");
//         WebClient postClient = WebClient.create("http://localhost:8081", providers);
//         WebClient.getConfig(postClient).getOutInterceptors().add(new LoggingOutInterceptor());
//         WebClient.getConfig(postClient).getInInterceptors().add(new LoggingInInterceptor());
//                  
//         // set Accept and ContentType headers 
//         postClient = postClient.accept("application/json").type("application/json").path("/partnerservice/partners");
//      	
//         String postRequestURI = postClient.getCurrentURI().toString();
//         System.out.println("Client POST METHOD Request URI:  " + postRequestURI);
//         String postRequestHeaders = postClient.getHeaders().toString();
//         System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders);
//          
//         PartnerRequest partnerReq = new PartnerRequest();
//         partnerReq.setId("0");
//         partnerReq.setCompanyName("Sony");
//         partnerReq.setUserName("sonyUsername");
//         partnerReq.setPassword("sonysPassword");
//         
//      	String responsePost =  postClient.post(partnerReq, String.class);
//        System.out.println("POST MEDTHOD Response ........." + responsePost);
//        
//        /*****************************************************************************************
//         * PUT METHOD Add new Product to Partner (add to marketPlace) 
//        *****************************************************************************************/
//        
//        System.out.println("PUT METHOD ...................................Partner adds new Product to MarketPlace");
//        WebClient putClient = WebClient.create("http://localhost:8081", providers);
//        WebClient.getConfig(putClient).getOutInterceptors().add(new LoggingOutInterceptor());
//        WebClient.getConfig(putClient).getInInterceptors().add(new LoggingInInterceptor());
//                 
//        // set Accept and ContentType headers 
//        // set path with Partner ID = 13 
//        putClient = putClient.accept("application/json").type("application/json").path("partnerservice/partners/13/newProduct");
//     	
//        String putRequestURI = putClient.getCurrentURI().toString();
//        System.out.println("Client POST METHOD Request URI:  " + putRequestURI);
//        String putRequestHeaders = putClient.getHeaders().toString();
//        System.out.println("Client POST METHOD Request Headers:  " + putRequestHeaders);
//        
//        ProductRequest productReq = new ProductRequest();
//        productReq.setId("0");
//        productReq.setName("swim suit");
//        productReq.setDescription("mens racing suit");
//        productReq.setPrice(50.00);
//        productReq.setProductOwnerID("13");
//        
//     	String responsePut =  putClient.put(productReq, String.class); 
//     	System.out.println("POST MEDTHOD Response ........." + responsePut);
//     	
//     	/*****************************************************************************************
//         * GET METHOD : Get all items in database that match a search term (goggles)
//         *****************************************************************************************/
//     	
//        System.out.println("GET METHOD .........................................................");
//        WebClient getSearchClient = WebClient.create("http://localhost:8081", providers);
//        
//        //Configuring the CXF logging intercepter for the outgoing message
//        WebClient.getConfig(getSearchClient).getOutInterceptors().add(new LoggingOutInterceptor());
//        //Configuring the CXF logging intercepter for the incoming response
//        WebClient.getConfig(getSearchClient).getInInterceptors().add(new LoggingInInterceptor());
//        
//        // set Accept and ContentType headers 
//        // set path with search term = goggles
//        getSearchClient = getSearchClient.accept("application/json").type("application/json").path("/productservice/products/searchresults/goggles");
//        
//        //The following lines are to show how to log messages without the CXF interceptors
//        String getSearchRequestURI = getSearchClient.getCurrentURI().toString();
//        System.out.println("Client GET METHOD Request URI:  " + getSearchRequestURI);
//        String getSearchRequestHeaders = getSearchClient.getHeaders().toString();
//        System.out.println("Client GET METHOD Request Headers:  " + getSearchRequestHeaders);
//        
//        //to see as raw XML/json
//        String searchResponse = getSearchClient.get(String.class);
//  
//        System.out.println("Results for product search of'googles'.");
//        System.out.println("GET METHOD Response: ...." + searchResponse);
//        
//     	
//     	/*****************************************************************************************
//         * POST METHOD : Accept credit card payment
//         *****************************************************************************************/
//        /* getAcknowledgmentClient = getAcknowledgmentClient.accept("application/json").type("application/json").path("/order/orderService/order/neworder");
//         acknowledgementResponse = getAcknowledgmentClient.get(String.class);
//         System.out.println("POST (Place Order) Method response: .... " + acknowledgementResponse);*/
//         WebClient orderClient = WebClient.create("http://localhost:8081", providers);
//         orderClient = orderClient.accept("application/json").type("application/json").path("/order/orderService/order/neworder");
//        
//         System.out.println("Place Order here.......................");
//         OrderRequest ordReq = new OrderRequest();
//         ArrayList<ProductRequest> oIList = new ArrayList<>();
//         ProductRequest oI = new ProductRequest();
//         oI.setId("10");
//         oI.setPrice(2.00);
//         oI.setQuantityOnOrder("12");
//         oIList.add(oI);
////         oI.setProductID("11");
////         oI.setProductPrice("5.00");
////         oI.setQtyOnOrder("7");
////         oIList.add(oI);
//         ordReq.setCcNo("421365816651");
//         ordReq.setCustomerId("2");
//         ordReq.setItems(oIList);
//         
//         /*****************************************************************************************
//          * PUT METHOD   Push ORDER to Partner in order manager
//         *****************************************************************************************/
//         System.out.println("___________________________________________");
//         System.out.println("");
//         String reqURI = orderClient.getCurrentURI().toString();
//         System.out.println("Client GET METHOD Request URI:  " + reqURI);
//         String reqHeaders = orderClient.getHeaders().toString();
//         System.out.println("Client GET METHOD Request Headers:  " + reqHeaders);
//         System.out.println("___________________________________________");
//         System.out.println("");
//         
//         //OrderRepresentation ordResponsePost;
//      	 String ordResponsePost =  orderClient.post(ordReq, String.class);
//      	 
//      	
//        
//         String tempOrderID = "10"; 
//         //tempOrderID = ordResponsePost.getOrderNo()
//      
//         System.out.println("POST METHOD Response ........." + tempOrderID + " is placed!");
//         
//         System.out.println("___________________________________________");
//         System.out.println("");
//         
//     	 /*****************************************************************************************
//         * PUT METHOD : Ship Orders
//         *****************************************************************************************/
//     	 WebClient shipClient = WebClient.create("http://localhost:8081", providers);
//     	 shipClient = shipClient.accept("application/json").type("application/json").path("/order/orderService/shippedOrder");
//     	
//     	 System.out.println("Partner now says that they shipped the order");
//     	 String isShipped = shipClient.put(tempOrderID, String.class);
//     	
//     	 System.out.println("Order " + tempOrderID + " is shipped true or false: " + isShipped);
//     	
//     	 /*****************************************************************************************
//         * GET METHOD : Provide Order Status
//         *****************************************************************************************/
//     	 WebClient statusClient = WebClient.create("http://localhost:8081", providers);
//     	 statusClient = statusClient.accept("application/json").type("application/json").path("/order/orderService/status/" + tempOrderID);
//     	
//     	 String ord = statusClient.get(String.class);
//     	 //String ordStr = statusClient.get(String.class);
//     	 //System.out.println("Order " + tempOrderID + " status get test: " + ord.getOrderStatus());
//     	 System.out.println(ord);
//     	
//     	 /*****************************************************************************************
//         * PUT METHOD : Fulfill Order
//         *****************************************************************************************/
//     	 WebClient fulfillClient = WebClient.create("http://localhost:8081", providers);
//     	 fulfillClient = fulfillClient.accept("application/json").type("application/json").path("order/orderService/fulfilledOrder");
//     	 System.out.println("Partner now says that the order has been delivered (fulfilled)");
//     	 String isFulfilled = fulfillClient.put(tempOrderID, String.class);
//     	 System.out.println("Order " + tempOrderID + " is fulfilled true or false: " + isFulfilled);
//        
//     	
//     	 WebClient checkFulfillClient = WebClient.create("http://localhost:8081", providers);
//     	 checkFulfillClient = checkFulfillClient.accept("application/json").type("application/json").path("order/orderService/order/fulfillmentAcknowledgement/" + tempOrderID);
//     			
//     	/*****************************************************************************************
//         * GET METHOD : Get Acknowledgement of fulfillment  
//         *****************************************************************************************/
//     	System.out.println("Get Acknowledgement notification:");
//     	ordResponsePost = checkFulfillClient.get(String.class);
//     	System.out.println(ordResponsePost);
//     	//System.out.println("Order " + tempOrderID + " status get test: " + ord.getOrderStatus());
//     	
//     	/*getAcknowledgmentClient = getAcknowledgmentClient.accept("application/json").type("application/json").path("/order/orderService/order/status?orderID=10");
//        acknowledgementResponse = getAcknowledgmentClient.get(String.class);
//        System.out.println("GET (Order Status) Method response: .... " + acknowledgementResponse);*/
//     	/*****************************************************************************************
//         * DELETE METHOD : Cancel Order
//         *****************************************************************************************/
//     	
//     	WebClient cancelClient = WebClient.create("http://localhost:8081", providers);
//     	cancelClient = cancelClient.accept("application/json").type("application/json").path("/order/orderService/order/cancelledorder/" + tempOrderID);
//     	
//     	cancelClient.delete();
//     	ord = statusClient.get(String.class);
//     	System.out.println(ord);
//     	//System.out.println("Order " + tempOrderID + " status: " + tempOrderID);
//     	
     	/*****************************************************************************************
         * LOGIN CUSTOMER
         *****************************************************************************************/
     	
     	
     	WebClient loginClient = WebClient.create("http://localhost:8081", providers);
     	WebClient.getConfig(loginClient).getOutInterceptors().add(new LoggingOutInterceptor());
        //Configuring the CXF logging intercepter for the incoming response
        WebClient.getConfig(loginClient).getInInterceptors().add(new LoggingInInterceptor());
        loginClient = loginClient.accept("application/xml").type("application/xml").path("/customerservice/customerAuthentication");
       
        System.out.println("LOGIN.......................");
        CustomerRequest custRequest = new CustomerRequest();
        custRequest.setUserName("JReiter");
        custRequest.setPassword("yourpassword6");
        
        String responsePost =  loginClient.post(custRequest, String.class);

	}

}
