package org.nahid.domain;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.nahid.stock.*;
/**
 * Represents a shopping cart to which you can add and remove books.
 */
public class ShoppingCart{

    private BigDecimal totalAmount;
    private List<Book> contents;
    private BookList stock;
    /**
     * Creates a new ShoppingCart with no content.
     */
    public ShoppingCart(){
	contents = new ArrayList<>();
	// TODO: Make FileStock a Singleton
	stock    = new FileStock();
    }
    /**
     * Adds a Book to this ShoppingCart.
     * @param b The book to be added
     */
    public void addToCart(Book b){
	contents.add(b);
    }
    /**
     * Removes a Book from this ShoppingCart
     * @param b The book to be removed
     */
    public void remove(Book b){
	contents.remove(b);
    }
    /**
     * Returns a List representation of this ShoppingCart.
     * @return A List&lt;Book&gt; with the contents of this ShoppingCart
     */
    public List<Book> asList(){
	return contents;
    }
    /**
     * Returns the total amount for the Books in this ShoppingCart
     * @return The total amount, i.e. the sum of the prices for the Books
     * in this ShoppingCart
     */
    public BigDecimal totalAmount(){
	BigDecimal total = new BigDecimal(0);
	for(Book b : contents){
	    total = total.add(b.price());
	}
	return total;
    }
}
