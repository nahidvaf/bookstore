package org.nahid.domain;
import java.util.Locale;
import java.text.NumberFormat;
import java.text.ParseException;
import java.math.BigDecimal;


/**
 * Represents a Book in the book store.
 *
 * TODO: implement comparable
 */
public class Book {
    private String title;
    private String author;
    private BigDecimal price;
    /**
     * Creates a new Book.
     * @param title The title of the book as a String
     * @param author The Author of the book as a String
     * @param price The price of the book as a BigDecimal
     */
    public Book(String title, String author, BigDecimal price){
	this.title  = title;
	this.author = author;
	this.price  = price;
    }
    /**
     * Returns the title of this Book
     * @return the title of this Book as a String
     */
    public String title(){
	return title;
    }
    /**
     * Returns the autor of this Book
     * @return the author of this Book as a String
     */
    public String author(){
	return author;
    }
    /**
     * Returns the price of this Book
     * @return the price of this Book as a BigDecimal
     */
    public BigDecimal price(){
	return price;
    }    
    @Override
    public boolean equals(Object o){
	if(! (o instanceof Book) ){
	    return false;
	}
	Book ob = (Book)o;
	return
	    this.title.equals(ob.title) &&
	    this.author.equals(ob.author) &&
	    this.price.compareTo(ob.price) == 0;
    }
    @Override
    public int hashCode(){
	// I'm not an expert in hashing, so I'm using a
	// hash function inspired by Joshua Bloch's Item 9
	// "Always override hashCode when overriding equals"
	// Source: http://ur1.ca/qkax7
	int result = 17;
	result = 31 * result + title.hashCode();
	result = 31 * result + author.hashCode();
	result = 31 * result + price.intValue();
	return result;
    }
    @Override
    public String toString(){
	// For some reason, the prices must be in US number format...
	NumberFormat formatter = NumberFormat.getInstance(Locale.US);
	formatter.setMaximumFractionDigits(2);
	formatter.setMinimumFractionDigits(2);	
	String formattedPrice = formatter.format(price.doubleValue());
	return new StringBuilder(title)
	    .append(";")
	    .append(author)
	    .append(";")
	    .append(formattedPrice)
	    .toString();
    }
}
