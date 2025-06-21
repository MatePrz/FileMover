/**
 * A package containing classes that handle the copying process - copying tasks, multiple threads.
 * Verification of the paths and files is also taking place
 */
package pl.filemover.services;

import java.io.File;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import pl.filemover.models.CopyStats;
import pl.filemover.ui.runnables.CopyFinishedRunnable;
import pl.filemover.ui.runnables.ErrorRunnable;
import pl.filemover.utils.Config;

/**
 * 
 * @author Jakub Ceranowicz
 * @author Maksymilian Grzelecki
 * @author Mateusz Przybysz
 * @version 23.06.2025
 * The class manages the copying of files from a source directory
 * to a destination directory, optionally filtering files based on a specified mask
 */
public class CopyThread extends Thread {
	/**
	 * file source
	 */
    private final File src;
    /**
     * file destination
     */
    private final File dest;
    /**
     * file mask
     */
    private final String mask;
    /**
     * button to press and start copying
     */
    private final JButton copyButton;

    /**
     * Constructor that creates a new CopyThread with file source, destination, file mask and button
     * @param src file source
     * @param dest file destination
     * @param mask file mask
     * @param copyButton button to press and start copying
     */
    public CopyThread(File src, File dest, String mask, JButton copyButton) {
        this.src = src;
        this.dest = dest;
        this.mask = mask;
        this.copyButton = copyButton;;
    }

    /**
     * Executes the file copying operation in a separate thread.
     * The method creates a CopyManager instance with configured thread count and copies files from source to destination directory
     */
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