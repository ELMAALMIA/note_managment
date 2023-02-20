package estm.dsic.web01.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "NoCacheFilter", urlPatterns = {"/*"})
public class NoCacheFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        httpServletResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
        httpServletResponse.setHeader("Expires", "0"); // Proxies
        chain.doFilter(request, response);
    }

    public void destroy() {
        // Nothing to do here
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        // Nothing to do here
    }
}
