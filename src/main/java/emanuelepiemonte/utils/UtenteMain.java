package emanuelepiemonte.utils;

import emanuelepiemonte.dao.*;
import emanuelepiemonte.entities.*;
import emanuelepiemonte.enums.PeriodicitaAbb;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UtenteMain {
    Scanner scanner = new Scanner(System.in);

    public void menuUtente(EntityManager em, Utente user) {
        UtenteDAO ud = new UtenteDAO(em);
        TesseraDao td = new TesseraDao(em);
        AbbonamentoDAO ad = new AbbonamentoDAO(em);
        TrattaDAO trd = new TrattaDAO(em);
        PuntoDiEmissioneDAO pded = new PuntoDiEmissioneDAO(em);
        Tessera tessera = td.trovaTesseraDaUtente(user.getUtenteId());
        while (true) {
            System.out.println("=============================================");
            System.out.println("                 Menu Utente                 ");
            System.out.println("=============================================");
            System.out.println("0. Esci");
            System.out.println("1. Dettagli profilo");
            System.out.println("2. Dettagli tessera");
            System.out.println("3. Storico abbonamenti");
            System.out.println("4. Controllo validitità abbonamento");
            System.out.println("5. Nuovo abbonamento");
            System.out.println("6. Vedi tratte disponibili");
            System.out.println("7. Cerca tratta per zona o capolinea");
            String input = scanner.nextLine();
            switch (input) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    Utente profile = ud.trovaUtentiPerId(user.getUtenteId());
                    System.out.println(profile.toString());
                }
                case "2" -> {
                    if (tessera == null) {
                        System.out.println("Nessuna tessera registrata per l'utente " + user.getNome() + " " + user.getCognome());
                        System.out.println("Registrare una tessera per continuare");
                        continue;
                    }
                    System.out.println(tessera);
                }
                case "3" -> {
                    if (tessera == null) {
                        System.out.println("Nessuna tessera registrata per l'utente " + user.getNome() + " " + user.getCognome());
                        System.out.println("Registrare una tessera per continuare");
                        continue;
                    }
                    List<Abbonamento> storicoAbbonamenti = ad.TrovaAbbonamentiDaTessera(tessera.getTesseraId());
                    if (storicoAbbonamenti.isEmpty()) System.out.println("Non hai nessun abbonamento");
                    storicoAbbonamenti.forEach(System.out::println);
                }
                case "4" -> {
                    if (tessera == null) {
                        System.out.println("Nessuna tessera registrata per l'utente " + user.getNome() + " " + user.getCognome());
                        System.out.println("Registrare una tessera per continuare");
                        continue;
                    }
                    Abbonamento abbAttivo = ad.trovaAbbAttivoPerTessera(tessera.getTesseraId());

                    if (abbAttivo == null) {
                        System.out.println("Non hai abbonamenti attivi");
                        continue;
                    }

                    if (abbAttivo.getDataScadenza().isAfter(LocalDate.now())) {
                        System.out.println("-------------Abbonamento attivo--------------");
                    } else if (abbAttivo.getDataScadenza().isBefore(LocalDate.now())) {
                        System.out.println("!-----ni------ABBONAMENTO SCADUTO-------------!");
                    }
                }
                case "5" -> {
                    PeriodicitaAbb periodicita;
                    System.out.println("Inserisci una città per trovare i punti di ritiro dell'abbonamento");
                    while (true) {
                        String citta = scanner.nextLine();
                        List<PuntoDiEmissione> listaPunti = pded.trovaPuntiPerCitta(citta);
                        listaPunti.forEach(System.out::println);
                        if (!listaPunti.isEmpty()) break;
                    }
                    System.out.println("Inserisci l'ID di un punto per selezionarlo");
                    UUID id;
                    while (true) {
                        try {
                            id = UUID.fromString(scanner.nextLine());
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("ID non valido");
                        }
                    }
                    PuntoDiEmissione punto = pded.trovaPuntoById(id);
                    if (punto == null) {
                        System.out.println("Punto non trovato");
                        continue;
                    }
                    System.out.println("Seleziona la durata dell'abbonamento");
                    while (true) {
                        System.out.println("1. Settimanale");
                        System.out.println("2. Mensile");
                        String selected = scanner.nextLine();
                        switch (selected) {
                            case "1" -> {
                                periodicita = PeriodicitaAbb.SETTIMANALE;
                            }
                            case "2" -> {
                                periodicita = PeriodicitaAbb.MENSILE;
                            }
                            default -> {
                                System.out.println("Valore non valido, riprovare");
                                continue;
                            }
                        }
                        break;
                    }
                    Abbonamento nuovoAbbonamento = new Abbonamento(periodicita, LocalDate.now(), punto, tessera);
                    ad.salva(nuovoAbbonamento);
                }
                case "6" -> {
                    List<Tratta> tratte = trd.getAll();
                    tratte.forEach(System.out::println);
                }
                case "7" -> {
                    System.out.println("Inserisci la zona");
                    String zona = scanner.nextLine();
                    List<Tratta> trattePerZona = trd.getByPartenza(zona);
                    if (trattePerZona.isEmpty()) {
                        System.out.println("Non ci sono mezzi in partenza da questa zona");
                    } else {
                        System.out.println("Le tratte disponibili in partenza da " + zona.toUpperCase() + ":");
                        trattePerZona.forEach(System.out::println);
                    }
                }
            }
        }
    }
}
