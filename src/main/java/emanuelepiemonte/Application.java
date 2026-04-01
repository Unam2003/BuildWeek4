package emanuelepiemonte;

import emanuelepiemonte.dao.*;
import emanuelepiemonte.entities.*;
import emanuelepiemonte.enums.PeriodicitaAbb;
import emanuelepiemonte.enums.Sesso;
import emanuelepiemonte.enums.TipoDiMezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BuildWeek4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        UtenteDAO utenteDao = new UtenteDAO(em);
        TesseraDao tesseraDao = new TesseraDao(em);
        AbbonamentoDAO abbonamentoDao = new AbbonamentoDAO(em);
        PuntoDiEmissioneDAO puntoDiEmissioneDAO = new PuntoDiEmissioneDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        ManutenzioneDAO manutenzioneDao = new ManutenzioneDAO(em);


        // Test Giorgia per :

        //ABBONAMENTO


        Utente u1 = new Utente("Mario", "Rossi", Sesso.MASCHIO, LocalDate.of(1990, 5, 10));
        Utente u2 = new Utente("Luigi", "Verdi", Sesso.FEMMINA, LocalDate.of(1985, 3, 20));
        Utente u3 = new Utente("Anna", "Bianchi", Sesso.ALTRO, LocalDate.of(2000, 7, 15));

//        utenteDao.save(u1);
//        utenteDao.save(u2);
//        utenteDao.save(u3);

        // UTENTE

        Utente u3fromDB = utenteDao.trovaUtentiPerId(UUID.fromString("06627139-af26-4b8d-bac9-f70787cb2ebc"));
        Utente u2fromDB = utenteDao.trovaUtentiPerId(UUID.fromString("1367b4fd-8efe-460c-b069-e7ffbe2dc504"));
        Utente u1fromDB = utenteDao.trovaUtentiPerId(UUID.fromString("701dd322-f40a-4c92-b97e-0491a04c8632"));

        // TESSERA

        Tessera t1 = new Tessera(u1fromDB);
        Tessera t2 = new Tessera(u2fromDB);
        Tessera t3 = new Tessera(u3fromDB);

//        tesseraDao.save(t1);
//      tesseraDao.save(t2);
//        tesseraDao.save(t3);

        Tessera t1fromDB = tesseraDao.findById(UUID.fromString("1f647180-01d7-492f-8a2c-24f59ba76eae"));
        Tessera t2fromDB = tesseraDao.findById(UUID.fromString("4a6757b1-1aaf-4cca-9802-892430310927"));
        Tessera t3fromDB = tesseraDao.findById(UUID.fromString("de64a63a-a309-48a1-b2ec-590b62b11f45"));


        // PUNTO DI EMISSIONE

        DistributoreAutomatico d1 = new DistributoreAutomatico("Via Roma 18", "Bologna", true);
        RivenditoreAutorizzato r1 = new RivenditoreAutorizzato("Via delle Gioia 38", "Palermo", "Da Pina");


//        puntoDiEmissioneDAO.save(d1);
//        puntoDiEmissioneDAO.save(r1);

        // Serve un cast perche restituisce classe padre.. In alternativa ancora meglio se non si conosce cosa tornera' posso fare un if con instance of

        RivenditoreAutorizzato r1fromDB =
                (RivenditoreAutorizzato) puntoDiEmissioneDAO.trovaPuntoById(UUID.fromString("65b7ffad-f70b-4381-96fb-be3744487adb"));

        DistributoreAutomatico d1fromDB =
                (DistributoreAutomatico) puntoDiEmissioneDAO.trovaPuntoById(UUID.fromString("c96ec501-fd0a-475d-b91b-eb9104739da2"));


        Abbonamento a1 = new Abbonamento(PeriodicitaAbb.MENSILE, r1fromDB, t1fromDB);
        Abbonamento a2 = new Abbonamento(PeriodicitaAbb.SETTIMANALE, d1fromDB, t2fromDB);

//        abbonamentoDao.salva(a1);
//        abbonamentoDao.salva(a2);


        // TEST TESSERA DAO
        Utente u4 = new Utente("Angelo", "Molteni", Sesso.MASCHIO, LocalDate.of(1987, 12, 21));
        //   utenteDao.save(u4);
        Utente u4fromDB = utenteDao.trovaUtentiPerId(UUID.fromString("410fe1dc-29c1-48be-b4ea-589308a03f26"));
        Tessera t4 = new Tessera(LocalDate.now().minusYears(2), u4fromDB); // PER SCADUTE
//        tesseraDao.save(t4);

        Utente u5 = new Utente("Luca", "Neri", Sesso.MASCHIO, LocalDate.of(1988, 6, 15));
        utenteDao.save(u5);

        Tessera t5 = new Tessera(LocalDate.now().minusDays(355), u5);
        tesseraDao.save(t5); // PER SCADENZA


        System.out.println("------------------------- TEST Trovo tessera da id utente-----------------------");
        Tessera t1fromDBdaUtente = tesseraDao.trovaTesseraDaUtente(u1fromDB.getUtenteId());
        Tessera t2fromDBdaUtente = tesseraDao.trovaTesseraDaUtente(u2fromDB.getUtenteId());
        Tessera t3fromDBdaUtente = tesseraDao.trovaTesseraDaUtente(u3fromDB.getUtenteId());
        System.out.println(t1fromDBdaUtente);
        System.out.println(t2fromDBdaUtente);
        System.out.println(t3fromDBdaUtente);

        System.out.println("----------------------- TEST trovaTutteTessere -------------------------");
        List<Tessera> tutteLeTessere = tesseraDao.trovaTutteTessere();
        tutteLeTessere.forEach(System.out::println);


        System.out.println("------------------------ TEST trovaTutteScadute -----------------------------");
        List<Tessera> tessereScadute = tesseraDao.trovaTutteScadute();
        tessereScadute.forEach(System.out::println);


        System.out.println("-------------------------TEST TrovaTutteAttive---------------------------------");
        List<Tessera> tessereAttive = tesseraDao.trovaTutteAttive();
        tessereAttive.forEach(System.out::println);


        System.out.println("-------------------------- TEST TrovaInScadenza -------------------------------");
        List<Tessera> tessereInScadenza = tesseraDao.trovaInScadenza(LocalDate.now().plusDays(30));
        tessereInScadenza.forEach(System.out::println);


        System.out.println("-------------------------- TEST Creazione Mezzo e cambiare Stato IN_MANUTEZIONE -------------------------------");
        Mezzo bus = new Mezzo(TipoDiMezzo.AUTOBUS, "SDWER23");
        mezzoDAO.save(bus);

        manutenzioneDao.entraInManutenzione(bus, "Rottura cambio");
        em.close();
        emf.close();
    }


}
