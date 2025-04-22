package br.com.incidentemanager.helpdesk.service;


import br.com.incidentemanager.helpdesk.domain.Anexo;
import br.com.incidentemanager.helpdesk.domain.Chamado;
import br.com.incidentemanager.helpdesk.domain.InteracaoChamado;
import br.com.incidentemanager.helpdesk.entity.AnexoChamadoEntity;
import br.com.incidentemanager.helpdesk.entity.ChamadoEntity;
import br.com.incidentemanager.helpdesk.entity.InteracaoChamadoEntity;
import br.com.incidentemanager.helpdesk.entity.UsuarioEntity;
import br.com.incidentemanager.helpdesk.enums.ChamadoStatus;
import br.com.incidentemanager.helpdesk.exception.BusinessException;
import br.com.incidentemanager.helpdesk.exception.ObjectNotFoundException;
import br.com.incidentemanager.helpdesk.mapper.ChamadoMapper;
import br.com.incidentemanager.helpdesk.repository.AnexoChamadoRepository;
import br.com.incidentemanager.helpdesk.repository.ChamadoRepository;
import br.com.incidentemanager.helpdesk.repository.InteracaoChamadoRepository;
import br.com.incidentemanager.helpdesk.repository.UsuarioRepository;
import br.com.incidentemanager.helpdesk.utils.FileUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ChamadoService {

    private ChamadoRepository chamadoRepository;

    private InteracaoChamadoRepository interacaoChamadoRepository;

    private UsuarioRepository usuarioRepository;

    private ChamadoMapper mapper;

    private AnexoChamadoRepository anexoChamadoRepository;

    @Value("${helpdesk.attachments-folder}")
    private String anexosPasta;
    
    public ChamadoService(InteracaoChamadoRepository interacaoChamadoRepository,
                          UsuarioRepository usuarioRepository,
                          ChamadoRepository chamadoRepository,
                          ChamadoMapper mapper,
                          AnexoChamadoRepository anexoChamadoRepository) {
        this.interacaoChamadoRepository = interacaoChamadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
        this.chamadoRepository = chamadoRepository;
        this.anexoChamadoRepository = anexoChamadoRepository;
    }

    //Só valida a transação caso tudo dê certo, caso 1 anexo dê falha ele não finaliza
    @Transactional
    public Chamado criaChamado(Chamado novoChamado, String username){

        ChamadoEntity entity = mapper.toEntity(novoChamado);

        UsuarioEntity criadoPeloUsuario = usuarioRepository.findByUsername(username).orElse(null);
       if(criadoPeloUsuario == null){
           throw new ObjectNotFoundException("Usuário não encontrado pelo id fornecido");
       }

        entity.setCriadoPor(criadoPeloUsuario);
        entity.setStatus(ChamadoStatus.ABERTO);
        entity.setCriadoEm(new Date());
        entity = chamadoRepository.save(entity);

        //Verificando se há anexos
        if(novoChamado.getAnexos() != null && !novoChamado.getAnexos().isEmpty()){
            for (Anexo anexo : novoChamado.getAnexos()) {
                AnexoChamadoEntity anexoChamadoEntity = new AnexoChamadoEntity();
                anexoChamadoEntity.setIdChamado(entity);
                anexoChamadoEntity.setCriadoPor(criadoPeloUsuario);
                anexoChamadoEntity.setCriadoEm(new Date());
                anexoChamadoEntity.setFilename(anexo.getFilename());
                anexoChamadoEntity = anexoChamadoRepository.save(anexoChamadoEntity); //P pegar o id
                saveFileToDisk(anexoChamadoEntity, anexo.getContent());

            }
        }

        return mapper.toDomain(entity);
    }

    public Chamado interacaoChamado(InteracaoChamado domain, String username) {


        ChamadoEntity chamado = chamadoRepository.findById(domain.getIdChamado()).orElse(null);

        if(chamado == null){
            throw new BusinessException("ERRO: " + domain.getIdChamado() +" Chamado não encontrado pelo id fornecido");
        }

        UsuarioEntity usuario = usuarioRepository.findByUsername(username).orElse(null);

        if(usuario == null){
            throw new BusinessException("ERRO: " + domain.getIdUsuario() +" Usuário não encontrado pelo id fornecido");
        }

        Date now = new Date();
        ChamadoStatus status = ChamadoStatus.EM_PROGRESSO;

        //Se o usuário for diferente da pessoa que abriu, atribua o atendente
        if(chamado.getCriadoPor().getIdUsuario() != usuario.getIdUsuario()){
            chamado.setAtendente(usuario);
            status = ChamadoStatus.AGUARDANDO_SOLICITANTE;
        }

        InteracaoChamadoEntity entity = new InteracaoChamadoEntity();
        entity.setIdChamado(chamado);; //variável ticket no vídeo
        entity.setMensagem(domain.getMensagem());
        entity.setCriadoPor(usuario);
        entity.setEnviadoPeloUsuarioId(usuario);
        entity.setCriadoEm(now);
        entity.setStatus(status);
        interacaoChamadoRepository.save(entity); //Salvando no BD

        if (domain.getAnexos() != null && !domain.getAnexos().isEmpty()) {
            for (Anexo attachment : domain.getAnexos()) {
                AnexoChamadoEntity anexoChamadoEntity = new AnexoChamadoEntity();
                //anexoChamadoEntity.setTicketInteraction(entity);
                anexoChamadoEntity.setIdInteracaoChamado(entity);
                anexoChamadoEntity.setCriadoPor(usuario);
                anexoChamadoEntity.setCriadoEm(new Date());
                anexoChamadoEntity.setFilename(attachment.getFilename());
                anexoChamadoEntity = anexoChamadoRepository.save(anexoChamadoEntity);
                saveFileToDisk(anexoChamadoEntity, attachment.getContent());
            }
        }

        chamado.setModificado_em(now); //Identifica que o chamado foi modificado
        chamado.setModificado_por(usuario);
        chamado.setStatus(status);
        chamado = chamadoRepository.save(chamado);  //Salvando no BD

        return mapper.toDomain(chamado);
    }


    private void saveFileToDisk(AnexoChamadoEntity entity, String content) {
        byte[] attachmentContent = null;
        try {
            attachmentContent = FileUtils.convertBase64ToByteArray(content);
            String fileName = entity.getIdAnexo().toString();

            FileUtils.saveByteArrayToFile(attachmentContent, new File(anexosPasta + fileName));
        } catch (IOException ex) {
            throw new BusinessException("Error saving " + entity.getFilename() + " file");
        }
    }

    public List<Chamado> listAll() {
        return mapper.toDomain(chamadoRepository.findAll());
    }

    public Chamado getById(UUID idChamado) {
        ChamadoEntity entity = chamadoRepository.findById(idChamado)
                .orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado"));
        return mapper.toDomain(entity);
    }

    public List<InteracaoChamado> getInteracoesByChamadoId(UUID idChamado) {
        ChamadoEntity chamadoEntity = chamadoRepository.findById(idChamado)
                .orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado"));
        return mapper.toInteracaoDomain(interacaoChamadoRepository.findByIdChamado(chamadoEntity));
    }
}