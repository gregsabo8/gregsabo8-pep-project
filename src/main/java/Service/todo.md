You will need to design and create your own Service classes from scratch.
You should refer to prior mini-project lab examples and course material for guidance.

package Application.Service;

import Application.Model.Author;
import Application.DAO.AuthorDAO;

import java.util.List;

/**
 * The purpose of a Service class is to contain "business logic" that sits between the web layer (controller) and
 * persistence layer (DAO). That means that the Service class performs tasks that aren't done through the web or
 * SQL: programming tasks like checking that the input is valid, conducting additional security checks, or saving the
 * actions undertaken by the API to a logging file.
 *
 * It's perfectly normal to have Service methods that only contain a single line that calls a DAO method. An
 * application that follows best practices will often have unnecessary code, but this makes the code more
 * readable and maintainable in the long run!
 */
public class AuthorService {
    private AuthorDAO authorDAO;
    /**
     * no-args constructor for creating a new AuthorService with a new AuthorDAO.
     * There is no need to change this constructor.
     */
    public AuthorService(){
        authorDAO = new AuthorDAO();
    }
    /**
     * Constructor for a AuthorService when a AuthorDAO is provided.
     * This is used for when a mock AuthorDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of AuthorService independently of AuthorDAO.
     * There is no need to modify this constructor.
     * @param authorDAO
     */
    public AuthorService(AuthorDAO authorDAO){
        this.authorDAO = authorDAO;
    }
    /**
     * TODO: Use the AuthorDAO to retrieve all authors.
     *
     * @return all authors
     */
    public List<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }
    /**
     * TODO: Use the AuthorDAO to persist an author. The given Author will not have an id provided.
     *
     * @param author an author object.
     * @return The persisted author if the persistence is successful.
     */
    public Author addAuthor(Author author) {
        return authorDAO.insertAuthor(author);
    }
}


package Application.Service;

import Application.DAO.BookDAO;
import Application.Model.Book;

import java.util.List;

/**
 * The purpose of a Service class is to contain "business logic" that sits between the web layer (controller) and
 * persistence layer (DAO). That means that the Service class performs tasks that aren't done through the web or
 * SQL: programming tasks like checking that the input is valid, conducting additional security checks, or saving the
 * actions undertaken by the API to a logging file.
 *
 * It's perfectly normal to have Service methods that only contain a single line that calls a DAO method. An
 * application that follows best practices will often have unnecessary code, but this makes the code more
 * readable and maintainable in the long run!
 */
public class BookService {
    public BookDAO bookDAO;

    /**
     * No-args constructor for bookService which creates a BookDAO.
     * There is no need to change this constructor.
     */
    public BookService(){
        bookDAO = new BookDAO();
    }
    /**
     * Constructor for a BookService when a BookDAO is provided.
     * This is used for when a mock BookDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of BookService independently of BookDAO.
     * There is no need to modify this constructor.
     * @param bookDAO
     */
    public BookService(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }
    /**
     * TODO: Use the bookDAO to retrieve all books.
     * @return all books.
     */
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }
    /**
     * TODO: Use the bookDAO to persist a book to the database.
     * An ISBN will be provided in Book. Method should check if the book ISBN already exists before it attempts to
     * persist it.
     * @param book a book object.
     * @return book if it was successfully persisted, null if it was not successfully persisted (eg if the book primary
     * key was already in use.)
     */
    public Book addBook(Book book) {
       if(bookDAO.getBookByIsbn(book.getIsbn()) != null){
        return null;
       }
        return bookDAO.insertBook(book);
        
    }
    /**
     * TODO: Use the bookDAO to retrieve a list of all books that have a bookCount above 0.
     * @return all available books (bookCount over zero)
     */
    public List<Book> getAllAvailableBooks() {
        return bookDAO.getBooksWithBookCountOverZero();
        
        
    }

}


package Application.Service;

import Application.DAO.BookDAO;
import Application.Model.Book;

import java.util.List;

/**
 * The purpose of a Service class is to contain "business logic" that sits between the web layer (controller) and
 * persistence layer (DAO). That means that the Service class performs tasks that aren't done through the web or
 * SQL: programming tasks like checking that the input is valid, conducting additional security checks, or saving the
 * actions undertaken by the API to a logging file.
 *
 * It's perfectly normal to have Service methods that only contain a single line that calls a DAO method. An
 * application that follows best practices will often have unnecessary code, but this makes the code more
 * readable and maintainable in the long run!
 */
public class BookService {
    public BookDAO bookDAO;

    /**
     * No-args constructor for bookService which creates a BookDAO.
     * There is no need to change this constructor.
     */
    public BookService(){
        bookDAO = new BookDAO();
    }
    /**
     * Constructor for a BookService when a BookDAO is provided.
     * This is used for when a mock BookDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of BookService independently of BookDAO.
     * There is no need to modify this constructor.
     * @param bookDAO
     */
    public BookService(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }
    /**
     * TODO: Use the bookDAO to retrieve all books.
     * @return all books.
     */
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }
    /**
     * TODO: Use the bookDAO to persist a book to the database.
     * An ISBN will be provided in Book. Method should check if the book ISBN already exists before it attempts to
     * persist it.
     * @param book a book object.
     * @return book if it was successfully persisted, null if it was not successfully persisted (eg if the book primary
     * key was already in use.)
     */
    public Book addBook(Book book) {
       if(bookDAO.getBookByIsbn(book.getIsbn()) != null){
        return null;
       }
        return bookDAO.insertBook(book);
        
    }
    /**
     * TODO: Use the bookDAO to retrieve a list of all books that have a bookCount above 0.
     * @return all available books (bookCount over zero)
     */
    public List<Book> getAllAvailableBooks() {
        return bookDAO.getBooksWithBookCountOverZero();
        
        
    }

}

