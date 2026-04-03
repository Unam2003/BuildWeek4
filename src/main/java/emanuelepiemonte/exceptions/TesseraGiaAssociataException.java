package emanuelepiemonte.exceptions;

import java.util.UUID;

public class TesseraGiaAssociataException extends RuntimeException {
    public TesseraGiaAssociataException(UUID utenteId) {
        super("Errore: L'utente con ID " + utenteId + " ha già una tessera attiva!");
    }
}
