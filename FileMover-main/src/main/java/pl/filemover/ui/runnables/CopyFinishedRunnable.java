package pl.filemover.ui.runnables;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import pl.filemover.utils.Messages;

public class CopyFinishedRunnable implements Runnable {
    private final int copied;
    private final int skipped;
    private final JButton copyButton;

    public CopyFinishedRunnable(int copied, int skipped, JButton copyButton) {
        this.copied = copied;
        this.skipped = skipped;
        this.copyButton = copyButton;
    }

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
