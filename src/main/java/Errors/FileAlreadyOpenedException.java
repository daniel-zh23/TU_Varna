package Errors;

public class FileAlreadyOpenedException extends Exception{
    public FileAlreadyOpenedException(String message) {
        super(message);
    }
}
