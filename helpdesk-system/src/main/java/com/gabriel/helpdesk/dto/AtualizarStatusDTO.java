package com.gabriel.helpdesk.dto;

import com.gabriel.helpdesk.model.StatusChamado;
import jakarta.validation.constraints.NotNull;

/**
 * DTO - representa os dados enviados ao atualizar o status de um chamado (US03).
 */
public class AtualizarStatusDTO {

    @NotNull(message = "O novo status e obrigatorio")
    private StatusChamado novoStatus;

    public AtualizarStatusDTO() {
    }

    public StatusChamado getNovoStatus() {
        return novoStatus;
    }

    public void setNovoStatus(StatusChamado novoStatus) {
        this.novoStatus = novoStatus;
    }
}