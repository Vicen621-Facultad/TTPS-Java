package io.github.vicen621.filtros.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebFilter(filterName = "LanguageClientFilter", urlPatterns = "/*")
public class LanguageClientFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setAttribute("lang", request.getLocale());
        chain.doFilter(request, response);
    }
}