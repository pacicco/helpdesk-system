package com.gabriel.helpdesk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gabriel.helpdesk.dto.AbrirChamadoDTO;
import com.gabriel.helpdesk.dto.AtualizarStatusDTO;
import com.gabriel.helpdesk.dto.ChamadoResponseDTO;
import com.gabriel.helpdesk.model.Chamado;
import com.gabriel.helpdesk.model.HistoricoStatus;
import com.gabriel.helpdesk.model.StatusChamado;
import com.gabriel.helpdesk.model.Usuario;
import com.gabriel.helpdesk.repository.ChamadoRepository;
import com.gabriel.helpdesk.repository.HistoricoStatusRepository;
import com.gabriel.helpdesk.repository.UsuarioRepository;

/**
 * Camada de Servico - concentra as regras de negocio.
 * O Controller nao deve falar diretamente com o Repository; ele
 * delega para o Service, que decide "como" a operacao acontece.
 */
@Service
public class ChamadoService {

   private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final HistoricoStatusRepository historicoStatusRepository;

    public ChamadoService(ChamadoRepository chamadoRepository, UsuarioRepository usuarioRepository,
                       HistoricoStatusRepository historicoStatusRepository) {
    this.chamadoRepository = chamadoRepository;
    this.usuarioRepository = usuarioRepository;
    this.historicoStatusRepository = historicoStatusRepository;
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

    /**
 * US03 - Atualizar status de um chamado.
 * Alem de mudar o status, registra a mudanca no historico (RF06),
 * preparando o terreno para a US05 (ver historico) no proximo sprint.
 */
    public ChamadoResponseDTO atualizarStatus(Long id, AtualizarStatusDTO dto) {
    Chamado chamado = chamadoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Chamado nao encontrado com id: " + id));

    StatusChamado statusAnterior = chamado.getStatus();
    StatusChamado statusNovo = dto.getNovoStatus();

    // Atualiza o status do chamado
    chamado.setStatus(statusNovo);
    Chamado atualizado = chamadoRepository.save(chamado);

    // Registra a mudanca no historico
    HistoricoStatus historico = new HistoricoStatus(chamado, statusAnterior, statusNovo);
    historicoStatusRepository.save(historico);

    return new ChamadoResponseDTO(atualizado);
}
}
