package br.com.incidentemanager.helpdesk.dto;

import br.com.incidentemanager.helpdesk.enums.ChamadoStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class InteracaoChamadoDto {
    private String mensagem;

    private ChamadoStatus status;

    private UUID idUsuario;


    private UUID idInteracao;

    private ChamadoDto chamado;

    private UsuarioDto enviadoPeloUsuarioId;

    private Date criadoEm;

    private UsuarioDto criadoPor;

    private Date modificadoEm ;

    private UsuarioDto modificadoPor ;

    private List<AnexoDto> anexos;

    
}
