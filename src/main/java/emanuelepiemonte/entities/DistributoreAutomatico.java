package emanuelepiemonte.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Distributore")
public class DistributoreAutomatico extends PuntoDiEmissione {
    private boolean inServizio;

    protected DistributoreAutomatico() {
    }

    public DistributoreAutomatico(String indirizzo, String citta, boolean inServizio) {
        super(indirizzo, citta);
        this.inServizio = inServizio;
    }


}
