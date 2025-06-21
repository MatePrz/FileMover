/**
 * Main package, starting point of the program.
 */
package pl.filemover;

import pl.filemover.ui.FileSelectorWindow;

/**
 * 
 * @author Jakub Ceranowicz
 * @author Maksymilian Grzelecki
 * @author Mateusz Przybysz
 * @version 23.06.2025
 * A main class, starting point of the program.
 *
 */

public class App 
{
	/**
	 * Main function, starting point of the program.
	 * @param args parameter for main method
	 */
    public static void main( String[] args )
    {
    	new FileSelectorWindow();
    }
}