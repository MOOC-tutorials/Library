package biblio.test;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.junit.Test;

import biblio.model.Book;
import biblio.model.BookField;
import biblio.model.Client;
import biblio.util.DateUtility;

public class Phase2Test extends BiblioTest {

	@Test
	public void testBorrowTwice() {
		Client client = getFirstClient();
		Book book = nextAvailableBook();
		assertTrue("the borrowing must success", book.borrow(client, new Date(), 1));
		assertFalse("the borrowing must fail, since the client has already a copy of the book", book.borrow(client, new Date(), 1));
	}
	
	@Test
	public void testBorrowAfterNoCopiesAvailable() {
		Book book = nextAvailableBook();
		Iterator<Client> clientIterator = clientRepository.getClients().iterator();
		BiblioTest.borrowAllCopies(book, clientIterator);
		Client client = BiblioTest.getClient(clientIterator);
		assertFalse("The book is not available anymore", book.borrow(BiblioTest.getClient(clientIterator), new Date(), 1));
	}


	@Test
	public void testReturnBook() {
		Book book = this.nextAvailableBook();
		Client client = getFirstClient();
		client.setFine(0);
		assertFalse("The client has not borrowed this book, so she cannot returned it", book.returnBook(client, new Date()));
		
		Date borrowingDate = new Date();
		assertTrue("The client can borrow a copy of the book", book.borrow(client, borrowingDate, 1));
		Date returnDate = borrowingDate;
		assertTrue(book.returnBook(client, returnDate));
		assertEquals(0.0, client.getFine());
		
		assertTrue("The client can borrow a copy of the book", book.borrow(client, borrowingDate, 1));
		returnDate = DateUtility.addToDate(borrowingDate, 1);
		assertTrue(book.returnBook(client, returnDate));
		assertEquals(0.0, client.getFine());
		
		assertTrue("The client can borrow a copy of the book", book.borrow(client, borrowingDate, 1));
		returnDate = DateUtility.addToDate(borrowingDate, 2);
		assertTrue(book.returnBook(client, returnDate));
		assertEquals(0.20, client.getFine());
		
		client.setFine(0);
		assertTrue("The client can borrow a copy of the book", book.borrow(client, borrowingDate, 1));
		returnDate = DateUtility.addToDate(borrowingDate, 6);
		assertTrue(book.returnBook(client, returnDate));
		assertEquals(1.0, client.getFine());
	}

	@Test
	public void testBorrowedBy() {
		Book book = nextAvailableBook();
		Client client = getFirstClient();
		assertFalse(book.borrowedBy(client));
		book.borrow(client, new Date(), 1);
		assertTrue(book.borrowedBy(client));
		assertTrue(book.numBorrowedCopies() == 1 && book.borrowedBy().iterator().next().equals(client));
	}
	

