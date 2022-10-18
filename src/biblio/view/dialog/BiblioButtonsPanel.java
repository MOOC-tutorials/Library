package biblio.view.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * A abstract class for panels containing buttons
 * 
 */
public abstract class BiblioButtonsPanel extends BiblioPanel implements ActionListener {
	protected void createButton(String buttonName) {
		JButton button = new JButton(buttonName);
		button.addActionListener(this);
		add(button);
	}

	public void actionPerformed(ActionEvent event) {
		String eventName = (String)event.getActionCommand();
		doAction(eventName);
		}

	protected abstract void doAction(String eventName);
}
