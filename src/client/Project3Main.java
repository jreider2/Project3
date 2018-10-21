package client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import service.represntation.OrderRequest;
import service.represntation.PartnerRequest;
import service.represntation.ProductRequest;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

public final class Project3Main {

	public static void main(String[] args) throws Exception {
		
		
		
		//TODO 
			//see unfinished functionalities at the bottom
		
		
		
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
         getClient = getClient.accept("application/json").type("application/json").path("/partner/partnerService/partner/17");
         
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
         postClient = postClient.accept("application/json").type("application/json").path("/partner/partnerService/partner");
      	
         String postRequestURI = postClient.getCurrentURI().toString();
         System.out.println("Client POST METHOD Request URI:  " + postRequestURI);
         String postRequestHeaders = postClient.getHeaders().toString();
         System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders);
         
         //TODO FIX ME -- not enough information to add the new partner to the DATABASE 
         PartnerRequest partnerReq = new PartnerRequest();
         partnerReq.setId("0");
         partnerReq.setCompanyName("Sony");
         partnerReq.setUserName("sonyUsername");
         partnerReq.setPassword("sonysPassword");
         
      	String responsePost =  postClient.post(partnerReq, String.class);
        System.out.println("POST MEDTHOD Response ........." + responsePost);
        
        /*****************************************************************************************
         * PUT METHOD Push Product to Partner (add to marketPlace) 
        *****************************************************************************************/
        
        System.out.println("PUT METHOD ...................................Partner adds new Product to MarketPlace");
        WebClient putClient = WebClient.create("http://localhost:8081", providers);
        WebClient.getConfig(putClient).getOutInterceptors().add(new LoggingOutInterceptor());
        WebClient.getConfig(putClient).getInInterceptors().add(new LoggingInInterceptor());
                 
        // set Accept and ContentType headers 
        // set path with Partner ID = 13 
        putClient = putClient.accept("application/json").type("application/json").path("partner/partnerService/partner/13/newProduct");
     	
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
        getSearchClient = getSearchClient.accept("application/json").type("application/json").path("/product/productservice/product/search/goggles");
        
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
        
        System.out.println("PUT METHOD .........................................................");
        WebClient pushOrderPutClient = WebClient.create("http://localhost:8081", providers);
        WebClient.getConfig(pushOrderPutClient).getOutInterceptors().add(new LoggingOutInterceptor());
        WebClient.getConfig(pushOrderPutClient).getInInterceptors().add(new LoggingInInterceptor());
                 
        // set Accept and ContentType headers
        pushOrderPutClient = pushOrderPutClient.accept("application/xml").type("application/xml").path("partner/partnerService/partner/pushOrder");
     	
        String pushOrderPutRequestURI = pushOrderPutClient.getCurrentURI().toString();
        System.out.println("Client POST METHOD Request URI:  " + pushOrderPutRequestURI);
        String pushOrderPutRequestHeaders = pushOrderPutClient.getHeaders().toString();
        System.out.println("Client POST METHOD Request Headers:  " + pushOrderPutRequestHeaders);
        
        ProductRequest productReqForOrderReq = new ProductRequest();
        productReqForOrderReq.setId("13");
        productReqForOrderReq.setName("swim suit");
        productReqForOrderReq.setDescription("mens racing suit");
        productReqForOrderReq.setPrice(50.00);
        productReqForOrderReq.setProductOwnerID("15");
        
        ArrayList<ProductRequest> items = new ArrayList<>();
        items.add(productReqForOrderReq);
        //items.add(productReqForOrderReq);
        
        OrderRequest orderReq = new OrderRequest();
        orderReq.setId("4");
        orderReq.setProducts(items);
        
     	String responsePushOrderPut =  pushOrderPutClient.put(orderReq, String.class); 
     	System.out.println("POST MEDTHOD Response ........." + responsePushOrderPut);
     	
     	/*****************************************************************************************
         * GET METHOD : Get Acknowledgement of fulfillment  
         *****************************************************************************************/
     	
     	
     	/*****************************************************************************************
         * POST METHOD : create new order // accept buy order
         *****************************************************************************************/
     	
     	
     	/*****************************************************************************************
         * POST METHOD : Accept credit card payment
         *****************************************************************************************/
     	
     	
     	/*****************************************************************************************
         * PUT METHOD : Ship Orders
         *****************************************************************************************/
     	
     	
     	/*****************************************************************************************
         * GET METHOD : Provide Order Status
         *****************************************************************************************/
     	
     	
     	/*****************************************************************************************
         * PUT METHOD : Cancel Order
         *****************************************************************************************/
     	
     	
     	/*****************************************************************************************
         * ERROR Handling
         *****************************************************************************************/
         
     	
     	/*****************************************************************************************
         * EXCEPTION Handling
         *****************************************************************************************/
     	
     	
     	/*****************************************************************************************
         * Fix the two aspects of the domain layer TA mentioned in his email about our Project2
         *****************************************************************************************/
     	/**
     	 * "Moving forward, make sure the domain layer of your project can handle things such as 
     	 * 1) Order confirmations, 
     	 * 2) checking availability of products before placing an order and so forth."
     	 */
     	
     	/*****************************************************************************************
         * POST METHOD : Add Address
         *****************************************************************************************/
     	//optional but maybe useful for our next layer
     	
     	/*****************************************************************************************
         * POST METHOD : Register Customer
         *****************************************************************************************/
     	//optional but maybe useful for our next layer

	}

}
