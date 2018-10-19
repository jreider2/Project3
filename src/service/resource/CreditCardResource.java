package service.resource;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import service.represntation.CreditCardRepresentation;
import service.workflow.CreditCardActivity;

public class CreditCardResource implements CreditCardService {
	private CreditCardActivity ccA;
	public CreditCardResource() {
		ccA = new CreditCardActivity();
	}
	
	@POST
	@Path("/creditcards")
	@Produces({"application/xml" , "application/json"})
	public ArrayList<CreditCardRepresentation> getCreditCards(String customerNumber) {
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
	
	
}
