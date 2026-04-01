package emanuelepiemonte.utils;

import emanuelepiemonte.dao.UtenteDAO;
import emanuelepiemonte.entities.Utente;
import emanuelepiemonte.enums.Sesso;
import emanuelepiemonte.enums.UserType;
import jakarta.persistence.EntityManager;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RegistrazioneLogin {
    Scanner scanner = new Scanner(System.in);

    public void registrazione(EntityManager em) {
        Utente newUtente = new Utente();
        String nome;
        String cognome;
        Sesso sesso;
        LocalDate dataDiNascita;
        UserType userType;
        String pw;
        System.out.println("Inserisci il nome");
        while (true) {
            nome = scanner.nextLine();
            if (!nome.matches(".*\\d.*")) break;
            System.out.println("Errore, il nome non può contenere numeri. Riprova.");
        }
        System.out.println("Inserisci il cognome");
        while (true) {
            cognome = scanner.nextLine();
            if (!cognome.matches(".*\\d.*")) break;
            System.out.println("Errore, il cognome non può contenere numeri. Riprova.");
        }
        System.out.println("Inserisci il Sesso(Maschio, Femmina, Altro, Non Specificato)");
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "maschio" -> {
                    sesso = Sesso.MASCHIO;
                    break;
                }
                case "femmina" -> {
                    sesso = Sesso.FEMMINA;
                    break;
                }
                case "altro" -> {
                    sesso = Sesso.ALTRO;
                    break;
                }
                case "non specificato" -> {
                    sesso = Sesso.NON_SPECIFICATO;
                    break;
                }
                default -> {
                    System.out.println("Valore non valido");
                    System.out.println("Valori consentiti: Maschio, Femmina, Altro, Non Specificato");
                    continue;
                }
            }
            break;
        }
        System.out.println("Inserisci la data di nascita(gg/mm/yyyy)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            String input = scanner.nextLine();
            try {
                dataDiNascita = LocalDate.parse(input, formatter);
                break;
            } catch (DateTimeException e) {
                System.out.println("Valore non valido");
                System.out.println("consentito: gg/mm/yyyy (es. 25/12/2000)");
            }
        }
        System.out.println("Selezionare il tipo di utente(ADMIN o USER)");
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "admin" -> {
                    userType = UserType.ADMIN;
                    break;
                }
                case "user" -> {
                    userType = UserType.USER;
                    break;
                }
                default -> {
                    System.out.println("Valore non valido");
                    System.out.println("Valori consentiti: admin o user");
                    continue;
                }
            }
            break;
        }
        System.out.println("Seleziona una password (lunghezza compresa tra i 6 e i 10 caratteri)");
        while (true) {
            pw = scanner.nextLine();
            if (pw.length() > 5 && pw.length() < 10) break;
            System.out.println("Password non valida, deve essere compresa tra 6 e 10 caratteri");
        }
        Utente nuovoUtente = new Utente(nome, cognome, sesso, dataDiNascita, userType, pw);
        UtenteDAO ud = new UtenteDAO(em);
        ud.save(nuovoUtente);
    }
}
