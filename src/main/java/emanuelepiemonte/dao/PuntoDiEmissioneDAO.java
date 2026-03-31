package emanuelepiemonte.dao;

import emanuelepiemonte.entities.PuntoDiEmissione;
import emanuelepiemonte.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
}
