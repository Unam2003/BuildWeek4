package emanuelepiemonte.utils;

import emanuelepiemonte.dao.AbbonamentoDAO;
import emanuelepiemonte.dao.TesseraDao;
import emanuelepiemonte.dao.UtenteDAO;
import emanuelepiemonte.entities.Abbonamento;
import emanuelepiemonte.entities.Tessera;
import emanuelepiemonte.entities.Utente;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class UtenteMain {
    Scanner scanner = new Scanner(System.in);

    public void menuUtente(EntityManager em, Utente user) {
        UtenteDAO ud = new UtenteDAO(em);
        TesseraDao td = new TesseraDao(em);
        AbbonamentoDAO ad = new AbbonamentoDAO(em);
        Tessera tessera = td.trovaTesseraDaUtente(user.getUtenteId());
        System.out.println("=============================================");
        System.out.println("-----------------Menu Utente-----------------");
        System.out.println("'0' Esci");
        System.out.println("'1' Dettagli profilo");
        System.out.println("'2' Dettagli tessera");
        System.out.println("'3' Storico abbonament");
        System.out.println("'4' Controllo validitità abbonamento");
        System.out.println("'5' Nuovo abbonamento");
        System.out.println("'6' Vedi tratte disponibili");
        System.out.println("'7' Cerca tratta per zona o capolinea");
        System.out.println("'8' Cerca i mezzi disponibili per una tratta");
        System.out.println("=============================================");
        System.out.println("Inserisci il comando");
        while (true) {
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
                    System.out.println(tessera.toString());
                }
                case "3" -> {
                    List<Abbonamento> storicoAbbonamenti = ad.TrovaAbbonamentiDaTessera(tessera.getTesseraId());
                    storicoAbbonamenti.forEach(System.out::println);
                }
                case "4" -> {
                    Abbonamento abbAttivo = ad.trovaAbbAttivoPerTessera(tessera.getTesseraId());
                    System.out.println(abbAttivo);
                }
//                case "5" -> {
//                    Abbonamento nuovoAbbonamento = new Abbonamento()
//                }
            }
        }
    }
}
