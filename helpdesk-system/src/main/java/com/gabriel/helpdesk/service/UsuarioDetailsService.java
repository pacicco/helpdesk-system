package com.gabriel.helpdesk.service;

import com.gabriel.helpdesk.model.Usuario;
import com.gabriel.helpdesk.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Ponte entre nosso Usuario e o sistema de autenticacao do Spring Security.
 * Quando alguem tenta fazer login, o Spring chama automaticamente o metodo
 * loadUserByUsername() abaixo para buscar o usuario e comparar a senha.
 */
@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado: " + email));

        // Constroi um objeto que o Spring Security entende, usando o email como "username"
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha()) // ja deve estar como hash, nao texto puro
                .roles(usuario.getTipo().name()) // ex: SOLICITANTE, TECNICO, ADMINISTRADOR
                .build();
    }
}
