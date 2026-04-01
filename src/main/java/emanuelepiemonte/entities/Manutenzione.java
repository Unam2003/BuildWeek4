package emanuelepiemonte.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "manutenzione")
public class Manutenzione {

    @Id
    @GeneratedValue
    @Column(name = "manutenzione_id")
    private Long manutenzioneId;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "data_inizio")
    private LocalDate dataInizio;

    @Column(name = "data_fine")
    private LocalDate dataFine;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;


    //COSTRUTTORI

    public Manutenzione() {
    }

    public Manutenzione(String descrizione, LocalDate dataInizio) {
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
    }

    public Manutenzione(String descrizione) {
        this.descrizione = descrizione;
        this.dataInizio = LocalDate.now();
    }

    //GETTER E SETTER

    public Long getManutenzioneId() {
        return manutenzioneId;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    @Override
    public String toString() {
        return "Manutenzione{" +
                "manutenzioneId=" + manutenzioneId +
                ", descrizione='" + descrizione + '\'' +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", mezzo=" + mezzo +
                '}';
    }
}