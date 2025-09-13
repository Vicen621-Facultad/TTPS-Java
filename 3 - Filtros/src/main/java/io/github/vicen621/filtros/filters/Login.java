package io.github.vicen621.filtros.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    private List<User> users;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        users = List.of(
                new User("admin", "admin"),
                new User("user", "user")
        );
    }

    @Override
    public void destroy() {
        super.destroy();
        users = null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = (Locale) request.getAttribute("lang");
        ResourceBundle bundle = ResourceBundle.getBundle("lang", locale);

        request.setAttribute("usernameLabel", bundle.getString("login.username"));
        request.setAttribute("passwordLabel", bundle.getString("login.password"));
        request.setAttribute("loginButton", bundle.getString("login.button"));

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Locale locale = (Locale) request.getAttribute("lang");

        boolean authenticated = users.stream().anyMatch(user -> user.authenticate(username, password));
        ResourceBundle bundle = ResourceBundle.getBundle("lang", locale);

        if (authenticated) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<html><body>");
            writer.println("<h1>" + bundle.getString("login.success") +
                    ", " + username + "!</h1>");
            writer.println("</body></html>");
            writer.close();
        } else {
            request.setAttribute("error", bundle.getString("login.error"));
            doGet(request, response); // Forward back to login page with error
        }
    }
}