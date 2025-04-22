package br.com.incidentemanager.helpdesk.dto;

import br.com.incidentemanager.helpdesk.domain.Anexo;
import br.com.incidentemanager.helpdesk.enums.ChamadoStatus;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CriaInteracaoChamadoDto {

    private String mensagem;

    //Não necessário mais por conta da autenticação
    //private UUID idUsuario;

    private ChamadoStatus status;

    private List<Anexo> anexos;
}
