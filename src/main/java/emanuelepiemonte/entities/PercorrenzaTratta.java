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

    public PercorrenzaTratta(int tempoEffettivoTratta, Tratta tratta) {
        this.tempoEffettivoTratta = tempoEffettivoTratta;
        this.tratta = tratta;
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
}
