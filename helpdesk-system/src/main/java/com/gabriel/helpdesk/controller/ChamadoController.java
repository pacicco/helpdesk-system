package com.gabriel.helpdesk.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.helpdesk.dto.AbrirChamadoDTO;
import com.gabriel.helpdesk.dto.AtualizarStatusDTO;
import com.gabriel.helpdesk.dto.ChamadoResponseDTO;
import com.gabriel.helpdesk.service.ChamadoService;

import jakarta.validation.Valid;

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

    /**
 * US03 - Atualizar status de um chamado.
 * PATCH /chamados/{id}/status
 * Corpo (JSON) esperado: { "novoStatus": "EM_ANDAMENTO" }
 */
    @PatchMapping("/{id}/status")
    public ResponseEntity<ChamadoResponseDTO> atualizarStatus(@PathVariable Long id,
                                                            @Valid @RequestBody AtualizarStatusDTO dto) {
    ChamadoResponseDTO atualizado = chamadoService.atualizarStatus(id, dto);
    return ResponseEntity.ok(atualizado);
    }
}
