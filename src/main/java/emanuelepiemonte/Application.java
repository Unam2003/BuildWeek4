package emanuelepiemonte;

<<<<<<< Updated upstream

import emanuelepiemonte.entities.Utente;
import emanuelepiemonte.enums.UserType;
import emanuelepiemonte.utils.AdminMain;
import emanuelepiemonte.utils.RegistrazioneLogin;
import emanuelepiemonte.utils.UtenteMain;
=======
import emanuelepiemonte.dao.*;
import emanuelepiemonte.entities.*;
import emanuelepiemonte.enums.Sesso;
import emanuelepiemonte.enums.TipoDiMezzo;
import emanuelepiemonte.enums.UserType;
import emanuelepiemonte.utils.RegistrazioneLogin;
>>>>>>> Stashed changes
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

<<<<<<< Updated upstream
import java.util.Scanner;
=======
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
>>>>>>> Stashed changes

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BuildWeek4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        //UTILS
        RegistrazioneLogin rl = new RegistrazioneLogin();
        UtenteMain um = new UtenteMain();
        AdminMain am = new AdminMain(emf);


        System.out.println("BENVENUTO");
        while (true) {
            Utente utenteLoggato = null;
            System.out.println("0. Esci");
            System.out.println("1. Registrati!");
            System.out.println("2. Login");
            String input = scanner.nextLine();
            switch (input) {
                case "0" -> {
                    break;
                }
                case "1" -> {
                    rl.registrazione(em);
                    continue;
                }
                case "2" -> utenteLoggato = rl.login(em);
                default -> {
                    System.out.println("Valore non valido");
                    continue;
                }
            }

            if (input.equals("0")) break;

            if (utenteLoggato.getUserType() == UserType.ADMIN) {
                am.adminMenu(scanner);
            } else if (utenteLoggato.getUserType() == UserType.USER) {
                um.menuUtente(em, utenteLoggato);
            }
        }
        // Test Giorgia per :

        //ABBONAMENTO

        //      TODO  butto giu' le tabella perche'hanno aggiunto user-type e password (pw)
        Utente u1 = new Utente("Mario", "Rossi", Sesso.MASCHIO, LocalDate.of(1990, 5, 10), UserType.USER, "Uhu11");
        Utente u2 = new Utente("Luigi", "Verdi", Sesso.FEMMINA, LocalDate.of(1985, 3, 20), UserType.USER, "passwordsupersicurissima");
        Utente u3 = new Utente("Anna", "Bianchi", Sesso.ALTRO, LocalDate.of(2000, 7, 15), UserType.ADMIN, "123456789");

//        utenteDao.save(u1);
//        utenteDao.save(u2);
//        utenteDao.save(u3);


//        Utente loggedIn = rl.login(em);
//        am.adminMenu(scanner);
//        um.menuUtente(em, loggedIn);


        // UTENTE

