# bookstore
What? A Contribe assignment test.

#Build and run:

##Fetching the file with the book inventory:
`javac org/nahid/admin/GetFile.java && java org.nahid.admin.GetFile http://www.contribe.se/bookstoredata/bookstoredata.txt`

##The main "application" simulating a web shop CLI:
`javac org/nahid/*/*.java  && java org.nahid.main.Main`

##Running the tests:
* Testing the Book class
..* `javac org/nahid/*/*.java && java -ea org.nahid.tests.TestBook`
* Testing the GetFile class
..* `javac org/nahid/*/*.java && java -ea org.nahid.tests.TestGetFile`
* Testing the FileStock class (the class implementing BookList)
..* `javac org/nahid/*/*.java && java -ea org.nahid.tests.TestFileStock`
* Testing the buy() method of FileStock
..* `javac org/nahid/*/*.java && java -ea org.nahid.tests.TestBuy`
* Testing the ShoppingCart class
..* `javac org/nahid/*/*.java && java -ea org.nahid.tests.TestShoppingCart`

#Original task requirements and description from employer:

>The task is to in Java implement a bookstore. While browsing the store, the following information about the store shall be available; title, author and price.
>
>Load the initial data from the following link:
>http://www.contribe.se/bookstoredata/bookstoredata.txt,
>The data shall only be downloaded, no upload is intended to work.
>The class that shall handle the books shall implement the following interface:
>public interface BookList {
>   public Book[] list(String searchString);
>      public boolean add(Book book, int quantity);
>         public int[] buy(Book... books);
>	 }
>
>Status from buy shall provide these statuses:
>OK(0),
>NOT_IN_STOCK(1),
>DOES_NOT_EXIST(2)
>
>The class “Book” shall contain the following variables:
>public class Book {
>   private String title;
>   private String author;
>   private BigDecimal price;
>}
>
>
>The user shall be able to list books, either the entire stock or by searching title/author.
>Furthermore it shall be possible for the user to add and remove books from their basket(where the total price will be available).
>
>It shall be possible to expand the bookstore with new items.
>
>Please provide suitable unit tests, where you find it is needed.
>
>Focus on the backend side, most likely there will be another frontend that shall be connected against your backend logic. Please use an IDE i.e. Eclipse or netbeans.
>Third-party software is allowed, if you can motivate the need. If any third-party software is used make sure to bring all the components needed to run the project.
>
>NOTE! It must be possible to interact against the store for instance via a command line. It is not acceptable to only be able to access the store through GET/POST-Requests.
