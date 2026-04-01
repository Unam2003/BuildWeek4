package emanuelepiemonte.utils;

import emanuelepiemonte.dao.*;
import emanuelepiemonte.entities.*;
import emanuelepiemonte.enums.PeriodicitaAbb;
import emanuelepiemonte.enums.Sesso;
import emanuelepiemonte.enums.TipoDiMezzo;
import emanuelepiemonte.enums.UserType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class AdminMain {

    private final MezzoDAO mezzoDAO;
    private final ManutenzioneDAO manutenzioneDAO;
    private final TrattaDAO trattaDAO;
    private final PercorrenzaTrattaDAO percorrenzaDAO;
    private final AbbonamentoDAO abbonamentoDAO;
    private final TesseraDao tesseraDAO;
    private final UtenteDAO utenteDAO;
    private final BigliettoDAO bigliettoDAO;
    private final PuntoDiEmissioneDAO puntoDiEmissioneDAO;

    public AdminMain(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        this.manutenzioneDAO = new ManutenzioneDAO(em);
        this.trattaDAO = new TrattaDAO(em);
        this.percorrenzaDAO = new PercorrenzaTrattaDAO(em);
        this.abbonamentoDAO = new AbbonamentoDAO(em);
        this.tesseraDAO = new TesseraDao(em);
        this.utenteDAO = new UtenteDAO(em);
        this.mezzoDAO = new MezzoDAO(em);
        this.puntoDiEmissioneDAO = new PuntoDiEmissioneDAO(em);
        this.bigliettoDAO = new BigliettoDAO(em);
    }

    // ----------!!!!!!!!!!!
    // ---------- MENU PRINCIPALE
    //----------- !!!!!!!!!!!!
    public void adminMenu(Scanner scanner) {
        boolean esci = false;

        while (!esci) {
            System.out.println("\n=============================================");
            System.out.println("          PANNELLO DI AMMINISTRAZIONE        ");
            System.out.println("=============================================");
            System.out.print("\nScegli un'opzione: ");
            System.out.println("\n1. Gestione Mezzi e Manutenzioni");
            System.out.println("2. Gestione Tratte e Percorrenze");
            System.out.println("3. Gestione Utenti e Tessere");
            System.out.println("4. Gestione Punti di Emissione");
            System.out.println("5. Emissione e Validazione Titoli (Biglietti/Abbonamenti)");
            System.out.println("0. Esci dal sistema");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    menuMezziManutenzioni(scanner);
                    break;
                case 2:
                    menuTrattePercorrenze(scanner);
                    break;
                case 3:
                    menuUtenti(scanner);
                    break;
                case 4:
                    menuPuntiEmissione(scanner);
                    break;
                case 5:
                    menuBigliettiAbbonamenti(scanner);
                    break;
                case 0:
                    esci = true;
                    System.out.println("Chiusura del pannello di amministrazione...");
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

    // -------------!!!!!!!!!!!!!!!!!!
    // -------------- MENU MEZZI E MANUTENZIONI
    // -------------!!!!!!!!!!!!!!!!!!
    private void menuMezziManutenzioni(Scanner scanner) {
        boolean indietro = false;
        while (!indietro) {
            System.out.println("\n--- GESTIONE MEZZI E MANUTENZIONI ---");
            System.out.println("1. Aggiungi nuovo Mezzo");
            System.out.println("2. Manda Mezzo in Manutenzione");
            System.out.println("3. Concludi Manutenzione Mezzo");
            System.out.println("0. Torna al menu principale");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    int tipo;
                    do {
                        System.out.println("Seleziona tipo (1=TRAM, 2=AUTOBUS): ");
                        tipo = scanner.nextInt();
                        scanner.nextLine();
                    } while (tipo != 1 && tipo != 2);
                    System.out.print("Inserisci Targa: ");
                    String targa = scanner.nextLine();

                    TipoDiMezzo tipoEnum = (tipo == 1) ? TipoDiMezzo.TRAM : TipoDiMezzo.AUTOBUS;
                    Mezzo nuovoMezzo = new Mezzo(tipoEnum, targa);
                    mezzoDAO.save(nuovoMezzo);
                    break;
                case 2:
                    System.out.print("ID del mezzo da mandare in manutenzione: ");
                    Long idGuasto = scanner.nextLong();
                    scanner.nextLine();
                    Mezzo mezzoGuasto = mezzoDAO.getById(idGuasto);
                    if (mezzoGuasto != null) {
                        System.out.print("Descrizione del problema: ");
                        String desc = scanner.nextLine();
                        Manutenzione nuovaMan = new Manutenzione(desc);
                        mezzoGuasto.getManutenzioni().add(nuovaMan);
                        mezzoDAO.update(mezzoGuasto);
                        System.out.println("Mezzo inviato in officina.");
                    } else {
                        System.out.println("Mezzo non trovato.");
                    }
                    break;
                case 3:
                    System.out.print("ID della manutenzione da concludere: ");
                    Long idMan = scanner.nextLong();
                    scanner.nextLine();
                    Manutenzione man = manutenzioneDAO.getById(idMan);
                    if (man != null && man.getDataFine() == null) {
                        man.setDataFine(LocalDate.now());
                        manutenzioneDAO.update(man);
                        System.out.println("Manutenzione conclusa. Mezzo di nuovo operativo.");
                    } else {
                        System.out.println("Manutenzione non trovata o già conclusa.");
                    }
                    break;
                case 0:
                    indietro = true;
                    break;
                default:
                    System.out.println("Opzione non valida.");
            }
        }
    }

    // -----------!!!!!!!!!!!!!!!!!!
    // -----------MENU TRATTE E PERCORRENZE
    // -----------!!!!!!!!!!!!!!!!!!
    private void menuTrattePercorrenze(Scanner scanner) {
        boolean indietro = false;
        while (!indietro) {
            System.out.println("\n--- GESTIONE TRATTE E PERCORRENZE ---");
            System.out.println("1. Crea nuova Tratta");
            System.out.println("2. Registra nuova Percorrenza di un mezzo");
            System.out.println("0. Torna al menu principale");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    System.out.print("Zona di partenza: ");
                    String partenza = scanner.nextLine();
                    System.out.print("Capolinea: ");
                    String arrivo = scanner.nextLine();
                    System.out.print("Tempo previsto (in minuti): ");
                    int tempoPrevisto = scanner.nextInt();
                    scanner.nextLine();

                    Tratta nuovaTratta = new Tratta(partenza, arrivo, tempoPrevisto);
                    trattaDAO.save(nuovaTratta);
                    break;
                case 2:
                    System.out.print("ID della Tratta: ");
                    Long idTratta = scanner.nextLong();
                    System.out.print("ID del Mezzo: ");
                    Long idMezzo = scanner.nextLong();
                    System.out.print("Tempo effettivo impiegato (in minuti): ");
                    int tempoEffettivo = scanner.nextInt();
                    scanner.nextLine();

                    Tratta tratta = trattaDAO.getById(idTratta);
                    Mezzo mezzo = mezzoDAO.getById(idMezzo);

                    if (tratta != null && mezzo != null) {
                        PercorrenzaTratta percorrenza = new PercorrenzaTratta(tempoEffettivo, tratta, mezzo);
                        percorrenzaDAO.save(percorrenza);
                    } else {
                        System.out.println("Tratta o Mezzo non trovati nel database.");
                    }
                    break;
                case 0:
                    indietro = true;
                    break;
                default:
                    System.out.println("Opzione non valida.");
            }
        }
    }

    // ----------!!!!!!!!!!!!
    // ---------- MENU UTENTI E TESSERE
    // ----------!!!!!!!!!!!!
    private void menuUtenti(Scanner scanner) {
        boolean indietro = false;
        while (!indietro) {
            System.out.println("\n--- GESTIONE UTENTI E TESSERE ---");
            System.out.println("1. Registra nuovo Utente");
            System.out.println("2. Emetti nuova Tessera per Utente");
            System.out.println("3. Rinnova Tessera scaduta");
            System.out.println("4. Rendi un Utente come nuovo ADMIN! (serve l'ID)");
            System.out.println("0. Torna al menu principale");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    try {
                        System.out.print("Nome utente: ");
                        String nome = scanner.nextLine();
                        System.out.print("Cognome utente: ");
                        String cognome = scanner.nextLine();
                        int tipo;
                        do {
                            System.out.println("Seleziona tipo (1=MASCHIO, 2=FEMMINA): ");
                            tipo = scanner.nextInt();
                            scanner.nextLine();
                        } while (tipo != 1 && tipo != 2);
                        Sesso sesso = (tipo == 1) ? Sesso.MASCHIO : Sesso.FEMMINA;

                        System.out.print("Data di nascita (Formato: YYYY-MM-DD): ");
                        String dataNascitaInput = scanner.nextLine();
                        LocalDate dataNascita = LocalDate.parse(dataNascitaInput);

                        Utente nuovoUtente = new Utente(nome, cognome, sesso, dataNascita, UserType.USER, "1234");
                        utenteDAO.save(nuovoUtente);
                        System.out.println("Utente creato con successo!");

                    } catch (Exception e) {
                        System.out.println("Errore nell'inserimento dei dati");
                    }
                    break;

                case 2:
                    System.out.print("Inserisci l'UUID dell'Utente a cui associare la tessera: ");
                    String uuidUtenteStr = scanner.nextLine();

                    try {
                        UUID idUtente = UUID.fromString(uuidUtenteStr);
                        Utente utenteTrovato = utenteDAO.trovaUtentiPerId(idUtente);

                        if (utenteTrovato != null) {
                            Tessera nuovaTessera = new Tessera(utenteTrovato);
                            tesseraDAO.save(nuovaTessera);
                            System.out.println("Tessera emessa con successo!");
                        } else {
                            System.out.println("Nessun utente trovato con questo UUID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Errore: Il formato dell'UUID inserito non è valido.");
                    }
                    break;

                case 3:
                    System.out.print("Inserisci l'UUID della Tessera da rinnovare: ");
                    String uuidTesseraStr = scanner.nextLine();

                    try {
                        UUID idTessera = UUID.fromString(uuidTesseraStr);
                        Tessera tesseraTrovata = tesseraDAO.findById(idTessera);

                        if (tesseraTrovata != null) {
                            tesseraTrovata.setData_scadenza(LocalDate.now().plusYears(1));
                            tesseraDAO.update(tesseraTrovata);
                            System.out.println("Tessera rinnovata! La nuova data di scadenza è: " + tesseraTrovata.getDataScadenza());
                        } else {
                            System.out.println("Tessera non trovata. Controlla l'UUID e riprova.");
                        }
                    } catch (Exception e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Inserisci l'ID dell'utente che vorresti rendere ADMIN!");
                    String uuidUtenteDaAdminizzare = scanner.nextLine();

                    try {
                        UUID uuidUtente = UUID.fromString(uuidUtenteDaAdminizzare);
                        Utente daAdminizzare = utenteDAO.trovaUtentiPerId(uuidUtente);
                        System.out.println("L'utente trovato è: " + daAdminizzare.getNome() + " " + daAdminizzare.getCognome());
                        int corretto;
                        do {
                            System.out.println("Corretto? 1=SI 2=NO");
                            corretto = Integer.parseInt(scanner.nextLine());
                        } while (corretto != 1 && corretto != 2);
                        if (corretto == 2) break;
                        else {
                            daAdminizzare.setUserType(UserType.ADMIN);
                            utenteDAO.update(daAdminizzare);
                            System.out.println("L'utente è stato impostato come amministratore!");
                        }
                    } catch (Exception e) {
                        System.out.println("Errore: " + e.getMessage());
                    }

                case 0:
                    indietro = true;
                    break;

                default:
                    System.out.println("Opzione non valida. Riprova.");
                    break;
            }
        }
    }

    //-----------!!!!!!!!!!
    // ---------- MENU PUNTI DI EMISSIONE
    //-----------!!!!!!!!!!
    private void menuPuntiEmissione(Scanner scanner) {
        boolean indietro = false;
        while (!indietro) {
            System.out.println("\n--- GESTIONE PUNTI DI EMISSIONE ---");
            System.out.println("1. Aggiungi Rivenditore Autorizzato");
            System.out.println("2. Aggiungi Distributore Automatico");
            System.out.println("3. Imposta Distributore come 'Fuori Servizio'");
            System.out.println("0. Torna al menu principale");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    //AGGIUNGI RIVENDITORE AUTORIZZATO
                    System.out.println("--- Inserimento Rivenditore Autorizzato ---");
                    System.out.print("Città: ");
                    String citta = scanner.nextLine();
                    System.out.print("Indirizzo: ");
                    String indirizzo = scanner.nextLine();
                    System.out.print("Nome all'insegna dell'attività (esempio: Centro Viaggi EPICODE): ");
                    String nomeAttivita = scanner.nextLine();

                    RivenditoreAutorizzato rivenditore = new RivenditoreAutorizzato(indirizzo, citta, nomeAttivita);
                    puntoDiEmissioneDAO.save(rivenditore);
                    System.out.println("Rivenditore salvato con successo!");
                    break;

                case 2:
                    //AGGIUNGI DISTRIBUTORE AUTOMATICO
                    System.out.println("--- Inserimento Distributore Automatico ---");
                    System.out.print("Città: ");
                    String cittaD = scanner.nextLine();
                    System.out.print("Indirizzo: ");
                    String indirizzoD = scanner.nextLine();
                    System.out.print("È subito attivo? (true/false): ");
                    boolean inServizio = scanner.nextBoolean();
                    scanner.nextLine(); // Svuota buffer

                    DistributoreAutomatico distributore = new DistributoreAutomatico(indirizzoD, cittaD, inServizio);
                    puntoDiEmissioneDAO.save(distributore);
                    System.out.println("Distributore salvato con successo!");
                    break;

                case 3:
                    //IMPOSTA FUORI SERVIZIO / IN SERVIZIO
                    System.out.print("Inserisci l'UUID del Distributore da aggiornare: ");
                    String uuidDistributore = scanner.nextLine();

                    try {
                        UUID idDistributore = UUID.fromString(uuidDistributore);
                        PuntoDiEmissione punto = puntoDiEmissioneDAO.trovaPuntoById(idDistributore);

                        if (punto instanceof DistributoreAutomatico distributoreTrovato) {
                            boolean nuovoStato = !distributoreTrovato.isInServizio();
                            distributoreTrovato.setInServizio(nuovoStato);

                            puntoDiEmissioneDAO.update(distributoreTrovato);
                            System.out.println("Stato aggiornato! Il distributore è ora: " + (nuovoStato ? "IN SERVIZIO" : "FUORI SERVIZIO"));
                        } else if (punto instanceof RivenditoreAutorizzato) {
                            System.out.println("L'ID inserito appartiene a un Rivenditore fisico, non può essere messo 'fuori servizio'.");
                        } else {
                            System.out.println("Distributore non trovato.");
                        }
                    } catch (Exception e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                    break;

                case 0:
                    indietro = true;
                    break;

                default:
                    System.out.println("Opzione non valida.");
                    break;
            }
        }
    }

    //-------------!!!!!!!!!!!
    //------------- MENU BIGLIETTI E ABBONAMENTI
    //-------------!!!!!!!!!!!
    private void menuBigliettiAbbonamenti(Scanner scanner) {
        boolean indietro = false;
        while (!indietro) {
            System.out.println("\n--- EMISSIONE E VALIDAZIONE ---");
            System.out.println("1. Emetti Biglietto singolo");
            System.out.println("2. Emetti Abbonamento (richiede Tessera)");
            System.out.println("3. Valida Biglietto su un Mezzo");
            System.out.println("0. Torna al menu principale");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    // EMISSIONE BIGLIETTO
                    try {
                        System.out.print("Inserisci l'UUID del Punto di Emissione: ");
                        UUID idPuntoEmissione = UUID.fromString(scanner.nextLine());
                        PuntoDiEmissione puntoEmissione = puntoDiEmissioneDAO.trovaPuntoById(idPuntoEmissione);

                        if (puntoEmissione != null) {
                            if (puntoEmissione instanceof DistributoreAutomatico && !((DistributoreAutomatico) puntoEmissione).isInServizio()) {
                                System.out.println("Errore: Il distributore è FUORI SERVIZIO.");
                            } else {
                                Biglietto nuovoBiglietto = new Biglietto(puntoEmissione, null);
                                bigliettoDAO.save(nuovoBiglietto);
                                System.out.println("Biglietto emesso con successo! ID: " + nuovoBiglietto.getBigliettoId());
                            }
                        } else {
                            System.out.println("Punto di emissione non trovato.");
                        }
                    } catch (Exception e) {
                        System.out.println("Errore, inserimento non valido: " + e.getMessage());
                    }
                    break;

                case 2:
                    // EMISSIONE ABBONAMENTO
                    try {
                        System.out.print("Inserisci l'UUID della Tessera: ");
                        UUID idTessera = UUID.fromString(scanner.nextLine());
                        Tessera tessera = tesseraDAO.findById(idTessera);

                        if (tessera == null) {
                            System.out.println("Tessera non trovata.");
                        } else if (tessera.getDataScadenza().isBefore(LocalDate.now())) {
                            System.out.println("Errore: La tessera è scaduta! Rinnovala prima di emettere l'abbonamento.");
                        } else {
                            System.out.print("Inserisci l'UUID del Punto di Emissione: ");
                            UUID idPuntoEmissione = UUID.fromString(scanner.nextLine());
                            PuntoDiEmissione puntoDiEmissione = puntoDiEmissioneDAO.trovaPuntoById(idPuntoEmissione);

                            if (puntoDiEmissione != null) {
                                int periodicitaScelta;
                                do {
                                    System.out.print("Periodicità (1 = SETTIMANALE, 2 = MENSILE): ");
                                    periodicitaScelta = Integer.parseInt(scanner.nextLine());
                                } while (periodicitaScelta != 1 && periodicitaScelta != 2);
                                PeriodicitaAbb periodicita = (periodicitaScelta == 1) ? PeriodicitaAbb.SETTIMANALE : PeriodicitaAbb.MENSILE;

                                Abbonamento nuovoAbb = new Abbonamento(periodicita, puntoDiEmissione, tessera);
                                abbonamentoDAO.salva(nuovoAbb);
                                System.out.println("Abbonamento " + periodicita + " emesso con successo!");
                            } else {
                                System.out.println("Punto di emissione non trovato.");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Errore durante l'emissione dell'abbonamento: " + e.getMessage());
                    }
                    break;

                case 3:
                    // VALIDAZIONE BIGLIETTO
                    try {
                        System.out.print("Inserisci l'UUID del Biglietto: ");
                        UUID idBiglietto = UUID.fromString(scanner.nextLine());

                        System.out.print("Inserisci l'ID del Mezzo: ");
                        Long idMezzo = Long.parseLong(scanner.nextLine());

                        Biglietto b = bigliettoDAO.findById(idBiglietto);
                        Mezzo mezzo = mezzoDAO.getById(idMezzo);

                        if (b == null || mezzo == null) {
                            System.out.println("Biglietto o Mezzo non trovati.");
                        } else if (b.getDataAnnullamento() != null) {
                            System.out.println("ERRORE: Biglietto già validato in data " + b.getDataAnnullamento());
                        } else {
                            b.setMezzo(mezzo);
                            b.setDataAnnullamento(LocalDate.now());
                            bigliettoDAO.update(b);
                            System.out.println("Validazione effettuata con successo sul mezzo: " + mezzo.getTarga());
                        }
                    } catch (Exception e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                    break;

                case 0:
                    indietro = true;
                    break;
            }
        }
    }
}