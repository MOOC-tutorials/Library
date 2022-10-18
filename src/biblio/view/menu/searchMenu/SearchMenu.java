/*
 * Created on Mar 22, 2007
 */
package biblio.view.menu.searchMenu;

import javax.swing.JMenu;

import biblio.controller.Controller;
import biblio.model.BookField;


/**
 * Main search Menu
 * 
 */
public class SearchMenu extends JMenu {

	
	public SearchMenu(final Controller controller) {
		
		JMenu simpleSearch = new JMenu("Simple Search");
		
		simpleSearch.add(new FindByCriteriaMenuItem(BookField.AUTHORS, controller));
		simpleSearch.add(new FindByCriteriaMenuItem(BookField.TITLE, controller));
		simpleSearch.add(new FindByCriteriaMenuItem(BookField.PUBLICATION_DATE, controller));
		simpleSearch.add(new FindByCriteriaMenuItem(BookField.SERIE, controller));
		simpleSearch.add(new FindByCriteriaMenuItem(BookField.NUMBER_PAGES, controller));
		simpleSearch.add(new FindByCriteriaMenuItem(BookField.REFERENCE, controller));
		simpleSearch.add(new FindByCriteriaMenuItem(BookField.EDITOR, controller));
		simpleSearch.add(new FindByCriteriaMenuItem(BookField.NUM_BORROWED_COPIES, controller));
		simpleSearch.add(new FindByCriteriaMenuItem(BookField.NUM_AVAILABLE_COPIES, controller));
		simpleSearch.add(new FindByCriteriaMenuItem(BookField.NUM_RESERVED_COPIES, controller));

		JMenu advancedSearch = new JMenu("Advanced Search");  //menu to be developed by the students
		
		add(simpleSearch);
		add(advancedSearch);
		add(new ShowAllMenuItem(controller));
		setText("Search");
	}

}
