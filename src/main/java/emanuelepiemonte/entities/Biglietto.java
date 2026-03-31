package emanuelepiemonte.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "biglietto")
public class Biglietto {
    @Id
    @GeneratedValue
    @Column(name = "abbonamento_id")
    private UUID abbonamentoId;

    @ManyToOne
    @JoinColumn(name = "rivenditore_id")
    private PuntoDiEmissione rivenditore;

    @Column(name = "data_emissione")
    private LocalDate dataEmissione;

    @Column(name = "data_annullamento")
    private LocalDate dataAnnullamento;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzoId;

    public Biglietto() {
    }

    public Biglietto(PuntoDiEmissione rivenditore, LocalDate dataEmissione, LocalDate dataAnnullamento) {
        this.rivenditore = rivenditore;
        this.dataEmissione = dataEmissione;
        this.dataAnnullamento = dataAnnullamento;
    }

    public Biglietto(PuntoDiEmissione rivenditore, LocalDate dataAnnullamento) {
        this.rivenditore = rivenditore;
        this.dataEmissione = LocalDate.now();
        this.dataAnnullamento = dataAnnullamento;
    }

    public PuntoDiEmissione getRivenditore() {
        return rivenditore;
    }

    public UUID getAbbonamentoId() {
        return abbonamentoId;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public LocalDate getDataAnnullamento() {
        return dataAnnullamento;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "abbonamentoId=" + abbonamentoId +
                ", rivenditore=" + rivenditore +
                ", dataEmissione=" + dataEmissione +
                ", dataAnnullamento=" + dataAnnullamento +
                '}';
    }
}
