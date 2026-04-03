package emanuelepiemonte.exceptions;

public class AbbonamentoNonValidoException extends RuntimeException {
    public AbbonamentoNonValidoException(String message) {
        super("Abbonamento non valido: " + message);
    }
}
