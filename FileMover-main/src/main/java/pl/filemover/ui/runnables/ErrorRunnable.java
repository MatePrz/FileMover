package pl.filemover.ui.runnables;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import pl.filemover.utils.Messages;

public class ErrorRunnable implements Runnable {
    private final Exception exception;
    private final JButton copyButton;

    public ErrorRunnable(Exception exception, JButton copyButton) {
        this.exception = exception;
        this.copyButton = copyButton;
    }

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
