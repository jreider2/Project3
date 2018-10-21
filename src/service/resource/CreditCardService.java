package service.resource;


import java.util.ArrayList;

import javax.jws.WebService;

import service.represntation.CreditCardRepresentation;


@WebService
public interface CreditCardService {

	public ArrayList<CreditCardRepresentation> getCreditCards(String customerNumber);

	public CreditCardRepresentation getCreditCard(String ccNo);
	
	public boolean deleteCreditCard(String ccNo);
	
}
