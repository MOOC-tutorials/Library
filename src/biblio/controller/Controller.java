package biblio.controller;

import java.io.File;
import java.util.Collection;

import biblio.model.Book;
import biblio.model.BookRepository;
import biblio.model.Client;
import biblio.model.ClientRepository;
import biblio.view.AdministratorGUI;

/**
 * This class decouples the data access and business logic from the data presentation.
 * All the messages sent to the interface are received by this class, 
 * which redirects them to the appropriate business object.
 * 
 */
public class Controller {
	
	// L'interface utilisateur à controller
	private AdministratorGUI userInterface;
	// Le répertoire d'ouvrages connus par le système de gestion de la bibliothéque
	private BookRepository bookRepository;
	// Le répertoire d'emprunteurs connus par le système de gestion de la bibliothéque
	private ClientRepository clientRepository;

	public Controller() {
	}

	/**
	 * @return the library's book repository
	 */
	public BookRepository getBookRepository() {
		return bookRepository;
	}

	/**
	 * @return the library's repository of known borrowers
	 */
	public ClientRepository getClientRepository() {
		return clientRepository;
	}

	/**
	 * Ask the user interface to refresh the list of ALL books shown to the user.
	 * For example, if after a search we (which only shows a subset of all books)
	 * we want to see all books again.
	 */
	public void updateBookList() {
		updateUIBookList(bookRepository.getBooks());
	}
	
	/**
	 * Updates the content of the book list table, according to the information of the BookRepository
	 * received as parameter. Note that not only the controllers repository but also the user interface's
	 * view on that repository needs to be updated.
	 */
	private void updateBookList(BookRepository bookRepository) {
		setBookRepository(bookRepository);
		updateBookList();
	}
	
	/**
	 * Updates the content of the book list table shown in the user interface
	 * with the contents of the Collection of Book objects received as parameter.
	 * For example, after a search query.
	 */
	private void updateUIBookList(Collection<Book> books) {
		userInterface.updateBookList(books);
	}

