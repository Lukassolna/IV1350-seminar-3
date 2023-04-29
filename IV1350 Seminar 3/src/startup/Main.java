package startup;

import controller.Controller;
import integration.ExternalSystemHandler;
import integration.Printer;
import model.CashRegister;
import model.Item;
import view.View;

public class Main {

    public static void main(String[] args) {
    	
        CashRegister cashRegister = new CashRegister();
        Printer printer = new Printer();
        ExternalSystemHandler extSysHan = new ExternalSystemHandler();
        Controller contr = new Controller(cashRegister, printer, extSysHan);
        View view = new View(contr);
        
        view.start();
    }
}
