package biblio.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Utility class for operations related to swing graphic libraries
 * 
 */
public class SwingUtility {
	
	public static File selectFile(int option) {
		File file = null;
		JFileChooser fc = new JFileChooser();
		fc.setDialogType(option);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
		}
		return file;
	}
	
	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error Message", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showWarningMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Warning Message", JOptionPane.WARNING_MESSAGE);
	}
	
	public static boolean confirm(String question) {
		return JOptionPane.showConfirmDialog(null, 
				question,
				"Please confirm",
				JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION;
	}
	
	public static String askValue(String message) {
		String value = (String) JOptionPane.showInputDialog(null,
				message, "Input please",
				JOptionPane.QUESTION_MESSAGE, null, null, "");
		return value;
	}
}
