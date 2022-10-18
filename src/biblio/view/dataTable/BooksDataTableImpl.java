package biblio.view.dataTable;

import biblio.model.Book;
import biblio.view.AdministratorGUI;


/**
 * Concrete implementation of the DataTable showing the books to the user
 * 
 */
public class BooksDataTableImpl extends BiblioDataTable<Book, BookModel> {
	
	private static final long serialVersionUID = 1L;
	
	public BooksDataTableImpl(AdministratorGUI gui) {
		super(gui);
		setModel(new BookModel());
	}
	
	public String getSelectedBookId() {
		Book book = getSelectedObject();
		return book!=null?book.getReference():null;
	}
	
	public Book getSelectedObject() {
		int selectedRow = getSelectedRow();
		String id = getIdSelectedObject(selectedRow);
		return gui.getController().getBookRepository().findById(id);
		/*
		Book book = null;
		
		if(selectedRow != -1) {
			book = new Book();
			
			book.setAuthorsString((String)getValueAt(selectedRow, 0));
			book.setTitle((String)getValueAt(selectedRow, 1));
			
			String temp = (String)getValueAt(selectedRow, 2);
			if(temp != null && temp.length() > 0)
				try {
					book.setPublicationDate(BiblioTableModel.DATE_FORMAT.parse(temp));
				} catch (ParseException e) {
					new RuntimeException(e);
				}
				
			book.setSerie((String)getValueAt(selectedRow, 3));
				
			
			
			temp = (String)getValueAt(selectedRow, 4);
			if(temp != null && temp.length() > 0)
				book.setNumberPages(Integer.parseInt(temp));
			
			book.setReference((String)getValueAt(selectedRow, 5));
			
			book.setEditor((String)getValueAt(selectedRow, 6));
			
		}
		return book;
		*/
	}
	
	public String getIdSelectedObject(int selectedRow) {
		if(selectedRow >= 0) return (String)getValueAt(selectedRow, 5);
		else return null;
	}
	
	





	
}
