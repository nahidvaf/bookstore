package org.nahid.tests;
import org.nahid.domain.Book;
import java.math.BigDecimal;

/**
 * Tests a few very simplistic and basic stuff
 * for the Book contructor and accessor methods.
 */
public class TestBook{
    public static void main(String[] args){
	Book b = new Book("Computers","ComputerGuy", new BigDecimal(100.50));
	System.out.print("Running price test...");
	assert (b.price().compareTo(new BigDecimal(100.50))==0):
	"Price was wrong. Got: " +
	    b.price()+" Expected: 100.50";
	System.out.println("done!");
	System.out.print("Running title test...");
	assert b.title() != null && b.title().equals("Computers"):
	    "Title was wrong. Got: "+
	    b.title() +
	    " Expected: Computers";
	System.out.println("done!");
	System.out.print("Running author test...");
	assert b.author() != null && b.author().equals("ComputerGuy"):
	    "Author was wrong. Got: " +
	    b.author() +
	    " Expected: ComputerGuy";
	System.out.println("done!");
	System.out.print("Running toString() test...");	
	assert b.toString() != null && b.toString().equals("Computers;ComputerGuy;100.50"):
	    "toString failed: " + b.toString();
	System.out.println("done!");
	System.out.print("Testing toString of large number for formatting");
	b = new Book("Monkeys","Java-wannabe", new BigDecimal(10022.50000));
	assert b.toString() !=null && b.toString().equals("Monkeys;Java-wannabe;10,022.50"):
	    "toString for large number-price failed. Got: "+b.toString()+ " Expected: "+
	    "Monkeys;Java-wannabe;10,022.50";
	System.out.println("done!");	
	System.err.println("Tests completed successfully.");
    }

}
