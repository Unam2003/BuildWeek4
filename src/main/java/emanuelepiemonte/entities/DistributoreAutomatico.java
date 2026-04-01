package emanuelepiemonte.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Distributore")
public class DistributoreAutomatico extends PuntoDiEmissione {
    @Column(name = "in_servizio")
    private boolean inServizio;

    protected DistributoreAutomatico() {
    }

    public DistributoreAutomatico(String indirizzo, String citta, boolean inServizio) {
        super(indirizzo, citta);
        this.inServizio = inServizio;
    }

    //GETTER E SETTER

    public boolean isInServizio() {
        return inServizio;
    }

    public void setInServizio(boolean inServizio) {
        this.inServizio = inServizio;
    }
}
