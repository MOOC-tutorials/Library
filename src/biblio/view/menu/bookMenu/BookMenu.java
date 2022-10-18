package biblio.view.menu.bookMenu;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenu;


/**
 * Main Book Menu
 * 
 */
public class BookMenu extends BiblioMenu {

	public BookMenu(final Controller controller) {
		super(controller);
		
		
		add(new BorrowBookMenu(controller));
		add(new DetailsBookMenu(controller));
		add(new ReserveBookMenu(controller));
		add(new ReturnBookMenu(controller));
		
		setText("Book");
	}

}
