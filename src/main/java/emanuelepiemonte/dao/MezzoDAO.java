package emanuelepiemonte.dao;

import emanuelepiemonte.entities.Manutenzione;
import emanuelepiemonte.entities.Mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
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

    //QUERY PIU' SPECIFICHE

    //Conta quanti biglietti sono stati validati su in singolo mezzo
    public Long bigliettiValidatiSuMezzo(Long mezzoId) { // uniformo con il long del mezzo
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(b) FROM Biglietto b WHERE b.mezzo.mezzoId = :id", // si chiama cosi in entity Biglietto
                Long.class);

        query.setParameter("id", mezzoId);
        return query.getSingleResult();
    }

    public Long bigliettiValidatiSuMezzoPerPeriodo(Long mezzoId, LocalDate inizio, LocalDate fine) {
        return em.createQuery(
                        "SELECT COUNT(b) FROM Biglietto b WHERE b.mezzo.mezzoId = :mezzoId AND b.dataAnnullamento BETWEEN :inizio AND :fine", Long.class)
                .setParameter("mezzoId", mezzoId)
                .setParameter("inizio", inizio)
                .setParameter("fine", fine)
                .getSingleResult();
    }

    //Ritorna TUTTI i mezzi in manutenzione
    public List<Mezzo> getMezziInManutenzione() {
        TypedQuery<Mezzo> query = em.createQuery(
                "SELECT m FROM Mezzo m JOIN m.manutenzioni man WHERE man.dataFine IS NULL",
                Mezzo.class);

        return query.getResultList();
    }

    //Manutenzioni di un MEZZO SPECIFICO in un certo periodo
    public List<Manutenzione> getManutenzioniPerPeriodo(Long mezzoId, LocalDate inizio, LocalDate fine) {
        TypedQuery<Manutenzione> query = em.createQuery(
                "SELECT man FROM Mezzo m JOIN m.manutenzioni man " +
                        "WHERE m.mezzoId = :id " +
                        "AND man.dataInizio >= :inizio " +
                        "AND (man.dataFine <= :fine OR man.dataFine IS NULL)",
                Manutenzione.class);

        query.setParameter("id", mezzoId);
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);

        return query.getResultList();
    }

    public Mezzo findMezzoByManutenzioneId(Long manId) {
        try {
            return em.createQuery(
                            "SELECT m FROM Mezzo m JOIN m.manutenzioni man WHERE man.id = :id",
                            Mezzo.class)
                    .setParameter("id", manId)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
            return null;
        }
    }
}