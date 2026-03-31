package emanuelepiemonte.dao;

import emanuelepiemonte.entities.Tessera;
import emanuelepiemonte.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
}