package org.nahid.tests;

import java.math.BigDecimal;
import org.nahid.domain.*;

public class TestShoppingCart{
    public static void main(String[] args){
	ShoppingCart sc = new ShoppingCart();	
	System.out.println("DEBUG: empty, newly created shopping cart: " + sc.asList());
	Book b = new Book("How To Spend Money", "Rich Bloke", new BigDecimal(1_000_000.00));
	System.out.print("Testing to add one book priced 1 000 000.....");
	sc.addToCart(b);
	assert sc.totalAmount().compareTo(new BigDecimal(1000000))==0
	    : "Total amount wrong, expected 1000000, got: " + sc.totalAmount();
	System.out.println("Adding one book, total amount test passed.");
	sc.remove(b);
	assert sc.totalAmount().compareTo(new BigDecimal(0))==0
	    : "Total amount wrong, expected 0, got: " + sc.totalAmount();
	System.out.println("Removing the only book, total amount test passed.");
	System.out.print("Testing to add one book priced 1 000 000 twice.....");
	sc.addToCart(b);
	sc.addToCart(b);
	assert sc.totalAmount().compareTo(new BigDecimal(2000000))==0
	    : "Total amount wrong, expected 2000000, got: " + sc.totalAmount();
	System.out.println("Adding one book, total amount test passed.");
    }
}
