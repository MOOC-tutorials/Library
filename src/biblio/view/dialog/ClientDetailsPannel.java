package biblio.view.dialog;

import biblio.model.Book;
import biblio.model.BookRepository;
import biblio.model.Client;

public class ClientDetailsPannel extends ClientEditionPanel {
	private static final long serialVersionUID = 1L;

	private BiblioCombo<Book> borrowedBooksCombo;
	private BiblioCombo<Book> reservedBooksCombo;
	
	public ClientDetailsPannel(Client client, BookRepository bookRepository) {
		super(client);
		loadBookDependencies(bookRepository);
		readOnlyMode();
	}
	
	@Override
	protected void createElements() {
		super.createElements();
		
		borrowedBooksCombo = new BiblioCombo<Book>();
		add("Books borrowed by the client", borrowedBooksCombo);
		
		reservedBooksCombo = new BiblioCombo<Book>();
		add("Books reserved by the client", reservedBooksCombo);
	}

	/**
	 * @param bookRepository the bookRepository to set
	 */
	public void loadBookDependencies(BookRepository bookRepository) {
		borrowedBooksCombo.setList(bookRepository.hasBorrowedWhat(client));
		reservedBooksCombo.setList(bookRepository.hasReservedWhat(client));	
	}
	
	
}
