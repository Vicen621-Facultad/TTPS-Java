package io.github.vicen621.ttps2025_entregable1_garcia_marti.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "VisualizarEstadisticas", value = "/visualizar_estadisticas")
public class VisualizarEstadisticas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("""
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Estadísticas de ventas</title>
                    <link rel="stylesheet" href="estadisticas.css">
                </head>
                <body>
                    <div class="container">
                        <h1>Estadísticas de ventas de películas</h1>
                        <div class="card">
                            <div class="toolbar">
                                <span class="hint">Entradas vendidas por película</span>
                                <a class="btn" href="index.html" title="Volver">← Volver</a>
                            </div>
                            <div class="table-wrap">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Película</th>
                                            <th>Entradas Vendidas</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                """);

        // ... existing code ...
        var ventas = (java.util.HashMap<String, Integer>) request.getServletContext().getAttribute("ventas_peliculas");
        if (ventas != null && !ventas.isEmpty()) {
            for (var entry : ventas.entrySet()) {
                out.printf("<tr><td>%s</td><td><span class='pill'>%d</span></td></tr>%n",
                        escape(entry.getKey()), entry.getValue());
            }
        } else {
            out.println("<tr><td class='empty' colspan='2'>No hay ventas registradas.</td></tr>");
        }
        // ... existing code ...

        out.println("""
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </body>
                </html>
                """);

        out.flush();
        out.close();
    }

    private String escape(String s) {
        if (s == null) return "";
        return s
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

}