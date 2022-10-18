package biblio.view.dialog;

import biblio.view.GUI;

/**
 * An abstract class for the dialogs that contains panels with information that have to be processed
 * in the events of the user pushing the OK button
 * 
 */
public abstract class BiblioEditionDialog extends BiblioOkCancelDialog {
	
	public BiblioEditionDialog(GUI gUI, String title, boolean modal) {
		super(gUI, title, modal);
	}
	
	@Override
	public BiblioEditionPanel getMainPanel() {
		return (BiblioEditionPanel) mainPanel;
	}
	
	@Override
	public void onOk() {
		if(getMainPanel().onOk())
			close();
	}
	
}
