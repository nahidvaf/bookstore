package org.nahid.domain;
import java.util.Locale;
import java.text.NumberFormat;
import java.text.ParseException;
import java.math.BigDecimal;

//TODO: implement comparable
public class Book {
    private String title;
    private String author;
    private BigDecimal price;

    public Book(String title, String author, BigDecimal price){
	this.title  = title;
	this.author = author;
	this.price  = price;
    }
    public String title(){
	return title;
    }
    public String author(){
	return author;
    }
    public BigDecimal price(){
	return price;
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
