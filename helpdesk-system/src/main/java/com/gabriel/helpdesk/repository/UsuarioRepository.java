package com.gabriel.helpdesk.repository;

import com.gabriel.helpdesk.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de Usuario.
 * Ao estender JpaRepository, ja ganhamos de graca metodos como
 * save(), findById(), findAll(), delete() - sem escrever SQL.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
