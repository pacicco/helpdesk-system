package com.gabriel.helpdesk.service;

import com.gabriel.helpdesk.dto.AbrirChamadoDTO;
import com.gabriel.helpdesk.dto.ChamadoResponseDTO;
import com.gabriel.helpdesk.model.Chamado;
import com.gabriel.helpdesk.model.Usuario;
import com.gabriel.helpdesk.repository.ChamadoRepository;
import com.gabriel.helpdesk.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Camada de Servico - concentra as regras de negocio.
 * O Controller nao deve falar diretamente com o Repository; ele
 * delega para o Service, que decide "como" a operacao acontece.
 */
@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;

    // Injecao de dependencia via construtor (padrao recomendado no Spring)
    public ChamadoService(ChamadoRepository chamadoRepository, UsuarioRepository usuarioRepository) {
        this.chamadoRepository = chamadoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * US01 - Abrir chamado.
     * Busca o usuario solicitante pelo id recebido e cria um novo Chamado.
     */
    public ChamadoResponseDTO abrirChamado(AbrirChamadoDTO dto) {
        Usuario solicitante = usuarioRepository.findById(dto.getSolicitanteId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuario solicitante nao encontrado com id: " + dto.getSolicitanteId()));

        Chamado chamado = new Chamado(dto.getDescricao(), solicitante);
        Chamado salvo = chamadoRepository.save(chamado);

        return new ChamadoResponseDTO(salvo);
    }

    /**
     * US02 - Listar todos os chamados com seus status.
     */
    public List<ChamadoResponseDTO> listarChamados() {
        return chamadoRepository.findAll()
                .stream()
                .map(ChamadoResponseDTO::new)
                .toList();
    }

    /**
     * US02/US03 - Consultar um chamado especifico pelo id.
     */
    public ChamadoResponseDTO buscarPorId(Long id) {
        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chamado nao encontrado com id: " + id));
        return new ChamadoResponseDTO(chamado);
    }
}
