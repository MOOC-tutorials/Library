package biblio.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.junit.Test;

import biblio.model.Book;
import biblio.model.BookCopy;
import biblio.model.Client;


public class Phase3Test extends BiblioTest {

	
	@Test
	public void testReserveWhenAvailable() {
		Client client = getFirstClient();
		Book book = nextAvailableBook();
		assertFalse("The book cannot be reserved if it is available", book.reserveFor(client));
	}
	
	@Test
	public void testReserveWhenNotAvailable() {
		Book book = nextAvailableBook();
		Iterator<Client> clientIterator = clientRepository.getClients().iterator();
		BiblioTest.borrowAllCopies(book, clientIterator);
		Client client = BiblioTest.getClient(clientIterator);
		assertTrue("Since the book is not available, a client should be able of reserving it", book.reserveFor(client));
	}
	
	/*
	 * Verify the following situation:
	 * - all the copies of the books have been borrowed
	 * - a reservation has been done
	 * - two copies of the book has been returned (therefore the first is available for the client who made the reservation, and the second for other client)
	 *  In this situation, a reservation attempt must fail, and a borrowing for a client who has not made a previous borrowing, must succeed.
	 */
	//- 
	@Test
	public void testBorrowBookReservedButAvailable() {
		Client firstClient, secondClient;
		Iterator<Client> clientIterator = clientRepository.getClients().iterator();
		Book book = nextAvailableBook(3);  //choose a book with at least three available copies for the test
		BiblioTest.borrowAllCopies(book, clientIterator);
		firstClient = BiblioTest.getClient(clientIterator);
		secondClient = BiblioTest.getClient(clientIterator);
		assertTrue(book.reserveFor(firstClient));
		assertTrue(book.reserveFor(secondClient));
		
		Iterator<BookCopy> iteratorBookCopy = book.getCopies().iterator();
		
		//returning one copy of the book, only one copy is available.
		book.returnBook(iteratorBookCopy.next().getBorrower(), new Date());  
		
		//since there is only one copy available, the only client who can borrow it is the one that made the reservation first
		//the borrowing attempts made by any other client, even if she had a reservation also, must fail
		assertFalse(book.borrow(secondClient, new Date(), 1));
		
		//returning a second copy of the book
		book.returnBook(iteratorBookCopy.next().getBorrower(), new Date());
		
		//returning a third copy of the book
		book.returnBook(iteratorBookCopy.next().getBorrower(), new Date());
		
		//any client can borrow the book, since there are two reservations and 3 copies available
		assertTrue(book.borrow(BiblioTest.getClient(clientIterator), new Date(), 1));
		
		//now only the clients who made the reservation can borrow the book, an attempt for borrowing the book for any other client must fail
		assertFalse(book.borrow(BiblioTest.getClient(clientIterator), new Date(), 1));
		
		//finally, verify that the clients who made the reservation, can in fact borrow the book, 
		//the order in which they borrow the copies is not important here since there are two reservations and two copies available
		assertTrue(book.borrow(secondClient, new Date(), 1));
		assertTrue(book.borrow(firstClient, new Date(), 1));
	}

	@Test
	public void testReservedBy() {
		Book book = nextAvailableBook();
		Iterator<Client> clientIterator = clientRepository.getClients().iterator();
		BiblioTest.borrowAllCopies(book, clientIterator);
		Client client = clientIterator.next();
		assertFalse(book.reservedBy(client));
		book.reserveFor(client);
		assertTrue(book.reservedBy(client));
		assertTrue(book.numReservedCopies() == 1 && book.getReservations().iterator().next().equals(client));
	}
	

	@Test
	public void testWhatHasReserved() {
		Iterator<Client> clientIterator = clientRepository.getClients().iterator();
		Book book = nextAvailableBook();
		borrowAllCopies(book, clientIterator);
		Client client = getClient(clientIterator);
		book.reserveFor(client);
		Collection<Book> books = bookRepository.hasReservedWhat(client);
		assertTrue(books.contains(book));
	}
	

	@Test
	public void testWhoCanBorrowWhenReservationExists() {
		Book book = nextAvailableBook(4);  //ask for a book with at least four available copies

		Iterator<Client> clientIterator = clientRepository.getClients().iterator();
		borrowAllCopies(book, clientIterator);  //first borrow all the copies, in order to be able of making reservations
		
		Client firstClient = getClient(clientIterator);  //get a client that has not borrowed a copy
		assertTrue(book.reserveFor(firstClient));  //reserve a copy for him, the operation must success since all the copies are borrowed
		book.returnBook(book.borrowedBy().iterator().next(), new Date());  //return one of the copies that were borrowed before, now only one copy is available
		
		//verify that the only client that can borrow a copy is the one who made the reservation
		assertTrue( clientRepository.whoCanBorrow(book).size() == 1 && clientRepository.whoCanBorrow(book).iterator().next().equals(firstClient) );
		
		//get another client
		Client secondClient = getClient(clientIterator);
		//reserve a book for this client. This operation must success since though there is one copy available, that copy has been already reserved
		assertTrue(book.reserveFor(secondClient));
		
		//although there are two clients who have made a reservation, only the first one can borrow a copy, since there is only one available
		assertTrue( clientRepository.whoCanBorrow(book).size() == 1 && clientRepository.whoCanBorrow(book).iterator().next().equals(firstClient) );
		
		//return another copy, now there are two copies available
		book.returnBook(book.borrowedBy().iterator().next(), new Date());
		
		//verify that only the two clients that made the reservation are able to borrow the book
		assertTrue( clientRepository.whoCanBorrow(book).size() == 2);
		assertTrue( clientRepository.whoCanBorrow(book).iterator().next().equals(firstClient) );
		assertTrue( clientRepository.whoCanBorrow(book).contains(secondClient) );

		//return another copy of the book. Now there are more available copies than reserved ones
		book.returnBook(book.borrowedBy().iterator().next(), new Date());
		
		//verify that now all the clients, with the exception of the ones that have already borrowed the book, can potentially borrow the book
		Collection<Client> resultA, resultB;
		resultA = new ArrayList<Client>(clientRepository.getClients());
		resultA.removeAll(book.borrowedBy());
		resultB = clientRepository.whoCanBorrow(book);
		assertTrue(resultA.size() == resultB.size());
	}
	
	@Test
	public void testWhoCanReserve() {
		Book book = nextAvailableBook();
		assertTrue(clientRepository.whoCanReserve(book).size() == 0);
		
		borrowAllCopies(book);
		
		Collection<Client> resultA, resultB;
		resultA = new ArrayList<Client>(clientRepository.getClients());
		resultA.removeAll(book.borrowedBy());
		resultB = clientRepository.whoCanReserve(book);
		assertTrue(resultA.size() == resultB.size());
	}
}
