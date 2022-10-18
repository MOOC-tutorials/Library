package biblio.test;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import biblio.model.Book;
import biblio.model.BookField;


public class Phase1Test extends BiblioTest{

	@Test
	public void testLoadingOfBooks() {
		assertEquals(bookRepository.getBooks().size(), 5070);
		Book book = getFirstBook();
		assertEquals(book.getAuthorsString(), "Bauer, F. L., Goos, G.");
		assertEquals(book.getEditor(), "Springer-Verlag Berlin");
		assertEquals(book.getNumberPages(), 213);
		assertEquals(book.getReference(), "010-54-01");
		assertEquals(book.getSerie(), "Heidelberger Tashenbucher");
		assertEquals(book.getTitle(), "Informatik, Eine einfuhrende ubersicht: Erster Teil");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(book.getPublicationDate());	
		assertEquals(calendar.get(Calendar.YEAR), 1971);
	}
	
	
	@Test
	public void testSearchByReference() {
		Collection<Book> implementedResult;
		implementedResult = bookRepository.search(BookField.REFERENCE, "010");
		//Collection<Book> expectedResult = applyFilter(BookField.REFERENCE, "010");
		//compareSearch("Search by Reference: \"010\"", expectedResult, implementedResult);
		verifySearchExtension("Search by Reference: \"010\"", 59, implementedResult);
	}

	@Test
	public void testBookToString() {
		String reference = "010-93-04";
		String author1 = "White Ron";
		String author2 = "Jackson Michel";
		String title = "How computers work";
		String editor = "Ziff-Davis Press";
		Date publicationDate = new Date();
		int numberPages = 202;
		String serie = "PC Computing";
		int numCopies = 1;
		
		Book book = new Book();
		book.setReference(reference);
		book.addAuthor(author1);
		book.addAuthor(author2);
		book.setTitle(title);
		book.setEditor(editor);
		book.setPublicationDate(publicationDate);
		book.setNumberPages(numberPages);
		book.setSerie(serie);
		book.addCopies(numCopies);
		
		String bookFields[] = book.toString().split("\n");
		assertEquals(bookFields.length, 8);
		assertEquals(bookFields[0], "%Reference " + reference);
		assertEquals(bookFields[1], "%Authors " + author1 + ", " + author2);
		assertEquals(bookFields[2], "%Title " + title);
		assertEquals(bookFields[3], "%Editor " + editor);
		assertEquals(bookFields[4], "%Date " + publicationDate);
		assertEquals(bookFields[5], "%Pages " + numberPages);
		assertEquals(bookFields[6], "%Series " + serie);
		assertEquals(bookFields[7], "%Copies " + numCopies);
	}
}
