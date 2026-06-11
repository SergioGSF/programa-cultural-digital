package br.com.projeto.arenapernambuco.filter;

import br.com.projeto.arenapernambuco.service.RateLimitService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimitFilter extends OncePerRequestFilter {
    
    @Autowired
    private RateLimitService rateLimitService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) 
            throws ServletException, IOException {
        
        // Aplicar rate limiting apenas no login
        if (request.getRequestURI().equals("/login") && "POST".equalsIgnoreCase(request.getMethod())) {
            String chaveIdentificador = obterChaveIdentificador(request);
            
            if (!rateLimitService.permitirRequisicao(chaveIdentificador)) {
                response.setStatus(429); // Too Many Requests
                response.getWriter().write("Muitas tentativas de login. Tente novamente em 15 minutos.");
                return;
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
    private String obterChaveIdentificador(HttpServletRequest request) {
        // Usar email da requisição + IP do cliente
        String email = request.getParameter("email");
        String ip = request.getRemoteAddr();
        return email != null ? email + "_" + ip : ip;
    }
}
