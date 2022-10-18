package biblio.view.dataTable;

import biblio.model.Client;
import biblio.view.AdministratorGUI;

/**
 * Concrete implementation of the DataTable showing the clients to the user
 * 
 */
public class ClientDataTableImpl extends BiblioDataTable<Client, ClientModel> {

	private static final long serialVersionUID = 1L;
	
	public ClientDataTableImpl(AdministratorGUI gui) {
		super(gui);
		setModel(new ClientModel());
	}
	
	public Client getSelectedObject() {
		int selectedRow = getSelectedRow();
		String id = getIdSelectedObject(selectedRow);
		return gui.getController().getClientRepository().findById(id);
	}
	
	public String getIdSelectedObject(int selectedRow) {
		if(selectedRow >= 0) return (String)getValueAt(selectedRow, 0);
		else return null;
	}



	
}
