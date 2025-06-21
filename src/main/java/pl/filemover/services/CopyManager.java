/**
 * A package containing classes that handle the copying process - copying tasks, multiple threads.
 * Verification of the paths and files is also taking place
 */
package pl.filemover.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import pl.filemover.models.CopyStats;

/**
 * 
 * @author Jakub Ceranowicz
 * @author Maksymilian Grzelecki
 * @author Mateusz Przybysz
 * @version 23.06.2025
 * A service class that handles file copying operations using multiple threads.
 * Provides functionality to copy files from source directory 
 * to destination directory while maintaining directory structure.
 *
 */

public class CopyManager implements AutoCloseable   {
	/**
	 * variable of the ExecutorService type
	 */
    private final ExecutorService executor;

    /**
     * Constructor with one parameter, the number of threads
     * @param threadCount constructor receives the number of threads in this variable
     */
    public CopyManager(int threadCount) {
        this.executor = Executors.newFixedThreadPool(threadCount);
    }
    
    /**
     * a method that copies files matching specified pattern from source directory to destination directory
     * @param src file source parameter
     * @param dest file destination parameter
     * @param patternStr string file names pattern, for example "*.txt"
     * @return method returns the number of copied and skipped files
     * @throws InterruptedException if thread operations are interrupted
     * @throws Exception if any error occurs during file copying
     */
    public CopyStats copyDirectory(File src, File dest, String patternStr) throws InterruptedException, Exception {
    	Pattern pattern = buildPattern(patternStr);
        List<Future<Boolean>> results = new ArrayList<>();
        walkAndSubmit(src, dest, pattern, results);
        
        int copied = 0;
        int skipped = 0;
        
        for (Future<Boolean> result : results) {
            try {
            	Boolean wasCopied = result.get();
                if (wasCopied) 
                	copied++;
                else 
                	skipped++;
            } catch (ExecutionException e) {
            	skipped++;
            	throw (Exception) e.getCause();
            }
        }
        return new CopyStats(copied, skipped);
    }
    
    /**
     * Recursively walks through directory tree and submits copy tasks to executor.
     * Only files matching specified pattern are submitted for copying
     * @param currentSrc file source to copy from
     * @param currentDest destination source to copy to
     * @param pattern file names pattern
     * @param results List that stores results of copying operations
     */
    private void walkAndSubmit(File currentSrc, File currentDest, Pattern pattern, List<Future<Boolean>> results) {
    	results.add(executor.submit(new CopyTask(currentSrc, currentDest)));

        File[] files = currentSrc.listFiles();
        if (files == null) return;
        for (File file : files) {
            File newDest = new File(currentDest, file.getName());
            if (file.isDirectory()) {
                walkAndSubmit(file, newDest, pattern, results);
            } else if (pattern.matcher(file.getName()).matches()) {
                results.add(executor.submit(new CopyTask(file, newDest)));
            }
        }
    }
    
    /**
     * Converts pattern string to regular expression pattern
     * @param patternStr pattern string, for example "*.txt"
     * @return compiled Pattern object
     */
    private Pattern buildPattern(String patternStr) {
        if ("*.*".equals(patternStr)) {
            return Pattern.compile(".*");
        } else {
            String regex = patternStr.replace(".", "\\.").replace("*", ".*");
            return Pattern.compile(regex);
        }
    }

    /**
     * Shuts down the executor service
     */
	@Override
	public void close(){
		try {
			executor.shutdown();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}