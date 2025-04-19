package br.com.incidentemanager.helpdesk.mapper;

import br.com.incidentemanager.helpdesk.domain.Chamado;
import br.com.incidentemanager.helpdesk.dto.ChamadoDto;
import br.com.incidentemanager.helpdesk.dto.CriaChamadoDto;
import br.com.incidentemanager.helpdesk.dto.CriaUsuarioDto;
import br.com.incidentemanager.helpdesk.entity.ChamadoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChamadoMapper {


    Chamado toDomain(ChamadoEntity entity);

    ChamadoDto toDto(Chamado domain);

    ChamadoEntity toEntity(Chamado domain);

    Chamado toDomain(CriaChamadoDto dto);

    //Chamado toDomain(CriaUsuarioDto dto);

}
