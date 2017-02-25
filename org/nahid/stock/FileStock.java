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
    public FileStock(){
	this.books = new BookFileReader().readAllBooks();
    }
    
    /* Since the original interface I was supposed to implement here
     * didn't provide one method for "search" and another for "list all",
     * I interpreted the task as to use the "list" method for both tasks.
     * However, I think it would have beed easier and cleaner to provide
     * two separate methods for these two separate tasks.
     * I'm using a search string of null as signifying "list all",
     * and anything else as "search".
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
	return result.toArray(new Book[0]);
    }
    /* I interpreted the add method from the specification of the
     * interface BookList such that it only adds new book titles.
     * Therefore, it doesn't update the quantity for existing books.
     *
     * In a future version, it might be a good idea to have an 
     * additional method, "updateQuantity" for the case when
     * you get more copies of a book out of stock.
     */
    public boolean add(Book book, int quantity){
	StockEntry se = new StockEntry(book, quantity);
	books.put(books.size()+1, se);
	new BookFileWriter().writeBooks(books);
	return true;
    }
    public int[] buy(Book... books){
	for(Book b : books){
	    ;//TODO
	}
	return null;
    }
    
    public String toString(){
	return books.toString();
    }
}
