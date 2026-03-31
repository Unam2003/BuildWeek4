package emanuelepiemonte.dao;

import emanuelepiemonte.entities.Mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class MezzoDAO {
    private final EntityManager em;

    public MezzoDAO(EntityManager em) {
        this.em = em;
    }

    //FUNZIONI BASE

    //Salvataggio
    public void save(Mezzo mezzo) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(mezzo);
            transaction.commit();
            System.out.println("Mezzo salvato con successo! ID: " + mezzo.getMezzoId());
        } catch (Exception e) {
            System.err.println("Errore durante il salvataggio del mezzo: " + e.getMessage());
        }
    }

    //Cerca Mezzo per ID
    public Mezzo getById(Long id) {
        return em.find(Mezzo.class, id);
    }

    //Prende tutti i mezzi
    public List<Mezzo> getAll() {
        return em.createQuery("SELECT m FROM Mezzo m", Mezzo.class).getResultList();
    }

    //Cancella per ID
    public void deleteById(Long id) {
        Mezzo found = em.find(Mezzo.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(found);
                transaction.commit();
                System.out.println("Mezzo con ID " + id + " eliminato correttamente.");
            } catch (Exception e) {
                System.err.println("Errore durante l'eliminazione: " + e.getMessage());
            }
        } else {
            System.out.println("Mezzo non trovato.");
        }
    }

    //Aggiorna i dati di un Mezzo (verrà controllato per ID)
    public void update(Mezzo mezzo) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(mezzo);
            transaction.commit();
            System.out.println("Mezzo aggiornato correttamente.");
        } catch (Exception e) {
            System.out.println("Errore durante il l'update: " + e.getMessage());
        }
    }
}