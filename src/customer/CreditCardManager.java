package customer;

import java.util.ArrayList;

import dao.CreditCardDAO;

public class CreditCardManager {
	
	private CreditCardDAO ccdao;

	public CreditCardManager() {
		ccdao = new CreditCardDAO();
	}
	
	public boolean Save(String ccNo, String expDate, String ccHolderName, String ccSecurityCode, String CustomerID) {
		CreditCard cc = new CreditCard();
		cc.setCreditCardNumber(ccNo);
		cc.setExpDate(expDate);
		cc.setcardHolderName(ccHolderName);
		cc.setSecurityCode(ccSecurityCode);
		cc.setCustomerID(CustomerID);
		return ccdao.enterCreditCard(cc);
	}
	
	public boolean Delete(String ccId) {
		return ccdao.removeCreditCard(ccId);
	}
	
	public CreditCard getCreditCard(String ccNo) {
		return ccdao.getCreditCardInfo(ccNo);
	}
	
	public ArrayList<CreditCard> getAllCreditCardsforCustomer(String customerId){
		return ccdao.getAllCreditCards(customerId);
	}
}