	/** 
	 * Sets the repository of books known by library application to the BookRepository received as parameter.
	 */
	private void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	/** 
	 * Sets the repository of borrowers known by library application to the ClientRepository received as parameter.
	 */
	private void setClientRepository(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	/**
	 * Updates the content of the client list table, according to the information of the ClientRepository
	 * received as parameter. Note that not only the controllers repository but also the user interface's
	 * view on that repository needs to be updated.
	 */
	private void updateClientList(ClientRepository clientRepository) {
		setClientRepository(clientRepository);
		updateUIClientList(clientRepository.getClients());
	}	
	
	/**
	 * Delete a client from the repository of borrowers known by the library application.
	 * Note that not only the controllers repository but also the user interface's
	 * view on that repository needs to be updated.
	 * (This is an auxiliary method called by deleteClient.)
	 */
	private void removeClient(Client client) {
		clientRepository.removeClient(client);
		updateUIClientList(clientRepository.getClients());
	}
	
	/**
	 * Updates the content of the client list table shown in the user interface
	 * with the contents of the Collection of Client objects received as parameter.
	 */
	private void updateUIClientList(Collection<Client> clients) {
		userInterface.updateClientList(clients);
	}

	/**
	 * Adds a new client to the controller's client repository (and the user interface's
	 * view on it)  after asking for the necessary information in the user interface.
	 */
	public void addClient() {
		Client client = userInterface.askForNewClientDialog();
		if(client != null) {
			clientRepository.addClient(client);
			updateUIClientList(clientRepository.getClients());
		}
	}

	/**
	 * Edits the client that is currently selected in the user interface;
	 * asks for selecting a client first if none is selected.
	 */
	public void editClient() {
		Client client = userInterface.getSelectedClient();
		if(client != null) {
			userInterface.showEditClientDialog(client);
			clientRepository.updateClient(client);
			updateUIClientList(clientRepository.getClients());
		} else
			askForSelectingClientFirst();
	}
	
	/**
	 * Shows the details of the currently selected client in the user interface;
	 * asks for selecting a client first if none is selected.
	 */
	public void showClient() {
		Client client = userInterface.getSelectedClient();
		if(client != null) {
			userInterface.showClientDetailsDialog(client, bookRepository);
		} else
			askForSelectingClientFirst();
	}
	
	/**
	 * Deletes the client that is currently selected from the user interface;
	 * asks for selecting a client first if none is selected.
	 */
	public void deleteClient() {
		Client client = userInterface.getSelectedClient();
		if(client != null) {
			if(userInterface.confirmDeletion(client)) {
				removeClient(client);
			}
		} else
			askForSelectingClientFirst();
	}
	
	/**
	 * Shows the details of the currently selected book in the user interface;
	 * asks for selecting a book first if none is selected.
	 */
	public void showBook() {
		Book book = userInterface.getSelectedBook();
		if(book != null) {
			userInterface.showBookDetailsDialog(book);
		} else
			askForSelectingBookFirst();
	}

	/**
	 * Makes the interface ask the user for the information needed for borrowing a book.
	 * Shows a message to suggest reserving the book if it cannot be borrowed.
	 * Asks for selecting a book first if none is selected.
	 */
	public void borrowBook() {
		Book book = userInterface.getSelectedBook();
		if(book != null) {
			if(book.canBeBorrowed(clientRepository))
				userInterface.showBorrowBookDialog(book, clientRepository.whoCanBorrow(book));
			else
				userInterface.showMessage("The book is not available now. Try reserving it.");
		} else
			askForSelectingBookFirst();
	}

	/**
	 * Makes the interface ask the user for the information needed for reserving a book.
	 * Shows a message dialog when the book cannot be reserved.
	 * Asks for selecting a book first if none is selected.
	 */
	public void reserveBook() {
		Book book = userInterface.getSelectedBook();
		if(book != null) {
			if(book.canBeReserved(clientRepository))
				userInterface.showReserveBookDialog(book, clientRepository.whoCanReserve(book));
			else
				userInterface.showMessage("No client can reserve this book.");
		} else
			askForSelectingBookFirst();
	}

	/**
	 * Makes the interface ask the user for the information needed for returning a book.
	 * Shows a message dialog when the book cannot be returned.
	 * Asks for selecting a book first if none is selected.
	 */
	public void returnBook() {
		Book book = userInterface.getSelectedBook();
		if(book != null) {
			if(book.canBeReturned())
				userInterface.showReturnBookDialog(book);
			else
				userInterface.showMessage("Nobody has rented the book "+book.getTitle());
		} else
			askForSelectingBookFirst();
	}

	/**
	 * Shows a dialog box in the user interface to ask the user to select a book from the repository.
	 */
	private void askForSelectingBookFirst() {
		userInterface.showMessage("Please select a book first");
	}
	
	/**
	 * Shows a dialog box in the user interface to ask the user to select a client from the repository.
	 */
	private void askForSelectingClientFirst() {
		userInterface.showMessage("Please select a client first");
	}
	
	/**
	 * Sets the administrator interface.
	 * (In the current implementation there is only an administrator interface but a more restricted
	 *  interface that is only valid for borrowers could be useful as well.)
	 */
	public void setAdministratorInterface(AdministratorGUI graphicInterface) {
		this.userInterface = graphicInterface;
	}
	

	/**
	 *  Initializes the controller with a default set of books and borrowers
	 */
	public void initialize() {
		updateBookList(new BookRepository());
		updateClientList(new ClientRepository());
	}

	/**
	 * Loads a new book catalog from file and sets it as the book catalog known by the library system
	 */
	public void loadCatalog() {
		File file = userInterface.selectFile();
		if(file != null) {
			updateBookList(new BookRepository(file.getAbsolutePath()));
		}
	}

	/**
	 * Searches the book repository for all books according to a given field.
	 * @param bookFieldCode in an int which determines by what field we want to search for books.
	 *   These field codes are defined as static variables by the Book class.
	 *   For example, the AUTHORS field corresponds to 0, the TITLE field to 1 etc. (consult
	 *   the Book class for the exact mapping of fields to field codes).
	 * @remark A cleaner implementation would be to use Java 1.5 enumeration types.
	 */
	public void searchBookByCriteria(int bookFieldCode) {
		if (bookRepository == null) {
			userInterface.showWarningMessage("Please first select a catalog: In the menu File, select Load Catalog");
			return;
		}
		Collection<Book> filteredBooks = null;
		
		String value = userInterface.askForBookField(bookFieldCode);
		if ((value != null) && (value.length() > 0)) {
			try {
				filteredBooks = bookRepository.search(bookFieldCode, value);
			} catch (IllegalArgumentException ex) {
				//bookManager.search is supposed to throw an IllegalArgumentException if 
				//the search arguments were wrong
			}
			if (filteredBooks != null) 
				updateUIBookList(filteredBooks);
			else 
				userInterface.showMessage("No book found with the selected criteria");
		}
	}

	public void printCatalog() {
		bookRepository.print(System.out);
		
	}

	public void printClients() {
		clientRepository.print(System.out);
		
	}


}