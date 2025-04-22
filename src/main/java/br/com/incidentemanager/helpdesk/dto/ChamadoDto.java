package br.com.incidentemanager.helpdesk.dto;

import br.com.incidentemanager.helpdesk.entity.UsuarioEntity;
import br.com.incidentemanager.helpdesk.enums.ChamadoCriticidade;
import br.com.incidentemanager.helpdesk.enums.ChamadoImpacto;
import br.com.incidentemanager.helpdesk.enums.ChamadoStatus;
import br.com.incidentemanager.helpdesk.enums.ChamadoTipoSolicitacao;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ChamadoDto {

    private UUID idChamado;

    private UsuarioDto atendente; //Dentro do dto é UsuarioDto

    private String assunto;

    private String descricao;

    private ChamadoStatus status;

    private ChamadoTipoSolicitacao tipo_solicitacao;

    private ChamadoCriticidade criticidade;

    private ChamadoImpacto impacto;

    private boolean ativo;

    private boolean foi_escalado;

    private Date criadoEm;

    //private UsuarioDto criadoPor;

    private Date modificado_em ;

    private UsuarioEntity modificado_por ;
}
