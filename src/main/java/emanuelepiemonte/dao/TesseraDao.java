package emanuelepiemonte.dao;

import emanuelepiemonte.entities.Tessera;
import emanuelepiemonte.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class TesseraDao {

    private final EntityManager em;

    public TesseraDao(EntityManager em) {
        this.em = em;
    }

    public void save(Tessera newTessera) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(newTessera);
        t.commit();
        System.out.println("La tessera " + newTessera.getTesseraId() + " è stata salvata correttamente");
    }

    public Tessera findById(UUID tesseraId) {
        Tessera found = em.find(Tessera.class, tesseraId);

        if (found == null) {
            throw new NotFoundException(tesseraId);
        }

        return found;
    }

    public void update(Tessera tessera) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(tessera);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Errore nell'update: " + e.getMessage());
        }
    }

    // QUERY PER FAR COSE

    public Tessera trovaTesseraDaUtente(UUID utenteId) {
        try {
            return em.createQuery(
                            "SELECT t FROM Tessera t WHERE t.utente.utenteId = :utenteId",
                            Tessera.class)
                    .setParameter("utenteId", utenteId)
                    .getSingleResult();

        } catch (NoResultException e) {
            throw new NotFoundException("Nessuna tessera trovata per l'utente " + utenteId);
        }
    }

    public void cancella(UUID tesseraId) {
        Tessera found = findById(tesseraId);

        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(found);
        t.commit();

        System.out.println("Tessera eliminata correttamente");
    }


    public List<Tessera> trovaTutteTessere() {
        List<Tessera> result = em.createQuery(
                        "SELECT t FROM Tessera t",
                        Tessera.class)
                .getResultList();

        if (result.isEmpty()) {
            throw new NotFoundException("Non ci sono tessere nel database");
        }

        return result;
    }

    public List<Tessera> trovaTutteScadute() {
        List<Tessera> result = em.createQuery(
                        "SELECT t FROM Tessera t WHERE t.data_scadenza < :oggi",
                        Tessera.class)
                .setParameter("oggi", LocalDate.now())
                .getResultList();

        if (result.isEmpty()) {
            throw new NotFoundException("Non ci sono tessere scadute");
        }

        return result;
    }


    public List<Tessera> trovaTutteAttive() {
        List<Tessera> result = em.createQuery(
                        "SELECT t FROM Tessera t WHERE t.data_scadenza >= :oggi",
                        Tessera.class)
                .setParameter("oggi", LocalDate.now())
                .getResultList();

        if (result.isEmpty()) {
            throw new NotFoundException("Non ci sono tessere attive");
        }

        return result;
    }

    // MAGARI PER MANDARE MAIL AVVISO SCADENZA

    public List<Tessera> trovaInScadenza(LocalDate dataLimite) {
        List<Tessera> result = em.createQuery(
                        "SELECT t FROM Tessera t WHERE t.data_scadenza BETWEEN :oggi AND :limite",
                        Tessera.class)
                .setParameter("oggi", LocalDate.now())
                .setParameter("limite", dataLimite)
                .getResultList();

        if (result.isEmpty()) {
            throw new NotFoundException("Non ci sono tessere in scadenza");
        }

        return result;
    }
}
