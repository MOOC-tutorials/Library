package biblio.view.dialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;

public class BiblioCombo<T> extends JComboBox {

	private List<T> list;
	
	public BiblioCombo() {
		this.setEditable(false);
	}
	
	public Collection<T> getList() {
		return Collections.unmodifiableCollection(list);
	}

	public void setList(Collection<T> list) {
		this.list = new ArrayList<T>(list);
		load(this.list);
	}
	
	private void load(Collection<T> list) {
		for (T item : list) {
			addItem(item);
		}
	}
	
	public T getSelectedItem() {
		T item = null;
		int index = getSelectedIndex();
		if(index != -1)
			item = list.get(index);
		System.out.println("index: " + index);
		return item;
	}

}
