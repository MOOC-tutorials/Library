package biblio.view.dialog;

import biblio.model.Book;
import biblio.model.Client;

/**
 * Panel that shows additional information of a book, 
 * such as the clients who has borrowed or reserved the book
 * 
 */
public class ExtendedBookEditionPanel extends SimpleBookEditionPanel {

	private BiblioCombo<Client> whoHasBorrowedCombo;
	private BiblioCombo<Client> whoHasReservedCombo;
	
	public ExtendedBookEditionPanel(Book book) {
		super(book);
		readOnlyMode();
	}
	
	@Override
	protected void createElements() {
		super.createElements();
		whoHasBorrowedCombo = new BiblioCombo<Client>();
		add("Clients who have borrowed this book", whoHasBorrowedCombo);
		
		whoHasReservedCombo = new BiblioCombo<Client>();
		add("Clients who have reserved this book", whoHasReservedCombo);
	}

	@Override
	public void buildFieldsFromBook() {
		super.buildFieldsFromBook();
		whoHasBorrowedCombo.setList(book.borrowedBy());
		whoHasReservedCombo.setList(book.getReservations());
	}
	
}

