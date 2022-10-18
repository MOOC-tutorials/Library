package biblio.view.dialog;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

/**
 * An abstract class for all the panels that show information to the user using a "form" design
 * 
 */
public abstract class BiblioFormPanel extends BiblioPanel {
	
	protected static final int SIZE_EDITABLE_COMPONENTS = 20;
	
	private List<Row> rows;
	private boolean readOnly; 
	
	public BiblioFormPanel() {
		rows = new ArrayList<Row>();
		createElements();
		createLayout();
	}
	
	public boolean isReadOnly() {
		return readOnly;
	}

	private void changeEditability(boolean editability) {
		for(Row row : rows) {
			if(row.component instanceof JTextComponent) {
				((JTextComponent)row.component).setEditable(editability);
			}
		}
	}
	
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		if(readOnly) readOnlyMode();
		else editionMode();
		
	}
	
	public void readOnlyMode() {
		changeEditability(false);
	}
	
	public void editionMode() {
		changeEditability(true);
	}
	
	protected abstract void createElements();

	private void createLayout() {
		setLayout(new GridLayout(rows.size(),2));
		for(Row row : rows) {
			add(row.label);
			add(row.component);
		}
	}
	
	protected void add(String labelText, JComponent component) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(component);
		rows.add(new Row(label, component));
	}




	private class Row {
		private JLabel label;
		private JComponent component;
		
		public Row(JLabel label, JComponent component) {
			this.label = label;
			this.component = component;
		}
	}
	
}
