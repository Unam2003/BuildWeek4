package emanuelepiemonte.dao;

import emanuelepiemonte.entities.PuntoDiEmissione;
import emanuelepiemonte.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.UUID;

public class PuntoDiEmissioneDAO {
    private final EntityManager em;

    public PuntoDiEmissioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(PuntoDiEmissione newPuntoDiEmissione) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(newPuntoDiEmissione);
        transaction.commit();
        System.out.println("L'emissione " + newPuntoDiEmissione.getEmissioneId() + " è stato salvato correttamente!");
    }

    public PuntoDiEmissione trovaPuntoById(UUID emissioneId) {
        PuntoDiEmissione found = em.find(PuntoDiEmissione.class, emissioneId);

        if (found == null) {
            throw new NotFoundException(emissioneId);
        }

        return found;
    }

    // History dei biglietti emessi dal distributore
    public void countBigliettiDistributore(LocalDate inizio, LocalDate fine) {
        long risultato = em.createQuery(
                        "SELECT COUNT(b) FROM Biglietto b " +
                                "WHERE TYPE(b.rivenditore) = DistributoreAutomatico " +
                                "AND b.dataEmissione BETWEEN :inizio AND :fine", Long.class)
                .setParameter("inizio", inizio)
                .setParameter("fine", fine)
                .getSingleResult();

        System.out.println("Biglietti emessi dai Distributori: " + risultato);
    }

    // History dei biglietti venduti dai rivenditori
    public void countBigliettiRivenditore(LocalDate inizio, LocalDate fine) {
        long risultato = em.createQuery(
                        "SELECT COUNT(b) FROM Biglietto b " +
                                "WHERE TYPE(b.rivenditore) = RivenditoreAutorizzato " +
                                "AND b.dataEmissione BETWEEN :inizio AND :fine", Long.class)
                .setParameter("inizio", inizio)
                .setParameter("fine", fine)
                .getSingleResult();

        System.out.println("Biglietti emessi dai Rivenditori: " + risultato);
    }
}
