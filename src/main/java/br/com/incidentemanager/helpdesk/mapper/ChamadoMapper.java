package br.com.incidentemanager.helpdesk.mapper;

import br.com.incidentemanager.helpdesk.domain.Anexo;
import br.com.incidentemanager.helpdesk.domain.Chamado;
import br.com.incidentemanager.helpdesk.domain.InteracaoChamado;
import br.com.incidentemanager.helpdesk.dto.*;
import br.com.incidentemanager.helpdesk.entity.ChamadoEntity;
import br.com.incidentemanager.helpdesk.entity.InteracaoChamadoEntity;
import br.com.incidentemanager.helpdesk.entity.UsuarioEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ChamadoMapper {


    Chamado toDomain(ChamadoEntity entity);

    ChamadoDto toDto(Chamado domain);

    ChamadoEntity toEntity(Chamado domain);

    Chamado toDomain(CriaChamadoDto dto);

    InteracaoChamado toDomain(CriaInteracaoChamadoDto dto);

    List<Chamado> toDomain(List<ChamadoEntity> entities);

    List<ChamadoDto> toDto(List<Chamado> domains);

    List<InteracaoChamadoDto> toInteracaoDto(List<InteracaoChamado> domains);

    List<InteracaoChamado> toInteracaoDomain(List<InteracaoChamadoEntity> domains);

    InteracaoChamadoDto toDto(InteracaoChamado domain);

    InteracaoChamado toDomain(InteracaoChamadoDto dto);

    default UUID map(ChamadoEntity entity) {
        return entity != null ? entity.getIdChamado() : null;
    }

    // faltam estes métodos
    UsuarioDto toDto(UsuarioEntity entity);
    AnexoDto toDto(Anexo domain);


}
