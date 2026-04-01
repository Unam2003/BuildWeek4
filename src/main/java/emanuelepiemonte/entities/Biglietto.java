package emanuelepiemonte.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "biglietto")
public class Biglietto {
    @Id
    @GeneratedValue
    @Column(name = "biglietto_id")
    private UUID bigliettoId;

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


    // Al momento manca modo di abbinare biglietto al mezzo setter necessario aggiunto ... RIFLETTUTO: Questo per che il modo che abbiamo al momento per validare il biglietto e
    // associarlo dopo al mezzo .. quindi il biglietto non puo'nascere con il mezzo nel costruttore.. ma parte da null
    // Pero'a sto punto aggiungo un UPDATE nel biglietto DAO che non c e'

    public PuntoDiEmissione getRivenditore() {
        return rivenditore;
    }

    public UUID getBigliettoId() {
        return bigliettoId;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public LocalDate getDataAnnullamento() {
        return dataAnnullamento;
    }

    public Mezzo getMezzoId() {
        return mezzoId;
    }

    public void setMezzoId(Mezzo mezzoId) {
        this.mezzoId = mezzoId;
    }

    // aggiungo mezzo se no non vedo update

    @Override
    public String toString() {
        return "Biglietto{" +
                "abbonamentoId=" + bigliettoId +
                ", rivenditore=" + rivenditore +
                ", dataEmissione=" + dataEmissione +
                ", dataAnnullamento=" + dataAnnullamento +
                ", mezzoId=" + mezzoId.getTarga() +
                '}';
    }
}
