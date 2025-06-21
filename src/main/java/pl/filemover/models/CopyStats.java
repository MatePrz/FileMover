/**
 * Package with a class that handles statistics of copied and skipped files in the process
 */
package pl.filemover.models;

/**
 * 
 * @author Jakub Ceranowicz
 * @author Maksymilian Grzelecki
 * @author Mateusz Przybysz
 * @version 23.06.2025
 * A class for gathering numbers of files that have been copied and skipped in the process
 *
 */

public class CopyStats {
	/**
	 * variable with the number of copied files
	 */
	private int copied;
	
	/**
	 * variable with the number of skipped files
	 */
	private int skipped;
	
	/**
	 * Creates an object with statistics of copied and skipped files
	 * @param copied copied files
	 * @param skipped skipped files
	 */
	public CopyStats(int copied, int skipped) {
		this.copied = copied;
		this.skipped = skipped;
	}
	
	/**
	 * Getter for skipped files variable
	 * @return value of skipped
	 */
	public int getSkipped() {
		return skipped;
	}
	
	/**
	 * Setter for skipped files
	 * @param skipped skipped files value
	 */
	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}
	
	/**
	 * Getter for copied files variable
	 * @return value of copied
	 */
	public int getCopied() {
		return copied;
	}
	 /**
	  * Setter for copied files
	  * @param copied copied files value
	  */
	public void setCopied(int copied) {
		this.copied = copied;
	}
}
