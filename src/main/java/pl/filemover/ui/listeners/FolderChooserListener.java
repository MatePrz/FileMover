/**
 * Listens for copy action events triggered by the copy button in the FileSelectorWindow.
 * Validates paths and initiates the file copying process
 */
package pl.filemover.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import pl.filemover.ui.FileSelectorWindow;
import pl.filemover.utils.Messages;

/**
 * 
 * @author Jakub Ceranowicz
 * @author Maksymilian Grzelecki
 * @author Mateusz Przybysz
 * @version 23.06.2025
 * Implementation of ActionListener that handles folder selection operations.
 * Manages the directory chooser dialog and updates path fields accordingly.
 */
public class FolderChooserListener implements ActionListener {
	/**
	 * Reference to the parent FileSelectorWindow that owns this listener.
     * Used to access UI elements.
	 */
	private final FileSelectorWindow parent;
	/**
	 * Flag indicating if this listener handles source path selection (true)
     * or destination path selection (false).
	 */
    private final boolean isStart;

    /**
     * Constructs a new FolderChooserListener instance bound to the specified parent window
     * @param parent the FileSelectorWindow instance that owns this listener
     * @param isStart true if handling source path selection, false for destination path
     */
    public FolderChooserListener(FileSelectorWindow parent, boolean isStart) {
        this.parent = parent;
        this.isStart = isStart;
    }

    /**
     * Handles the folder selection action triggered by either the source or destination path buttons.
     * Opens a directory chooser dialog and updates the corresponding path field based on user selection.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle(isStart ? Messages.SRC_TITLE : Messages.DEST_TITLE);
        int returnVal = chooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String selectedPath = chooser.getSelectedFile().getAbsolutePath();
            if (isStart) {
                parent.getStartPathField().setText(selectedPath);
            } else {
                parent.getEndPathField().setText(selectedPath);
            }
        }
	}

}
