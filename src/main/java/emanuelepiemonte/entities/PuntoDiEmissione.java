package emanuelepiemonte.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "punti_di_emissione")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_punto", discriminatorType = DiscriminatorType.STRING)
public abstract class PuntoDiEmissione {
    @Id
    @GeneratedValue
    @Column(name = "rivenditore_id")
    private UUID emissioneId;
    private String indirizzo;
    private String citta;

    @OneToMany(mappedBy = "rivenditore")
    private List<Abbonamento> abbonamenti;

    protected PuntoDiEmissione() {

    }

    public PuntoDiEmissione(String indirizzo, String citta) {
        this.indirizzo = indirizzo;
        this.citta = citta;
    }

    public UUID getEmissioneId() {
        return emissioneId;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    @Override
    public String toString() {
        return "PuntoDiEmissione{" +
                "emissioneId=" + emissioneId +
                ", indirizzo='" + indirizzo + '\'' +
                ", citta='" + citta + '\'' +
                '}';
    }
}
