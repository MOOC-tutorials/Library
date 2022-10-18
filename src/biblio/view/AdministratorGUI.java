package biblio.view;

import java.util.Collection;

import biblio.controller.Controller;
import biblio.model.Book;
import biblio.model.BookField;
import biblio.model.BookRepository;
import biblio.model.Client;
import biblio.util.SwingUtility;
import biblio.view.dataTable.BiblioDataTable;
import biblio.view.dataTable.BookModel;
import biblio.view.dataTable.ClientModel;
import biblio.view.dialog.BookEditionDialog;
import biblio.view.dialog.BorrowBookDialog;
import biblio.view.dialog.ClientDetailsDialog;
import biblio.view.dialog.ClientEditionDialog;
import biblio.view.dialog.ReserveBookDialog;
import biblio.view.dialog.ReturnBookDialog;

/**
 * Implementation of the Graphical User Interface (GUI) for the library administrator.
 * (Clients of the library may require a more restricted user interface.)
 * 
 */
public class AdministratorGUI extends GUI {
	
	// Table of book details shown in the user interface
	private BiblioDataTable<Book, BookModel> booksDataTable;
	// Table of client details shown in the user interface
	private BiblioDataTable<Client, ClientModel> clientsDataTable;
	
	public AdministratorGUI(Controller controller) {
		// To create a new user interface, a controller that handles the user interface requests needs to be provided.
		super(controller);
		// The user interface is then built by an auxiliary class  
		new AdministratorGuiBuilder().build(this);
		// The controller needs to keep a reference to the user interface it controls
		controller.setAdministratorInterface(this);
		// Finally, the controller and user interface are initialized with a default set of books and clients
		controller.initialize();
	}

	/**
	 * @param booksDataTable the booksDataTable to set
	 */
	protected void setBooksDataTable(BiblioDataTable<Book, BookModel> booksDataTable) {
		this.booksDataTable = booksDataTable;
	}

	/**
	 * @param clientsDataTable the clientsDataTable to set
	 */
	protected void setClientsDataTable(BiblioDataTable<Client, ClientModel> clientsDataTable) {
		this.clientsDataTable = clientsDataTable;
	}

	public void updateBookList(Collection<Book> books) {
		booksDataTable.update(books);
	}
	
	public void updateClientList(Collection<Client> clients) {
		clientsDataTable.update(clients);
	}
	
	public boolean confirmDeletion(Client client) {
		return SwingUtility.confirm("Do you really want to delete the client: "
				+ client.getName());
	}

	public Client getSelectedClient() {
		Client client = clientsDataTable.getSelectedObject();
		return client;
	}

	public Book getSelectedBook() {
		Book book = booksDataTable.getSelectedObject();
		return book;
	}
	
	public Client askForNewClientDialog() {
		ClientEditionDialog d = new ClientEditionDialog(this, "New Client", true);
		d.setVisible(true);
		return d.getClient();
	}

	public void showBookDetailsDialog(Book book) {
		BookEditionDialog d = new BookEditionDialog(this, "Book details", true, book);
		d.setVisible(true);
	}

	public void showBorrowBookDialog(Book book, Collection<Client> clients) {
		BorrowBookDialog d = new BorrowBookDialog(this, "Borrow book", true, book, clients);
		d.setVisible(true);
	}

	public void showEditClientDialog(Client client) {
		ClientEditionDialog d = new ClientEditionDialog(this, "Client edition", true, client);
		d.setVisible(true);
	}

	public void showReserveBookDialog(Book book, Collection<Client> clients) {
		ReserveBookDialog d = new ReserveBookDialog(this, "Reserve book", true, book, clients);
		d.setVisible(true);
	}

	public void showReturnBookDialog(Book book) {
		ReturnBookDialog d = new ReturnBookDialog(this, "Return book", true, book);
		d.setVisible(true);
	}

	public void showClientDetailsDialog(Client client, BookRepository bookRepository) {
		ClientDetailsDialog d = new ClientDetailsDialog(this, "Client details", true, client, bookRepository);
		d.setVisible(true);
	}
	
	public void refresh() {
		booksDataTable.refresh();
		clientsDataTable.refresh();
	}

	public String askForBookField(int bookdFieldCode) {
		String queryString = "Search by "+BookField.getFieldName(bookdFieldCode);
		return askValue(queryString);
	}



}
