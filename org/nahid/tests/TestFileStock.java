package org.nahid.tests;

import java.math.BigDecimal;
import java.util.Arrays;
import org.nahid.stock.FileStock;
import org.nahid.domain.Book;

public class TestFileStock{
    public static void main(String[] args){
	FileStock stock = new FileStock();
	System.out.println("All books:");
	System.out.println(Arrays.toString(stock.list(null)));
	System.out.println("All books with title or author on 'r':");
	System.out.println(Arrays.toString(stock.list("r")));
	System.out.println("All books with title or author on 'ra':");
	System.out.println(Arrays.toString(stock.list("ra")));
	System.out.println("All books with title or author on 'ri':");
	System.out.println(Arrays.toString(stock.list("ri")));

	//Adding book
	System.out.println("Adding a book" );
	Book b = new Book("Monkeys","Java-wannabe", new BigDecimal(10022.50000));
	stock.add(b,15);
	assert stock.list("Monkeys").length>0 : "Could not find newly added book";
	System.out.println("Adding book passed");
    }
}
