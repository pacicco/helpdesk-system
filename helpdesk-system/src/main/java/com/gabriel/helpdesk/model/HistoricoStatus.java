package com.gabriel.helpdesk.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Entidade HistoricoStatus - registra cada mudanca de status de um chamado.
 * Corresponde a tabela HISTORICO_STATUS do Modelo ER. Atende o RF06.
 *
 * Nota: esta entidade sera utilizada a partir do Sprint 3 (US05), quando
 * implementarmos a atualizacao de status (US03). Por enquanto ela so
 * define a estrutura da tabela.
 */
@Entity
@Table(name = "historico_status")
public class HistoricoStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chamado_id", nullable = false)
    private Chamado chamado;

    @Enumerated(EnumType.STRING)
    private StatusChamado statusAnterior;

    @Enumerated(EnumType.STRING)
    private StatusChamado statusNovo;

    private LocalDateTime dataHora;

    public HistoricoStatus() {
        // Construtor vazio exigido pelo JPA
    }

    public HistoricoStatus(Chamado chamado, StatusChamado statusAnterior, StatusChamado statusNovo) {
        this.chamado = chamado;
        this.statusAnterior = statusAnterior;
        this.statusNovo = statusNovo;
        this.dataHora = LocalDateTime.now();
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }

    public StatusChamado getStatusAnterior() {
        return statusAnterior;
    }

    public void setStatusAnterior(StatusChamado statusAnterior) {
        this.statusAnterior = statusAnterior;
    }

    public StatusChamado getStatusNovo() {
        return statusNovo;
    }

    public void setStatusNovo(StatusChamado statusNovo) {
        this.statusNovo = statusNovo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
