package br.com.projeto.arenapernambuco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                            "/login",
                            "/cadastro",
                            "/events",
                            "/compra/**",
                            "/politica-privacidade",
                            "/css/**",
                            "/js/**",
                            "/images/**"
                    ).permitAll()

                .requestMatchers("/empresa/**")
                .hasRole("EMPRESA")

                .requestMatchers("/gestor/**")
                .hasRole("GESTOR")

                .anyRequest()
                .authenticated()
            )

            .formLogin(login -> login
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(successHandler())
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/events")
            );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
            var roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

            if (roles.contains("ROLE_GESTOR")) {
                response.sendRedirect("/gestor/dashboard");
                return;
            }

            if (roles.contains("ROLE_EMPRESA")) {
                response.sendRedirect("/empresa/dashboard");
                return;
            }

            response.sendRedirect("/events");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}