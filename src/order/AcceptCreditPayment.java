package order;

import dao.AcceptCreditPaymentDAO;

public class AcceptCreditPayment {

	public AcceptCreditPayment(String ccNo, String customerNo) {
		//Should we create a customer object from the customerNo rather than pass a bunch of individual properties?
	}
	
	public boolean submitPayment(String ccNo) {
		AcceptCreditPaymentDAO acpDAO = new AcceptCreditPaymentDAO();
		return acpDAO.submitPayment(ccNo);
	}
}
