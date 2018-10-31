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
         
         System.out.println("GET METHOD ......................................Get partner with id 17");
         WebClient getClient = WebClient.create("http://localhost:8081", providers);
         
         //Configuring the CXF logging intercepter for the outgoing message
         WebClient.getConfig(getClient).getOutInterceptors().add(new LoggingOutInterceptor());
         //Configuring the CXF logging intercepter for the incoming response
         WebClient.getConfig(getClient).getInInterceptors().add(new LoggingInInterceptor());
         
         // set Accept and ContentType headers 
         // set path with Partner ID = 17
         getClient = getClient.accept("application/json").type("application/json").path("/partnerservice/partners/17");
         
         //The following lines are to show how to log messages without the CXF interceptors
         String getRequestURI = getClient.getCurrentURI().toString();
         System.out.println("Client GET METHOD Request URI:  " + getRequestURI);
         String getRequestHeaders = getClient.getHeaders().toString();
         System.out.println("Client GET METHOD Request Headers:  " + getRequestHeaders);
         
         //to see as raw XML/json
         String response = getClient.get(String.class);
         System.out.println("GET METHOD Response: ...." + response);
         
        //to get the response as object of Employee class
        //Employee employee = client.get(Employee.class);
        //System.out.println("Name:" + employee.getFirstName());
        //System.out.println("privileges:" + employee.getPrivileges());
        
      
         /*****************************************************************************************
          * POST METHOD Register a new Partner
         *****************************************************************************************/
         
         System.out.println("POST METHOD ........................................Register new Partner");
         WebClient postClient = WebClient.create("http://localhost:8081", providers);
         WebClient.getConfig(postClient).getOutInterceptors().add(new LoggingOutInterceptor());
         WebClient.getConfig(postClient).getInInterceptors().add(new LoggingInInterceptor());
                  
         // set Accept and ContentType headers 
         postClient = postClient.accept("application/json").type("application/json").path("/partnerservice/partners");
      	
         String postRequestURI = postClient.getCurrentURI().toString();
         System.out.println("Client POST METHOD Request URI:  " + postRequestURI);
         String postRequestHeaders = postClient.getHeaders().toString();
         System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders);
          
         PartnerRequest partnerReq = new PartnerRequest();
         partnerReq.setId("0");
         partnerReq.setCompanyName("Sony");
         partnerReq.setUserName("sonyUsername");
         partnerReq.setPassword("sonysPassword");
         
      	String responsePost =  postClient.post(partnerReq, String.class);
        System.out.println("POST MEDTHOD Response ........." + responsePost);
        
        /*****************************************************************************************
         * PUT METHOD Add new Product to Partner (add to marketPlace) 
        *****************************************************************************************/
        
        System.out.println("PUT METHOD ...................................Partner adds new Product to MarketPlace");
        WebClient putClient = WebClient.create("http://localhost:8081", providers);
        WebClient.getConfig(putClient).getOutInterceptors().add(new LoggingOutInterceptor());
        WebClient.getConfig(putClient).getInInterceptors().add(new LoggingInInterceptor());
                 
        // set Accept and ContentType headers 
        // set path with Partner ID = 13 
        putClient = putClient.accept("application/json").type("application/json").path("partnerservice/partners/13/newProduct");
     	
        String putRequestURI = putClient.getCurrentURI().toString();
        System.out.println("Client POST METHOD Request URI:  " + putRequestURI);
        String putRequestHeaders = putClient.getHeaders().toString();
        System.out.println("Client POST METHOD Request Headers:  " + putRequestHeaders);
        
        ProductRequest productReq = new ProductRequest();
        productReq.setId("0");
        productReq.setName("swim suit");
        productReq.setDescription("mens racing suit");
        productReq.setPrice(50.00);
        productReq.setProductOwnerID("13");
        
     	String responsePut =  putClient.put(productReq, String.class); 
     	System.out.println("POST MEDTHOD Response ........." + responsePut);
     	
     	/*****************************************************************************************
         * GET METHOD : Get all items in database that match a search term (goggles)
         *****************************************************************************************/
     	
        System.out.println("GET METHOD .........................................................");
        WebClient getSearchClient = WebClient.create("http://localhost:8081", providers);
        
        //Configuring the CXF logging intercepter for the outgoing message
        WebClient.getConfig(getSearchClient).getOutInterceptors().add(new LoggingOutInterceptor());
        //Configuring the CXF logging intercepter for the incoming response
        WebClient.getConfig(getSearchClient).getInInterceptors().add(new LoggingInInterceptor());
        
        // set Accept and ContentType headers 
        // set path with search term = goggles
        getSearchClient = getSearchClient.accept("application/json").type("application/json").path("/productservice/products/searchresults/goggles");
        
        //The following lines are to show how to log messages without the CXF interceptors
        String getSearchRequestURI = getSearchClient.getCurrentURI().toString();
        System.out.println("Client GET METHOD Request URI:  " + getSearchRequestURI);
        String getSearchRequestHeaders = getSearchClient.getHeaders().toString();
        System.out.println("Client GET METHOD Request Headers:  " + getSearchRequestHeaders);
        
        //to see as raw XML/json
        String searchResponse = getSearchClient.get(String.class);
  
        System.out.println("Results for product search of'googles'.");
        System.out.println("GET METHOD Response: ...." + searchResponse);
        
        /*****************************************************************************************
         * PUT METHOD   Push ORDER to Partner
        *****************************************************************************************/
        
       /* System.out.println("PUT METHOD .........................................................");
        WebClient pushOrderPutClient = WebClient.create("http://localhost:8081", providers);
        WebClient.getConfig(pushOrderPutClient).getOutInterceptors().add(new LoggingOutInterceptor());
        WebClient.getConfig(pushOrderPutClient).getInInterceptors().add(new LoggingInInterceptor());
                 
        // set Accept and ContentType headers
        pushOrderPutClient = pushOrderPutClient.accept("application/xml").type("application/xml").path("partnerservice/partners/orderNotification");
     	
        String pushOrderPutRequestURI = pushOrderPutClient.getCurrentURI().toString();
        System.out.println("Client POST METHOD Request URI:  " + pushOrderPutRequestURI);
        String pushOrderPutRequestHeaders = pushOrderPutClient.getHeaders().toString();
        System.out.println("Client POST METHOD Request Headers:  " + pushOrderPutRequestHeaders);
        
        OrderedItem productReqForOrderReq = new OrderedItem();
        productReqForOrderReq.setProductID("13");
        productReqForOrderReq.setProductPrice("50.00");
        productReqForOrderReq.setQtyOnOrder("15");
        
        ArrayList<OrderedItem> items = new ArrayList<>();
        items.add(productReqForOrderReq);
        //items.add(productReqForOrderReq);
        
        OrderRequest orderReq = new OrderRequest();
        orderReq.setCcNo("320121325155");
        orderReq.setCustomerId("4");
        orderReq.setItems(items);
        
     	String responsePushOrderPut =  pushOrderPutClient.put(orderReq, String.class); 
     	System.out.println("POST MEDTHOD Response ........." + responsePushOrderPut);
     	*/
     	/*****************************************************************************************
         * GET METHOD : Get Acknowledgement of fulfillment  
         *****************************************************************************************/
     	
     	 /*System.out.println("GET METHOD ......................................Get Acknowledgement of Order fulfillment Order ID 5 ");
         WebClient getAcknowledgmentClient = WebClient.create("http://localhost:8081", providers);
         
         //Configuring the CXF logging intercepter for the outgoing message
         WebClient.getConfig(getAcknowledgmentClient).getOutInterceptors().add(new LoggingOutInterceptor());
         //Configuring the CXF logging intercepter for the incoming response
         WebClient.getConfig(getAcknowledgmentClient).getInInterceptors().add(new LoggingInInterceptor());
         
         // set Accept and ContentType headers 
         // set path with Order ID = 5
         getAcknowledgmentClient = getAcknowledgmentClient.accept("application/json").type("application/json").path("/order/orderService/order/fulfillmentAcknowledgement/5");
         
         //log messages without the CXF interceptors
         String getAcknowledgementRequestURI = getAcknowledgmentClient.getCurrentURI().toString();
         System.out.println("Client GET METHOD Request URI:  " + getAcknowledgementRequestURI);
         String getAcknowledgementRequestHeaders = getAcknowledgmentClient.getHeaders().toString();
         System.out.println("Client GET METHOD Request Headers:  " + getAcknowledgementRequestHeaders);
         
         //to see as raw XML/json
         String acknowledgementResponse = getAcknowledgmentClient.get(String.class);
         System.out.println("GET METHOD Response: ...." + acknowledgementResponse + "OK");*/
        
     	
     	/*****************************************************************************************
         * POST METHOD : create new order // accept buy order
         *****************************************************************************************/
        /* System.out.println("POST METHOD ........................................Create new order");
         postClient = WebClient.create("http://localhost:8081", providers);
         WebClient.getConfig(postClient).getOutInterceptors().add(new LoggingOutInterceptor());
         WebClient.getConfig(postClient).getInInterceptors().add(new LoggingInInterceptor());
                  
         // set Accept and ContentType headers 
         postClient = postClient.accept("application/json").type("application/json").path("/order/orderService/");
      	
         postRequestURI = postClient.getCurrentURI().toString();
         System.out.println("Client POST METHOD Request URI:  " + postRequestURI);
         postRequestHeaders = postClient.getHeaders().toString();
         System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders);
         */
         //TODO create an order request complete with products in it 
         
         //TOD call the post method on the postClient with the orderRequest as an argument
     	
     	/*****************************************************************************************
         * POST METHOD : Accept credit card payment
         *****************************************************************************************/
        /* getAcknowledgmentClient = getAcknowledgmentClient.accept("application/json").type("application/json").path("/order/orderService/order/neworder");
         acknowledgementResponse = getAcknowledgmentClient.get(String.class);
         System.out.println("POST (Place Order) Method response: .... " + acknowledgementResponse);*/
        WebClient orderClient = WebClient.create("http://localhost:8081", providers);
         orderClient = orderClient.accept("application/json").type("application/json").path("/order/orderService/order/neworder");
        
         System.out.println("Place Order here.......................");
         OrderRequest ordReq = new OrderRequest();
         ArrayList<OrderedItem> oIList = new ArrayList<>();
         OrderedItem oI = new OrderedItem();
         oI.setProductID("10");
         oI.setProductPrice("2.00");
         oI.setQtyOnOrder("12");
         oIList.add(oI);
         oI.setProductID("11");
         oI.setProductPrice("5.00");
         oI.setQtyOnOrder("7");
         oIList.add(oI);
         ordReq.setCcNo("421365816651");
         ordReq.setCustomerId("2");
         ordReq.setItems(oIList);
         
      	OrderRepresentation ordResponsePost =  orderClient.post(ordReq, OrderRepresentation.class);
      	System.out.println("POST METHOD Response ........." + ordResponsePost.getOrderNo() + " is placed!");
     	/*****************************************************************************************
         * PUT METHOD : Ship Orders
         *****************************************************************************************/
     	WebClient shipClient = WebClient.create("http://localhost:8081", providers);
     	shipClient = shipClient.accept("application/json").type("application/json").path("/order/orderService/shippedOrder");
     	
     	System.out.println("Partner now says that they shipped the order");
     	String isShipped = shipClient.put(ordResponsePost.getOrderNo(), String.class);
     	
     	System.out.println("Order " + ordResponsePost.getOrderNo() + " is shipped true or false: " + isShipped);
     	
     	/*****************************************************************************************
         * GET METHOD : Provide Order Status
         *****************************************************************************************/
     	WebClient statusClient = WebClient.create("http://localhost:8081", providers);
     	statusClient = statusClient.accept("application/json").type("application/json").path("/order/orderService/status/" + ordResponsePost.getOrderNo());
     	
     	OrderRepresentation ord = statusClient.get(OrderRepresentation.class);
     	System.out.println("Order " + ordResponsePost.getOrderNo() + " status get test: " + ord.getOrderStatus());
     	
     	
     	/*****************************************************************************************
         * PUT METHOD : Fulfill Order
         *****************************************************************************************/
     	WebClient fulfillClient = WebClient.create("http://localhost:8081", providers);
     	fulfillClient = fulfillClient.accept("application/json").type("application/json").path("order/orderService/fulfilledOrder");
     	System.out.println("Partner now says that the order has been delivered (fulfilled)");
     	String isFulfilled = fulfillClient.put(ordResponsePost.getOrderNo(), String.class);
     	System.out.println("Order " + ordResponsePost.getOrderNo() + " is fulfilled true or false: " + isFulfilled);
        
     	
     	WebClient checkFulfillClient = WebClient.create("http://localhost:8081", providers);
     	checkFulfillClient = checkFulfillClient.accept("application/json").type("application/json").path("order/orderService/order/fulfillmentAcknowledgement/" + ordResponsePost.getOrderNo());
     			
     	System.out.println("Get Acknowledgement notification:");
     	ordResponsePost = checkFulfillClient.get(OrderRepresentation.class);
     	System.out.println("Order " + ordResponsePost.getOrderNo() + " status get test: " + ord.getOrderStatus());
     	
     	/*getAcknowledgmentClient = getAcknowledgmentClient.accept("application/json").type("application/json").path("/order/orderService/order/status?orderID=10");
        acknowledgementResponse = getAcknowledgmentClient.get(String.class);
        System.out.println("GET (Order Status) Method response: .... " + acknowledgementResponse);*/
     	/*****************************************************************************************
         * DELETE METHOD : Cancel Order
         *****************************************************************************************/
     	
     	WebClient cancelClient = WebClient.create("http://localhost:8081", providers);
     	cancelClient = cancelClient.accept("application/json").type("application/json").path("/order/orderService/order/cancelledorder/" + ordResponsePost.getOrderNo());
     	
     	cancelClient.delete();
     	ord = statusClient.get(OrderRepresentation.class);
     	System.out.println("Order " + ord.getOrderNo() + " status: " + ord.getOrderNo());
     	
        /*getAcknowledgmentClient = getAcknowledgmentClient.accept("application/json").type("application/json").path("/order/orderService/order/cancelledorder?orderID=10");
        acknowledgementResponse = getAcknowledgmentClient.get(String.class);
        System.out.println("DELETE (Cancel Order) Method response: .... " + acknowledgementResponse);*/
     	/*****************************************************************************************
         * ERROR Handling
         *****************************************************************************************/
         // business errors 
         		//such as item not available
         
     	
     
     	/*****************************************************************************************
         * Remaining requirements on Project 3
         *****************************************************************************************/
         
         // 1) Detailed documentation of your architecture and implementation. 
         // 2) Email source code and configuration files OR point me to the repository you of your solution.
         
         
         //Project Due Date:
         //- EOD Oct 28th, 2018 
     	
     	

	}

}
