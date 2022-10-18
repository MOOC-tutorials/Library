package biblio.view.dialog;

import biblio.model.Book;
import biblio.model.Client;
import biblio.util.SwingUtility;
import biblio.view.GUI;

/**
 * Dialog implementing the returning of a book
 * 
 */
public class ReturnBookDialog extends BiblioEditionDialog {

	public ReturnBookDialog(GUI gUI, String title, boolean modal, Book book) {
		super(gUI, title, modal);
		
		BiblioButtonsPanel buttonsPanel;
		if(book.borrowedBy().size() > 0)
			buttonsPanel = new OkCancelButtonsPanel(this);
		else
			buttonsPanel = new CloseButtonsPanel(this);
		
		build(new ReturnBookPanel(book), buttonsPanel);
	}
	
	@Override
	public void onOk() {
		try {
			if(getMainPanel().updateFine()) {
				Client client = getMainPanel().getSelectedClient();
				Book book = getMainPanel().getBook();
				book.returnBook(client, getMainPanel().getRealReturnDate());
				super.onOk();
				close();
			}
		} catch (Exception e) {
			SwingUtility.showWarningMessage(e.getMessage());
		}
	}
	
	public ReturnBookPanel getMainPanel() {
		return (ReturnBookPanel)super.getMainPanel();
	}
}
