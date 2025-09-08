package io.github.vicen621.clasificados;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String siteName = context.getInitParameter("site-name");
        String siteEmail = context.getInitParameter("site-email");
        String sitePhone = context.getInitParameter("site-phone");

        context.setAttribute("sitioClasificado", new SitioClasificado(siteName, siteEmail, sitePhone));
    }
}