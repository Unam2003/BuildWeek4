package emanuelepiemonte.dao;

import emanuelepiemonte.entities.Tessera;
import emanuelepiemonte.entities.Utente;
import emanuelepiemonte.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class UtenteDAO {
    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente newUtente) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(newUtente);
        transaction.commit();
        System.out.println("L'utente " + newUtente.getUtenteId() + " è stato salvato correttamente");
    }

    public Tessera findById(UUID tesseraId) {
        Tessera found = em.find(Tessera.class, tesseraId);

        if (found == null) {
            throw new NotFoundException(tesseraId);
        }

        return found;
    }
}
