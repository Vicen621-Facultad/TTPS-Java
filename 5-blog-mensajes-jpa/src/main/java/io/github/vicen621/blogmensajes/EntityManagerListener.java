package io.github.vicen621.blogmensajes.persistance;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class EntityManagerListener implements ServletContextListener {

    private static EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(">>> Inicializando EntityManagerFactory...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Ahora sí, creamos el Factory
            emf = Persistence.createEntityManagerFactory("io.github.vicen621.blogmensajes.jpa");
            System.out.println(">>> EntityManagerFactory creado con éxito.");
        } catch (Exception e) {
            System.err.println("!!! ERROR FATAL al inicializar EntityManagerFactory !!!");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (emf != null && emf.isOpen()) {
            System.out.println(">>> Cerrando EntityManagerFactory...");
            emf.close();
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}