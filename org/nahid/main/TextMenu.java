package org.nahid.main;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

import org.nahid.domain.*;
import org.nahid.stock.*;
/**
 * Represents a simplified text-based menu system.
 *
 * TODO: Perhaps a menu option 7 - Check out - which
 * calls "buy()". Not sure if this is needed?
 * buy() is implemented but never used.
 */
public class TextMenu{
    BookList stock;
    ShoppingCart cart;
    /**
     * Creates a new TextMenu.
     * @param stock The BookList representing the inventory.
     */
    public TextMenu(BookList stock){
	this.stock = stock;
	cart = new ShoppingCart();
    }
    /**
     * Starts the menu.
     */
    public void start(){
	runMenu();
    }
    private String getReply(String prompt){
	System.out.println(prompt);
	// Crashes on Ctrl-D (EOF but I don't remember how to fix that)
	return new Scanner(System.in).nextLine();
    }
    private void printOptions(){
	System.out.println("1. List all books");
	System.out.println("2. Search books and authors");
	System.out.println("3. Add book to cart");
	System.out.println("4. Remove book from cart");
	System.out.println("5. View cart.");
	System.out.println("6. Quit this beautiful program."); 
    }
    private boolean isInvalidReply(String reply){
	List<String>validStrings = Arrays.asList("1", "2", "3", "4", "5", "6");
	return !validStrings.contains(reply);
    }
    private void listAllBooks(){
	for(Book b : stock.list(null)){
	    try{
		System.out.println("BookID: [" + stock.getID(b) + "] - " + b);
	    }catch(NoSuchBookException e){
		// Ignore
	    }
	}
    }
    private void searchBooks(){
	String q = getReply("Your search string (searches both titles and authors)");
	int numHits=0;
	for(Book b : stock.list(q)){
	    try{
		System.out.println("BookID: [" + stock.getID(b) + "] - " + b);
		numHits++;
	    }catch(NoSuchBookException e){
		// Ignore
	    }
	}
	if(numHits==0){
	    System.out.println("No matches for " + q);
	}
    }
    private void addToCart(){
	String ID = getReply("Please enter the ID of the book you want to add to cart (please answer with only the number)");
	try{
	    int id = Integer.parseInt(ID);
	    cart.addToCart(stock.getBookByID(id));
	    System.out.println("Your new cart:");
	    viewCart();
	}catch(NoSuchBookException|NumberFormatException e){
	    System.out.println("Sorry, no such ID");
	    // TODO: Give the user another chance - in a loop
	}
    }
    private void removeFromCart(){
	System.out.println("Your current cart:");
	viewCart();
	String ID = getReply("Please enter the ID of the book you want to remove (please answer with only the number)");
	try{
	    int id = Integer.parseInt(ID);
	    cart.remove(stock.getBookByID(id));
	    // TODO: Give error message if ID is not in the cart...
	    // if the ID is in stock but not in cart...
	    System.out.println("Your new cart:");
	    viewCart();
	}catch(NoSuchBookException|NumberFormatException e){
	    System.out.println("Sorry, no such ID");
	    // TODO: Give the user another chance - in a loop
	}
    }
    private void viewCart(){
	System.out.println("===Shopping cart contents===");
	for(Book b : cart.asList()){
	    try{
		System.out.println("BookID: [" + stock.getID(b) + "] - " + b);
	    }catch(NoSuchBookException e){
		// Ignore - this is just printing
	    }
	}
	System.out.println("============================");
	System.out.println("Total amount: " + cart.totalAmount());
	System.out.println("============================");
    }
    private void runMenu(){
	while(true){
	    printOptions();
	    String reply=null;
	    reply=getReply("Enter your choice (1-6):");
	    while(isInvalidReply(reply)){
		reply = getReply("Please enter a number between 1 - 6");
	    }
	    switch (reply){
	    case "1":
		listAllBooks();
		break;
	    case "2":
		searchBooks();
		break;
	    case "3":
		addToCart();
		break;
	    case "4":
		removeFromCart();
		break;
	    case "5":
		viewCart();
		break;
	    case "6":
		System.out.println("Bye!");
		System.exit(0);
	    }
	}
    }
}
