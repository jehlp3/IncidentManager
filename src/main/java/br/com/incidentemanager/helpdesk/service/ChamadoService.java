package br.com.incidentemanager.helpdesk.service;


import br.com.incidentemanager.helpdesk.domain.Chamado;
import br.com.incidentemanager.helpdesk.domain.InteracaoChamado;
import br.com.incidentemanager.helpdesk.entity.ChamadoEntity;
import br.com.incidentemanager.helpdesk.entity.InteracaoChamadoEntity;
import br.com.incidentemanager.helpdesk.entity.UsuarioEntity;
import br.com.incidentemanager.helpdesk.enums.ChamadoStatus;
import br.com.incidentemanager.helpdesk.exception.BusinessException;
import br.com.incidentemanager.helpdesk.exception.ObjectNotFoundException;
import br.com.incidentemanager.helpdesk.mapper.ChamadoMapper;
import br.com.incidentemanager.helpdesk.repository.ChamadoRepository;
import br.com.incidentemanager.helpdesk.repository.InteracaoChamadoRepository;
import br.com.incidentemanager.helpdesk.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class ChamadoService {

    private ChamadoRepository chamadoRepository;

    private InteracaoChamadoRepository interacaoChamadoRepository;

    private UsuarioRepository usuarioRepository;


    private ChamadoMapper mapper;

    public ChamadoService(InteracaoChamadoRepository interacaoChamadoRepository, UsuarioRepository usuarioRepository, ChamadoRepository chamadoRepository, ChamadoMapper mapper) {
        this.interacaoChamadoRepository = interacaoChamadoRepository;
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

    public Chamado interacaoChamado(InteracaoChamado domain) {
        ChamadoEntity chamado = chamadoRepository.findById(domain.getIdChamado()).orElse(null);

        if(chamado == null){
            throw new BusinessException("ERRO: " + domain.getIdChamado() +" Chamado não encontrado pelo id fornecido");
        }

        UsuarioEntity usuario = usuarioRepository.findById(domain.getIdUsuario()).orElse(null);

        if(usuario == null){
            throw new BusinessException("ERRO: " + domain.getIdUsuario() +" Usuário não encontrado pelo id fornecido");
        }

        Date now = new Date();

        InteracaoChamadoEntity entity = new InteracaoChamadoEntity();
        entity.setIdChamado(chamado); //variável ticket no vídeo
        entity.setMensagem(domain.getMensagem());
        entity.setCriadoPor(usuario);
        entity.setEnviadoPeloUsuarioId(usuario);
        entity.setCriadoEm(now);
        entity.setStatus(domain.getStatus());
        interacaoChamadoRepository.save(entity); //Salvando no BD

        //Se o usuário for diferente da pessoa que abriu, atribua o atendente
        if(chamado.getCriadoPor().getIdUsuario() != usuario.getIdUsuario()){
            chamado.setAtendente(usuario);
        }

        chamado.setModificado_em(now); //Identifica que o chamado foi modificado
        chamado.setModificado_por(usuario);
        chamado.setStatus(domain.getStatus());
        chamado = chamadoRepository.save(chamado);  //Salvando no BD

        return mapper.toDomain(chamado);
    }
}