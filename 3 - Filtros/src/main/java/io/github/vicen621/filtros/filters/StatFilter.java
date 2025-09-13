package io.github.vicen621.filtros.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebFilter(filterName = "StatFilter", urlPatterns = "/*")
public class StatFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);

        HttpServletRequest req = (HttpServletRequest) request;
        String ip = req.getRemoteAddr();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy/MMM/dd:HH:mm:ss"));
        String method = req.getMethod();
        String uri = req.getRequestURI();
        String protocol = req.getProtocol();
        String userAgent = req.getHeader("User-Agent");
        System.out.printf("%s [%s] \"%s %s %s\" - %s%n", ip, now, method, uri, protocol, userAgent);
    }
}