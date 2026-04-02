package emanuelepiemonte.exceptions;

public class MezzoNonInManutenzioneException extends RuntimeException {
    public MezzoNonInManutenzioneException(String targa) {
        super("Il mezzo con targa " + targa + " non risulta in manutenzione");
    }
}