<<<<<<< Updated upstream
//        Utente u3fromDB = utenteDao.trovaUtentiPerId(UUID.fromString("031d58dd-a760-476c-b604-7a6140854a21"));
//        Utente u2fromDB = utenteDao.trovaUtentiPerId(UUID.fromString("22da0f66-e09e-4d31-993a-3c72f3ead787"));
//        Utente u1fromDB = utenteDao.trovaUtentiPerId(UUID.fromString("933fb2f0-a6d0-4bac-b3f8-04b61808da5c"));
//
//        // TESSERA
//
////        Tessera t1 = new Tessera(u1fromDB);
////        Tessera t2 = new Tessera(u2fromDB);
////        Tessera t3 = new Tessera(u3fromDB);
//
////        tesseraDao.save(t1);
////        tesseraDao.save(t2);
////        tesseraDao.save(t3);
//
//        Tessera t1fromDB = tesseraDao.findById(UUID.fromString("1bb9ceb7-4854-42ca-820a-62766c88c724"));
//        Tessera t2fromDB = tesseraDao.findById(UUID.fromString("d23194c4-36c1-4b8e-b43d-ff7748d32eba"));
//        Tessera t3fromDB = tesseraDao.findById(UUID.fromString("7fb8ea85-5197-4ba9-943b-1fc372f26cea"));
//
//
//        // PUNTO DI EMISSIONE
//
////        DistributoreAutomatico d1 = new DistributoreAutomatico("Via Roma 18", "Bologna", true);
////        RivenditoreAutorizzato r1 = new RivenditoreAutorizzato("Via delle Gioia 38", "Palermo", "Da Pina");
//
//
////        puntoDiEmissioneDAO.save(d1);
////        puntoDiEmissioneDAO.save(r1);
//
//        // Serve un cast perche restituisce classe padre.. In alternativa ancora meglio se non si conosce cosa tornera' posso fare un if con instance of
//
//        RivenditoreAutorizzato r1fromDB =
//                (RivenditoreAutorizzato) puntoDiEmissioneDAO.trovaPuntoById(UUID.fromString("235df389-879f-4d18-b3ce-07507e2b84b4"));
//
//        DistributoreAutomatico d1fromDB =
//                (DistributoreAutomatico) puntoDiEmissioneDAO.trovaPuntoById(UUID.fromString("7da27ac7-5042-4f31-a0cc-4765e82aa5cb"));
//
//
////        Abbonamento a1 = new Abbonamento(PeriodicitaAbb.MENSILE, r1fromDB, t1fromDB);
////        Abbonamento a2 = new Abbonamento(PeriodicitaAbb.SETTIMANALE, d1fromDB, t2fromDB);
//
////        abbonamentoDao.salva(a1);
////        abbonamentoDao.salva(a2);
//
//
//        // TEST TESSERA DAO
//        Utente u4 = new Utente("Angelo", "Molteni", Sesso.MASCHIO, LocalDate.of(1987, 12, 21));
////        utenteDao.save(u4);
//        Utente u4fromDB = utenteDao.trovaUtentiPerId(UUID.fromString("f0d5d782-ce14-4262-92ea-5ceaf2295cb6"));
//        Tessera t4 = new Tessera(LocalDate.now().minusYears(2), u4fromDB); // PER SCADUTE
////        tesseraDao.save(t4);
//
//        Utente u5 = new Utente("Luca", "Neri", Sesso.MASCHIO, LocalDate.of(1988, 6, 15));
////        utenteDao.save(u5);
//
//        Tessera t5 = new Tessera(LocalDate.now().minusDays(355), u5);
////        tesseraDao.save(t5); // PER SCADENZA
//
//
////        System.out.println("------------------------- TEST Trovo tessera da id utente-----------------------");
////        Tessera t1fromDBdaUtente = tesseraDao.trovaTesseraDaUtente(u1fromDB.getUtenteId());
////        Tessera t2fromDBdaUtente = tesseraDao.trovaTesseraDaUtente(u2fromDB.getUtenteId());
////        Tessera t3fromDBdaUtente = tesseraDao.trovaTesseraDaUtente(u3fromDB.getUtenteId());
////        System.out.println(t1fromDBdaUtente);
////        System.out.println(t2fromDBdaUtente);
////        System.out.println(t3fromDBdaUtente);
//
////        System.out.println("----------------------- TEST trovaTutteTessere -------------------------");
////        List<Tessera> tutteLeTessere = tesseraDao.trovaTutteTessere();
////        tutteLeTessere.forEach(System.out::println);
//
//
////        System.out.println("------------------------ TEST trovaTutteScadute -----------------------------");
////        List<Tessera> tessereScadute = tesseraDao.trovaTutteScadute();
////        tessereScadute.forEach(System.out::println);
//
//
////        System.out.println("-------------------------TEST TrovaTutteAttive---------------------------------");
////        List<Tessera> tessereAttive = tesseraDao.trovaTutteAttive();
////        tessereAttive.forEach(System.out::println);
//
//
//        System.out.println("-------------------------- TEST TrovaInScadenza -------------------------------");
//        List<Tessera> tessereInScadenza = tesseraDao.trovaInScadenza(LocalDate.now().plusDays(30));
//        tessereInScadenza.forEach(System.out::println);
//
//
//        // Test Giorgia giorno 2
//
//        // TRATTA
//
//        TrattaDAO trattaDAO = new TrattaDAO(em);
//
//        Tratta tr1 = new Tratta("Napoli", "Salerno", 45);
//        Tratta tr2 = new Tratta("Bergamo", "Verona", 90);
//        Tratta tr3 = new Tratta("FI-Careggi", "FI-Porta Al Prato", 17);
//        Tratta tr4 = new Tratta("Cologno al Serio", "Bergamo", 20);
//        Tratta tr5 = new Tratta("Napoli", "Aversa", 43);
//        Tratta tr6 = new Tratta("Milano", "Bergamo", 38);
//
////        trattaDAO.save(tr1);
////        trattaDAO.save(tr2);
////        trattaDAO.save(tr3);
////        trattaDAO.save(tr4);
////        trattaDAO.save(tr5);
//        //      trattaDAO.save(tr6);
//
//        System.out.println("-------------------------- TEST TrovaTrattaPerId -------------------------------");
//
//        Tratta tr1FromDB = trattaDAO.getById(1L);
//        Tratta tr2FromDB = trattaDAO.getById(2L);
//        Tratta tr3FromDB = trattaDAO.getById(3L);
//        Tratta tr4FromDB = trattaDAO.getById(52L);
//        Tratta tr5FromDB = trattaDAO.getById(53L);
//        Tratta tr6FromDB = trattaDAO.getById(102L);
//        System.out.println(tr1FromDB);
//        System.out.println(tr2FromDB);
//        System.out.println(tr3FromDB);
//        System.out.println(tr4FromDB);
//        System.out.println(tr5FromDB);
//        System.out.println(tr6FromDB);
//
//
//        System.out.println("-------------------------- TEST TrovaTutteTratte -------------------------------");
//
//
//        List<Tratta> trTutte = trattaDAO.getAll();
//        trTutte.forEach(System.out::println);
//
//
//        System.out.println("-------------------------- TEST aggiorna -------------------------------");
//
////        System.out.println("PRIMA:");
////        System.out.println(trattaDAO.getById(tr1FromDB.getTrattaId()));
////        tr1FromDB.setCapolinea("Roma");
////        tr1FromDB.setTempoPrevistoTratta(120);
////        trattaDAO.update(tr1);
////        System.out.println("DOPO:");
////        System.out.println(trattaDAO.getById(tr1FromDB.getTrattaId()));
//
//        System.out.println("-------------------------- TEST per cancella -------------------------------");
////        trattaDAO.deleteById(tr4FromDB.getTrattaId());
////        trattaDAO.getAll().forEach(System.out::println);
////
//        // null pointer se gia cancellata
//
//
//        System.out.println("-------------------------- TEST   trovaPerPartenza -------------------------------");
//        ;
//        trattaDAO.getByPartenza("Napoli").forEach(System.out::println);
//
//
//        System.out.println("-------------------------- TEST   trovaPerArrivo -------------------------------");
//        trattaDAO.getByArrivo("Bergamo").forEach(System.out::println);
//
//
//        // MEZZO BASE
//        MezzoDAO mezzoDAO = new MezzoDAO(em);
//        BigliettoDAO bigliettiDAO = new BigliettoDAO(em);
//
//        Mezzo m1 = new Mezzo(TipoDiMezzo.AUTOBUS, "AB123CD");
//        Mezzo m2 = new Mezzo(TipoDiMezzo.TRAM, "TR456EF");
//        Mezzo m3 = new Mezzo(TipoDiMezzo.AUTOBUS, "XY789ZZ");
//
////        mezzoDAO.save(m1);
////        mezzoDAO.save(m2);
////        mezzoDAO.save(m3);
//
//        System.out.println("-------------------------- TEST  TrovaDaID -------------------------------");
//
//        Mezzo m1FromDB = mezzoDAO.getById(1L);
//        Mezzo m2FromDB = mezzoDAO.getById(2L);
//        Mezzo m3FromDB = mezzoDAO.getById(3L);
//
//        System.out.println(m1FromDB);
//        System.out.println(m2FromDB);
//        System.out.println(m3FromDB);
//
//        System.out.println("-------------------------- TEST  PrendiTutti -------------------------------");
//
//        List<Mezzo> tuttiIMezzi = mezzoDAO.getAll();
//        tuttiIMezzi.forEach(System.out::println);
//
//
//        System.out.println("-------------------------- TEST  Aggiorna -------------------------------");
//
=======
        Utente u3fromDB = utenteDao.trovaUtentiPerId(UUID.fromString("031d58dd-a760-476c-b604-7a6140854a21"));
        Utente u2fromDB = utenteDao.trovaUtentiPerId(UUID.fromString("22da0f66-e09e-4d31-993a-3c72f3ead787"));
        Utente u1fromDB = utenteDao.trovaUtentiPerId(UUID.fromString("933fb2f0-a6d0-4bac-b3f8-04b61808da5c"));

        // TESSERA

