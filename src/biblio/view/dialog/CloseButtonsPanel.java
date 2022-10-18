package biblio.view.dialog;

/**
 * A panel with just a close button
 * Useful for showing read only information
 * 
 */
public class CloseButtonsPanel extends BiblioButtonsPanel {
	private BiblioDialog dialog;
	
	public CloseButtonsPanel(BiblioDialog dialog) {
		this.dialog = dialog;
		createButton("Close");
	}
	
	protected void doAction(String eventName) {
		if( eventName.equals("Close") ) 
			dialog.onClose();
	}
}
