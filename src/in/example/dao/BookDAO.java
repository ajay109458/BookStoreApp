package in.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.example.entity.Book;
import in.example.hibernate.HibernateUtils;

public class BookDAO {

	private static BookDAO instance;
	
	private BookDAO() {

	}
	
	public static BookDAO getInstance() {
		
		if (instance == null) {
			instance = new BookDAO();
		}
		
		return instance;
	}

	public void addBook(Book book) {
		Transaction transaction = null;

		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(book);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
				e.printStackTrace();
			}
		}

	}

	public List<Book> getBooks() {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			return session.createQuery("from Book", Book.class).list();
		}
	}

}
