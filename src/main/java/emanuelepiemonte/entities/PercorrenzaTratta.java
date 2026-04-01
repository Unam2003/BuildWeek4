package emanuelepiemonte.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "percorrenza_tratta")
public class PercorrenzaTratta {

    @Id
    @GeneratedValue
    @Column(name = "percorso_id")
    private Long percorsoId;

    @Column(name = "tempo_effettivo_tratta")
    private int tempoEffettivoTratta;

    @ManyToOne
    @JoinColumn(name = "tratta_id", nullable = false)
    private Tratta tratta;

    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = false)
    private Mezzo mezzo;


    // COSTRUTTORI

    public PercorrenzaTratta() {
    }

//    public PercorrenzaTratta(int tempoEffettivoTratta, Tratta tratta) {
//        this.tempoEffettivoTratta = tempoEffettivoTratta;
//        this.tratta = tratta;
//    }                     cotruttore sbagliato perche nullable false in mezzo .. se non lo metti esplode

    public PercorrenzaTratta(int tempoEffettivoTratta, Tratta tratta, Mezzo mezzo) {
        this.tempoEffettivoTratta = tempoEffettivoTratta;
        this.tratta = tratta;
        this.mezzo = mezzo;
    }
    //GETTER E SETTER

    public Long getPercorsoId() {
        return percorsoId;
    }

    public int getTempoEffettivoTratta() {
        return tempoEffettivoTratta;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTempoEffettivoTratta(int tempoEffettivoTratta) {
        this.tempoEffettivoTratta = tempoEffettivoTratta;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    @Override
    public String toString() {
        return "PercorrenzaTratta{" +
                "percorsoId=" + percorsoId +
                ", tempoEffettivoTratta=" + tempoEffettivoTratta +
                ", trattaId=" + (tratta != null ? tratta.getTrattaId() : null) +
                ", mezzoId=" + (mezzo != null ? mezzo.getMezzoId() : null) +
                '}';
    }
}
