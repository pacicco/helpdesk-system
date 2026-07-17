package com.gabriel.helpdesk.controller;

import com.gabriel.helpdesk.dto.AbrirChamadoDTO;
import com.gabriel.helpdesk.dto.ChamadoResponseDTO;
import com.gabriel.helpdesk.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de Chamado - camada responsavel por expor os endpoints HTTP.
 * Implementa o Sprint 1 do backlog: US01 (abrir chamado) e US02 (listar chamados).
 *
 * Todos os endpoints ficam disponiveis em: http://localhost:8080/chamados
 */
@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    /**
     * US01 - Abrir chamado.
     * POST /chamados
     * Corpo (JSON) esperado: { "descricao": "...", "solicitanteId": 1 }
     */
    @PostMapping
    public ResponseEntity<ChamadoResponseDTO> abrirChamado(@Valid @RequestBody AbrirChamadoDTO dto) {
        ChamadoResponseDTO chamadoCriado = chamadoService.abrirChamado(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(chamadoCriado);
    }

    /**
     * US02 - Listar todos os chamados.
     * GET /chamados
     */
    @GetMapping
    public ResponseEntity<List<ChamadoResponseDTO>> listarChamados() {
        return ResponseEntity.ok(chamadoService.listarChamados());
    }

    /**
     * US02/US03 - Consultar status de um chamado especifico.
     * GET /chamados/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ChamadoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(chamadoService.buscarPorId(id));
    }
}
