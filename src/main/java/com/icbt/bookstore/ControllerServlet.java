package main.java.com.icbt.bookstore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDAO bookDAO;
	
	public void init() {
		
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		
		bookDAO = new BookDAO(jdbcURL, jdbcUsername, jdbcPassword);
		
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		try {
			
			switch (action) {
			
			case "/new":
				showNewForm(request, response);
				break;
				
			case "/insert":
				insertBook(request, response);
				break;
			
			case "/delete":
				deleteBook(request, response);
				break;
				
			case "/edit":
				showEditForm(request, response);
				break;
				
			case "/update":
				updateBook(request, response);
				break;
				
			default:
				listBook(request, response);
				break;
			}
			
		} catch(Exception ex) {
			throw new ServletException(ex);
		}
		
	}

	private void listBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Book> listBook = bookDAO.listAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher =  request.getRequestDispatcher("BookList.jsp");
		dispatcher.forward(request, response);
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book existingBook = bookDAO.getBook(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
		request.setAttribute("book", existingBook);
		dispatcher.forward(request, response);
		
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String title =  request.getParameter("title");
		String author =  request.getParameter("author");
		float price =  Float.parseFloat(request.getParameter("price"));
		
		Book book = new Book(title, author, price);
		bookDAO.insertBook(book);
		response.sendRedirect("list");
		
		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =  request.getRequestDispatcher("BookForm.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
