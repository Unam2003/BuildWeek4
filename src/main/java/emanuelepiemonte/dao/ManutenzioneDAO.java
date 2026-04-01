package emanuelepiemonte.dao;

import emanuelepiemonte.entities.Manutenzione;
import emanuelepiemonte.entities.Mezzo;
import emanuelepiemonte.enums.StatoMezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ManutenzioneDAO {
    private final EntityManager em;

    public ManutenzioneDAO(EntityManager em) {
        this.em = em;
    }

    //FUNZIONI BASE

    //Salvataggio
    public void save(Manutenzione manutenzione) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(manutenzione);
            transaction.commit();
            System.out.println("Manutenzione registrata con successo!");
        } catch (Exception e) {
            System.err.println("Errore nel salvataggio manutenzione: " + e.getMessage());
        }
    }

    //Cerca Manutenzione èer ID
    public Manutenzione getById(Long id) {
        return em.find(Manutenzione.class, id);
    }

    //Prende tutte le Manutenzioni
    public List<Manutenzione> getAll() {
        return em.createQuery("SELECT m FROM Manutenzione m", Manutenzione.class).getResultList();
    }


    //Aggiorna una Manutenzione
    public void update(Manutenzione manutenzione) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(manutenzione);
            transaction.commit();
            System.out.println("Manutenzione aggiornata correttamente.");
        } catch (Exception e) {
            System.out.println("Errore durante l'update: " + e.getMessage());
        }
    }

    //Cancella Manutenzione per ID
    public void deleteById(Long id) {
        Manutenzione found = em.find(Manutenzione.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(found);
                transaction.commit();
                System.out.println("Manutenzione eliminata.");
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                System.err.println("Errore eliminazione: " + e.getMessage());
            }
        }
    }

    //QUERY PIU' SPECIFICHE

    //Prende tutte le Manutenzioni IN CORSO
    public List<Manutenzione> getManutenzioniInCorso() {
        TypedQuery<Manutenzione> query = em.createQuery(
                "SELECT m FROM Manutenzione m WHERE m.dataFine IS NULL",
                Manutenzione.class);
        return query.getResultList();
    }


    // Creazione manutenzione e cambio stato mezzo (IN MANUTENZIONE)
    public void entraInManutenzione(Mezzo m, String motivo) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Manutenzione manutenzione = new Manutenzione(motivo);
        manutenzione.setMezzo(m);
        m.setStato(StatoMezzo.IN_MANUTENZIONE);
        em.persist(manutenzione);
        em.merge(m);
        transaction.commit();
    }
    

}