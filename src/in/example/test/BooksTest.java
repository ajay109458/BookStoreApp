package in.example.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.example.dao.BookDAO;
import in.example.entity.Book;

public class BooksTest {

	@Test
	void checkGetBooks() {

		BookDAO bookDAO = BookDAO.getInstance();
		
		Book book = createBook();
		bookDAO.addBook(book);
		
		List<Book> books = bookDAO.getBooks();

		System.out.println(books);

		int currentLength = books.size();

		assertTrue(currentLength > 0);
	}

	private Book createBook() {
		Book book = new Book();

		int ranId = (int) Math.random() * 100;
		book.setAuthor("Author" + ranId);
		book.setBarcode("Barcode" + ranId);
		book.setName("Book" + ranId);
		book.setQuantitiy(ranId);
		book.setPrice(ranId * 10);

		return book;
	}

}
