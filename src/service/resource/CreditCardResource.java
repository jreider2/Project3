package service.resource;

import java.util.ArrayList;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.http.MediaType;

import service.represntation.CreditCardRepresentation;
import service.represntation.CreditCardRequest;
import service.workflow.CreditCardActivity;

@Path("/")
public class CreditCardResource implements CreditCardService {
	private CreditCardActivity ccA;
	public CreditCardResource() {
		ccA = new CreditCardActivity();
	}
	
	@GET
	@Path("/getcards")
	@Produces({"application/xml" , "application/json"})
	public ArrayList<CreditCardRepresentation> getCreditCards(@QueryParam("customerID") String customerNumber) {
		return ccA.GetCreditCards(customerNumber);
	}
	
	@GET
	@Path ("/getcard")
	@Produces({"application/xml" , "application/json"})
	public CreditCardRepresentation getCreditCard(@QueryParam("ccNo") String ccNo) {
		return ccA.GetCreditCard(ccNo);
	}
	
	@DELETE
	@Path ("/delete")
	@Produces({"application/xml" , "application/json"})
	public boolean deleteCreditCard(@QueryParam("ccNo") String ccNo) {
		return ccA.removeCreditCard(ccNo);
	}
	
	@POST
	@Path("/add")
	@Produces({"application/xml" , "application/json"})
	@Consumes({"application/xml" , "application/json"})
	public boolean addCreditCard(CreditCardRequest ccR) {
		System.out.println("POST new creditcard ..................");
		CreditCardActivity ccA = new CreditCardActivity();
		return ccA.addCreditCard(ccR.getCcNum(), ccR.getCcExpirationDate(), ccR.getCcHolder(), ccR.getCcSecurityCode(), ccR.getCcCustomerNo());
	}
	
	
}
