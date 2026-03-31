package emanuelepiemonte.entities;

import emanuelepiemonte.enums.PeriodicitaAbb;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "abbonamento")
public class Abbonamento {
    @Id
    @GeneratedValue
    @Column(name = "abbonamento_id")
    private UUID abbonamentoId;

    @Column(name = "periodicita")
    private PeriodicitaAbb periodicita;

    @Column(name = "data_emissione")
    private LocalDate dataEmissione;

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @ManyToOne
    @JoinColumn(name = "rivenditore_id")
    private PuntoDiEmissione rivenditore;

//    @ManyToOne
//    @JoinColumn(name = "tessera_id")
//    private Tessera tessera;


    public Abbonamento() {
    }

    public Abbonamento(PeriodicitaAbb periodicita, LocalDate dataEmissione, PuntoDiEmissione rivenditore) {
        this.periodicita = periodicita;
        this.dataEmissione = dataEmissione;
        if (periodicita == PeriodicitaAbb.MENSILE) {
            this.dataScadenza = dataEmissione.plusMonths(1);
        } else if (periodicita == PeriodicitaAbb.SETTIMANALE) {
            this.dataScadenza = dataEmissione.plusDays(7);
        }
        this.rivenditore = rivenditore;
    }

    public Abbonamento(PeriodicitaAbb periodicita, PuntoDiEmissione rivenditore) {
        this.periodicita = periodicita;
        this.dataEmissione = LocalDate.now();
        if (periodicita == PeriodicitaAbb.MENSILE) {
            this.dataScadenza = dataEmissione.plusMonths(1);
        } else if (periodicita == PeriodicitaAbb.SETTIMANALE) {
            this.dataScadenza = dataEmissione.plusDays(7);
        }
        this.rivenditore = rivenditore;
    }
}
