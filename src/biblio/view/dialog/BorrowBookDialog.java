package biblio.view.dialog;

import java.util.Collection;
import java.util.Date;

import biblio.model.Book;
import biblio.model.Client;
import biblio.view.GUI;

/**
 * Class describing the dialog for the borrowing of a book
 * 
 */
public class BorrowBookDialog extends BiblioEditionDialog {
	
	public BorrowBookDialog(GUI gUI, String title, boolean modal, Book book, Collection<Client> clients) {
		super(gUI, title, modal);
		
		BiblioButtonsPanel buttonsPanel;
		if(clients.size() > 0)
			buttonsPanel = new OkCancelButtonsPanel(this);
		else
			buttonsPanel = new CloseButtonsPanel(this);
		
		build(new BorrowBookPanel(book, clients), buttonsPanel);
	}

	@Override
	public void onOk() {
		super.onOk();
		Client client = getMainPanel().getSelectedClient();
		Book book = getMainPanel().getBook();
		int numDays = getMainPanel().getNumDays();
		book.borrow(client, new Date(), numDays);
		close();
	}
	
	public BorrowBookPanel getMainPanel() {
		return (BorrowBookPanel)super.getMainPanel();
	}


}
