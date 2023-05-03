package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.Printer;

class SaleTest {
	private Sale sale;
	private Printer printer;

	@BeforeEach
	void setUp() throws Exception {
		 printer = new Printer();
	    sale = new Sale(printer);
	    
	}

	@AfterEach
	void tearDown() throws Exception {
	}


	@Test
	void testAddItem() {
	   
		

	  
	    Item item = new Item(1, 10, 0.25, "Test Item");

	    
	    sale.addItem(item);

	    
	    List<Item> itemList = sale.getItemList();
	    assertEquals(1, itemList.size(), "Item should have been added to the sale");
	    assertEquals(item, itemList.get(0), "Item added to the sale should be the same as the one passed to addItem()");

	   
	    double expectedTotalPrice = item.getItemPrice() * (1 + item.getVAT());
	    assertEquals(expectedTotalPrice, sale.getTotalPrice(), "Total price should be updated correctly after calling addItem()");
	}

	@Test
	void testApplyDiscount() {
	  

	   
	    Item item = new Item(1, 10, 0.25, "Test Item");

	   
	    sale.addItem(item);

	    // Apply a discount to the sale
	    double discount = 0.9; // 10% discount
	    sale.applyDiscount(discount);

	    
	    double expectedTotalPrice = item.getItemPrice() * (1 + item.getVAT()) * (discount);
	    
	    assertEquals(expectedTotalPrice, sale.getTotalPrice(), "Total price should be updated correctly after applying a discount");
	}
	@Test
	void testSaleConstructor() {
		
	    // Check that the time of sale is not null
	    assertNotNull(sale.getTimeOfSale(), "Time of sale should not be null");

	    //check that the itemQuantityMap is empty
	    assertTrue(sale.getItemQuantityMap().isEmpty(), "Item quantity map should be empty");

	    // check that the printer is the same as the one passed to the constructor
	    assertEquals(printer, sale.getPrinter(), "Printer should be the same as the one passed to the constructor");

	    // check that the itemList is empty
	    assertTrue(sale.getItemList().isEmpty(), "Item list should be empty");
	}
	
	@Test
	public void testPay() {
	   
	    sale.pay(100.0);
	    assertEquals(100.0, sale.getPayment(), 0.01);
	}

	@Test
	public void testGetters() {
		

	  

	    sale.applyDiscount(0.1);
	    sale.pay(100);
	   

	    assertEquals(sale.getPayment(), 100, 0.001);
	    
	    assertEquals(sale.getPrinter(), printer);
	    assertEquals(sale.getDiscount(), 0.1, 0.001);
	    assertEquals(sale.getItemList(), new ArrayList<Item>());
	    assertEquals(sale.getTotalPrice(), 0.0, 0.001); // the total price should be 0 before any items are added
	}



}
