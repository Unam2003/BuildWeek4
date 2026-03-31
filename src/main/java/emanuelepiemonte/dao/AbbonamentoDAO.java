package emanuelepiemonte.dao;

import emanuelepiemonte.entities.Abbonamento;
import emanuelepiemonte.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AbbonamentoDAO {

    private final EntityManager em;

    public AbbonamentoDAO(EntityManager em) {
        this.em = em;
    }

    public void salva(Abbonamento newAbbonamento) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(newAbbonamento);
        t.commit();
        System.out.println("L'abbonamento " + newAbbonamento.getAbbonamentoId() + " è stato salvato correttamente");
    }

    public Abbonamento trovaDaId(UUID abbonamentoId) {
        Abbonamento found = em.find(Abbonamento.class, abbonamentoId);

        if (found == null) {
            throw new NotFoundException(abbonamentoId);
        }

        return found;
    }


    public List<Abbonamento> TrovaAbbonamentiDaTessera(UUID tesseraId) {
        return em.createQuery(
                        "SELECT a FROM Abbonamento a WHERE a.tessera.tesseraId = :tesseraId",
                        Abbonamento.class)
                .setParameter("tesseraId", tesseraId)
                .getResultList();
    }


    public Abbonamento trovaAbbAttivoPerTessera(UUID tesseraId) {
        try {
            return em.createQuery(
                            "SELECT a FROM Abbonamento a WHERE a.tessera.tesseraId = :tesseraId AND a.dataScadenza >= :oggi",
                            Abbonamento.class)
                    .setParameter("tesseraId", tesseraId)
                    .setParameter("oggi", LocalDate.now())
                    .getSingleResult();

        } catch (NoResultException e) {
            throw new NotFoundException("Nessun abbonamento attivo per la tessera " + tesseraId);
        }
    }

    public List<Abbonamento> trovaAbbScadutiPerTessera() {
        return em.createQuery(
                        "SELECT a FROM Abbonamento a WHERE a.dataScadenza < :oggi",
                        Abbonamento.class)
                .setParameter("oggi", LocalDate.now())
                .getResultList();
    }


    public List<Abbonamento> trovaAttiviTutti() {
        return em.createQuery(
                        "SELECT a FROM Abbonamento a WHERE a.dataScadenza >= :oggi",
                        Abbonamento.class)
                .setParameter("oggi", LocalDate.now())
                .getResultList();
    }

    public List<Abbonamento> trovaInScadenzaTutti(LocalDate limite) {
        return em.createQuery(
                        "SELECT a FROM Abbonamento a WHERE a.dataScadenza BETWEEN :oggi AND :limite",
                        Abbonamento.class)
                .setParameter("oggi", LocalDate.now())
                .setParameter("limite", limite)
                .getResultList();
    }

}
