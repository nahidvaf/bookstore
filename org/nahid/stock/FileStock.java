package org.nahid.stock;
import java.nio.file.*;
import java.io.IOException;
import java.util.stream.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.nio.charset.StandardCharsets;

import org.nahid.domain.BookList;
import org.nahid.domain.Book;
import org.nahid.admin.GetFile;
import static org.nahid.admin.GetFile.INVENTORY_FILE;

/**
 * Represents the Stock of books (read from the stock inventory file).
 *
 * Implements the BookList interface provided by Contribe.
 *
 * Using that interface allows for writing other types of inventories
 * in the future, for instance a DatabaseStock which keeps the books
 * in a database rather than in a text file.
 *
 * TODO: Make this class a Singleton - it probably makes more
 * sense to allow for only one Inventory for the application.
 */
public class FileStock implements BookList{

    private static class BookFileWriter{
	private void writeBooks(Map<Integer, StockEntry> stock){
	    try{
		StringBuilder sb = new StringBuilder();
		for(Integer id: stock.keySet()){
		    StockEntry se = stock.get(id);
		    String line = se.book().toString()+";" + se.numberInStock();
		    sb.append(line).append("\n");
		}
		Path file = Paths.get(INVENTORY_FILE);
		Files.write(file, sb.toString().getBytes(StandardCharsets.UTF_8),StandardOpenOption.WRITE);
	    }catch(IOException e){
		System.err.println("Fatal: could not save inventory: " + e.getMessage());
	    }
	}
    }
    private static class BookFileReader{
	private Map<Integer, StockEntry>  readAllBooks(){
	    Map<Integer, StockEntry> books = new HashMap<>();
	    Set<String>linesRead = new HashSet<>(); // To detect duplicate rows
	    try{
		int bookId = 0;
		List<String>lines=Files.lines(Paths.get(INVENTORY_FILE)).collect(Collectors.toList());
		for(String line : lines){
		    if(linesRead.contains(line)){
			System.err.println("Warning: Ignoring duplicate line: " + line);
			continue;
		    }
		    linesRead.add(line);
		    //Mastering åäö;Average Swede;762.00;15
		    String title = line.split(";")[0];
		    String author= line.split(";")[1];
		    String price = line.split(";")[2];
		    String num   = line.split(";")[3];
		    bookId++; // Id starts at 1;
		    try{
			NumberFormat format = NumberFormat.getInstance(Locale.US);
			Number number = format.parse(price);
			double p = number.doubleValue();
			Book b = new Book(title, author, new BigDecimal(p));
			StockEntry se = new StockEntry(b, Integer.parseInt(num));
			books.put(new Integer(bookId), se);
		    }catch(NumberFormatException|java.text.ParseException|ArrayIndexOutOfBoundsException  e ){
			System.err.println("Warning: could not parse INVENTORY_FILE: " + e.getMessage());
			System.err.println("Bad line was: "+line);
		    }
		}
	    }catch(IOException e){
		System.err.println("Error reading inventory file: " + e.getMessage());
	    }
	    return books;
	}
    }
    private Map<Integer, StockEntry> books;

    /**
     * Creates a new FileStock (which reads the Books from the
     * invetory upon creation).
     */
    public FileStock(){
	this.books = new BookFileReader().readAllBooks();
    }
    
