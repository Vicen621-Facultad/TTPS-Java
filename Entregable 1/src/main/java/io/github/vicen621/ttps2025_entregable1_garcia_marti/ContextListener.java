package io.github.vicen621.ttps2025_entregable1_garcia_marti;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        int cantidadLatas = 10;
        try {
            cantidadLatas = Integer.parseInt(context.getInitParameter("cantidad.latas"));
        } catch (NumberFormatException ignored) {
            System.out.println("Error al parsear cantidad.latas, se usará el valor por defecto 10");
        }

        context.setAttribute("cantidad_latas", cantidadLatas);
    }
}