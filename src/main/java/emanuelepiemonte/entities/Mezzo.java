package emanuelepiemonte.entities;

import emanuelepiemonte.enums.TipoDiMezzo;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mezzo")
public class Mezzo {

    @Id
    @GeneratedValue
    @Column(name = "mezzo_id")
    private Long mezzoId;

    @Column(name = "capienza")
    private int capienza;

    @Enumerated
    @Column(name = "tipo_di_mezzo")
    private TipoDiMezzo tipoDiMezzo;

    @Column(name = "targa", length = 20)
    private String targa;

    @OneToMany
    @JoinColumn(name = "mezzo_id")
    private List<Manutenzione> manutenzioni = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "mezzo_id")
    private List<PercorrenzaTratta> percorrenze = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "mezzo_id")
    private List<Biglietto> bigliettiValidati = new ArrayList<>();


    //COSTRUTTORI - SGN

    public Mezzo() {
    }

    public Mezzo(TipoDiMezzo tipoDiMezzo, String targa) {
        this.tipoDiMezzo = tipoDiMezzo;
        this.targa = targa;
        this.capienza = tipoDiMezzo.getValore();
    }

    //GETTER E SETTER - SGN

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public void setTipoDiMezzo(TipoDiMezzo tipoDiMezzo) {
        this.tipoDiMezzo = tipoDiMezzo;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public Long getMezzoId() {
        return mezzoId;
    }

    public int getCapienza() {
        return capienza;
    }

    public TipoDiMezzo getTipoDiMezzo() {
        return tipoDiMezzo;
    }

    public String getTarga() {
        return targa;
    }

    public List<Manutenzione> getManutenzioni() {
        return manutenzioni;
    }

    public List<PercorrenzaTratta> getPercorrenze() {
        return percorrenze;
    }
}