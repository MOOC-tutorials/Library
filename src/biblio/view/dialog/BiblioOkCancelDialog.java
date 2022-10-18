package biblio.view.dialog;

import biblio.view.GUI;

/**
 * An abstract class for the dialogs that contain and OK and Cancel button
 * 
 */
public abstract class BiblioOkCancelDialog extends BiblioDialog {

	public BiblioOkCancelDialog(GUI gUI, String title, boolean modal) {
		super(gUI, title, modal);
	}
	
	
	public void onOk() {
		close();
	}
	
	public void onCancel() {
		close();
	}
	
	
	


}
