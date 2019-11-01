package in.example.restpackage;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import in.example.dao.BookDAO;
import in.example.entity.Book;

@Path("/books")
public class BooksRESTService {

	private BookDAO bookDAO = BookDAO.getInstance();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks() {

		List<Book> books = bookDAO.getBooks();

		GenericEntity<List<Book>> entity = new GenericEntity<List<Book>>(books) {
		};

		return Response.ok(entity).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBook(Book book) {
		//Book b = book.getValue();
		bookDAO.addBook(book);

		return Response.ok().build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{bookId}")
	public Response buyBook(@PathParam("bookId") int bookId) {
		
		// TODO handle race condition
		Book book = bookDAO.getBookById(bookId);
		
		Book result = null;
		
		int bookQuantity = book.getQuantitiy(); 
		if ( bookQuantity > 0) {
			book.setQuantitiy(bookQuantity - 1);
			result = bookDAO.updateBook(book);
		}
		
		return Response.ok(result).build();
	}

}