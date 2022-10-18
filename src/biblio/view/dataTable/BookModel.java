package biblio.view.dataTable;

import biblio.model.Book;
import biblio.model.BookField;
import biblio.model.BookRepository;



/**
 * The model handling the date for the books
 * 
 */
public class BookModel extends BiblioTableModel<Book> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int BOOK_NUMBER_OF_FIELDS = 10;
	
	public BookModel() {
	}
	
	public void update(BookRepository bookRepository) {
		update(bookRepository.getBooks());
	}
	
	public String getColumnName(int col) { 
		switch(col) {
			case (0): return "Author";
			case (1): return "Title";
			case (2): return "Publication date";
			case (3): return "Serie";
			case (4): return "N. pages";
			case (5): return "Reference";
			case (6): return "Editor";
			case (7): return "N. borrowed copies";
			case (8): return "N. available copies";
			case (9): return "N. reserved copies";
			default:
				throw new RuntimeException("Column number not recognized");
		}
	}
	
	
	public int getColumnCount() { return BOOK_NUMBER_OF_FIELDS; }
	
	public Object getValueAt(int row, int col) {
		return formatObject((rows.get(row)).getFieldValue(getBookFieldCode(col)));
	}
	
	private int getBookFieldCode(int posInTable) {
		switch (posInTable) {
		case(0): return BookField.AUTHORS;
		case(1): return BookField.TITLE;
		case(2): return BookField.PUBLICATION_DATE;
		case(3): return BookField.SERIE;
		case(4): return BookField.NUMBER_PAGES;
		case(5): return BookField.REFERENCE;
		case(6): return BookField.EDITOR;
		case(7): return BookField.NUM_BORROWED_COPIES;
		case(8): return BookField.NUM_AVAILABLE_COPIES;
		case(9): return BookField.NUM_RESERVED_COPIES;
		default: throw new RuntimeException("No recognized field position");
		}
	}

}