package br.com.incidentemanager.helpdesk.controller;

import br.com.incidentemanager.helpdesk.domain.Chamado;
import br.com.incidentemanager.helpdesk.dto.ChamadoDto;
import br.com.incidentemanager.helpdesk.dto.CriaChamadoDto;
import br.com.incidentemanager.helpdesk.mapper.ChamadoMapper;
import br.com.incidentemanager.helpdesk.service.ChamadoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


// Especifica que essa classe é um controller
@Controller
@OpenAPIDefinition
@RequiredArgsConstructor
@RequestMapping(path = "/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    private final ChamadoMapper mapper;


    @Operation(description = "Este método cria um novo chamado no sistema")
    @PostMapping
    public ResponseEntity<ChamadoDto> create(@RequestBody CriaChamadoDto request) {
        Chamado domain = mapper.toDomain(request);
        ChamadoDto chamadoCriado = mapper.toDto(chamadoService.criaChamado(domain));
        return ResponseEntity.ok(chamadoCriado);


    }
}
