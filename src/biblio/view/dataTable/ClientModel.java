package biblio.view.dataTable;

import biblio.model.Client;
import biblio.model.ClientField;
import biblio.model.ClientRepository;



/**
 * The model handling the date for the clients
 * 
 */
public class ClientModel extends BiblioTableModel<Client> {
	
	private static final long serialVersionUID = 1L;
	
	private final int CLIENT_NUMBER_OF_FIELDS = 3;

	public ClientModel() {
	}
	
	public void update(ClientRepository clientRepository) {
		update(clientRepository.getClients());
	}
	
	public String getColumnName(int col) { 
		switch(col) {
			case (0): return "Id";
			case (1): return "Name";
			case (2): return "Fine";
			default:
				throw new RuntimeException("Column number not recognized");
		}
	}
	
	public int getColumnCount() {
		return CLIENT_NUMBER_OF_FIELDS;
	}
	
	
	public Object getValueAt(int row, int col) {
		return formatObject((rows.get(row)).getFieldValue(getClientFieldCode(col)));
	}
	
	private int getClientFieldCode(int posInTable) {
		switch (posInTable) {
			case(0): return ClientField.ID;
			case(1): return ClientField.NAME;
			case(2): return ClientField.FINE;
			default: throw new RuntimeException("No recognized field position");
		}
	}

}

	