package org.nahid.stock;

import org.nahid.domain.Book;

public class StockEntry{
    private Book book;
    private int numberInStock;
    public StockEntry(Book book, int numberInStock){
	this.book = book;
	this.numberInStock = numberInStock;
    }
    public Book book () {return book;}
    public int numberInStock(){return numberInStock;}
    public String toString(){ return book + " " + numberInStock;}
    public void decreaseStock(){
	if(numberInStock > 0){
	    numberInStock--;
	}else{
	    throw new IllegalStateException("Can't decrease the stock count when it's already zero.");
	}
    }
}