    /**
     * Returns an array of element type Book matching the 
     * search string parameter. Or all Books, if no search
     * string is given (see comment below).
     *
     * The search is case insensitive and is done using
     * startsWith() so a search for the string "r" would
     * return all books whose title starts with an "r" or "R"
     * and all books whose author starts with the same.
     *
     * @param searchString The String to search for, or null
     * signifying "all books".
     * @return An array of element type Book with the books matching
     * the search string on titles or authors.
     * Since the original interface I was supposed to implement here
     * didn't provide one method for "search" and another for "list all",
     * I interpreted the task as to use the "list" method for both tasks.
     * However, I think it would have beed easier and cleaner to provide
     * two separate methods for these two separate tasks.
     * I'm using a search string of null as signifying "list all",
     * and anything else as "search".
     *
     * TODO: Add corresponding methods (as described above) to the
     * BookList interface.
     */
    public Book[] list(String searchString){
	List<Book> result = new ArrayList<>();
	if(searchString == null){
	    for(StockEntry se : books.values()){
		result.add(se.book());
	    }
	    return result.toArray(new Book[0]);
	}
	for(StockEntry se : books.values()){
	    if(se.book().author().toLowerCase().startsWith(searchString.toLowerCase())||
	       se.book().title().toLowerCase().startsWith(searchString.toLowerCase())){
		result.add(se.book());
	    }
	}
	// The interface BookList required a return type using an Array,
	// so, let's convert the list to an array.
	return result.toArray(new Book[0]);
    }
    @Override
    public Book getBookByID(int id)throws NoSuchBookException{
	if(!books.containsKey(new Integer(id))){
	    throw new NoSuchBookException("Can't find book with ID " + id);
	}
	return books.get(id).book();
	
    }
    @Override
    public int getID(Book b)throws NoSuchBookException{
	for(Integer id : books.keySet()){
	    if(books.get(id).book().equals(b)){
		return id.intValue();
	    }
	}
	throw new NoSuchBookException("Couldn't find " + b + " in the stock.");
    }

    
    /* I interpreted the add method from the specification of the
     * interface BookList such that it only adds new book titles.
     * Therefore, it doesn't update the quantity for existing books.
     *
     * In a future version, it might be a good idea to have an 
     * additional method, "updateQuantity" for the case when
     * you get more copies of a book out of stock.
     *
     * Also, it currently always returns true (since I don't know
     * what it would mean to return false...).
     * 
     * TODO: Change the interface to declare that this
     * method throws an Exception if the book can't be
     * added.
     */
    public boolean add(Book book, int quantity){
	StockEntry se = new StockEntry(book, quantity);
	books.put(books.size()+1, se);
	new BookFileWriter().writeBooks(books);
	return true;
    }
    
    private boolean stockContains(Book b){
	if(b==null){
	    return false;
	}
	for(StockEntry se : books.values()){
	    if(b.equals(se.book())){
		return true;
	    }
	}
	return false;
    }
    public StockEntry getStockEntry(Book b){
	for(StockEntry se : books.values()){
	    if(se.book().equals(b)){
		return se;
	    }
	}
	return null; // Perhaps cleaner to throw an exception, but...
    }
    // This is how I guestimate that you want this function to work:
    // buy(b1, b2, b3) ->
    // create an array of three ints (same as number of books to buy),
    // return the result of trying to but b1 in the array [0],
    // the result of trying to buy b2 in the array[1] etc...
    // If possible to buy, also decrease the quantity for this book
    // in the stock.
    public int[] buy(Book... books){
	final int OK             = 0;
	final int NOT_IN_STOCK   = 1;
	final int DOES_NOT_EXIST = 2;
	int[] statuses = new int[books.length];
	int index=0;
	/*
	  The books HashMap has the IDs as keys, and StockEntry as values
	 */
	for(Book book: books){
	    if(stockContains(book)){
		StockEntry se = getStockEntry(book);
		// Book exists, so check qty
		if(se.numberInStock()!=0){
		    statuses[index] = OK;
		    // decrease qty in stock
		    se.decreaseStock();
		    // This would be a good place to call
		    // some other module, like PlaceOrder/Payment or so.
		}else{
		    statuses[index] = NOT_IN_STOCK;
		}
	    }else{
		// Unclear how someone tried to buy a non-existing
		// book, but if they do, this is what happens ;-)
		statuses[index] = DOES_NOT_EXIST;
	    }	    
	    index++;	    
	}
	return statuses;
    }
    @Override
    public String toString(){
	return books.toString();
    }
}
