package emanuelepiemonte.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tessera")

public class Tessera {

    @Id
    @GeneratedValue
    @Column(name = "tessera_id")
    private UUID tesseraId;
    @Column(name = "data_emissione")
    private LocalDate dataEmissione;
    @Column(name = "data_scadenza")
    private LocalDate data_scadenza;
    @OneToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    protected Tessera() {
    }

    public Tessera(Utente utente) {
        this.dataEmissione = LocalDate.now();
        this.data_scadenza = LocalDate.now().plusYears(1);
        this.utente = utente;
    }

    public Tessera(LocalDate dataEmissioneInput, Utente utente) {
        this.dataEmissione = dataEmissioneInput;
        this.data_scadenza = dataEmissioneInput.plusYears(1);
        this.utente = utente;
    }

    public UUID getTesseraId() {
        return tesseraId;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return data_scadenza;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public void setData_scadenza(LocalDate data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }


    @Override
    public String toString() {
        return "Tessera{" +
                "tesseraId=" + tesseraId +
                ", dataEmissione=" + dataEmissione +
                ", data_scadenza=" + data_scadenza +
                '}';
    }
}


;