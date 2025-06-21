/**
 * Configuration classes containing static constants for file operation parameters.
 * These values control the performance characteristics of file operations.
 */
package pl.filemover.utils;

/**
 * 
 * @author Jakub Ceranowicz
 * @author Maksymilian Grzelecki
 * @author Mateusz Przybysz
 * @version 23.06.2025
 * Utility class providing configuration constants for file operations
 */
public class Config {
	/**
     * Number of threads to use for concurrent file operations.
     */
	public static final int THREAD_COUNT = 10;
	/**
	 * Size of the buffer used for file operations in bytes.
     * Set to 32 KB (32 * 1024 bytes) for optimal performance.
	 */
	public static final int BUFFER_SIZE = 32 * 1024; // 32 KB
}
