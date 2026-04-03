package emanuelepiemonte.dao;

import emanuelepiemonte.entities.Biglietto;
import emanuelepiemonte.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.UUID;

public class BigliettoDAO {

    private final EntityManager em;

    public BigliettoDAO(EntityManager em) {
        this.em = em;
    }

    //    METODO PER SALVARE BIGLIETTO
    public void save(Biglietto newBiglietto) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(newBiglietto);
        t.commit();
    }

    //UPDATE
    public void update(Biglietto newBiglietto) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(newBiglietto);
        t.commit();
        System.out.println("Biglietto " + newBiglietto.getBigliettoId() + " aggiornato!");
    }

    //    METODO PER TROVARE BIGLIETTO DA ID
    public Biglietto findById(UUID bigliettoId) {
        Biglietto found = em.find(Biglietto.class, bigliettoId);
        if (found == null) {
            throw new NotFoundException(bigliettoId);
        }
        return found;
    }

    //  METODO PER ELIMINARE IL BIGLIETTO TRAMITE ID
    public void deleteById(UUID bigliettoid) {
        Biglietto found = this.findById(bigliettoid);
        EntityTransaction t = this.em.getTransaction();
        t.begin();
        em.remove(found);
        t.commit();
        System.out.println("Biglietto " + found.getBigliettoId() + " eliminato con successo");
    }

    //Conteggio Biglietti emessi da un punto in un periodo di tempo
    public Long contaPerPuntoEPeriodo(UUID puntoId, LocalDate inizio, LocalDate fine) {
        return em.createQuery(
                        "SELECT COUNT(b) FROM Biglietto b WHERE b.rivenditore.emissioneId = :puntoId AND b.dataEmissione BETWEEN :inizio AND :fine", Long.class)
                .setParameter("puntoId", puntoId)
                .setParameter("inizio", inizio)
                .setParameter("fine", fine)
                .getSingleResult();
    }

    //Conteggio Biglietto emessi da un punto in generale (alltime)
    public Long countAllPerPunto(UUID puntoId) {
        return em.createQuery(
                        "SELECT COUNT(b) FROM Biglietto b WHERE b.rivenditore.emissioneId = :puntoId", Long.class)
                .setParameter("puntoId", puntoId)
                .getSingleResult();
    }

    // Conteggio Biglietti Emessi in un certo periodo di tempo (indipendentemente dal punto)
    public Long countBigliettiPerPeriodo(LocalDate inizio, LocalDate fine) {
        return em.createQuery(
                        "SELECT COUNT(b) FROM Biglietto b WHERE b.dataEmissione BETWEEN :inizio AND :fine", Long.class)
                .setParameter("inizio", inizio)
                .setParameter("fine", fine)
                .getSingleResult();
    }
}
