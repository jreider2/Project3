package service.resource;

import java.util.ArrayList;

import javax.websocket.server.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import service.represntation.CreditCardRepresentation;
import service.represntation.CreditCardRequest;
import service.workflow.CreditCardActivity;

@Path("/ccservice/")
public class CreditCardResource implements CreditCardService {
	private CreditCardActivity ccA;
	public CreditCardResource() {
		ccA = new CreditCardActivity();
	}
	
	@GET
	@Path("/creditcardsandrew")
	@Produces({"application/xml" , "application/json"})
	public ArrayList<CreditCardRepresentation> getCreditCards(@PathParam("customerID") String customerNumber) {
		return ccA.GetCreditCards(customerNumber);
	}
	
	@POST
	@Path ("/creditcard")
	@Produces({"application/xml" , "application/json"})
	public CreditCardRepresentation getCreditCard(String ccNo) {
		return ccA.GetCreditCard(ccNo);
	}
	
	@DELETE
	@Path ("/deletecreditcard")
	@Produces({"application/xml" , "application/json"})
	public boolean deleteCreditCard(String ccNo) {
		return ccA.removeCreditCard(ccNo);
	}
	
	@POST
	@Path("/addcard")
	@Produces({"application/xml" , "application/json"})
	public boolean addCreditCard(CreditCardRequest ccR) {
		System.out.println("POST new creditcard ..................");
		CreditCardActivity ccA = new CreditCardActivity();
		return ccA.addCreditCard(ccR.getCcNum(), ccR.getCcExpirationDate(), ccR.getCcHolder(), ccR.getCcSecurityCode(), ccR.getCcCustomerNo());
	}
	
	
}
