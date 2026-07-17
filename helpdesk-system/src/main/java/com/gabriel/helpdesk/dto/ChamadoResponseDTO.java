package com.gabriel.helpdesk.dto;

import com.gabriel.helpdesk.model.Chamado;
import com.gabriel.helpdesk.model.StatusChamado;

import java.time.LocalDateTime;

/**
 * DTO de resposta - o que o sistema devolve ao listar/consultar chamados (US02, US03).
 * Evita expor a entidade completa (ex: dados internos do Usuario) na API.
 */
public class ChamadoResponseDTO {

    private Long id;
    private String descricao;
    private LocalDateTime dataAbertura;
    private StatusChamado status;
    private String solicitanteNome;
    private String tecnicoNome;

    public ChamadoResponseDTO(Chamado chamado) {
        this.id = chamado.getId();
        this.descricao = chamado.getDescricao();
        this.dataAbertura = chamado.getDataAbertura();
        this.status = chamado.getStatus();
        this.solicitanteNome = chamado.getSolicitante().getNome();
        this.tecnicoNome = chamado.getTecnico() != null ? chamado.getTecnico().getNome() : null;
    }

    // Getters (sem setters - este objeto e somente leitura, usado so na resposta)

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public String getSolicitanteNome() {
        return solicitanteNome;
    }

    public String getTecnicoNome() {
        return tecnicoNome;
    }
}
