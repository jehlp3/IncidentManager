package br.com.incidentemanager.helpdesk.dto;

import br.com.incidentemanager.helpdesk.domain.Anexo;
import br.com.incidentemanager.helpdesk.enums.ChamadoCriticidade;
import br.com.incidentemanager.helpdesk.enums.ChamadoImpacto;
import br.com.incidentemanager.helpdesk.enums.ChamadoStatus;
import br.com.incidentemanager.helpdesk.enums.ChamadoTipoSolicitacao;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CriaChamadoDto {

    //Só as informações que eu quero passar pelo front
    //private UUID atendente; //Dentro do dto é UsuarioDto, comentar quando for criar o 1º

    private String assunto;

    private String descricao;

    private ChamadoStatus status;

    private ChamadoTipoSolicitacao tipo_solicitacao;

    private ChamadoCriticidade criticidade;

    private ChamadoImpacto impacto;

    private boolean ativo;

    private boolean foi_escalado;

    private UUID criadoPorUsuarioId; //Nomes diferentes para não ter conflito no mapper

    //Aula 6 - Criando a lista dos anexos que serão recebidos no sistema
    private List<Anexo> anexos;

}
