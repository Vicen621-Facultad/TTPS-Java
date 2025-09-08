package io.github.vicen621.clasificados;

import io.github.vicen621.clasificados.users.User;
import io.github.vicen621.clasificados.users.UserType;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    private List<User> users;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        users = new ArrayList<>();
        users.add(new User("publisher", "publisher", UserType.PUBLISHER));
        users.add(new User("admin", "admin", UserType.ADMIN));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        users.stream()
                .filter(u -> u.validateCredentials(username, password))
                .findFirst().ifPresent(u -> {
                    request.setAttribute("userType", u.getUserType());
                    try {
                        this.getServletContext().getRequestDispatcher("/menu").forward(request, response);
                    } catch (ServletException | IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        response.sendRedirect("error.html");
    }
}