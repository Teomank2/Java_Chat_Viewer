package chatviewer;

public class Error_Handler {
    public static class FileReadException extends Exception {
        public FileReadException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class ParseException extends Exception {
        public ParseException(String message, Throwable cause) {
            super(message, cause);
        }

        public ParseException(String message) {
            super(message);
        }
    }

    public static class UnknownFormatException extends Exception {
        public UnknownFormatException(String message) {
            super(message);
        }
    }
}