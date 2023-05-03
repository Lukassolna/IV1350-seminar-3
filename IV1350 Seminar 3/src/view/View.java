package view;

import java.util.Scanner;

import controller.Controller;

/**
 * View is a class that represents a user interface and contains hard coded controller calls + printouts
 * 
 * 
 */

public class View {
    
    private Controller controller;
    
    /**
     * Constructs a new View object with the specified Controller.
     * 
     * @param controller the Controller object to use for handling user input and processing sales
     */
    public View(Controller controller) {
        this.controller = controller;
    }
    
    /**
     * Starts a new sale by invoking the appropriate methods on the Controller object.
     * 
     *  (next Double input using locale standard of comma instead of . for input)
     */
    


    public void start() {
        controller.startSale();
        
        Scanner scanner = new Scanner(System.in);
        
        int itemId;
        do {
            System.out.print("Enter item ID (or 0 to finish): ");
            itemId = scanner.nextInt();
            if (itemId != 0) {
                controller.enterIdentifier(itemId);
                System.out.println("Running total is: " + controller.getSaleInformation().getTotalPrice()+ " SEK ");
                System.out.println();
            }
        } while (itemId != 0);
        
        System.out.print("Enter customer ID for discount: ");
        
        int customerId = scanner.nextInt();
        controller.fetchDiscount(customerId);
       
        
        double totalPrice = controller.endSale();
        System.out.println(" Sale has concluded and the total price is : "+  totalPrice);
        
        
    
        
        System.out.println("Enter payment amount: ");
        
        
        
        controller.pay( scanner.nextDouble());
        controller.printReceipt();
        
        scanner.close();
    }
    // Old code. Added a scanner instead for enterIdentifier
   
    /*
    public void start() {
    controller.startSale();
    
        controller.enterIdentifier(5);
       controller.enterIdentifier(4);
       controller.enterIdentifier(3);
       controller.enterIdentifier(3);
       controller.enterIdentifier(3);
       controller.enterIdentifier(3);
       controller.enterIdentifier(6);
       
       controller.endSale
       controller.fetchDiscount(1);
       
       
       controller.pay(totalPrice);
       controller.printReceipt();
   }
   */
}


