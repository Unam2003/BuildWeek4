package emanuelepiemonte.dao;

import emanuelepiemonte.entities.Biglietto;
import emanuelepiemonte.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
        System.out.println("Biglietto " + newBiglietto.getBigliettoId() + " erogato con successo");
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
}
