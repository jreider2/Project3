/**
 * 
 */
package order;

/**
 * @author julianareider
 *
 */
public class Payment {
	
	private double amount;
	private boolean isComplete;

	/**
	 * 
	 */
	public Payment() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the isComplete
	 */
	public boolean isComplete() {
		return isComplete;
	}

	/**
	 * @param isComplete the isComplete to set
	 */
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

}
