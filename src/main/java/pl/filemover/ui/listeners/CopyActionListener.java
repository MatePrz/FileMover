/**
 * Listens for copy action events triggered by the copy button in the FileSelectorWindow.
 * Validates paths and initiates the file copying process
 */
package pl.filemover.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import pl.filemover.services.CopyThread;
import pl.filemover.ui.FileSelectorWindow;
import pl.filemover.utils.Messages;

/**
 * 
 * @author Jakub Ceranowicz
 * @author Maksymilian Grzelecki
 * @author Mateusz Przybysz
 * @version 23.06.2025
 * Implementation of ActionListener that handles the file copying operation.
 * Coordinates between the UI components and the background copy process.
 */
public class CopyActionListener implements ActionListener{
	/**
	 * Reference to the parent FileSelectorWindow that contains the copy button
	 */
	private final FileSelectorWindow parent;

	/**
	 * Constructs a new CopyActionListener instance bound to the specified parent window
	 * @param parent FileSelectorWindow that contains the copy button
	 */
    public CopyActionListener(FileSelectorWindow parent) {
        this.parent = parent;
    }
    
    /**
     * Handles the copy action event triggered by the copy button.
     * Performs validation checks and initiates the file copying process
     * 
     * @param e the ActionEvent object
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton copyButton = parent.getCopyButton();
		copyButton.setEnabled(false);

        String srcPath = parent.getStartPathField().getText();
        String destPath = parent.getEndPathField().getText();
        String mask = parent.getSelectedMask();
        
        if (srcPath.isEmpty() || destPath.isEmpty()) {
            JOptionPane.showMessageDialog(parent, Messages.PATHS_EMPTY_ERROR, Messages.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
            copyButton.setEnabled(true);
            return;
        }
        
        File src = new File(srcPath);
        File dest = new File(destPath);
        
        if (srcPath.equalsIgnoreCase(destPath)) {
            JOptionPane.showMessageDialog(parent, Messages.PATHS_SAME_ERROR, Messages.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
            copyButton.setEnabled(true);
            return;
        }
        
        if (!src.exists() || !src.isDirectory()) {
            JOptionPane.showMessageDialog(parent, Messages.SRC_PATH_ERROR, Messages.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
            copyButton.setEnabled(true);
            return;
        }
        
        if (dest.getName().contains(".")) {
            JOptionPane.showMessageDialog(parent, Messages.DEST_PATH_ERROR, Messages.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
            copyButton.setEnabled(true);
            return;
        }
        
        new CopyThread(src, dest, mask, copyButton).start();
	}

}