package biblio.view.dialog;

import biblio.model.Book;
import biblio.view.GUI;

/**
 * Class describing the edition of a book
 * 
 */
public class BookEditionDialog extends BiblioEditionDialog {
	
	public BookEditionDialog(GUI gUI, String title, boolean modal, Book book) {
		super(gUI, title, modal);
		build(new ExtendedBookEditionPanel(book), new CloseButtonsPanel(this));
	}
	

}