//        Tessera t1 = new Tessera(u1fromDB);
//        Tessera t2 = new Tessera(u2fromDB);
//        Tessera t3 = new Tessera(u3fromDB);

//        tesseraDao.save(t1);
//        tesseraDao.save(t2);
//        tesseraDao.save(t3);

        Tessera t1fromDB = tesseraDao.findById(UUID.fromString("1bb9ceb7-4854-42ca-820a-62766c88c724"));
        Tessera t2fromDB = tesseraDao.findById(UUID.fromString("d23194c4-36c1-4b8e-b43d-ff7748d32eba"));
        Tessera t3fromDB = tesseraDao.findById(UUID.fromString("7fb8ea85-5197-4ba9-943b-1fc372f26cea"));


        // PUNTO DI EMISSIONE

//        DistributoreAutomatico d1 = new DistributoreAutomatico("Via Roma 18", "Bologna", true);
//        RivenditoreAutorizzato r1 = new RivenditoreAutorizzato("Via delle Gioia 38", "Palermo", "Da Pina");


//        puntoDiEmissioneDAO.save(d1);
//        puntoDiEmissioneDAO.save(r1);

        // Serve un cast perche restituisce classe padre.. In alternativa ancora meglio se non si conosce cosa tornera' posso fare un if con instance of

        RivenditoreAutorizzato r1fromDB =
                (RivenditoreAutorizzato) puntoDiEmissioneDAO.trovaPuntoById(UUID.fromString("235df389-879f-4d18-b3ce-07507e2b84b4"));

        DistributoreAutomatico d1fromDB =
                (DistributoreAutomatico) puntoDiEmissioneDAO.trovaPuntoById(UUID.fromString("7da27ac7-5042-4f31-a0cc-4765e82aa5cb"));


