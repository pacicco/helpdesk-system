package com.gabriel.helpdesk.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuracao central de seguranca do sistema.
 * Define: como senhas sao criptografadas, e quais rotas exigem login.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Bean = um objeto que o Spring gerencia e disponibiliza para outras
    // classes usarem automaticamente (ex: o UsuarioController vai "pedir"
    // esse PasswordEncoder para criptografar senhas no cadastro).
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Desativa protecao CSRF - necessaria em apps com formulario HTML,
            // mas nossa API e "stateless" (sem sessao), entao nao se aplica aqui.
            .csrf(csrf -> csrf.disable())

            // Define quais rotas sao publicas e quais exigem login
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(org.springframework.http.HttpMethod.POST, "/usuarios").permitAll() // cadastro é livre
                .anyRequest().authenticated() // todo o resto exige login
            )

            // Usa autenticacao HTTP Basic (usuario/senha no cabecalho da requisicao)
            // - simples e suficiente para essa fase do projeto
            .httpBasic(basic -> {})

            // Nao guarda sessao no servidor - cada requisicao se autentica sozinha
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}