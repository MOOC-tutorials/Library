package biblio.view.dialog;

import java.util.Collection;

import biblio.model.Book;
import biblio.model.Client;
import biblio.view.GUI;

/**
 * Dialog implementing the reservation of a book
 * 
 */
public class ReserveBookDialog extends BiblioEditionDialog {

	public ReserveBookDialog(GUI gUI, String title, boolean modal, Book book, Collection<Client> clients) {
		super(gUI, title, modal);
		BiblioButtonsPanel buttonsPanel;
		if(clients.size() > 0)
			buttonsPanel = new OkCancelButtonsPanel(this);
		else
			buttonsPanel = new CloseButtonsPanel(this);
				
		build(new ReserveBookPanel(book, clients), buttonsPanel);
	}
	
	@Override
	public void onOk() {
		super.onOk();
		
		Client client = getMainPanel().getSelectedClient();
		Book book = getMainPanel().getBook();
		book.reserveFor(client);
		
		close();
	}
	
	public ReserveBookPanel getMainPanel() {
		return (ReserveBookPanel)super.getMainPanel();
	}
	
}
