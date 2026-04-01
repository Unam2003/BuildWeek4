package emanuelepiemonte.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tratta")
public class Tratta {

    @Id
    @GeneratedValue
    @Column(name = "tratta_id")
    private Long trattaId;

    @Column(name = "zona_partenza")
    private String zonaPartenza;

    @Column(name = "capolinea")
    private String capolinea;

    @Column(name = "tempo_previsto_tratta")
    private Integer tempoPrevistoTratta;

    @OneToMany(mappedBy = "tratta")
    private List<PercorrenzaTratta> percorrenze = new ArrayList<>();

    //COSTRUTTORI

    public Tratta() {
    }

    public Tratta(String zonaPartenza, String capolinea, Integer tempoPrevistoTratta) {
        this.zonaPartenza = zonaPartenza;
        this.capolinea = capolinea;
        this.tempoPrevistoTratta = tempoPrevistoTratta;
    }

    //GETTER E SETTER

    public Long getTrattaId() {
        return trattaId;
    }

    public String getZonaPartenza() {
        return zonaPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public Integer getTempoPrevistoTratta() {
        return tempoPrevistoTratta;
    }

    public List<PercorrenzaTratta> getPercorrenze() {
        return percorrenze;
    }

    public void setZonaPartenza(String zonaPartenza) {
        this.zonaPartenza = zonaPartenza;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public void setTempoPrevistoTratta(Integer tempoPrevistoTratta) {
        this.tempoPrevistoTratta = tempoPrevistoTratta;
    }

    @Override
    public String toString() {
        return " TRATTA: " +
                "zonaPartenza= " + zonaPartenza +
                ", capolinea=" + capolinea +
                ", tempoPrevistoTratta=" + tempoPrevistoTratta +
                ", percorrenze=" + percorrenze +
                ", Id=" + trattaId;

    }
}