/**
 *  A Runnable implementation that handles the completion notification of file copying operations
 *  Displays a message dialog with copy statistics and re-enables the copy button
 */
package pl.filemover.ui.runnables;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import pl.filemover.utils.Messages;

/**
 * 
 * @author Jakub Ceranowicz
 * @author Maksymilian Grzelecki
 * @author Mateusz Przybysz
 * @version 23.06.2025
 * Implementation of Runnable that provides completion feedback for file copy operations.
 * Updates the UI with copy statistics and restores button functionality.
 */
public class CopyFinishedRunnable implements Runnable {
	/**
	 * number of copied files
	 */
    private final int copied;
    /**
     * number of skipped files
     */
    private final int skipped;
    /**
     * The copy button to be re-enabled after the operation completes
     */
    private final JButton copyButton;

    /**
     * Constructs a new CopyFinishedRunnable instance with the specified copy statistics and button reference
     * @param copied number of copied files
     * @param skipped number of skipped files
     * @param copyButton the button to be re-enabled after completion
     */
    public CopyFinishedRunnable(int copied, int skipped, JButton copyButton) {
        this.copied = copied;
        this.skipped = skipped;
        this.copyButton = copyButton;
    }

    /**
     * Displays a message dialog showing the copy operation statistics and re-enables the copy button
     */
    @Override
    public void run() {
        JOptionPane.showMessageDialog(
        		null,
                Messages.COPIED_MSG + copied + Messages.SKIPPED_MSG + skipped,
                Messages.FINISH_TITLE,
                JOptionPane.INFORMATION_MESSAGE
        );
        copyButton.setEnabled(true);
    }
}