	@Test
	public void testSearchByFields() {
		
		Collection<Book> implementedResult;
		//Collection<Book> expectedResult;
		
		implementedResult = bookRepository.search(BookField.AUTHORS, "Iliffe");
		//expectedResult = applyFilter(BookField.AUTHORS, "Iliffe");
		//compareSearch("Search by Authors: \"Iliffe\"", expectedResult, implementedResult);
		verifySearchExtension("Search by Authors: \"Iliffe\"", 1, implementedResult);
		
		implementedResult = bookRepository.search(BookField.TITLE, "Basic");
		//expectedResult = applyFilter(BookField.TITLE, "Basic");
		//compareSearch("Search by Title: \"Basic\"", expectedResult, implementedResult);
		verifySearchExtension("Search by Title: \"Basic\"", 20, implementedResult);
		
		implementedResult = bookRepository.search(BookField.PUBLICATION_DATE, "1968");
		//expectedResult = applyFilter(BookField.PUBLICATION_DATE, "1968");
		//compareSearch("Search by Publication Date: \"1968\"", expectedResult, implementedResult);
		verifySearchExtension("Search by Publication Date: \"1968\"", 95, implementedResult);
		
		implementedResult = bookRepository.search(BookField.SERIE, "Computer");
		//expectedResult = applyFilter(BookField.SERIE, "Computer");
		//compareSearch("Search by Serie: \"Computer\"", expectedResult, implementedResult);
		verifySearchExtension("Search by Serie: \"Computer\"", 537, implementedResult);
		
		implementedResult = bookRepository.search(BookField.NUMBER_PAGES, "100");
		//expectedResult = applyFilter(BookField.NUMBER_PAGES, "100");
		//compareSearch("Search by Number of pages: \"100\"", expectedResult, implementedResult);
		verifySearchExtension("Search by Number of pages: \"100\"", 5, implementedResult);
		
		implementedResult = bookRepository.search(BookField.REFERENCE, "010");
		//expectedResult = applyFilter(BookField.REFERENCE, "010");
		//compareSearch("Search by Reference: \"010\"", expectedResult, implementedResult);
		verifySearchExtension("Search by Reference: \"010\"", 59, implementedResult);
		
		implementedResult = bookRepository.search(BookField.EDITOR, "Mac");
		//expectedResult = applyFilter(BookField.EDITOR, "Mac");
		//compareSearch("Search by Editor: \"Mac\"", expectedResult, implementedResult);
		verifySearchExtension("Search by Editor: \"Mac\"", 53, implementedResult);
		
		implementedResult = bookRepository.search(BookField.NUM_AVAILABLE_COPIES, "2");
		//expectedResult = applyFilter(BookField.NUM_AVAILABLE_COPIES, "2");
		//compareSearch("Search by Available: \"2\"", expectedResult, implementedResult);
		verifySearchExtension("Search by Available: \"2\"", 341, implementedResult);
		
		implementedResult = bookRepository.search(BookField.NUM_BORROWED_COPIES, "1");
		//expectedResult = applyFilter(BookField.NUM_BORROWED_COPIES, "1");
		//compareSearch("Search by Borrowed: \"1\"", expectedResult, implementedResult);
		verifySearchExtension("Search by Borrowed: \"1\"", 2, implementedResult);
		
		implementedResult = bookRepository.search(BookField.NUM_RESERVED_COPIES, "0");
		//expectedResult = applyFilter(BookField.NUM_RESERVED_COPIES, "0");
		//compareSearch("Search by Reserved: \"0\"", expectedResult, implementedResult);
		verifySearchExtension("Search by Reserved: \"0\"", 5070, implementedResult);
	}
	
	@Test
	public void testWhatHasBorrowed() {
		Client client = getFirstClient();
		Book book = nextAvailableBook();
		book.borrow(client, new Date(), 1);
		Collection<Book> books = bookRepository.hasBorrowedWhat(client);
		assertTrue(books.contains(book));
		book = nextAvailableBook();
		assertFalse(books.contains(book));
		book.borrow(client, new Date(), 1);
		books = bookRepository.hasBorrowedWhat(client);
		assertTrue(books.contains(book));
	}
	

	@Test
	public void testClientEdition() {
		int size = clientRepository.getClients().size();
		Client client = new Client("A dummy code", "A dummy name");
		clientRepository.addClient(client);
		assertTrue(clientRepository.getClients().size() == size + 1);
		assertTrue(clientRepository.getClients().contains(client));
		clientRepository.removeClient(client);
		assertTrue(clientRepository.getClients().size() == size);
		assertFalse(clientRepository.getClients().contains(client));
	}
	
	@Test
	public void testWhoCanBorrowWhenNoReservationExists() {
		Book book = nextAvailableBook();
		
		Collection<Client> resultA, resultB;
		resultA = clientRepository.getClients();
		assertTrue("There are not enough clients for testing", resultA.size() > 0);
		resultB = clientRepository.whoCanBorrow(book);
		assertEquals(resultA.size(), resultB.size());
		borrowAllCopies(book);
		assertTrue(clientRepository.whoCanBorrow(book).size() == 0);

	}
}
