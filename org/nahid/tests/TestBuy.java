package org.nahid.tests;

import java.math.BigDecimal;
import java.util.Arrays;

import org.nahid.domain.*;
import org.nahid.stock.*;


public class TestBuy{
    public static void main(String[] args){
	System.out.print("Getting a FileStock...");
	FileStock stock = new FileStock();
	System.out.println("Done!");
	System.out.print("Testing to buy the same book three times (but it has a stock count of 1)...");
	Book b = new Book("How To Spend Money", "Rich Bloke", new BigDecimal(1_000_000.00));
	int[] results = stock.buy(b,b,b);
	assert results.length == 3 : "Unexpected length of "+
	    "the result of calling buy()."+
	    " Expected 3, Got: " + results.length;
	assert (results[0] == 0 && results[1] == 1 && results[2] == 1)
	    : "Got wrong statuscodes in the resulting array. " +
	    "Expected [0, 1, 1], got: " +
	    Arrays.toString(results);
	System.out.println("Buying a book out of stock passed the test.");
	b = new Book("Doesn't exist", "nobody", new BigDecimal(0));
	System.out.print("Testing to buy an unexisting book...");
	results = stock.buy(b);
	assert results.length == 1 : "Unexpected length of "+
	    "the result of calling buy()."+
	    " Expected 1, Got: " + results.length;
	assert (results[0] == 2)
	    : "Got wrong statuscodes in the resulting array. " +
	    "Expected [0], got: " +
	    Arrays.toString(results);
	System.out.println("Buying an unexisting book passed the test.");	
	System.out.println("All tests passed.");
    }
}

