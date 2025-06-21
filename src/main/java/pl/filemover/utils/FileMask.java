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
 * Enumerator that represents common file extensions with their string patterns
 */
public enum FileMask {
	/**
	 * All extensions included
	 */
    ALL("*.*"),
    /**
     * .txt extension files
     */
    TXT("*.txt"),
    /**
     * .pdf extension files
     */
    PDF("*.pdf"),
    /**
     * .doc extension files
     */
    DOC("*.doc"),
    /**
     * .docx extension files
     */
    DOCX("*.docx"),
    /**
     * .xls extension files
     */
    XLS("*.xls"),
    /**
     * .xlsx extension files
     */
    XLSX("*.xlsx"),
    /**
     * .ppt extension files
     */
    PPT("*.ppt"),
    /**
     * .pptx extension files
     */
    PPTX("*.pptx"),
    /**
     * .jpg extension files
     */
    JPG("*.jpg"),
    /**
     * .jpeg extension files
     */
    JPEG("*.jpeg"),
    /**
     * .png extension files
     */
    PNG("*.png"),
    /**
     * .gif extension files
     */
    GIF("*.gif"),
    /**
     * .bmp extension files
     */
    BMP("*.bmp"),
    /**
     * .mp3 extension files
     */
    MP3("*.mp3"),
    /**
     * .wav extension files
     */
    WAV("*.wav"),
    /**
     * .mp4 extension files
     */
    MP4("*.mp4"),
    /**
     * .avi extension files
     */
    AVI("*.avi"),
    /**
     * .mkv extension files
     */
    MKV("*.mkv"),
    /**
     * .zip extension files
     */
    ZIP("*.zip"),
    /**
     * .rar extension files
     */
    RAR("*.rar"),
    /**
     * .7z extension files
     */
    SEVEN_Z("*.7z"),
    /**
     * .exe extension files
     */
    EXE("*.exe"),
    /**
     * .jar extension files
     */
    JAR("*.jar"),
    /**
     * .csv extension files
     */
    CSV("*.csv"),
    /**
     * .json extension files
     */
    JSON("*.json"),
    /**
     * .xml extension files
     */
    XML("*.xml"),
    /**
     * .html extension files
     */
    HTML("*.html"),
    /**
     * .css extension files
     */
    CSS("*.css"),
    /**
     * .js extension files
     */
    JS("*.js"),
    /**
     * .java extension files
     */
    JAVA("*.java"),
    /**
     * .c extension files
     */
    C("*.c"),
    /**
     * .cpp extension files
     */
    CPP("*.cpp"),
    /**
     * .py extension files
     */
    PY("*.py"),
    /**
     * .sql extension files
     */
    SQL("*.sql");

	/**
	 * Pattern used for matching file types
	 */
    private final String pattern;

    /**
     * Constructs a new FileMask with the specified pattern
     * @param pattern file extension pattern
     */
    FileMask(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Returns the pattern associated with this file mask
     * @return file extension pattern
     */
    public String getPattern() {
        return pattern;
    }
}