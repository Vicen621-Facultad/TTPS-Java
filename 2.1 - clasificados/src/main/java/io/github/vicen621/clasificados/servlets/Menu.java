package io.github.vicen621.clasificados.servlets;

import io.github.vicen621.clasificados.users.UserType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "Menu", value = "/menu")
public class Menu extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("error401.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/header");
        if (dispatcher != null)
            dispatcher.include(request, response);

        out.println("<h2>Menu</h2>");
        out.println("<ul>");
        Arrays.stream(((UserType) request.getAttribute("userType")).getMenuEntries())
                .forEach(entry -> out.println("<li>" + entry + "</li>"));
        out.println("</ul>");
        out.println("</body></html>");
        out.close();
    }
}