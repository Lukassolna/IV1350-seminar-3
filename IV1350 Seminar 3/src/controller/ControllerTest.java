package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.ExternalSystemHandler;
import integration.Printer;
import model.CashRegister;
import model.Sale;


class ControllerTest {
    private CashRegister cashRegister;
    private Printer printer;
    private ExternalSystemHandler extSysHan;
    private Controller controller;

    @BeforeEach
    void setUp() throws Exception {
        cashRegister = new CashRegister();
        printer = new Printer();
        extSysHan = new ExternalSystemHandler();
        controller = new Controller(cashRegister, printer, extSysHan);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testStartSale() {
        controller.startSale();
        assertNotNull(controller.getSaleInformation(), "Sale information should not be null");
        assertTrue(controller.getSaleInformation() instanceof Sale, "Sale information should be an instance of Sale");
    }

    @Test
    void testEnterIdentifierValidItem() {
        controller.startSale();
        boolean result = controller.enterIdentifier(1);
        assertTrue(result, "enterIdentifier() should return true for a valid item ID");
        assertEquals(1, controller.getSaleInformation().getItemList().size(), "Sale should have one item after adding a valid item ID");
    }

    @Test
    void testEnterIdentifierInvalidItem() {
        controller.startSale();
        boolean result = controller.enterIdentifier(11);
        assertFalse(result, "enterIdentifier() should return false for an invalid item ID");
        assertEquals(0, controller.getSaleInformation().getItemList().size(), "Sale should have zero items after adding an invalid item ID");
    }

    @Test
    void testFetchDiscount() {
        controller.startSale();
        controller.enterIdentifier(1);
        double discountTest=0.2;
        double priceBefore=controller.getSaleInformation().getTotalPrice();
        controller.fetchDiscount(1);
        double priceAfter=controller.getSaleInformation().getTotalPrice();
        assertEquals(1-discountTest, controller.getSaleInformation().getDiscount(), "Discount should be applied for customer ID 1");
 
        assertEquals(priceAfter, priceBefore*(1-discountTest), "Discount should be applied for customer ID 1");
   
    }
    @Test
    void testInvalidCustomerId() {
    	  controller.startSale();
          controller.enterIdentifier(1);
          
          double priceBefore=controller.getSaleInformation().getTotalPrice();
          controller.fetchDiscount(4);
          
          double priceAfter=controller.getSaleInformation().getTotalPrice();
         
          assertEquals(priceBefore, priceAfter, "Price should not change for an invalid customer ID");
    }


    @Test
    void testPay() {
        controller.startSale();
        controller.enterIdentifier(1);
        controller.pay(100);
        assertEquals(100, controller.getSaleInformation().getPayment(), "Payment should be recorded correctly after calling pay()");
        assertEquals(100, cashRegister.getTotalBalance(), "Cash register balance should be updated after calling pay()");
    }
    
    @Test
    void testPayWithZero() {
        controller.startSale();
        controller.enterIdentifier(1);
        controller.pay(0);
        assertEquals(0, controller.getSaleInformation().getPayment(), "Payment should be recorded correctly after calling pay() with an amount of zero");
        assertEquals(0, cashRegister.getTotalBalance(), "Cash register balance should not be updated after calling pay() with an amount of zero");
    }

     
}
