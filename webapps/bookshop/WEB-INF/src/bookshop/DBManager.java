package bookshop;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DBManager implements AutoCloseable {

    private Connection connection;

    public DBManager() throws SQLException, NamingException {
        connect();
    }

    private void connect() throws SQLException, NamingException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/BookShop");
        connection = ds.getConnection();
    }

    /**
     * Close the connection to the database if it is still open.
     *
     */
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }

    /**
     * Return the number of units in stock of the given book.
     *
     * @param book The book object.
     * @return The number of units in stock, or 0 if the book does not
     *         exist in the database.
     * @throws SQLException If somthing fails with the DB.
     */
    public int getStock(Book book) throws SQLException {
        return getStock(book.getIsbn());
    }

    /**
     * Return the number of units in stock of the given book.
     *
     * @param isbn The book identifier in the database.
     * @return The number of units in stock, or 0 if the book does not
     *         exist in the database.
     */
    public int getStock(String isbn) throws SQLException {
        String query = "SELECT * FROM books INNER JOIN inventario ON id_libro = books.id WHERE books.isbn= "+isbn;
        try(Statement stmt = connection.createStatement()){
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                int stock = resultSet.getInt("unidades");
                return stock;
            }
        } catch (SQLException ex) {
            System.out.println (" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println (" VendorError : " + ex.getErrorCode());
            System.out.println (" SQLState : " + ex.getSQLState());

        }
        return 0;
    }

    public Book getBookbyId(int id){
        Book book = null;
        String query = "SELECT * FROM books WHERE id = " + id;
        try(Statement stmt = connection.createStatement()){
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                String title = resultSet.getString("titulo");
                String isbn = resultSet.getString("isbn");
                int year = resultSet.getInt("anio");
                book = new Book();

                book.setId(id);
                book.setTitle(title); 
                book.setIsbn(isbn);
                book.setYear(year);
                
                return book;
            }
        } catch (SQLException ex) {
            System.out.println (" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println (" VendorError : " + ex.getErrorCode());
            System.out.println (" SQLState : " + ex.getSQLState());

        }
        return null;
    }
                      
    /**
     * Search book by ISBN.
     *
     * @param isbn The ISBN of the book.
     * @return The Book object, or null if not found.
     * @throws SQLException If somthing fails with the DB.
     */
    public Book searchBook(String isbn) throws SQLException {
        Book book = null;
        String query = "SELECT * FROM books WHERE isbn = '" + isbn + "'";
        try(Statement stmt = connection.createStatement()){
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String title = resultSet.getString("titulo");
                int year = resultSet.getInt("anio");
                book = new Book();

                book.setId(id);
                book.setTitle(title); 
                book.setIsbn(isbn);
                book.setYear(year);
                
                return book;
            }
        } catch (SQLException ex) {
            System.out.println (" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println (" VendorError : " + ex.getErrorCode());
            System.out.println (" SQLState : " + ex.getSQLState());

        }
        return null;
    }

    /**
     * Sell a book.
     *
     * @param book The book.
     * @param units Number of units that are being sold.
     * @return True if the operation succeeds, or false otherwise
     *         (e.g. when the stock of the book is not big enough).
     * @throws SQLException If somthing fails with the DB.
     */
    public boolean sellBook(Book book, int units) throws SQLException {
        return sellBook(book.getId(), units);
    }

    /**
     * Sell a book.
     *
     * @param book The book's identifier.
     * @param units Number of units that are being sold.
     * @return True if the operation succeeds, or false otherwise
     *         (e.g. when the stock of the book is not big enough).
     * @throws SQLException If something fails with the DB.
     */
    public boolean sellBook(int book, int units) throws SQLException {
        String queryVentas = "UPDATE ventas SET unidades_vendidas = unidades_vendidas +" + units + " WHERE id_libro = " + book;
        String queryUnicades = "UPDATE inventario SET unidades = unidades -" + units + " WHERE id_libro="+book+" AND unidades_vendidas>="+units; //la parte de unidades vendidas no funciona
        try (Statement stmt = connection.createStatement()) {
            int queryVentas1 = stmt.executeUpdate(queryVentas);
            int queryVentas2 = stmt.executeUpdate(queryUnicades);
            if( (queryVentas1!=0) && (queryVentas2!=0) ){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println (" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println (" VendorError : " + ex.getErrorCode());
            System.out.println (" SQLState : " + ex.getSQLState());
        }

        return false;
    }
        

    /**
     * Return a list with all the books in the database.
     *
     * @return List with all the books.
     * @throws SQLException If something fails with the DB.
     */
    public List<Book> listBooks() throws SQLException {
        String query = "SELECT * FROM books";

        try(Statement stmt = connection.createStatement()){
            ResultSet resultSet = stmt.executeQuery(query);
            List<Book> books = new ArrayList<Book>();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String title = resultSet.getString("titulo");
                String isbn = resultSet.getString("isbn");
                int year = resultSet.getInt("anio");
                Book book = new Book();

                book.setId(id);
                book.setTitle(title); 
                book.setIsbn(isbn);
                book.setYear(year);
                books.add(book);
            }
            return books;
        } catch (SQLException ex) {
            System.out.println (" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println (" VendorError : " + ex.getErrorCode());
            System.out.println (" SQLState : " + ex.getSQLState());

        }
        return new ArrayList<Book>();
    }
}
