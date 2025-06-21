/**
 * A package containing classes that handle the copying process - copying tasks, multiple threads.
 * Verification of the paths and files is also taking place
 */
package pl.filemover.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;

import pl.filemover.utils.Config;
import pl.filemover.utils.Messages;


/**
 * @author Jakub Ceranowicz
 * @author Maksymilian Grzelecki
 * @author Mateusz Przybysz
 * @version 23.06.2025
 * Class represents a single file or directory copy operation
 * Implements Callable interface
 */
public class CopyTask implements Callable<Boolean> {
	/**
	 * Source file to copy
	 */
	private final File source;
	/**
	 * Destination for the copied file
	 */
	private final File destination;
	
	/**
	 * Creates a new copy task for the specified source and destination
	 * @param source file source
	 * @param dest file destination
	 */
	public CopyTask(File source, File dest) {
        this.source = source;
        this.destination = dest;
    }
	
    /**
     * Executes the copy operation. Returns true if the copy was needed,
     * false if the target already exists and matches the source.
     * 
     * @return true if the copy operation was performed, false if skipped
     * @throws IOException if there's an error during the copy operation
     */
	@Override
	public Boolean call() throws IOException {
		if (source.isDirectory()) {
			if (!destination.exists()) {
				destination.mkdirs();
				return true;
	        }
            return false;
        }
		
		if (destination.exists() && areFilesEqual(source, destination)) {
            return false;
        }
		
		File parent = destination.getParentFile();
	    if (!parent.exists()) {
	    	parent.mkdirs();
	    }
		
        copyFile(source, destination);
        return true;
	}
	
	/**
	 * Copies a single file using buffered streams for optimal performance
     * Uses the configured buffer size from Config for efficient memory usage
	 * @param source file source
	 * @param dest file destination
	 * @throws IOException if there is an error during operation
	 */
	private void copyFile(File source, File dest) throws IOException {
        try (
            InputStream in = new BufferedInputStream(new FileInputStream(source));
            OutputStream out = new BufferedOutputStream(new FileOutputStream(dest))
        ) {
            byte[] buffer = new byte[Config.BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        catch(Exception ex) {
        	throw new IOException(Messages.FILE_CREATE_ERROR + source);
        }
    }
	
	/**
	 * Compares two files for content equality. First checks file lengths, then compares
     * byte-by-byte if lengths match
	 * @param f1 first file to compare
	 * @param f2 second file to compare
	 * @return false if files are different, true if the same
	 * @throws IOException if an error occures
	 */
	private boolean areFilesEqual(File f1, File f2) throws IOException {
        if (f1.length() != f2.length()) return false;

        try (
            InputStream in1 = new BufferedInputStream(new FileInputStream(f1));
            InputStream in2 = new BufferedInputStream(new FileInputStream(f2))
        ) {
            int b1, b2;
            while ((b1 = in1.read()) != -1) {
                b2 = in2.read();
                if (b1 != b2) return false;
            }
            return true;
        }
    }
}