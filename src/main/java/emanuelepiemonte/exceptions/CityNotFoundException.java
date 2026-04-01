package emanuelepiemonte.exceptions;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String city) {
        super("Nella città: " + city + " non ci sono punti di ritiro");
    }
}
