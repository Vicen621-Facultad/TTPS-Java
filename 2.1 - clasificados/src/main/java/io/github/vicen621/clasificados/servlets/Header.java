package io.github.vicen621.clasificados.servlets;

import io.github.vicen621.clasificados.SitioClasificado;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Header", value = "/header")
public class Header extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        var out = response.getWriter();
        var sitioClasificado = (SitioClasificado) this.getServletContext().getAttribute("sitioClasificado");
        out.println("<div style='background-color: lightgray; padding: 10px;'>");
        out.println("<h1>" + sitioClasificado.getName() + "</h1>");
        out.println("<p>Contact: " + sitioClasificado.getEmail() + " | " + sitioClasificado.getPhone() + "</p>");
        out.println("</div>");
    }
}