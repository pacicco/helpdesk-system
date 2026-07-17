package com.gabriel.helpdesk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO (Data Transfer Object) - representa os dados que chegam do
 * "cliente" (front-end, Postman, etc.) ao abrir um novo chamado (US01).
 *
 * Usamos um DTO em vez de expor a entidade Chamado diretamente para
 * controlar exatamente quais campos podem ser enviados pelo usuario
 * (ex: o usuario nao deve poder definir o status ou o id na abertura).
 */
public class AbrirChamadoDTO {

    @NotBlank(message = "A descricao e obrigatoria")
    private String descricao;

    @NotNull(message = "O id do solicitante e obrigatorio")
    private Long solicitanteId;

    public AbrirChamadoDTO() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(Long solicitanteId) {
        this.solicitanteId = solicitanteId;
    }
}
