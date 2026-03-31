package emanuelepiemonte.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Object id) {
        super("La risorsa con id" + id + " non è stata trovata");
    }
}
