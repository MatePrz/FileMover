/**
 * Listens for copy action events triggered by the copy button in the FileSelectorWindow.
 * Validates paths and initiates the file copying process
 */
package pl.filemover.ui.listeners;

import javax.swing.JComboBox;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 * 
 * @author Jakub Ceranowicz
 * @author Maksymilian Grzelecki
 * @author Mateusz Przybysz
 * @version 23.06.2025
 * Implementation of PopupMenuListener that handles combo box popup events
 */
public class MaskSelectPopupListener implements PopupMenuListener {
	/**
	 * The JComboBox component that triggers this listener
	 */
	private final JComboBox<String> comboBox;

	/**
	 * Constructs a new MaskSelectPopupListener instance bound to the specified combo box
	 * @param comboBox the JComboBox instance that owns this listener
	 */
    public MaskSelectPopupListener(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }
    
    /**
     * Called before the popup menu becomes visible.
     * Clears the editor's content to ensure a clean state when selecting a new mask pattern.
     * 
     * @param e the PopupMenuEvent object
     */
	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		comboBox.getEditor().setItem("");
	}

	/**
	 * Currently no action is taken at this point.
     * 
     * @param e the PopupMenuEvent object
	 */
	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

	/**
	 * Currently no action is taken at this point.
     * 
     * @param e the PopupMenuEvent object
	 */
	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {}

}