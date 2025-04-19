package br.com.incidentemanager.helpdesk.service;


import br.com.incidentemanager.helpdesk.domain.Chamado;
import br.com.incidentemanager.helpdesk.entity.ChamadoEntity;
import br.com.incidentemanager.helpdesk.entity.UsuarioEntity;
import br.com.incidentemanager.helpdesk.enums.ChamadoStatus;
import br.com.incidentemanager.helpdesk.exception.ObjectNotFoundException;
import br.com.incidentemanager.helpdesk.mapper.ChamadoMapper;
import br.com.incidentemanager.helpdesk.repository.ChamadoRepository;
import br.com.incidentemanager.helpdesk.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class ChamadoService {

    private ChamadoRepository chamadoRepository;

    private UsuarioRepository usuarioRepository;


    private ChamadoMapper mapper;

    public ChamadoService(UsuarioRepository usuarioRepository, ChamadoRepository chamadoRepository, ChamadoMapper mapper) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
        this.chamadoRepository = chamadoRepository;
    }

    public Chamado criaChamado(Chamado novoChamado){

        ChamadoEntity entity = mapper.toEntity(novoChamado);
        Optional<UsuarioEntity> criadoPeloUsuario = usuarioRepository.findById(novoChamado.getCriadoPorUsuarioId());
        if(criadoPeloUsuario.isEmpty()){
            throw new ObjectNotFoundException("ERRO: "+ novoChamado.getCriadoPorUsuarioId() +" = usuário não encontrado pelo id fornecido");

        }
        entity.setCriadoPor(criadoPeloUsuario.get());
        entity.setStatus(ChamadoStatus.ABERTO);
        entity.setCriadoEm(new Date());
        entity = chamadoRepository.save(entity);
        return mapper.toDomain(entity);
    }

}