//        Abbonamento a1 = new Abbonamento(PeriodicitaAbb.MENSILE, r1fromDB, t1fromDB);
//        Abbonamento a2 = new Abbonamento(PeriodicitaAbb.SETTIMANALE, d1fromDB, t2fromDB);

//        abbonamentoDao.salva(a1);
//        abbonamentoDao.salva(a2);

        // TODO  butto giu' le tabella perche'hanno aggiunto user-type e password (pw)
        // TEST TESSERA DAO
        Utente u4 = new Utente("Angelo", "Molteni", Sesso.MASCHIO, LocalDate.of(1987, 12, 21), UserType.USER, "ciao11");
//        utenteDao.save(u4);
        Utente u4fromDB = utenteDao.trovaUtentiPerId(UUID.fromString("f0d5d782-ce14-4262-92ea-5ceaf2295cb6"));
        Tessera t4 = new Tessera(LocalDate.now().minusYears(2), u4fromDB); // PER SCADUTE
//        tesseraDao.save(t4);

        Utente u5 = new Utente("Luca", "Neri", Sesso.MASCHIO, LocalDate.of(1988, 6, 15), UserType.ADMIN, "pass55");
//        utenteDao.save(u5);

        Tessera t5 = new Tessera(LocalDate.now().minusDays(355), u5);
//        tesseraDao.save(t5); // PER SCADENZA


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


        // Test Giorgia giorno 2

        // TRATTA

        TrattaDAO trattaDAO = new TrattaDAO(em);

        Tratta tr1 = new Tratta("Napoli", "Salerno", 45);
        Tratta tr2 = new Tratta("Bergamo", "Verona", 90);
        Tratta tr3 = new Tratta("FI-Careggi", "FI-Porta Al Prato", 17);
        Tratta tr4 = new Tratta("Cologno al Serio", "Bergamo", 20);
        Tratta tr5 = new Tratta("Napoli", "Aversa", 43);
        Tratta tr6 = new Tratta("Milano", "Bergamo", 38);

