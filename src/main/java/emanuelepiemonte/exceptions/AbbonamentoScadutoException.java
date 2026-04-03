package emanuelepiemonte.exceptions;

import java.util.UUID;

public class AbbonamentoScadutoException extends RuntimeException {
    public AbbonamentoScadutoException(UUID abbonamentoId) {
        super("L'abbonamento con ID " + abbonamentoId + " è scaduto. Rinnovalo");
    }
}
