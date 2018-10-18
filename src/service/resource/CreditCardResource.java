package service.resource;

import java.util.ArrayList;

import service.represntation.CreditCardRepresentation;
import service.workflow.CreditCardActivity;

public class CreditCardResource {
	private CreditCardActivity ccA;
	public CreditCardResource() {
		ccA = new CreditCardActivity();
	}
	
	//@POST with customer number
	//@Path("/creditcards")
	//@Produces({"application/xml" , "application/json"})
	public ArrayList<CreditCardRepresentation> getCreditCards(String customerNumber) {
		return ccA.GetCreditCards(customerNumber);
	}
	
	//@POST with credit card number
	//@Path ("/creditcard")
	//@Produces({"application/xml" , "application/json"})
	public CreditCardRepresentation getCreditCard(String ccNo) {
		return ccA.GetCreditCard(ccNo);
	}
	
	//@DELETE with credit card number
	//@Path ("/deletecreditcard")
	//@Produces({"application/xml" , "application/json"})
	public boolean deleteCreditCard(String ccNo) {
		return ccA.removeCreditCard(ccNo);
	}
	
	
}
