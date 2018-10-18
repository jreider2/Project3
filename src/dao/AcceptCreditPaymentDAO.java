package dao;

public class AcceptCreditPaymentDAO {
	public AcceptCreditPaymentDAO() {
		//super("connectionString");
	}
	
	/** Note to partner: this method is NOT considered complete */
	public boolean submitPayment(String ccNo) {
		String sqlQuery = "SELECT * FROM CREDITCARDS WHERE ccNo = '" + ccNo + "'";
		
		//TODO: if the result contains a credit card then we know we have a valid payment method.
		
		//I thought we might use the pattern from the class example code, (as can be seen in the classes I've implemented so far)
		//Feel free to add DBConnection back in if you see it as appropriate. 
		
		//TODO: insert payment information into the table.
		sqlQuery = "INSERT INTO PAYMENTS VALUES(" + "Other payment info here";
		
		return true;
	}
	
	
}
