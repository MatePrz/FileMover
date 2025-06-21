/**
 * Listens for copy action events triggered by the copy button in the FileSelectorWindow.
 * Validates paths and initiates the file copying process
 */
package pl.filemover.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import pl.filemover.ui.FileSelectorWindow;

/**
 * 
 * @author Jakub Ceranowicz
 * @author Maksymilian Grzelecki
 * @author Mateusz Przybysz
 * @version 23.06.2025
 * Implementation of ActionListener that handles file mask selection changes.
 */
public class MaskSelectActionListener implements ActionListener {
	/**
	 * Reference to the parent FileSelectorWindow that owns this listener
	 */
	private final FileSelectorWindow parent;
	/**
	 * The JComboBox component that triggers this listener.
     * Contains the available file mask patterns for selection.
	 */
    private final JComboBox<String> comboBox;
    
    /**
     * Constructs a new MaskSelectActionListener instance bound to the specified parent window and combo box.
     * @param parent FileSelectorWindow that owns this listener
     * @param comboBox component that triggers this listener
     */
    public MaskSelectActionListener(FileSelectorWindow parent, JComboBox<String> comboBox) {
        this.parent = parent;
        this.comboBox = comboBox;
    }
    
    /**
     * Handles the selection change event in the mask combo box.
     * Updates the selected mask pattern in the parent window when a new selection is made.
     * 
     * @param e the ActionEvent object
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object item = comboBox.getSelectedItem();
        if (item != null) {
        	parent.setSelectedMask(item.toString().toLowerCase().trim());
        }
	}

}
