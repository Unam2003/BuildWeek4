package emanuelepiemonte.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Rivenditore")
public class RivenditoreAutorizzato extends PuntoDiEmissione {
    @Column(name = "nome_attivita")
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
        return " RIVENDITORE AUTORIZZATO: " +
                "Nome attività=" + nomeAttivita +
                " Citta=" + super.getCitta() +
                " Indirizzo=" + super.getIndirizzo() +
                " Id=" + super.getEmissioneId();
    }

}