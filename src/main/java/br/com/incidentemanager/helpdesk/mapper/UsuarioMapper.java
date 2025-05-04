package br.com.incidentemanager.helpdesk.mapper;

import br.com.incidentemanager.helpdesk.domain.Chamado;
import br.com.incidentemanager.helpdesk.domain.Usuario;
import br.com.incidentemanager.helpdesk.dto.ChamadoDto;
import br.com.incidentemanager.helpdesk.dto.CriaUsuarioDto;
import br.com.incidentemanager.helpdesk.dto.UsuarioDto;
import br.com.incidentemanager.helpdesk.entity.UsuarioEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {


    Usuario toDomain(UsuarioEntity entity);

    UsuarioDto toDto(Usuario domain);

    UsuarioEntity toEntity(Usuario domain);

    Usuario toDomain(CriaUsuarioDto dto);

    List<Usuario> toDomain(List<UsuarioEntity> entities);

    List<UsuarioDto> toDto(List<Usuario> domains);

}
