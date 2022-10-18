package biblio.model.searchFilters;

import java.util.ArrayList;
import java.util.Collection;

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

		for (Book book : books)
		{
			Object fieldValue = book.getFieldValue(fieldCode);
			Class<?> fieldType = BookField.getFieldType(fieldCode);
			if (fieldValue == null)
				continue;
			if (int.class.equals(fieldType))
			{
				if (((Integer) fieldValue).equals(new Integer(value)))
					result.add(book);
			} else
			{
				String received = value.toLowerCase();
				String searched = fieldValue.toString().toLowerCase();
				if (searched.indexOf(received) != -1)
					result.add(book);
			} /*
			 * else if (Date.class.equals(fieldType)) { Date publicationDate;
			 * try { publicationDate = dateFormat.parse(value); } catch
			 * (ParseException e) { throw new RuntimeException(e); } if (((Date)
			 * field).equals(publicationDate)) result.add(book); }
			 */
		}
		return result;
	}

}