//        trattaDAO.save(tr1);
//        trattaDAO.save(tr2);
//        trattaDAO.save(tr3);
//        trattaDAO.save(tr4);
//        trattaDAO.save(tr5);
        //      trattaDAO.save(tr6);

        System.out.println("-------------------------- TEST TrovaTrattaPerId -------------------------------");

        Tratta tr1FromDB = trattaDAO.getById(1L);
        Tratta tr2FromDB = trattaDAO.getById(2L);
        Tratta tr3FromDB = trattaDAO.getById(3L);
        Tratta tr4FromDB = trattaDAO.getById(52L);
        Tratta tr5FromDB = trattaDAO.getById(53L);
        Tratta tr6FromDB = trattaDAO.getById(102L);
        System.out.println(tr1FromDB);
        System.out.println(tr2FromDB);
        System.out.println(tr3FromDB);
        System.out.println(tr4FromDB);
        System.out.println(tr5FromDB);
        System.out.println(tr6FromDB);


        System.out.println("-------------------------- TEST TrovaTutteTratte -------------------------------");


        List<Tratta> trTutte = trattaDAO.getAll();
        trTutte.forEach(System.out::println);


        System.out.println("-------------------------- TEST aggiorna -------------------------------");

>>>>>>> Stashed changes
//        System.out.println("PRIMA:");
//        System.out.println(mezzoDAO.getById(m1FromDB.getMezzoId()));
//
//        m1FromDB.setTarga("ZZ000YY");
//        m1FromDB.setTipoDiMezzo(TipoDiMezzo.TRAM);
//
//        mezzoDAO.update(m1FromDB);
//
//        System.out.println("DOPO:");
//        System.out.println(mezzoDAO.getById(m1FromDB.getMezzoId()));
//
<<<<<<< Updated upstream
=======
        // null pointer se gia cancellata


        System.out.println("-------------------------- TEST   trovaPerPartenza -------------------------------");
        ;
        trattaDAO.getByPartenza("Napoli").forEach(System.out::println);


        System.out.println("-------------------------- TEST   trovaPerArrivo -------------------------------");
        trattaDAO.getByArrivo("Bergamo").forEach(System.out::println);


        // MEZZO BASE

        BigliettoDAO bigliettiDAO = new BigliettoDAO(em);

        Mezzo m1 = new Mezzo(TipoDiMezzo.AUTOBUS, "AB123CD");
        Mezzo m2 = new Mezzo(TipoDiMezzo.TRAM, "TR456EF");
        Mezzo m3 = new Mezzo(TipoDiMezzo.AUTOBUS, "XY789ZZ");

//        mezzoDAO.save(m1);
//        mezzoDAO.save(m2);
//        mezzoDAO.save(m3);

        System.out.println("-------------------------- TEST  TrovaDaID -------------------------------");

        Mezzo m1FromDB = mezzoDAO.getById(1L);
        Mezzo m2FromDB = mezzoDAO.getById(2L);
        Mezzo m3FromDB = mezzoDAO.getById(3L);

        System.out.println(m1FromDB);
        System.out.println(m2FromDB);
        System.out.println(m3FromDB);

        System.out.println("-------------------------- TEST  PrendiTutti -------------------------------");

        List<Mezzo> tuttiIMezzi = mezzoDAO.getAll();
        tuttiIMezzi.forEach(System.out::println);


        System.out.println("-------------------------- TEST  Aggiorna -------------------------------");

        System.out.println("PRIMA:");
        System.out.println(mezzoDAO.getById(m1FromDB.getMezzoId()));

        m1FromDB.setTarga("ZZ000YY");
        m1FromDB.setTipoDiMezzo(TipoDiMezzo.TRAM);

        mezzoDAO.update(m1FromDB);

        System.out.println("DOPO:");
        System.out.println(mezzoDAO.getById(m1FromDB.getMezzoId()));


        // BIGLIETTI per DAO MEZZO

        Biglietto b1 = new Biglietto(r1fromDB, LocalDate.of(2026, 3, 31));
        Biglietto b2 = new Biglietto(d1fromDB, LocalDate.of(2026, 3, 31));
        Biglietto b3 = new Biglietto(d1fromDB, LocalDate.of(2026, 3, 16));
        Biglietto b4 = new Biglietto(d1fromDB, LocalDate.of(2026, 2, 8));
        Biglietto b5 = new Biglietto(d1fromDB, LocalDate.of(2026, 3, 31));

