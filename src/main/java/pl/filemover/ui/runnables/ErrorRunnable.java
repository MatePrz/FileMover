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
 * A Runnable implementation that handles error notification and UI state management
 * when file operations fail. Displays error messages and restores button functionality.
 */
public class ErrorRunnable implements Runnable {
	/**
	 * The exception that caused the error condition
	 */
    private final Exception exception;
    /**
     * The copy button to be re-enabled after error occurs
     */
    private final JButton copyButton;

    /**
     * Constructs a new ErrorRunnable instance with the specified error and button reference
     * @param exception the exception that occurred during the operation
     * @param copyButton the button to be re-enabled after the error
     */
    public ErrorRunnable(Exception exception, JButton copyButton) {
        this.exception = exception;
        this.copyButton = copyButton;
    }

    /**
     * Displays an error message dialog with the exception details and re-enables the copy button
     */
    @Override
    public void run() {
        JOptionPane.showMessageDialog(
            null,
            Messages.ERROR_MSG + exception.getMessage(),
            Messages.ERROR_LABEL,
            JOptionPane.ERROR_MESSAGE
        );
        copyButton.setEnabled(true);
    }
}
