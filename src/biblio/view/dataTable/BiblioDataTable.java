package biblio.view.dataTable;

import java.util.Collection;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import biblio.view.AdministratorGUI;

/**
 * Abstract class that provides the basic behavior for the classes representing 
 * swing DataTable objects in the system
 * 
 */
public abstract class BiblioDataTable<T_Row, T_TableModel extends BiblioTableModel<T_Row>> extends JTable {
	
	protected AdministratorGUI gui;
	
	public BiblioDataTable(AdministratorGUI gui) {
		this.gui = gui;
		setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		doLayout();
	}
	
	
	public abstract T_Row getSelectedObject();
	
	public void refresh() {
		getTableModel().refresh();
	}

	public T_TableModel getTableModel() {
		return (T_TableModel)getModel();
	}

	public void update(Collection<T_Row> rows) {
		getTableModel().update(rows);
	}

}
