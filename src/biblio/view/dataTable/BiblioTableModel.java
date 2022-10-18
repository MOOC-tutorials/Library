package biblio.view.dataTable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;


/**
 * Abstract class that provides the basic behavior for the classes representing 
 * TableModel objects in the system
 * 
 */
public abstract class BiblioTableModel<T_Row> extends AbstractTableModel {

	protected List<T_Row> rows;
	
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("MMM yyyy");
	
	public BiblioTableModel() {
		rows = new ArrayList<T_Row>();
	}

	public BiblioTableModel(Collection<T_Row> rows) {
		this();
		update(rows);
	}
	
	protected String formatObject(Object o) {
		if(o==null)
			return "";
		if(o.getClass().equals(Date.class)) {
			return DATE_FORMAT.format((Date)o);
		}
		return o.toString();
	}
	
	public void update(Collection<T_Row> rows) {
		setRows(rows);			
		fireTableDataChanged();
	}
	
	public void refresh() {
		update(rows);
	}

	/**
	 * @return the rows
	 */
	public Collection<T_Row> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(Collection<T_Row> rows) {
		this.rows = new ArrayList<T_Row>(rows);
	}
	
	public T_Row getRow(int row) {
		return rows.get(row);
	}
	
	public int getRowCount() { return rows.size(); }

}
