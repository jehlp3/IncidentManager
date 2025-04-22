package br.com.incidentemanager.helpdesk.controller;

import br.com.incidentemanager.helpdesk.domain.Chamado;
import br.com.incidentemanager.helpdesk.domain.InteracaoChamado;
import br.com.incidentemanager.helpdesk.dto.ChamadoDto;
import br.com.incidentemanager.helpdesk.dto.CriaChamadoDto;
import br.com.incidentemanager.helpdesk.dto.CriaInteracaoChamadoDto;
import br.com.incidentemanager.helpdesk.dto.InteracaoChamadoDto;
import br.com.incidentemanager.helpdesk.mapper.ChamadoMapper;
import br.com.incidentemanager.helpdesk.service.ChamadoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


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
    public ResponseEntity<ChamadoDto> create(@RequestBody CriaChamadoDto request,
                                             Authentication authentication
                                            //Ao adicionar a autenticação vc consegue pegar o usuário da ação
                                            ) {
        Chamado domain = mapper.toDomain(request);
        ChamadoDto chamadoCriado = mapper.toDto(chamadoService.criaChamado(domain, authentication.getName()));
        return ResponseEntity.ok(chamadoCriado);
    }


    @Operation(description = "Este método cria uma nova interação do chamado no sistema")
    @PostMapping(value = "/{id}/interacao")
    public ResponseEntity<ChamadoDto> create(@PathVariable(name = "id") UUID idChamado,
                                             @RequestBody CriaInteracaoChamadoDto request,
                                             Authentication authentication) {
        InteracaoChamado domain = mapper.toDomain(request);
        domain.setIdChamado(idChamado);

        ChamadoDto chamadoEditado = mapper.toDto(chamadoService.interacaoChamado(domain, authentication.getName()));
        return ResponseEntity.ok(chamadoEditado);
    }

    @Operation(description = "Este método busca um chamado pelo seu id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDto> getById(@PathVariable(name = "id") UUID idChamado,
                                             Authentication authentication) {
        ChamadoDto chamado = mapper.toDto(chamadoService.getById(idChamado));
        return ResponseEntity.ok(chamado);
    }

    @Operation(description = "Este método busca as interações de um chamado pelo seu id")
    @GetMapping(value = "/{id}/interacoes")
    public ResponseEntity<List<InteracaoChamadoDto>> getInteracoesByChamadoId(@PathVariable(name = "id") UUID idChamado,
                                              Authentication authentication) {
        List<InteracaoChamadoDto> chamado = mapper.toInteracaoDto(chamadoService.getInteracoesByChamadoId(idChamado));
        return ResponseEntity.ok(chamado);
    }


    @Operation(description = "This method creates a new support ticket interaction in the system")
    @GetMapping
    public ResponseEntity<List<ChamadoDto>> listAllTickets() {

        List<ChamadoDto> chamados = mapper.toDto(chamadoService.listAll());
        return ResponseEntity.ok(chamados);
    }

}
