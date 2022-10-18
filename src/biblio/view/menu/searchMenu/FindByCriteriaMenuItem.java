package biblio.view.menu.searchMenu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;

import biblio.controller.Controller;
import biblio.model.BookField;

/**
 * Search a book by criteria Menu
 * 
 */
public class FindByCriteriaMenuItem extends JMenuItem {

	public FindByCriteriaMenuItem(final int bookFieldCode, final Controller controller) {
		setAction(new AbstractAction(){
			/**
			 * delegates the book search to the appropriate book manager method
			 */
			public void actionPerformed(ActionEvent e) {
				controller.searchBookByCriteria(bookFieldCode);
			}
		});
		setText(BookField.getFieldName(bookFieldCode));
	}

}	
