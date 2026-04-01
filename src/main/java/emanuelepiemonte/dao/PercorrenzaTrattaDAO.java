package emanuelepiemonte.dao;

import emanuelepiemonte.entities.PercorrenzaTratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PercorrenzaTrattaDAO {
    private final EntityManager em;

    public PercorrenzaTrattaDAO(EntityManager em) {
        this.em = em;
    }

    //FUNZIONI BASE

    //Salvataggio
    public void save(PercorrenzaTratta percorrenza) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(percorrenza);
            transaction.commit();
            System.out.println("Percorrenza salvata con successo!");
        } catch (Exception e) {
            System.err.println("Errore nel salvataggio: " + e.getMessage());
        }
    }

    //Cerca Percorrenza per ID
    public PercorrenzaTratta getById(Long id) {
        return em.find(PercorrenzaTratta.class, id);
    }


    //Aggiornamento di una Percorrenza
    public void update(PercorrenzaTratta percorrenza) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(percorrenza);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Errore nell'update: " + e.getMessage());
        }
    }

    //Cancella per ID
    public void deleteById(Long id) {
        PercorrenzaTratta found = em.find(PercorrenzaTratta.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(found);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    // per avere tutta la tabella a lista oggetti
    public List<PercorrenzaTratta> getAll() {
        return em.createQuery("SELECT p FROM PercorrenzaTratta p", PercorrenzaTratta.class).getResultList();
    }

    //QUERY èIU' SPECIFICHE

    //Calcola tempo medio per tratta (da tutti i mezzi) (SOLO DALL'ADMIN!!)
    public Double getTempoMedioPerTratta(Long trattaId) {
        TypedQuery<Double> query = em.createQuery(
                "SELECT AVG(p.tempoEffettivoTratta) FROM PercorrenzaTratta p WHERE p.tratta.trattaId = :id",
                Double.class);
        query.setParameter("id", trattaId);
        return query.getSingleResult();
    }

    //Calcola tempo medio per tratta E PER MEZZO SINGOLO (SOLO ADMIN!!!)
    public Double getTempoMedioPerTrattaEMezzo(Long trattaId, Long mezzoId) {
        TypedQuery<Double> query = em.createQuery(
                "SELECT AVG(p.tempoEffettivoTratta) FROM PercorrenzaTratta p WHERE p.tratta.trattaId = :trattaid AND p.mezzo.mezzoId = :mezzoid",
                Double.class);
        query.setParameter("trattaid", trattaId);
        query.setParameter("mezzoid", mezzoId);
        return query.getSingleResult();
    }

    //Cerca una lista di Percorrenze fatta da un determinato Mezzo
    public List<PercorrenzaTratta> getByMezzo(Long mezzoId) {
        TypedQuery<PercorrenzaTratta> query = em.createQuery(
                "SELECT p FROM PercorrenzaTratta p WHERE p.mezzo.mezzoId = :id",
                PercorrenzaTratta.class);
        query.setParameter("id", mezzoId);
        return query.getResultList();
    }

    //Conta quante volte un MEZZO SPECIFICO ha percorso OGNI TRATTA DIFFERENTE
    public void printDettaglioPercorrenzePerTratta(Long mezzoId) {
        List<Object[]> risultati = em.createQuery(
                        "SELECT p.tratta.zonaPartenza, p.tratta.capolinea, COUNT(p) " +
                                "FROM PercorrenzaTratta p " +
                                "WHERE p.mezzo.mezzoId = :id " +
                                "GROUP BY p.tratta.zonaPartenza, p.tratta.capolinea", Object[].class)
                .setParameter("id", mezzoId)
                .getResultList();

        System.out.println("Dettaglio percorrenze per il mezzo ID: " + mezzoId);
        for (Object[] riga : risultati) {
            System.out.println("Tratta: " + riga[0] + " -> " + riga[1] + " | Volte percorse: " + riga[2]);
        }
    }
}
