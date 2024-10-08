You will need to design and create your own DAO classes from scratch. 
You should refer to prior mini-project lab examples and course material for guidance.

Please refrain from using a 'try-with-resources' block when connecting to your database. 
The ConnectionUtil provided uses a singleton, and using a try-with-resources will cause issues in the tests.

package Application.DAO;

import Application.Model.Author;
import Application.Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A DAO is a class that mediates the transformation of data between the format of objects in Java to rows in a
 * database. The methods here are mostly filled out, you will just need to add a SQL statement.
 *
 * We may assume that the database has already created a table named 'author'.
 * It contains similar values as the Author class:
 * id, which is of type int and is a primary key,
 * name, which is of type varchar(255).
 */
public class AuthorDAO {

    /**
     * TODO: retrieve all authors from the Author table.
     * You only need to change the sql String.
     * @return all Authors.
     */
    public List<Author> getAllAuthors(){
        Connection connection = ConnectionUtil.getConnection();
        List<Author> authors = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM author";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Author author = new Author(rs.getInt("id"), rs.getString("name"));
                authors.add(author);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return authors;
    }

    /**
     * TODO: insert an author into the Author table.
     * The author_id should be automatically generated by the sql database if it is not provided because it was
     * set to auto_increment. Therefore, you only need to insert a record with a single column (name).
     * You only need to change the sql String and leverage PreparedStatements' setString methods.
     */
    public Author insertAuthor(Author author){
        Connection connection = ConnectionUtil.getConnection();
        try {
//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            String sql = "INSERT INTO author(id, name) VALUES (?,?)" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString method here.
            preparedStatement.setInt(1, author.getId());
            preparedStatement.setString(2,author.getName());
            
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_author_id = (int) pkeyResultSet.getLong(1);
                return new Author(generated_author_id, author.getName());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

package Application.DAO;

import Application.Model.Author;
import Application.Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A DAO is a class that mediates the transformation of data between the format of objects in Java to rows in a
 * database. The methods here are mostly filled out, you will just need to add a SQL statement.
 *
 * We may assume that the database has already created a table named 'author'.
 * It contains similar values as the Author class:
 * id, which is of type int and is a primary key,
 * name, which is of type varchar(255).
 */
public class AuthorDAO {

    /**
     * TODO: retrieve all authors from the Author table.
     * You only need to change the sql String.
     * @return all Authors.
     */
    public List<Author> getAllAuthors(){
        Connection connection = ConnectionUtil.getConnection();
        List<Author> authors = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM author";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Author author = new Author(rs.getInt("id"), rs.getString("name"));
                authors.add(author);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return authors;
    }

    /**
     * TODO: insert an author into the Author table.
     * The author_id should be automatically generated by the sql database if it is not provided because it was
     * set to auto_increment. Therefore, you only need to insert a record with a single column (name).
     * You only need to change the sql String and leverage PreparedStatements' setString methods.
     */
    public Author insertAuthor(Author author){
        Connection connection = ConnectionUtil.getConnection();
        try {
//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            String sql = "INSERT INTO author(id, name) VALUES (?,?)" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString method here.
            preparedStatement.setInt(1, author.getId());
            preparedStatement.setString(2,author.getName());
            
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_author_id = (int) pkeyResultSet.getLong(1);
                return new Author(generated_author_id, author.getName());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

package Application.DAO;

import Application.Util.ConnectionUtil;
import Application.Model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A DAO is a class that mediates the transformation of data between the format of objects in Java to rows in a
 * database. The methods here are mostly filled out, you will just need to add a SQL statement.
 *
 * We may assume that the database has already created a table named 'book'.
 * It contains similar values as the Author class:
 * isbn, which is of type int and is a primary key,
 * author_id, which is of type int, and is a foreign key associated with the column 'id' of 'author',
 * title, which is of type varchar(255),
 * copies_available, which is of type int.
 */
public class BookDAO {
    /**
     * TODO: retrieve all books from the Book table.
     * You only need to change the sql String.
     * @return all Books.
     */
    public List<Book> getAllBooks(){
        Connection connection = ConnectionUtil.getConnection();
        List<Book> books = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM book";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Book book = new Book(rs.getInt("isbn"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getInt("copies_available"));
                books.add(book);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return books;
    }

    /**
     * TODO: retrieve a book from the Book table, identified by its isbn.
     * You only need to change the sql String and leverage PreparedStatement's setString and setInt methods.
     * @return a book identified by isbn.
     */
    public Book getBookByIsbn(int isbn){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM book WHERE isbn = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setInt method here.
            preparedStatement.setInt(1, isbn);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Book book = new Book(rs.getInt("isbn"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getInt("copies_available"));
                return book;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * TODO: insert a book into the Book table.
     * Unlike some of the other insert problems, the primary key here will be provided by the client as part of the
     * Book object. Given the specific nature of an ISBN as both a numerical organization of books outside of this
     * database, and as a primary key, it would make sense for the client to submit an ISBN when submitting a book.
     * You only need to change the sql String and leverage PreparedStatement's setString and setInt methods.
     */
    public Book insertBook(Book book){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "INSERT INTO book (isbn, author_id,title,copies_available) VALUES (?,?,?,?)" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1,book.getIsbn());
            preparedStatement.setInt(2, book.getAuthor_id());
            preparedStatement.setString(3, book.getTitle());
            preparedStatement.setInt(4, book.getCopies_available());

            preparedStatement.executeUpdate();
            return book;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * TODO: retrieve all books from the Book table with copies_available over zero.
     * You only need to change the sql String with a query that utilizes a WHERE clause.
     * @returnall books with book count > 0.
     */
    public List<Book> getBooksWithBookCountOverZero(){
        Connection connection = ConnectionUtil.getConnection();
        List<Book> books = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM book WHERE copies_available > 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Book book = new Book(rs.getInt("isbn"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getInt("copies_available"));
                books.add(book);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return books;
    }
}

    /**
     * TODO: Update the flight identified by the flight id to the values contained in the flight object.
     *
     * You only need to change the sql String and set preparedStatement parameters.
     *
     * Remember that the format of an update PreparedStatement written as a Java String looks something like this:
     * String sql = "update TableName set ColumnName1=?, ColumnName2=? where ColumnName3 = ?;";
     * The question marks will be filled in by the preparedStatement setString, setInt, etc methods. they follow
     * this format, where the first argument identifies the question mark to be filled (left to right, starting
     * from one) and the second argument identifies the value to be used:
     * preparedStatement.setString(1,string1);
     * preparedStatement.setString(2,string2);
     * preparedStatement.setInt(3,int1);
     *
     * @param id a flight ID.
     * @param flight a flight object. the flight object does not contain a flight ID.
     */
    public void updateFlight(int id, Flight flight){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "UPDATE flight SET departure_city = ?,arrival_city=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write PreparedStatement setString and setInt methods here.
            preparedStatement.setString(1, flight.departure_city);
            preparedStatement.setString(2, flight.arrival_city);

            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    service
       /**
     * TODO: Use the FlightDAO to update an existing flight from the database.
     * You should first check that the flight ID already exists. To do this, you could use an if statement that checks
     * if flightDAO.getFlightById returns null for the flight's ID, as this would indicate that the flight id does not
     * exist.
     *
     * @param flight_id the ID of the flight to be modified.
     * @param flight an object containing all data that should replace the values contained by the existing flight_id.
     *         the flight object does not contain a flight ID.
     * @return the newly updated flight if the update operation was successful. Return null if the update operation was
     *         unsuccessful. We do this to inform our application about successful/unsuccessful operations. (eg, the
     *         user should have some insight if they attempted to edit a nonexistent flight.)
     */
    public Flight updateFlight(int flight_id, Flight flight){
        if(flightDAO.getFlightById(flight_id)==null){
            return null;
        }
        return flightDAO.getFlightById(flight_id );
    }