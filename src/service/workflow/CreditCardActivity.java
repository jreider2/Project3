package service.workflow;

import java.util.ArrayList;

import customer.CreditCard;
import customer.CreditCardManager;
import service.represntation.CreditCardRepresentation;

public class CreditCardActivity {
	
	private static CreditCardManager ccManager = new CreditCardManager();
	
	public CreditCardActivity() {
		
	}
	
	public CreditCardRepresentation GetCreditCard(String ccId) {
		CreditCard cc = ccManager.getCreditCard(ccId);
				
		CreditCardRepresentation ccR = new CreditCardRepresentation();
		ccR.setCcNo(cc.getCreditCardNumber());
		ccR.setExpDate(cc.getExpDate());
		ccR.setNameOncc(cc.getCardHolderName());
		return ccR;
		
	}

	public ArrayList<CreditCardRepresentation> GetCreditCards(String customerNumber){
		ArrayList<CreditCard> arCC = ccManager.getAllCreditCardsforCustomer(customerNumber);
		ArrayList<CreditCardRepresentation> arCCRep = new ArrayList<>();
		for(CreditCard cc : arCC) {
			arCCRep.add(GetCreditCard(cc.getId()));
		}
		return arCCRep;
	}
	
	public boolean removeCreditCard(String ccId) {
		return ccManager.Delete(ccId);
	}
	
	public boolean addCreditCard(String ccNo, String expDate, String ccHolderName, String ccSecurityCode, String CustomerID) {
		return ccManager.Save(ccNo, expDate, ccHolderName, ccSecurityCode, CustomerID);
	}
}
