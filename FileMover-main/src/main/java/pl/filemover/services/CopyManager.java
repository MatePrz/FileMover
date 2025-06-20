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

public class CopyManager implements AutoCloseable   {
    private final ExecutorService executor;

    public CopyManager(int threadCount) {
        this.executor = Executors.newFixedThreadPool(threadCount);
    }
    
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
    
    private Pattern buildPattern(String patternStr) {
        if ("*.*".equals(patternStr)) {
            return Pattern.compile(".*");
        } else {
            String regex = patternStr.replace(".", "\\.").replace("*", ".*");
            return Pattern.compile(regex);
        }
    }

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
