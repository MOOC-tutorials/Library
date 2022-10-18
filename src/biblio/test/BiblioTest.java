package biblio.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import biblio.model.Book;
import biblio.model.BookField;
import biblio.model.BookRepository;
import biblio.model.Client;
import biblio.model.ClientRepository;

/*
 * Class providing utility methods for initializing resources or that are needed for different phases of the tests
 */
public class BiblioTest extends TestCase {

	protected static BookRepository bookRepository;
	protected static Iterator<Book> bookIterator;
	
	protected static ClientRepository clientRepository;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		loadRepositories();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	
	
	
	public Client getFirstClient() {
		return clientRepository.getClients().iterator().next();
	}
	
	public Book getFirstBook() {
		return bookRepository.getBooks().iterator().next();
	}
	
	/*
	 * return a book that has at least one available copy
	 */
	public Book nextAvailableBook() {
		return nextAvailableBook(1);
	}
	
	/*
	 * Return a book which have at least certain number of available copies specified as a parameter
	 */
	public Book nextAvailableBook(int numCopies) {
		while(bookIterator.hasNext()) {
			Book availableBook = bookIterator.next();
			if ( availableBook.numAvailableCopies()>=numCopies )
				return availableBook;
		}
		fail("not enough available books for testing");
		return null;
	}
	
	/*
	 * load the clientRepository and the bookRepository.
	 * In this way they will be accessible for new tests
	 */
	private static void loadRepositories() {
		//System.out.println("Checking repositories ... ");
		if(clientRepository == null) {
			//System.out.println("Creating new Client Repository for testing ... ");
			clientRepository = new ClientRepository();
		}
		assertTrue("No clients in the repository for testing", clientRepository.getClients().size()>0);
		//System.out.println("Client repository loaded: "+clientRepository.getClients().size()+" clients found");
		if(bookRepository == null) {
			//System.out.println("Creating new Book Repository for testing ... ");
			bookRepository = new BookRepository();
			bookIterator = bookRepository.getBooks().iterator();
		}
		assertTrue("No Books in the repository for testing", bookRepository.getBooks().size()>0);
		//System.out.println("Book repository loaded: "+bookRepository.getBooks().size()+" books found");
	}
	
	/*
	 * Borrow all the copies of a book
	 * This escenario is useful for different tests (i.e. when testing reservation is needed)
	 */
	public static void borrowAllCopies(Book book) {
		borrowAllCopies(book, clientRepository.getClients().iterator());
	}
	
	/*
	 * borrow all the copies of the book to the clients present in the iterator
	 */
	public static void borrowAllCopies(Book book, Iterator<Client> clientIterator) {
		while(book.numAvailableCopies()>0) {
			Client client = getClient(clientIterator);
			book.borrow(client, new Date(), 1);
		}
	}
	
	/*
	 * return a client from an iterator
	 * instead or throwing a weird exception in case that the iterator is empty, this method will explicitly show the right warning
	 */
	public static Client getClient(Iterator<Client> clientIterator) {
		assertTrue("Not enough clients for testing", clientIterator.hasNext());
		return clientIterator.next();
	}

	/*
	 * Method that search in the repository of books according to a field code and its value
	 */
	public static Collection<Book> applyFilter(int fieldCode, String value) {
		Collection<Book> books = bookRepository.getBooks();
		Collection<Book> result = new ArrayList<Book>();
		
		for (Book book : books) {
			Object fieldValue = book.getFieldValue(fieldCode);
			Class fieldType = BookField.getFieldType(fieldCode);
			if(fieldValue == null)
				continue;
			if (int.class.equals(fieldType)) {
				if (((Integer) fieldValue).equals(new Integer(value)))
					result.add(book);
			} else {
				String received = value.toLowerCase();
				String searched = fieldValue.toString().toLowerCase();
				if (searched.indexOf(received) != -1)
					result.add(book);
			} 
		}
		return result;
	}
	
	/*
	 * Utility method for comparing the results of two searches
	 * This is a simple implementation that only compares the dimension of the results
	 */
	public static boolean compareSearch(String searchName, Collection<Book> resultA, Collection<Book> resultB) {
		if(resultA == null || resultB == null) {
			fail("Attempt of comparing null values");
			return false;
		} 
		return verifySearchExtension(searchName, resultA.size(), resultB);
	}
	
	public static boolean verifySearchExtension(String searchName, int expectedDimension, Collection searchResult) {
		assertNotNull(searchResult);
		System.out.print("search name: " + searchName + " , expected results:" + expectedDimension + " , implemented results: " + searchResult.size() + " -----> ");
		if(searchResult.size() != expectedDimension) {
			System.out.println("FAILURE");
			fail("The search "+searchName+" is not working."
					+ "The test method found "+searchResult.size()+" results, "
					+ "and the expected number of results was "+expectedDimension);
			return false;
		} else {
			System.out.println("SUCCESS");
		}
			return true;
	}
}
