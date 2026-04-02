package emanuelepiemonte.dao;

import emanuelepiemonte.entities.Abbonamento;
import emanuelepiemonte.exceptions.AbbonamentoNonValidoException;
import emanuelepiemonte.exceptions.AbbonamentoScadutoException;
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
        if (newAbbonamento.getTessera().getDataScadenza().isBefore(LocalDate.now())) {
            throw new AbbonamentoNonValidoException("Impossibile fare abbonamento: tessera scaduta");
        }
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(newAbbonamento);
            t.commit();
            System.out.println("L'abbonamento " + newAbbonamento.getAbbonamentoId() + " è stato salvato correttamente");
        } catch (Exception e) {
            if (t.isActive()) t.rollback();
            throw e;
        }
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
                    .setMaxResults(1)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
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

    public boolean verificaValidita(UUID abbonamentoId) {
        Abbonamento abb = trovaDaId(abbonamentoId);

        if (abb.getTessera().getDataScadenza().isBefore(LocalDate.now())) {
            throw new AbbonamentoNonValidoException("La tessera " + abb.getTessera().getTesseraId() + " è scaduta!");
        }

        if (abb.getDataScadenza().isBefore(LocalDate.now())) {
            throw new AbbonamentoScadutoException(abb.getAbbonamentoId());
        }

        System.out.println("Abbonamento ID " + abbonamentoId + " valido");
        return true;
    }

}
