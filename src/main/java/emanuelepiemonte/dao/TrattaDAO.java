package emanuelepiemonte.dao;

import emanuelepiemonte.entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TrattaDAO {
    private final EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    //FUNZIONI BASE

    //Salvataggio
    public void save(Tratta tratta) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(tratta);
            transaction.commit();
            System.out.println("Tratta salvata con successo!");
        } catch (Exception e) {
            System.err.println("Errore nel salvataggio della tratta: " + e.getMessage());
        }
    }

    //Cerca Tratta per ID
    public Tratta getById(Long id) {
        return em.find(Tratta.class, id);
    }

    //Prende tutte le Tratte
    public List<Tratta> getAll() {
        return em.createQuery("SELECT t FROM Tratta t", Tratta.class).getResultList();
    }


    //Aggiorna una tratta
    public void update(Tratta tratta) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(tratta);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Errore durante l'update: " + e.getMessage());
        }
    }

    //Cancella per ID
    public void deleteById(Long id) {
        Tratta found = em.find(Tratta.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(found);
                transaction.commit();
                System.out.println("Tratta rimossa.");
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                System.err.println("Errore eliminazione: " + e.getMessage());
            }
        }
    }
    //QUERY PIU' SPECIFICHE

    //Prendo tutte le Tratte per Partenza
    public List<Tratta> getByPartenza(String partenza) {
        TypedQuery<Tratta> query = em.createQuery(
                "SELECT t FROM Tratta t WHERE LOWER(t.zonaPartenza) = LOWER(:p)",
                Tratta.class);
        query.setParameter("p", partenza);
        return query.getResultList();
    }

    //Prendo tutte le Tratte per Arrivo
    public List<Tratta> getByArrivo(String arrivo) {
        TypedQuery<Tratta> query = em.createQuery(
                "SELECT t FROM Tratta t WHERE LOWER(t.capolinea) = LOWER(:t)",
                Tratta.class);
        query.setParameter("t", arrivo);
        return query.getResultList();
    }
}