package model;

public class CashPayment {
	private double amount;
	
	/**
	 * Represents a cash payment.
	 * 
	 * @param amount the amount of cash paid
	 */
	public CashPayment(double amount) {
		this.amount = amount;
	}

	/**
	 * Returns the amount of cash paid.
	 * 
	 * @return the amount of cash paid
	 */
	public double getAmount() {
		return amount;
	}
}
