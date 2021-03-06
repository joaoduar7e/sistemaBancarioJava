package utils;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;

public class JpaUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("donuscodePU");
    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void close(@Disposes EntityManager manager) {
        manager.close();
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/donuscode", "postgres", "postgres");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
