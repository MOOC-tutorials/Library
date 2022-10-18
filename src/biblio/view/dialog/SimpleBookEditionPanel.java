package biblio.view.dialog;


import javax.swing.JTextField;

import biblio.BiblioSystem;
import biblio.model.Book;

/**
 * Panel that shows the basic book information
 * this class can be useful if someone decide to make an interface for modifying books
 * 
 */
public class SimpleBookEditionPanel extends BiblioEditionPanel {

	private JTextField bookRefTextField;
	private JTextField bookTitleTextField;
	private JTextField authorsTextField;
	private JTextField publicationDateTextField;
	private JTextField serieTextField;
	private JTextField numberPagesTextField;
	private JTextField editorTextField;
	private JTextField registeredCopiesTextField;
	private JTextField availableCopiesTextField;
	private JTextField borrowedCopiesTextField;
	private JTextField reservedCopiesTextField;
	
	protected Book book;
	
	public SimpleBookEditionPanel() {
		
	}
	
	public SimpleBookEditionPanel(Book book) {
		setBook(book);
		bookRefTextField.setEditable(false);
	}

	public Book getBook() {
		return book;
	}



	public void setBook(Book book) {
		this.book = book;
		buildFieldsFromBook();
	}

	public void buildFieldsFromBook() {
		bookRefTextField.setText(book.getReference());
		bookTitleTextField.setText(book.getTitle());
		authorsTextField.setText(book.getAuthorsString());
		publicationDateTextField.setText(book.getPublicationDate()!=null?BiblioSystem.DATE_FORMAT.format(book.getPublicationDate()):"");
		serieTextField.setText(book.getSerie());
		numberPagesTextField.setText(new Integer(book.getNumberPages()).toString());
		editorTextField.setText(book.getEditor());
		registeredCopiesTextField.setText(""+book.numCopies());
		availableCopiesTextField.setText(""+book.numAvailableCopies());
		borrowedCopiesTextField.setText(""+book.numBorrowedCopies());
		reservedCopiesTextField.setText(""+book.numReservedCopies());
	}


	@Override
	protected void createElements() {
		bookRefTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Book reference", bookRefTextField);
		
		bookTitleTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Book Title", bookTitleTextField);
		
		authorsTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Authors", authorsTextField);
		
		publicationDateTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Publication Date", publicationDateTextField);
		
		serieTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Serie", serieTextField);
		
		numberPagesTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Number of Pages", numberPagesTextField);
		
		editorTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Editor", editorTextField);
		
		registeredCopiesTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Registered copies", registeredCopiesTextField);
		
		availableCopiesTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Available copies", availableCopiesTextField);
		
		borrowedCopiesTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Borrowed copies", borrowedCopiesTextField);
		
		reservedCopiesTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Reserved copies", reservedCopiesTextField);
	}

	//if someone wants to implement the creation of new books, this class could work pretty well if this method is implemented
	@Override
	public boolean onOk() {
		return true;
	}
	

	

	
}
