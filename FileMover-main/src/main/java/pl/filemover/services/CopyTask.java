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

public class CopyTask implements Callable<Boolean> {
	private final File source;
	private final File destination;
	
	public CopyTask(File source, File dest) {
        this.source = source;
        this.destination = dest;
    }
	
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
