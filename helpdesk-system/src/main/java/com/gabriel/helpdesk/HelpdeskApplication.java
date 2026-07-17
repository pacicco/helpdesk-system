package com.gabriel.helpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal - ponto de entrada da aplicacao.
 * Ao rodar o metodo main, o Spring Boot sobe um servidor web
 * e conecta automaticamente no banco de dados configurado
 * em application.properties.
 */
@SpringBootApplication
public class HelpdeskApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelpdeskApplication.class, args);
    }

}
