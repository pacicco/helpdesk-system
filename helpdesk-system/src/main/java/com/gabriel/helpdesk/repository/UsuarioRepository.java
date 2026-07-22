package com.gabriel.helpdesk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.helpdesk.model.Usuario;

/**
 * Repositorio de Usuario.
 * Ao estender JpaRepository, ja ganhamos de graca metodos como
 * save(), findById(), findAll(), delete() - sem escrever SQL.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
