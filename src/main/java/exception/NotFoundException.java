package exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String id) {
        super("elemento con id " + id + " non trovato");
    }
}
