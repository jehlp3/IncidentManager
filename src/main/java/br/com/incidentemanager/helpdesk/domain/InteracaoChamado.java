package br.com.incidentemanager.helpdesk.domain;

import br.com.incidentemanager.helpdesk.entity.ChamadoEntity;
import br.com.incidentemanager.helpdesk.entity.UsuarioEntity;
import br.com.incidentemanager.helpdesk.enums.ChamadoStatus;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class InteracaoChamado {

    private String mensagem;

    private ChamadoStatus status;

    private UUID idUsuario;

    private UUID idChamado;

    private UUID idInteracao;

    private ChamadoEntity chamado;

    private UsuarioEntity enviadoPeloUsuarioId;

    private Date criadoEm;

    private UsuarioEntity criadoPor;

    private Date modificadoEm ;

    private UsuarioEntity modificadoPor ;
}
