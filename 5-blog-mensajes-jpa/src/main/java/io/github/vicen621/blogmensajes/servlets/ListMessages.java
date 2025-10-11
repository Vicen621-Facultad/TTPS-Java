package io.github.vicen621.blogmensajes.servlets;

import io.github.vicen621.blogmensajes.model.Message;
import io.github.vicen621.blogmensajes.persistance.dao.FactoryDAO;
import io.github.vicen621.blogmensajes.persistance.dao.MessageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ListMessages", value = "/list_messages")
public class ListMessages extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MessageDAO messageDAO = FactoryDAO.getMessageDAO();
        List<Message> messages = messageDAO.findAll();

        // Indicar que el contenido es HTML
        response.setContentType("text/html;charset=UTF-8");

        // Obtener el writer para escribir la respuesta
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Mensajes</title>");
            out.println("<link rel='stylesheet' " +
                    "href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container mt-5'>");
            out.println("<h1 class='mb-4'>Mensajes publicados</h1>");

            // Recorrer los mensajes y generar HTML
            for (Message msg : messages) {
                out.println("<div class='card mb-3'>");
                out.println("<div class='card-body'>");
                out.println("<h5 class='card-title'>" + escapeHtml(msg.getSender().getUsername()) + "</h5>");
                out.println("<p class='card-text'>" + escapeHtml(msg.getText()) + "</p>");
                out.println("</div>");
                out.println("</div>");
            }

            out.println("<a href='add_message.html' class='btn btn-primary'>Agregar mensaje</a>");

            out.println("</div>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    // simple method to scape HTML special characters
    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}