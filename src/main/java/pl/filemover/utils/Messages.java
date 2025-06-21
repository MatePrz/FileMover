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
 * Utility class providing all translatable messages and UI text for the application
 */
public class Messages {
	/**
	 * The title displayed in the application window
	 */
	public static final String APP_TITLE = "File Mover";
	/**
	 * Label for the source path input field
	 */
	public static final String SOURCE_LABEL = "Œcie¿ka Ÿród³owa";
	/**
	 * Label for the destination path input field.
	 */
	public static final String DEST_LABEL = "Œcie¿ka docelowa";
	/**
	 * Text displayed on the path selection field
	 */
	public static final String BUTTON_SELECT = "Wybierz";
	/**
	 * Title for the source directory selection
	 */
	public static final String SRC_TITLE = "Wybierz katalog Ÿród³owy";
	/**
	 * Title for the destination directory selection
	 */
	public static final String DEST_TITLE = "Wybierz katalog docelowy";
	/**
	 * Label for the file mask selection
	 */
	public static final String MASK_LABEL = "Maska plików";
	/**
	 * Text displayed on the button that initiates the copy operation
	 */
	public static final String COPY_BUTTON = "Rozpocznij proces kopiowania";
	/**
	 * Title used for error message
	 */
	public static final String ERROR_LABEL = "B³¹d";
	/**
	 * Error message displayed when paths are empty
	 */
	public static final String PATHS_EMPTY_ERROR = "Podaj œcie¿ki Ÿród³ow¹ i docelow¹";
	/**
	 * Error message displayed when source and destination paths are identical
	 */
	public static final String PATHS_SAME_ERROR = "Katalog Ÿród³owy i docelowy nie mog¹ byæ takie same";
	/**
	 * Error message displayed when the source path is invalid
	 */
	public static final String SRC_PATH_ERROR = "Nieprawid³owy katalog Ÿród³owy";
	/**
	 * Error message displayed when the destination path is invalid
	 */
	public static final String DEST_PATH_ERROR = "Nieprawid³owy katalog docelowy";
	/**
	 * Prefix for error messages
	 */
	public static final String ERROR_MSG = "Wyst¹pi³ b³¹d: ";
	/**
	 * Prefix for displaying the number of copied files
	 */
	public static final String COPIED_MSG = "Skopiowano: ";
	/**
	 * Prefix for displaying the number of skipped files
	 */
	public static final String SKIPPED_MSG = "\nPominiêto: ";
	/**
	 * Title for the completion dialog shown after the copy operation finishes
	 */
	public static final String FINISH_TITLE = "Zakoñczono kopiowanie";
	/**
	 * Prefix for file-specific error messages
	 */
	public static final String FILE_CREATE_ERROR = "Nie uda³o siê skopiowaæ pliku: ";
}
