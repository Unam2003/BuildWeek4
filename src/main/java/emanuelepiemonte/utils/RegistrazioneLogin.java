package emanuelepiemonte.utils;

import emanuelepiemonte.dao.UtenteDAO;
import emanuelepiemonte.entities.Utente;
import emanuelepiemonte.enums.Sesso;
import emanuelepiemonte.enums.UserType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RegistrazioneLogin {
    Scanner scanner = new Scanner(System.in);

    //    Metodo per registrare un utente tramite scanner
    public void registrazione(EntityManager em) {
        String nome;
        String cognome;
        Sesso sesso;
        LocalDate dataDiNascita;
        String pw;
        System.out.println("--------------REGISTRATI--------------");
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
        System.out.println("Seleziona una password (lunghezza compresa tra i 6 e i 10 caratteri)");
        while (true) {
            pw = scanner.nextLine();
            if (pw.length() > 5 && pw.length() < 10) break;
            System.out.println("Password non valida, deve essere compresa tra 6 e 10 caratteri");
        }
        Utente nuovoUtente = new Utente(nome, cognome, sesso, dataDiNascita, UserType.USER, pw);
        UtenteDAO ud = new UtenteDAO(em);
        ud.save(nuovoUtente);
    }

    //    Metodo per il login con scanner
    public Utente login(EntityManager em) {
        System.out.println("--------------LOGIN--------------");
        while (true) {
            System.out.println("Inserisci l'username(nome + cognome in minuscolo es.'mariorossi')");
            String loginUsername = scanner.nextLine();
            System.out.println("Inserisci la Password");
            String loginPw = scanner.nextLine();
            try {
                Utente searched = em.createQuery("SELECT u FROM Utente u WHERE u.username = :username AND u.pw = :pw", Utente.class)
                        .setParameter("username", loginUsername)
                        .setParameter("pw", loginPw)
                        .getSingleResult();
                System.out.println("Login effettuato con successo!");
                return searched;
            } catch (NoResultException e) {
                System.out.println("!---Username o Password errati---!");
            }
        }
    }
}


