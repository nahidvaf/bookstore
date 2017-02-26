package org.nahid.domain;

public interface BookList {
    public Book[] list(String searchString);
    public boolean add(Book book, int quantity);
    public int[] buy(Book... books);

    /* Had to add this - how else could I keep track of books? */
    public int getID(Book b) throws org.nahid.stock.NoSuchBookException;
    /* I also needed this one, for instance to add a specific book to the cart,
     * so, I need to get the specific book by the ID from the stock.
     */
    public Book getBookByID(int id) throws org.nahid.stock.NoSuchBookException;
}
