package biblio.model.searchFilters;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import biblio.model.Book;
import biblio.model.BookField;
import biblio.model.BookRepository;

/**
 * Utility class for searching in a list of books according to a specific value
 * of a book field
 * 
 */
public class FilterByBookField extends GenericSearchFilter
{

	public FilterByBookField(BookRepository bookRepository)
	{
		super(bookRepository);
	}

	/**
	 * Methode generique qui implemente l'algoritme de recherche
	 * 
	 * @sinf1151 ce code ne sera PAS donne aux etudiants; c'est la solution
	 *           "ideale"
	 */
	public Collection<Book> applyFilter(int fieldCode, String value)
	{
		Collection<Book> books = bookRepository.getBooks();
		Collection<Book> result = new ArrayList<Book>();

		for (Book book : books) {
			Object fieldValue;
			switch (fieldCode) {
			case BookField.AUTHORS: 
				fieldValue = book.getAuthorsString();
				break;
			case BookField.TITLE: 
				fieldValue = book.getTitle();
				break;
			case BookField.PUBLICATION_DATE: 
				fieldValue = book.getPublicationDate();
				break;
			case BookField.SERIE: 
				fieldValue = book.getSerie();
				break;
			case BookField.NUMBER_PAGES: 
				fieldValue = book.getNumberPages();
				break;
			case BookField.REFERENCE: 
				fieldValue = book.getReference();
				break;
			case BookField.EDITOR: 
				fieldValue = book.getEditor();
				break;
			case BookField.NUM_BORROWED_COPIES: 
				fieldValue = book.numBorrowedCopies();
				break;
			case BookField.NUM_AVAILABLE_COPIES: 
				fieldValue = book.numAvailableCopies();
				break;
			case BookField.NUM_RESERVED_COPIES: 
				fieldValue = book.numReservedCopies();
				break;
			default: throw new RuntimeException("Book code no recognized");
			}
			Class<?> fieldType = BookField.getFieldType(fieldCode);
			if (int.class.equals(fieldType))
			{
				if (fieldValue == null)
					continue;
				else if (fieldType == null)
					continue;
				if (((Integer) fieldValue).equals(Integer.parseInt(value)))
					result.add(book);
			}  else
			{
				if (fieldValue == null)
					continue;
				else if (fieldType == null)
					continue;
				
				String received = value.toLowerCase();
				String searched = fieldValue.toString().toLowerCase();
				if (searched.indexOf(received) != -1)
					result.add(book);
			} 
		}
		return result;
	}

}
