package com.gabriel.helpdesk.controller;

import com.gabriel.helpdesk.model.Usuario;
import com.gabriel.helpdesk.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller simples de Usuario.
 * Necessario para cadastrar Solicitantes/Tecnicos antes de testar a
 * abertura de chamados (todo Chamado precisa de um solicitante_id valido).
 *
 * Nota: a autenticacao (RNF04) sera implementada no Sprint 2 (US06).
 * Por enquanto, qualquer um pode criar um usuario - isso e temporario.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario) {
        Usuario salvo = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }
}
