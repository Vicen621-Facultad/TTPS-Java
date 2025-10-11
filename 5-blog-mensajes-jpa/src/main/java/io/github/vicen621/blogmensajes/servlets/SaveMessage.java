package io.github.vicen621.blogmensajes.servlets;

import io.github.vicen621.blogmensajes.model.Message;
import io.github.vicen621.blogmensajes.model.User;
import io.github.vicen621.blogmensajes.persistance.dao.FactoryDAO;
import io.github.vicen621.blogmensajes.persistance.dao.MessageDAO;
import io.github.vicen621.blogmensajes.persistance.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SaveMessage", value = "/save_message")
public class SaveMessage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String message = request.getParameter("message");
        String sender = request.getParameter("sender");

        if (message == null || message.trim().isEmpty() ||sender == null || sender.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El remitente y el mensaje no pueden estar vacíos.");
            return;
        }

        UserDAO userDAO = FactoryDAO.getUserDAO();
        User user = userDAO.findByUsername(sender);
        if (user == null) {
            user = new User(sender);
            userDAO.create(user);
        }

        MessageDAO messageDAO = FactoryDAO.getMessageDAO();
        messageDAO.create(new Message(message, user));
        response.sendRedirect(request.getContextPath() + "/list_messages");
    }
}