//        bigliettiDAO.save(b1);
//        bigliettiDAO.save(b2);
//        bigliettiDAO.save(b3);
//        bigliettiDAO.save(b4);
//        bigliettiDAO.save(b5);

        System.out.println("-------------------------- Trova Biglietto -------------------------------");

        Biglietto b1FromDB = bigliettiDAO.findById(UUID.fromString("11132b22-e34f-4d40-92de-f20b21287e42"));
        Biglietto b2FromDB = bigliettiDAO.findById(UUID.fromString("384639e0-c0a1-4efa-93c6-f82d8ce73d8d"));
        Biglietto b3FromDB = bigliettiDAO.findById(UUID.fromString("93dec3e1-2864-444d-bcd3-a0998efe05b8"));
        Biglietto b4FromDB = bigliettiDAO.findById(UUID.fromString("c0f374c8-c475-4ae4-980f-297f6081e09b"));
        Biglietto b5FromDB = bigliettiDAO.findById(UUID.fromString("e9e01193-0743-4378-ad1c-6d496e26cf35"));

        System.out.println("-------------------------- Piu setta il bus di validazione -------------------------------");
//        b1FromDB.setMezzoId(m1FromDB);
//        b2FromDB.setMezzoId(m1FromDB);
//        b3FromDB.setMezzoId(m1FromDB);
//        b4FromDB.setMezzoId(m2FromDB);
//        b5FromDB.setMezzoId(m2FromDB);
>>>>>>> Stashed changes
//
//        // BIGLIETTI per DAO MEZZO
//
<<<<<<< Updated upstream
//        Biglietto b1 = new Biglietto(r1fromDB, LocalDate.of(2026, 3, 31));
//        Biglietto b2 = new Biglietto(d1fromDB, LocalDate.of(2026, 3, 31));
//        Biglietto b3 = new Biglietto(d1fromDB, LocalDate.of(2026, 3, 16));
//        Biglietto b4 = new Biglietto(d1fromDB, LocalDate.of(2026, 2, 8));
//        Biglietto b5 = new Biglietto(d1fromDB, LocalDate.of(2026, 3, 31));
//
////        bigliettiDAO.save(b1);
////        bigliettiDAO.save(b2);
////        bigliettiDAO.save(b3);
////        bigliettiDAO.save(b4);
////        bigliettiDAO.save(b5);
//
//        System.out.println("-------------------------- Trova Biglietto -------------------------------");
//
//        Biglietto b1FromDB = bigliettiDAO.findById(UUID.fromString("11132b22-e34f-4d40-92de-f20b21287e42"));
//        Biglietto b2FromDB = bigliettiDAO.findById(UUID.fromString("384639e0-c0a1-4efa-93c6-f82d8ce73d8d"));
//        Biglietto b3FromDB = bigliettiDAO.findById(UUID.fromString("93dec3e1-2864-444d-bcd3-a0998efe05b8"));
//        Biglietto b4FromDB = bigliettiDAO.findById(UUID.fromString("c0f374c8-c475-4ae4-980f-297f6081e09b"));
//        Biglietto b5FromDB = bigliettiDAO.findById(UUID.fromString("e9e01193-0743-4378-ad1c-6d496e26cf35"));
//
//        System.out.println("-------------------------- Piu setta il bus di validazione -------------------------------");
////        b1FromDB.setMezzoId(m1FromDB);
////        b2FromDB.setMezzoId(m1FromDB);
////        b3FromDB.setMezzoId(m1FromDB);
////        b4FromDB.setMezzoId(m2FromDB);
////        b5FromDB.setMezzoId(m2FromDB);
////
////        bigliettiDAO.update(b1FromDB);
////        bigliettiDAO.update(b2FromDB);
////        bigliettiDAO.update(b3FromDB);
////        bigliettiDAO.update(b4FromDB);
////        bigliettiDAO.update(b5FromDB);
//
//
//        System.out.println(b1FromDB);
//        System.out.println(b2FromDB);
//        System.out.println(b3FromDB);
//        System.out.println(b4FromDB);
//        System.out.println(b5FromDB);
//
//
//        System.out.println("-------------------------- Test per Biglietti validati per Mezzo -------------------------------");
//
//        System.out.println("Biglietti validati su m1: " + mezzoDAO.bigliettiValidatiSuMezzo(m1FromDB.getMezzoId()));
//        System.out.println("Biglietti validati su m2: " + mezzoDAO.bigliettiValidatiSuMezzo(m2FromDB.getMezzoId()));
//
//
//        // MANUTENZIONE per DAO MEZZO
//
//        ManutenzioneDAO manutenzioneDAO = new ManutenzioneDAO(em);
//
//        Manutenzione man1 = new Manutenzione("Cambio gomme", LocalDate.of(2025, 3, 20), m1FromDB);
//        Manutenzione man2 = new Manutenzione("Revisione motore", LocalDate.of(2026, 3, 1), m2FromDB);
//        Manutenzione man3 = new Manutenzione("Controllo freni", LocalDate.of(2025, 2, 15), m1FromDB);
//
////        manutenzioneDAO.save(man1);
////        manutenzioneDAO.save(man2);
////        manutenzioneDAO.save(man3);
//
//        Manutenzione man1FromDB = manutenzioneDAO.getById(1L);
//        Manutenzione man2FromDB = manutenzioneDAO.getById(2L);
//        Manutenzione man3FromDB = manutenzioneDAO.getById(3L);
//
//        System.out.println(man1FromDB);
//        System.out.println(man2FromDB);
//        System.out.println(man3FromDB);
//
//        System.out.println("-------------------------- TEST ChiudoManutenzioni E AGGIORNO -------------------------------");
//
//        man1FromDB.setDataFine(LocalDate.of(2025, 2, 25));
//        man3FromDB.setDataFine(LocalDate.of(2025, 2, 28));
//
////        manutenzioneDAO.update(man1FromDB); /////// SUPER OCCHIO CHE MI SCORDO SEMPRE
////        manutenzioneDAO.update(man3FromDB);
//
//        System.out.println(man1FromDB);
//        System.out.println(man3FromDB);
//
//        System.out.println("-------------------------- TEST TutteManutenzioni -------------------------------");
//        manutenzioneDAO.getAll().forEach(System.out::println);
//
//        System.out.println("-------------------------- TEST ManutenzioniInCorso -------------------------------");
//        manutenzioneDAO.getManutenzioniInCorso().forEach(System.out::println);
//
//
////     TORNO A MEZZI
//
//        System.out.println("-------------------------- TEST MezziInManutenzione -------------------------------");
//        mezzoDAO.getMezziInManutenzione().forEach(System.out::println);
//
//        System.out.println("-------------------------- TEST getManutenzioniPerPeriodo -------------------------------");
//        mezzoDAO.getManutenzioniPerPeriodo(
//                m1FromDB.getMezzoId(),
//                LocalDate.of(2025, 2, 1),
//                LocalDate.of(2025, 4, 30)   // Excp Invalid date APRIL 31 OPS
//        ).forEach(System.out::println);
//
//
//        // PERCORRENZATRATTA
//
//        PercorrenzaTrattaDAO ptDAO = new PercorrenzaTrattaDAO(em);
//
//        PercorrenzaTratta pt1 = new PercorrenzaTratta(50, tr1FromDB, m1FromDB);
//        PercorrenzaTratta pt2 = new PercorrenzaTratta(47, tr1FromDB, m1FromDB);
//        PercorrenzaTratta pt3 = new PercorrenzaTratta(60, tr1FromDB, m2FromDB);
//        PercorrenzaTratta pt4 = new PercorrenzaTratta(95, tr2FromDB, m2FromDB);
//        PercorrenzaTratta pt5 = new PercorrenzaTratta(90, tr2FromDB, m2FromDB);
//
////
////        ptDAO.save(pt1);
////        ptDAO.save(pt2);
////        ptDAO.save(pt3);
////        ptDAO.save(pt4);
////        ptDAO.save(pt5);
//
//
//        System.out.println("-------------------------- Trova PercorrenzaTratta -------------------------------");
//        PercorrenzaTratta pt1FromDB = ptDAO.getById(1L); //    Occhio bisogna usare percorso_id della tabella
//        PercorrenzaTratta pt2FromDB = ptDAO.getById(2L);
//        PercorrenzaTratta pt3FromDB = ptDAO.getById(3L);
//        PercorrenzaTratta pt4FromDB = ptDAO.getById(4L);
//        PercorrenzaTratta pt5FromDB = ptDAO.getById(5L);
//
//        System.out.println(pt1FromDB);
//        System.out.println(pt2FromDB);
//        System.out.println(pt3FromDB);
//        System.out.println(pt4FromDB);
//        System.out.println(pt5FromDB);
//
//
//        System.out.println("-------------------------- Prendo tutte percorrenze stampo lista -------------------------------");
//        ptDAO.getAll().forEach(System.out::println);
//
//        System.out.println("-------------------------- TEST TempoMedioPerTratta su tutti i mezzi -------------------------------");
//        System.out.println(ptDAO.getTempoMedioPerTratta(tr1FromDB.getTrattaId()));
//
//
//        System.out.println("-------------------------- TEST getTempoMedioPerTrattasuMezzo -------------------------------");
//        System.out.println(ptDAO.getTempoMedioPerTrattaEMezzo(
//                tr1FromDB.getTrattaId(),
//                m1FromDB.getMezzoId()
//        ));
//
//        System.out.println("-------------------------- TEST TutteTratteFatteDalMezzo -------------------------------");
//        ptDAO.getByMezzo(m1FromDB.getMezzoId()).forEach(System.out::println);
//
//
//        System.out.println("-------------------------- TEST volte tratta per mezzo -------------------------------");
//        ptDAO.printDettaglioPercorrenzePerTratta(m2FromDB.getMezzoId());
//        System.out.println("-------------------------- TEST TrovaInScadenza -------------------------------");
//        List<Tessera> tessereInScadenza = tesseraDao.trovaInScadenza(LocalDate.now().plusDays(30));
//        tessereInScadenza.forEach(System.out::println);
=======
//        ptDAO.save(pt1);
//        ptDAO.save(pt2);
//        ptDAO.save(pt3);
//        ptDAO.save(pt4);
//        ptDAO.save(pt5);


        System.out.println("-------------------------- Trova PercorrenzaTratta -------------------------------");
        PercorrenzaTratta pt1FromDB = ptDAO.getById(1L); //    Occhio bisogna usare percorso_id della tabella
        PercorrenzaTratta pt2FromDB = ptDAO.getById(2L);
        PercorrenzaTratta pt3FromDB = ptDAO.getById(3L);
        PercorrenzaTratta pt4FromDB = ptDAO.getById(4L);
        PercorrenzaTratta pt5FromDB = ptDAO.getById(5L);

        System.out.println(pt1FromDB);
        System.out.println(pt2FromDB);
        System.out.println(pt3FromDB);
        System.out.println(pt4FromDB);
        System.out.println(pt5FromDB);


        System.out.println("-------------------------- Prendo tutte percorrenze stampo lista -------------------------------");
        ptDAO.getAll().forEach(System.out::println);

        System.out.println("-------------------------- TEST TempoMedioPerTratta su tutti i mezzi -------------------------------");
        System.out.println(ptDAO.getTempoMedioPerTratta(tr1FromDB.getTrattaId()));


        System.out.println("-------------------------- TEST getTempoMedioPerTrattasuMezzo -------------------------------");
        System.out.println(ptDAO.getTempoMedioPerTrattaEMezzo(
                tr1FromDB.getTrattaId(),
                m1FromDB.getMezzoId()
        ));

        System.out.println("-------------------------- TEST TutteTratteFatteDalMezzo -------------------------------");
        ptDAO.getByMezzo(m1FromDB.getMezzoId()).forEach(System.out::println);


        System.out.println("-------------------------- TEST volte tratta per mezzo -------------------------------");
        ptDAO.printDettaglioPercorrenzePerTratta(m2FromDB.getMezzoId());
>>>>>>> Stashed changes


//        System.out.println("-------------------------- TEST Creazione Mezzo e cambiare Stato IN_MANUTEZIONE -------------------------------");
//        ManutenzioneDAO manutenzioneDAO = new ManutenzioneDAO(em);
//        Mezzo bus = new Mezzo(TipoDiMezzo.AUTOBUS, "SDWER23");
//        mezzoDAO.save(bus);
//
//        manutenzioneDAO.entraInManutenzione(bus, "Rottura cambio");
//        em.close();
//        emf.close();
//
//        System.out.println("-------------------------- TEST Creazione Biglietto e aggiornamento vidimazione -------------------------------");
//        Biglietto nuovoBiglietto = new Biglietto(r1fromDB, null);
//        bigliettoDAO.save(nuovoBiglietto);
//
//        Biglietto b = bigliettoDAO.findById(UUID.fromString("74b7ehlt-c85d-2511-96fb-ra37359234gryue"));
//
//        b.setVidimato(true);          // ===> quindi nessun setVidimato?
//        b.setMezzo(bus);  // stesso bus di sopra
//        b.setDataAnnullamento(LocalDate.now());
//
//        System.out.println("Biglietto vidimato con successo sul bus " + bus.getTarga());


    }


}
