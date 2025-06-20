package pl.filemover.services;

import java.io.File;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import pl.filemover.models.CopyStats;
import pl.filemover.ui.runnables.CopyFinishedRunnable;
import pl.filemover.ui.runnables.ErrorRunnable;
import pl.filemover.utils.Config;

public class CopyThread extends Thread {
    private final File src;
    private final File dest;
    private final String mask;
    private final JButton copyButton;

    public CopyThread(File src, File dest, String mask, JButton copyButton) {
        this.src = src;
        this.dest = dest;
        this.mask = mask;
        this.copyButton = copyButton;;
    }

    @Override
    public void run() {
        try (CopyManager manager = new CopyManager(Config.THREAD_COUNT)){
            final CopyStats stats = manager.copyDirectory(src, dest, mask);
            SwingUtilities.invokeLater(new CopyFinishedRunnable(stats.getCopied(), stats.getSkipped(), copyButton));
        } catch (Exception ex) {
            ex.printStackTrace();
            SwingUtilities.invokeLater(new ErrorRunnable(ex, copyButton));
        }
    }
}
