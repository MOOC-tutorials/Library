package biblio.view.dialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import biblio.model.Book;
import biblio.model.Client;

/**
 * Panel implementing the reservation of a book
 * 
 */
public class ReserveBookPanel extends SimpleBookEditionPanel {

	protected List<Client> clients;
	private BiblioCombo<Client> borrowersCombo;
	
	public ReserveBookPanel(Book book, Collection<Client> clients) {
		super(book);
		setClients(clients);
		readOnlyMode();
		borrowersCombo.setEditable(true);
	}
	
	
	@Override
	protected void createElements() {
		super.createElements();
		borrowersCombo = new BiblioCombo<Client>();
		add("Clients who can reserve this book", borrowersCombo);
	}

	public void setClients(Collection<Client> clients) {
		this.clients = new ArrayList<Client>(clients);
		borrowersCombo.setList(clients);
	}
	
	public Client getSelectedClient() {
		return borrowersCombo.getSelectedItem();
	}
	
	
}
