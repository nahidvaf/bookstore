package org.nahid.domain;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.nahid.stock.*;

public class ShoppingCart{

    private BigDecimal totalAmount;
    private List<Book> contents;
    private BookList stock;
    public ShoppingCart(){
	contents = new ArrayList<>();
	stock    = new FileStock();
    }
    public void addToCart(Book b){
	contents.add(b);
    }
    public void remove(Book b){
	contents.remove(b);
    }
    public List<Book> asList(){
	return contents;
    }
    public BigDecimal totalAmount(){
	BigDecimal total = new BigDecimal(0);
	for(Book b : contents){
	    total = total.add(b.price());
	}
	return total;
    }
}
