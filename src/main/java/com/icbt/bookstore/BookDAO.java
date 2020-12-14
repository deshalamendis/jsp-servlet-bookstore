package main.java.com.icbt.bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This DAO class provides CRUD database operations for the table book in the database 
 * @author macbookpro
 *
 */
public class BookDAO {
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public BookDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	/**
	 * JDBC Connection Start
	 * @throws SQLException
	 */
	protected void connect() throws SQLException{
		if(jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}catch(Exception ex) {
				throw new SQLException(ex);
			}
			jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	/**
	 * JDBC Connection close
	 * @throws SQLException
	 */
	protected void disconnect() throws SQLException {
		if(jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	/**
	 * Book insert 
	 * @param book
	 * @return
	 * @throws SQLException
	 */
	public boolean insertBook(Book book) throws SQLException {
		
		String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, book.getTitle());
		statement.setString(2, book.getAuthor());		
		statement.setFloat(3, book.getPrice());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowInserted;
		
	}
	
	/**
	 * Query all books
	 * @return
	 * @throws SQLException
	 */
	public List<Book> listAllBooks() throws SQLException {
		
		List<Book> listBook = new ArrayList<>();
		
		String sql = "SELECT * FROM book";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("book_id");
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			float price =  resultSet.getFloat("price");
			
			Book book = new Book(id, title, author, price);
			listBook.add(book);
		} 
		
		resultSet.close();
		statement.close();
		
		return listBook;
		
	}
	
	/**
	 * book update
	 * @param book
	 * @return
	 * @throws SQLException
	 */
	public boolean updateBook(Book book) throws SQLException {
		
		String sql = "UPDATE book SET title = ?, author = ?, price = ?";
		sql += " WHERE book_id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, book.getTitle());
		statement.setString(2, book.getAuthor());		
		statement.setFloat(3, book.getPrice());
		statement.setInt(4, book.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		
		return rowUpdated;
	}
	
	/**
	 * Delete book
	 * @param book
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteBook(Book book) throws SQLException {
		
		String sql = "DELETE FROM book where book_id = ?";
		
		connect();
		
		PreparedStatement statement =  jdbcConnection.prepareStatement(sql);
		statement.setInt(1, book.getId());
		
		boolean rowDeleted =  statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		
		return rowDeleted;
	}
	
	/**
	 * Get book
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Book getBook(int id) throws SQLException {
		
		Book book = null;
		String sql = "SELECT * FROM book WHERE book_id = ?";
		
		connect();
		
		PreparedStatement statement =  jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			float price =  resultSet.getFloat("price");
			
			book = new Book(id, title, author, price);			
		}
		
		resultSet.close();
		statement.close();
		
		return book;
	}
	
	
	
	
}
