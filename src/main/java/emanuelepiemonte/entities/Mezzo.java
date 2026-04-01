package emanuelepiemonte.entities;

import emanuelepiemonte.enums.StatoMezzo;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "stato_mezzo")
    private StatoMezzo stato;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_di_mezzo")
    private TipoDiMezzo tipoDiMezzo;

    @Column(name = "targa", length = 20)
    private String targa;

    @OneToMany(mappedBy = "mezzo")
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
        this.stato = StatoMezzo.IN_SERVIZIO;
    }

    //GETTER E SETTER - SGN

    public Long getMezzoId() {
        return mezzoId;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public TipoDiMezzo getTipoDiMezzo() {
        return tipoDiMezzo;
    }

    public void setTipoDiMezzo(TipoDiMezzo tipoDiMezzo) {
        this.tipoDiMezzo = tipoDiMezzo;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public void setStato(StatoMezzo stato) {
        this.stato = stato;
    }

    public List<Manutenzione> getManutenzioni() {
        return manutenzioni;
    }

    public List<PercorrenzaTratta> getPercorrenze() {
        return percorrenze;
    }


    @Override
    public String toString() {
        return "Mezzo{" +
                "mezzoId=" + mezzoId +
                ", capienza=" + capienza +
                ", tipoDiMezzo=" + tipoDiMezzo +
                ", targa='" + targa + ' ' +
                '}';
    }
}