package com.gabriel.helpdesk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * Entidade Chamado - o nucleo do sistema.
 * Corresponde a tabela CHAMADO do Modelo ER.
 * Atende os requisitos RF01 (abrir), RF02/RF03 (status), RF05 (solucao).
 */
@Entity
@Table(name = "chamado")
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A descricao e obrigatoria")
    @Column(length = 1000)
    private String descricao;

    private LocalDateTime dataAbertura;

    @Enumerated(EnumType.STRING)
    private StatusChamado status;

    @Column(length = 1000)
    private String solucao;

    // Relacionamento: quem abriu o chamado (Solicitante)
    @ManyToOne
    @JoinColumn(name = "solicitante_id", nullable = false)
    private Usuario solicitante;

    // Relacionamento: quem esta atendendo o chamado (Tecnico) - pode ser nulo ate ser atribuido
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;

    public Chamado() {
        // Construtor vazio exigido pelo JPA
    }

    public Chamado(String descricao, Usuario solicitante) {
        this.descricao = descricao;
        this.solicitante = solicitante;
        this.dataAbertura = LocalDateTime.now();
        this.status = StatusChamado.ABERTO;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }
}
