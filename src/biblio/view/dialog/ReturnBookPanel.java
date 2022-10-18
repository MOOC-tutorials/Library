package biblio.view.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JTextField;

import biblio.BiblioSystem;
import biblio.model.Book;
import biblio.model.BookCopy;
import biblio.model.Client;
import biblio.util.SwingUtility;

/**
 * Panel implementing the returning of a book
 * 
 */
public class ReturnBookPanel extends SimpleBookEditionPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private BiblioCombo<Client> borrowersCombo;
	private JTextField fineTextField;
	private JTextField dateReservationTextField;
	private JTextField deadLineTextField;
	private JTextField realReturnDateTextField;
	
	public ReturnBookPanel(Book book) {
		super(book);
		readOnlyMode();
		realReturnDateTextField.setEditable(true);
	}
	
	@Override
	public void setBook(Book book) {
		super.setBook(book);
		borrowersCombo.setList(book.borrowedBy());
	}
	
	@Override
	protected void createElements() {
		super.createElements();

		borrowersCombo = new BiblioCombo<Client>();
		borrowersCombo.addActionListener(this);
		add("Clients who can return this book", borrowersCombo);
		
		dateReservationTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Reservation date", dateReservationTextField);
		
		deadLineTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Deadline for returning the book", deadLineTextField);
		
		fineTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		add("Fine (if returned now)", fineTextField);
		
		realReturnDateTextField = new JTextField(SIZE_EDITABLE_COMPONENTS);
		realReturnDateTextField.addActionListener(this);
		add("Returning date", realReturnDateTextField);
	}

	public Client getSelectedClient() {
		return borrowersCombo.getSelectedItem();
	}

	public BookCopy getCopy() {
		return book.getBorrowedCopy(getSelectedClient());
	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(borrowersCombo))
			onChangeBorrower();
		else if(event.getSource().equals(realReturnDateTextField))
			onChangeRealReturnDate();
	}

	private void onChangeRealReturnDate() {
		updateFine();
	}

	private void onChangeBorrower() {
		updateReservationDate();
		updateDeadLine();
		setRealReturnDate(new Date());
		updateFine();
	}
	
	public void updateReservationDate() {
		dateReservationTextField.setText(BiblioSystem.DATE_FORMAT.format(getCopy().getBorrowDate()));
	}
	
	public void updateDeadLine() {
		deadLineTextField.setText(BiblioSystem.DATE_FORMAT.format(getCopy().getReturnDate()));
	}
	
	public boolean updateFine() {
		Date returnDate;
		try {
			returnDate = getRealReturnDate();
			if(!checkRealReturnDate(returnDate)) {
				SwingUtility.showWarningMessage("The return date cannot be set before the borrowing of the book");
				return false;
			}
			else {
				setFine(calculateFine(returnDate));
				return true;
			}
		} catch (ParseException e) {
			SwingUtility.showWarningMessage("Please correct the format of the date");
			return false;
		} 
	}

	public void setFine(double fine) {
		fineTextField.setText(""+fine);
	}

	public double getFine() {
		return Double.parseDouble(fineTextField.getText());
	}

	public double calculateFine(Date realReturnDate) {
		return getCopy().calculateFine(realReturnDate);
	}
	
	public boolean checkRealReturnDate(Date realReturnDate) {
		return !realReturnDate.before(getCopy().getBorrowDate());
	}
	
	public void setRealReturnDate(Date date) {
		realReturnDateTextField.setText(BiblioSystem.DATE_FORMAT.format(date));
	}
	
	public Date getRealReturnDate() throws ParseException {
		return BiblioSystem.DATE_FORMAT.parse(realReturnDateTextField.getText());
	}
	
}
