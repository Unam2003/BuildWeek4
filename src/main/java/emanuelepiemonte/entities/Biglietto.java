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
    private Mezzo mezzo;

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

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

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

    public void setDataAnnullamento(LocalDate dataAnnullamento) {
        this.dataAnnullamento = dataAnnullamento;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public void setRivenditore(PuntoDiEmissione rivenditore) {
        this.rivenditore = rivenditore;
    }

    @Override
    public String toString() {
        return " BIGLIETTO: " +
                "Id=" + bigliettoId +
                ", rivenditore=" + rivenditore +
                ", dataEmissione=" + dataEmissione +
                ", dataAnnullamento=" + dataAnnullamento;
    }
}
