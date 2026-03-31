package emanuelepiemonte.enums;

public enum TipoDiMezzo {
    TRAM(70),
    AUTOBUS(100);

    private final int valore;

    private TipoDiMezzo(int valore) {
        this.valore = valore;
    }

    public int getValore() {
        return this.valore;
    }
}