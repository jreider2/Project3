package client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import service.represntation.PartnerRequest;
import service.represntation.ProductRequest;

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
         System.out.println("GET METHOD .........................................................");
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
         System.out.println("POST METHOD .........................................................");
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
         * PUT METHOD Push Product to Partner
        *****************************************************************************************/
        System.out.println("PUT METHOD .........................................................");
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
        
        //TODO FIX ME -- not enough information to add the new partner to the DATABASE 
        ProductRequest productReq = new ProductRequest();
//        partnerReq.setId("0");
//        partnerReq.setCompanyName("Sony");
//        partnerReq.setUserName("sonyUsername");
//        partnerReq.setPassword("sonysPassword");
//        
//     	String responsePost =  putClient.post(partnerReq, String.class);
//     	System.out.println("POST MEDTHOD Response ........." + responsePost);
         


	}

}
