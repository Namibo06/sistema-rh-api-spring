package com.waitomo.sistema_rh.configs;

import com.waitomo.sistema_rh.exceptions.NotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class Filter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        if("/employee".equals(requestURI) || "/login".equals(requestURI) || ("/enterprises".equals(requestURI) && "POST".equalsIgnoreCase(method)) || requestURI.startsWith("/swagger-ui/") || requestURI.startsWith("/v3/api-docs") || requestURI.startsWith("favicon.ico")){
            filterChain.doFilter(request,response);
            return;
        }

        try{
            var token = findToken(request);

            if (token == null) {
                throw new NotFoundException("Token",'o');
            }

            filterChain.doFilter(request,response);
        }catch (NotFoundException ex){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.write("{\"message\": \"" + ex.getMessage() + "\", \"status\": " + HttpStatus.NOT_FOUND.value() + "}");
            writer.flush();
        }


    }

    private String findToken(HttpServletRequest request){
        var authorization = request.getHeader("Authorization");
        if(authorization == null){
            throw new NotFoundException("Token",'o');
        }

        return authorization.replace("Bearer","");
    }
}
