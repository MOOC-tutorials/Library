package biblio.view.dialog;

import java.util.Collection;

import biblio.model.Client;
import biblio.view.GUI;

/**
 * Class describing the dialog for the edition of a client
 * 
 */
public class ClientEditionDialog extends BiblioEditionDialog  {

	public ClientEditionDialog(GUI gUI, String title, boolean modal) {
		super(gUI, title, modal);
		Collection<Client> clients = gUI.getController().getClientRepository().getClients();
		build(new ClientEditionPanel(clients), new OkCancelButtonsPanel(this));
	}
	
	public ClientEditionDialog(GUI gUI, String title, boolean modal, Client client) {
		super(gUI, title, modal);
		Collection<Client> clients = gUI.getController().getClientRepository().getClients();
		build(new ClientEditionPanel(client, clients), new OkCancelButtonsPanel(this));
	}
	
	public Client getClient() {
		return getMainPanel().getClient();
	}
	
	public ClientEditionPanel getMainPanel() {
		return (ClientEditionPanel) mainPanel;
	}	
	
}
