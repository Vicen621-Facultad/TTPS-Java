package io.github.vicen621.ttps2025_entregable1_garcia_marti.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@WebFilter(filterName = "VentaPeliculas", urlPatterns = {"/imprime_entrada"})
public class FiltroVentaPeliculas implements Filter {
    public void init(FilterConfig config) {
        config.getServletContext().setAttribute("ventas_peliculas", new HashMap<String, Integer>());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HashMap<String, Integer> ventas = (HashMap<String, Integer>) request.getServletContext().getAttribute("ventas_peliculas");
        String pelicula = request.getParameter("pelicula");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        ventas.putIfAbsent(pelicula, 0);
        ventas.put(pelicula, ventas.get(pelicula) + cantidad);
        request.getServletContext().setAttribute("ventas_peliculas", ventas);

        chain.doFilter(request, response);
    }
}