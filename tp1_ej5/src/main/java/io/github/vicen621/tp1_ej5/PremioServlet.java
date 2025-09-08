package io.github.vicen621.tp1_ej5;

import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "premioServlet", value = "/premio-servlet", initParams = {
        @WebInitParam(name = "mensaje", value = "¡Felicitaciones @! eres el visitante número # de nuestro sitio y has sido " +
                "seleccionado para el gran premio TTPS - Cursada APROBADA")
})
public class PremioServlet extends HttpServlet {
    private String mensaje;
    private String ultimoAcceso;
    private int visitas;

    public void init() {
        mensaje = this.getInitParameter("mensaje");
        ultimoAcceso = "NULL";
        visitas = 0;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        visitas++;
        String name = request.getParameter("name");
        this.ultimoAcceso = name;
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<p>" + mensaje.replace("@", name).replace("#", visitas + "") + "</p>");
        out.println("</body></html>");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("formato").equals("json")) {
            response.setContentType("application/json");

            // Hello
            PrintWriter out = response.getWriter();
            out.println("{\"name\":\"" + ultimoAcceso + "\",\"visitas\":\"" + visitas + "\"}");
        }
    }



    public void destroy() {
    }
}