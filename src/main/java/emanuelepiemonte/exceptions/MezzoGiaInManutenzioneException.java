package emanuelepiemonte.exceptions;

public class MezzoGiaInManutenzioneException extends RuntimeException {
    public MezzoGiaInManutenzioneException(String targa) {
        super("Il mezzo con targa " + targa + " è già in manutenzione. Non puoi inserirlo di nuovo!");
    }
}
