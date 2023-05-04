package view;

import java.util.Scanner;

import controller.Controller;
import model.Item;

/**
 * View is a class that represents a user interface and contains hard coded
 * controller calls + printouts
 * 
 * 
 */

public class View {
	private Controller controller;

	/**
	 * Constructs a new View object with the specified Controller.
	 * 
	 * @param controller the Controller object to use for handling user input and
	 *                   processing sales
	 */
	public View(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Starts a new sale by invoking the appropriate methods on the Controller
	 * object.
	 * 
	 * (next Double input using locale standard of comma instead of . for input)
	 */

	public void start() {
		controller.startSale();

		Scanner scanner = new Scanner(System.in);
		int itemId;

		do {
			System.out.print("Enter item ID (or 0 to finish): ");
			itemId = scanner.nextInt();
			if (itemId != 0) {
				boolean itemIdValidity = controller.enterIdentifier(itemId);
				if (!itemIdValidity) {
					System.out.println("The item (ID: " + itemId + ") is not valid");
				}
			}
			System.out.println("\nRunning total is: " + controller.getSaleInformation().getTotalPrice() + " SEK\n\n_______");
			System.out.println("Current cart:\n");
			for(Item item : controller.getItemsInSale()) {
				System.out.println(item.getItemName() + ": " + item.getItemPrice()+"\n");
			}
			System.out.println("_______\n");

		} while (itemId != 0);
		
		System.out.print("Enter customer ID for discount: ");
		int customerId = scanner.nextInt();
		controller.fetchDiscount(customerId);
		double totalPrice = controller.endSale();
		System.out.println("Discount has been added. The new total is: " + totalPrice);

		double payment;
		do {
			System.out.print("Enter payment amount: ");
			payment = scanner.nextDouble();
			if (payment < controller.getSaleInformation().getTotalPrice()) {
				System.out.println("Payment must exceed the sale total!");
			}
		} while (payment < controller.getSaleInformation().getTotalPrice());

		controller.pay(payment);
		double change = controller.change();
		System.out.println("Your change: " + change + "\n");

		controller.printReceipt();
		scanner.close();
	}

}