package emanuelepiemonte.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Rivenditore")
public class RivenditoreAutorizzato extends PuntoDiEmissione {
    private String nomeAttivita;

    protected RivenditoreAutorizzato() {
    }

    public RivenditoreAutorizzato(String indirizzo, String citta, String nomeAttivita) {
        super(indirizzo, citta);
        this.nomeAttivita = nomeAttivita;
    }

    public String getNomeAttivita() {
        return nomeAttivita;
    }

    @Override
    public String toString() {
        return "RivenditoreAutorizzato{" +
                "nomeAttivita='" + nomeAttivita + '\'' +
                '}';
    }
}