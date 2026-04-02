package emanuelepiemonte.exceptions;

import java.util.UUID;

public class TesseraScadutaException extends RuntimeException {
    public TesseraScadutaException(UUID tesseraId) {
        super("Errore: La tessera con ID " + tesseraId + " è scaduta! Bisogna rinnovarla");
    }
}
