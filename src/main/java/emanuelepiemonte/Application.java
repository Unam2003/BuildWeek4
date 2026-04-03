package emanuelepiemonte;


import emanuelepiemonte.entities.Utente;
import emanuelepiemonte.enums.UserType;
import emanuelepiemonte.utils.AdminMain;
import emanuelepiemonte.utils.RegistrazioneLogin;
import emanuelepiemonte.utils.UtenteMain;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BuildWeek4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        //region Istanze
        //endregion
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
    }
}
