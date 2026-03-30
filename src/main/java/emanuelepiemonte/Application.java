package emanuelepiemonte;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BuildWeek4");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager();
    }
}
