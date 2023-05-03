package controller;

import integration.ExternalSystemHandler;
import integration.Printer;
import model.CashRegister;
import model.Item;
import model.Sale;
import model.CashPayment;

public class Controller {

	
	private ExternalSystemHandler extSysHan;
	
	private CashRegister cashRegister;
	private Printer printer;

	private Sale saleInformation;

	/**
	 * Constructs a new Controller instance and initializes the cash register,printer and external system handler.
	 */
	public Controller(CashRegister cashRegister , Printer printer, ExternalSystemHandler extSysHan){
		this.cashRegister=cashRegister;
		this.extSysHan=extSysHan;
		this.printer=printer;
	}

	/**
	 * Starts a new sale by creating a new Sale object.
	 */
	public void startSale () {
		saleInformation=new Sale(printer);
	}

	/**
	 * Looks up an item in the external inventory system based on its ID and adds it to the current sale if its exists
	 * 
	 * @param itemID the ID of the item to look up
	 * @return true if the item is found and added to the sale, false otherwise
	 */
	public boolean enterIdentifier(int itemID) {
	    Item foundItem = extSysHan.getExternalInventorySystem().fetchItemInformation(itemID);
	    if (foundItem != null) {
	        saleInformation.addItem(foundItem);
	        return true;
	        
	    } else {
	    	
	        return false;
	    }
	}

	/**
	 * Fetches the discount for a customer based on their ID and the current sale.
	 * 
	 * 
	 * @param customerID the ID of the customer
	 * @param saleinformation the current sale
	 * @return the discount percentage for the customer
	 */	
	public void fetchDiscount(int customerID) {
		double discountToApply=extSysHan.calculateDiscount(customerID);
		saleInformation.applyDiscount(discountToApply);
	}

	/**
	 * Processes a cash payment for the current sale, updates external systems,  creates a Cashpayment and adds it to cashregisters. 
	 * 
	 * @param amount the amount of cash paid by the customer
	 */
	public void pay (double amount) {
		extSysHan.updateExternalSystems(saleInformation);
		cashRegister.addPayment(new CashPayment(amount));
		saleInformation.pay(amount);
		
	}

	/**
	 * Ends the current sale and returns the total price of the items in the sale. 
	 * @return the total price of the items in the sale
	 */
	public double endSale() {
	    return saleInformation.getTotalPrice();
	}

	/**
	 * Prints receipt for the current Sale
	 */
	public void printReceipt() {
		saleInformation.printReceipt();
	}
	
	public Sale getSaleInformation() {
	    return saleInformation;
	}
}



