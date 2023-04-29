package view;

import controller.Controller;

public class View {
    
    private Controller controller;
    
    public View(Controller controller) {
        this.controller = controller;
    }
    
    public void start() {
        controller.startSale();
        
       

       
            controller.enterIdentifier(1);
            controller.enterIdentifier(1);
            controller.enterIdentifier(3);
            controller.enterIdentifier(2);
            controller.enterIdentifier(5);
           
        
       
        controller.fetchDiscount(10);
        double totalPrice=controller.getSaleInformation().getTotalPrice();        
        

        controller.pay(totalPrice);
        
        
        controller.endSale();
        
       
    }
    
  
}

