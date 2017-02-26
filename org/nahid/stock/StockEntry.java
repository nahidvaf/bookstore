package org.nahid.stock;

import org.nahid.domain.Book;

/**
 * Represents an entry in the inventory. It consists
 * of a Book and the number of items in stock for
 * that book.
 *
 * Used as a convenience class for storing the books
 * in the inventory.
 */
public class StockEntry{
    private Book book;
    private int numberInStock;

    /**
     * Creates a StockEntry.
     * @param book The book in this entry
     * @param numberInStock How many of this book we have in stock
     */
    public StockEntry(Book book, int numberInStock){
	this.book = book;
	this.numberInStock = numberInStock;
    }

    /**
     * Returns the "Book part" of this entry.
     * @return The Book part of this entry
     */
    public Book book () {return book;}

    /**
     * Returns the number in stock figure for this entry
     * @return The numberInStock part of this entry
     */
    public int numberInStock(){return numberInStock;}

    @Override
    public String toString(){ return book + " " + numberInStock;}

    /**
     * Decreases the number in stock figure of this entry by one.
     */
    public void decreaseStock(){
	if(numberInStock > 0){
	    numberInStock--;
	}else{
	    throw new IllegalStateException("Can't decrease the stock count when it's already zero.");
	}
    }
}
