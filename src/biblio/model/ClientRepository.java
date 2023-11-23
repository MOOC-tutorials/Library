package biblio.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Cette classe ClientRepository represente un ensemble de clients
 * 
 */
public class ClientRepository {

	//the list of the clients that can borrow books
	List<Client> clientList;
	
	/*
	 * normally the client will be extracted from a database or file.
	 * for simplification some clients are defined in the constructor
	 * these clients help us to run some tests
	 */
	public ClientRepository() {
		clientList = new ArrayList<Client>();
		clientList.add(new Client("86", "Diego Armando Maradona"));
		clientList.add(new Client("67", "Ernesto Guevara de la Serna"));
		clientList.add(new Client("12", "Homer Simpson"));
		clientList.add(new Client("33", "Raynaud de la Ferriere"));
		clientList.add(new Client("01", "Albert Einstein"));
		clientList.add(new Client("02", "Ludwing Beethoven"));
		clientList.add(new Client("03", "Wolfgang Mozart"));
		clientList.add(new Client("04", "John Bach"));
		clientList.add(new Client("05", "Antonio Vivaldi"));
		clientList.add(new Client("06", "Frederic Chopin"));
	}

	/*
	 * This method add a new client to the clientRepository, in this way it can be retrieved later
	 */
	public void addClient(Client client) {
		clientList.add(client);
	}

	/*
	 * This method remove an existing client from the clientRepository
	 */
	public void removeClient(Client client) {
		clientList.remove(client);
	}

	/*
	 * This method return the set of clients managed by this clientRepository.
	 * The reason the method Collections.unmodifiableCollection(Collection) is used, 
	 * is for preserving the encapsulation of the lists of clients
	 */
	public Collection<Client> getClients() {
		return Collections.unmodifiableCollection(clientList);
	}
	
	/*
	 * This method update an existing client in the clientRepository
	 */
	public void updateClient(Client client) {
		int index = clientList.indexOf(client);
		if(index != -1) {
			clientList.remove(index);
			clientList.add(index, client);
		}
	}
	
	/*
	 * This method find and return a client according to its id
	 */
	public Client findById(String id) {
		for (Client c : clientList) {
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}
	
	/**
	 * Answers the list of all clients who can borrow a given book.
	 * (This list is needed to provide a list of clients to select from in the user interface.)
	 * In case there are no available copies, no one can borrow the book.
	 * In the case the number of reserved copies is greater or equal than the number of available copies, 
	 * then the only clients who can borrow the book are those who have made a reservation. Also, in this
	 * situation having a reservation is not the only condition that a client must fulfill for borrowing the
	 * book, also her position in the reservation queue must be equal or minor than the number of total
	 * available books. (For example: if there are only two books available and 5 reserves, only the first 
	 * two clients who made the reservation -- according to their order in the reservation queue -- can borrow
	 * the book. If a new book is returned, then the third could also borrow the book, and so on.
	 * In case that the number of reservations is equal to the number of available books, only the clients
	 * with a reservation can borrow the book.
	 * In case the number of available books is greater than the number of reserved books, anybody can borrow 
	 * the book (with the exception of the clients who already borrowed the book), since there are enough
	 * books for the clients who made the reservation and even for the ones who did not.
	 */
	public Collection<Client> whoCanBorrow(Book book) {
		List<Client> theyCanBorrow;
		if(book.numAvailableCopies() == 0) {
			theyCanBorrow = new ArrayList<Client>();
		} else if(book.numReservedCopies() >= book.numAvailableCopies()) {
			theyCanBorrow = new ArrayList<Client>();
			for(int i=0; i<= book.numAvailableCopies(); i++) {
				List<Client> reservations = book.getReservations();
				theyCanBorrow.add(reservations.get(i));
			}
		}
		else {
			theyCanBorrow = new ArrayList<Client>(clientList);
			theyCanBorrow.removeAll(book.borrowedBy());
		}
		return theyCanBorrow;
	}

	/**
	 * Answer the list of clients who can reserve a book.
	 * They are only those clients that have not borrowed it
	 * If the book is available, the answer must be an empty set
	 */
	public Collection<Client> whoCanReserve(Book book) {
		if(book.numAvailableCopies() > 0 
			&& book.getReservations().size()<book.numAvailableCopies()) {
			return new ArrayList<Client>();
		}
		List<Client> theyCanReserve = new ArrayList<Client>(clientList);
		theyCanReserve.removeAll(book.getReservations());
		theyCanReserve.removeAll(book.borrowedBy());
		
		return theyCanReserve;
	}

	public void print(PrintStream out) {
		out.println("Printing the clients");
		
	}
	
}
