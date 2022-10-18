package biblio.model.searchFilters;

import biblio.model.BookRepository;

/**
 * Utility class for searching in a list of books according to different
 * parameters
 * 
 */
public abstract class GenericSearchFilter
{

	protected BookRepository bookRepository;

	public GenericSearchFilter(BookRepository bookRepository)
	{
		this.bookRepository = bookRepository;
	}

}
