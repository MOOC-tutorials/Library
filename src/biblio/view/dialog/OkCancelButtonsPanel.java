package biblio.view.dialog;

/**
 * Panel that contains and OK and Cancel button
 * 
 */
public class OkCancelButtonsPanel extends BiblioButtonsPanel  {

	private BiblioOkCancelDialog okCancelDialog;
	
	public OkCancelButtonsPanel(BiblioOkCancelDialog okCancelDialog) {
		this.okCancelDialog = okCancelDialog;
		createButton("Ok");
		createButton("Cancel");
	}

	protected void doAction(String eventName) {
		if( eventName.equals("Ok") ) 
			okCancelDialog.onOk();
		else if( eventName.equals("Cancel") )
			okCancelDialog.onCancel();
	}

}
