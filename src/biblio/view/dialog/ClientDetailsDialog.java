package biblio.view.dialog;

import biblio.model.BookRepository;
import biblio.model.Client;
import biblio.view.GUI;

public class ClientDetailsDialog extends BiblioEditionDialog {

	public ClientDetailsDialog(GUI gUI, String title, boolean modal, Client client, BookRepository bookRepository) {
		super(gUI, title, modal);
		build(new ClientDetailsPannel(client, bookRepository), new CloseButtonsPanel(this));
	}

}
