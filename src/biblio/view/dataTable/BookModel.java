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
		Book book = rows.get(row);
		int fieldCode = -1;
		Object fieldValue;

		if(col == 0) {
			fieldCode = BookField.AUTHORS;
			if(fieldCode == BookField.AUTHORS) { 
				fieldValue = book.getAuthorsString();
				return formatObject(fieldValue);
			}
		} else if(col == 1) {
			fieldCode = BookField.TITLE;
			if(fieldCode == BookField.TITLE) { 
				fieldValue = book.getTitle();
				return formatObject(fieldValue);
			}
		} else if(col == 2) {
			fieldCode = BookField.PUBLICATION_DATE;
			if(fieldCode == BookField.PUBLICATION_DATE) { 
				fieldValue = book.getPublicationDate();
				return formatObject(fieldValue);
			}
		} else if(col == 3) {
			fieldCode = BookField.SERIE;
			if(fieldCode == BookField.SERIE) { 
				fieldValue = book.getSerie();
				return formatObject(fieldValue);
			}
		} else if(col == 4) {
			fieldCode = BookField.NUMBER_PAGES;
			if(fieldCode == BookField.NUMBER_PAGES) { 
				fieldValue = book.getNumberPages();
				return formatObject(fieldValue);
			}
		} else if(col == 5) {
			fieldCode = BookField.REFERENCE;
			if(fieldCode == BookField.REFERENCE) { 
				fieldValue = book.getReference();
				return formatObject(fieldValue);
			}
		} else if(col == 6) {
			fieldCode = BookField.EDITOR;
			if(fieldCode == BookField.EDITOR) { 
				fieldValue = book.getEditor();
				return formatObject(fieldValue);
			}
		} else if(col == 7) {
			fieldCode = BookField.NUM_BORROWED_COPIES;
			if(fieldCode == BookField.NUM_BORROWED_COPIES) { 
				fieldValue = book.numBorrowedCopies();
				return formatObject(fieldValue);
			}
		} else if(col == 8) {
			fieldCode = BookField.NUM_AVAILABLE_COPIES;
			if(fieldCode == BookField.NUM_AVAILABLE_COPIES) { 
				fieldValue = book.numAvailableCopies();
				return formatObject(fieldValue);
			}
		} else if(col == 9) {
			fieldCode = BookField.NUM_RESERVED_COPIES;
			if(fieldCode == BookField.NUM_RESERVED_COPIES) {
				fieldValue = book.numReservedCopies();
				return formatObject(fieldValue);
			}
		} else {
			throw new RuntimeException("Book code no recognized");
		}
		return -1;
	}
}