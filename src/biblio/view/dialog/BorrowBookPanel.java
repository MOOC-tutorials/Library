package biblio.view.dialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import biblio.model.Book;
import biblio.model.Client;

/**
 * Class describing the panel for the borrowing of a book
 * 
 */
public class BorrowBookPanel extends SimpleBookEditionPanel  {

	protected List<Client> clients;
	
	private JSpinner numDaysSpinner;
	private BiblioCombo<Client> borrowersCombo;
	
	public BorrowBookPanel(Book book, Collection<Client> clients) {
		super(book);
		setClients(clients);
		readOnlyMode();
	}
	
	@Override
	protected void createElements() {
		super.createElements();
		SpinnerModel model = new SpinnerNumberModel(1,1,365,1);
		numDaysSpinner = new JSpinner(model);
		add("Number of days", numDaysSpinner);
		borrowersCombo = new BiblioCombo<Client>();
		add("Clients who can borrow this book", borrowersCombo);
	}
	
	public int getNumDays() {
		return (Integer)numDaysSpinner.getValue();
	}

	public void setClients(Collection<Client> clients) {
		this.clients = new ArrayList<Client>(clients);
		borrowersCombo.setList(clients);
	}

	public Client getSelectedClient() {
		return borrowersCombo.getSelectedItem();
	}


}


