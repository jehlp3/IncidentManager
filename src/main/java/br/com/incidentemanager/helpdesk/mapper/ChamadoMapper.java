package br.com.incidentemanager.helpdesk.mapper;

import br.com.incidentemanager.helpdesk.domain.Chamado;
import br.com.incidentemanager.helpdesk.domain.InteracaoChamado;
import br.com.incidentemanager.helpdesk.dto.ChamadoDto;
import br.com.incidentemanager.helpdesk.dto.CriaChamadoDto;
import br.com.incidentemanager.helpdesk.dto.CriaInteracaoChamadoDto;
import br.com.incidentemanager.helpdesk.entity.ChamadoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChamadoMapper {


    Chamado toDomain(ChamadoEntity entity);

    ChamadoDto toDto(Chamado domain);

    ChamadoEntity toEntity(Chamado domain);

    Chamado toDomain(CriaChamadoDto dto);

    InteracaoChamado toDomain(CriaInteracaoChamadoDto dto);

    List<Chamado> toDomain(List<ChamadoEntity> entities);

    List<ChamadoDto> toDto(List<Chamado> domains);


}
