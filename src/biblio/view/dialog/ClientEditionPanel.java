package biblio.view.dialog;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JTextField;

import biblio.model.Client;
import biblio.util.SwingUtility;

/**
 * Class describing the panel for the edition of a client
 * 
 */
public class ClientEditionPanel extends BiblioEditionPanel {
	
	private JTextField clientIdTextField;
	
	private JTextField clientNameTextField;
	
	private JTextField clientFineTextField;
	
	protected Client client;
	
	private Collection<Client> otherClients;
	
	

	
	public ClientEditionPanel(Collection<Client> clients) {
		clientFineTextField.setText("0.0");
		clientFineTextField.setEditable(false);
		otherClients = new ArrayList<Client>(clients);
	}
	
	public ClientEditionPanel(Client client) {
		this(client, new ArrayList<Client>());
	}
	
	public ClientEditionPanel(Client client, Collection<Client> clients) {
		setClient(client);
		clientIdTextField.setEditable(false);
		otherClients = new ArrayList<Client>(clients);
		otherClients.remove(client);
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
		buildFieldsFromClient();
	}
	
	public void buildFieldsFromClient() {
		if(client != null) {
			clientIdTextField.setText(client.getId());
			clientNameTextField.setText(client.getName());
			clientFineTextField.setText(new Double(client.getFine()).toString());
		}
	}
	
	@Override
	protected void createElements() {	
		clientIdTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Client Id", clientIdTextField);
		
		clientNameTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Client name", clientNameTextField);
		
		clientFineTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Fine", clientFineTextField);
	}


	@Override
	public boolean onOk() {
		if(client != null)
			return buildClientFromFields(client);
		else {
			Client aClient = new Client();
			if(buildClientFromFields(aClient)) {  //try to build a client checking first that the input data is ok
				this.client = aClient;
				return true;
			} else
				return false;
		}
	}

	private boolean buildClientFromFields(Client client) {
		if(clientIdTextField.getText().length() == 0) {
			SwingUtility.showErrorMessage("Invalid id");
			return false;
		} 
		else {
			if(checkNumber(clientIdTextField.getText())) {
				SwingUtility.showErrorMessage("Client ids must be numbers only");
				return false;
			} else {
				if(otherClients.contains(client)) {
					SwingUtility.showErrorMessage("The client with id "+client.getId()+" already exist, try with another one");
					return false;
				} else {
					client.setId(clientIdTextField.getText());
				}
			}
		}
		
		if(clientNameTextField.getText().length() == 0) {
			SwingUtility.showErrorMessage("Invalid name");
			return false;
		}
		else
			client.setName(clientNameTextField.getText());
		
		if(clientFineTextField.getText().length() == 0) {
			SwingUtility.showErrorMessage("Please specify a fine");
			return false;
		}
		else {
			try {
				double fine = Double.parseDouble(clientFineTextField.getText());
				if(fine>=0) {
					client.setFine(fine);
					if(fine >0)
						SwingUtility.showMessage("Client with a fine!");
				}
				else {
					SwingUtility.showErrorMessage("The fine must be 0 or a positive number");
					return false;
				}
			} catch(NumberFormatException e) {
				SwingUtility.showErrorMessage("Please correct the amount for the fine");
				return false;
			}
		}	
		return true;
	}


	private boolean checkNumber(String value) {
		try {
			int id = Integer.parseInt(clientIdTextField.getText());
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}

}
