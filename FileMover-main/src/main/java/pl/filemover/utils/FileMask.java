package pl.filemover.utils;

public enum FileMask {
    ALL("*.*"),
    TXT("*.txt"),
    PDF("*.pdf"),
    DOC("*.doc"),
    DOCX("*.docx"),
    XLS("*.xls"),
    XLSX("*.xlsx"),
    PPT("*.ppt"),
    PPTX("*.pptx"),
    JPG("*.jpg"),
    JPEG("*.jpeg"),
    PNG("*.png"),
    GIF("*.gif"),
    BMP("*.bmp"),
    MP3("*.mp3"),
    WAV("*.wav"),
    MP4("*.mp4"),
    AVI("*.avi"),
    MKV("*.mkv"),
    ZIP("*.zip"),
    RAR("*.rar"),
    SEVEN_Z("*.7z"),
    EXE("*.exe"),
    JAR("*.jar"),
    CSV("*.csv"),
    JSON("*.json"),
    XML("*.xml"),
    HTML("*.html"),
    CSS("*.css"),
    JS("*.js"),
    JAVA("*.java"),
    C("*.c"),
    CPP("*.cpp"),
    PY("*.py"),
    SQL("*.sql");

    private final String pattern;

    FileMask(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
