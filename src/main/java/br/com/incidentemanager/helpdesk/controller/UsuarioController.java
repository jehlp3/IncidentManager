package br.com.incidentemanager.helpdesk.controller;

import br.com.incidentemanager.helpdesk.domain.Usuario;
import br.com.incidentemanager.helpdesk.dto.ChamadoDto;
import br.com.incidentemanager.helpdesk.dto.CriaUsuarioDto;
import br.com.incidentemanager.helpdesk.dto.UsuarioDto;
import br.com.incidentemanager.helpdesk.mapper.UsuarioMapper;
import br.com.incidentemanager.helpdesk.service.UsuarioService;
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
@RequiredArgsConstructor // Lombok irá gerar o construtor automaticamente para injetar o UsuarioService
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioMapper mapper;

    // O Spring irá injetar automaticamente o UsuarioService
    //private final UsuarioService usuarioService; //Não funcionou




    @Operation(description = "Este método cria um novo usuário no sistema")
    @PostMapping
    public ResponseEntity<UsuarioDto> create(@RequestBody CriaUsuarioDto request) {
        Usuario domain = mapper.toDomain(request);
        UsuarioDto createdUser = mapper.toDto(usuarioService.criaUsuario(domain));
        return ResponseEntity.ok(createdUser);







        //Antes da utilização do Mapper - Funcional
        // Cria a instância de Usuario usando os dados do DTO
//        Usuario domain = new Usuario(
//                UUID.randomUUID(),
//                request.username(),
//                request.senha(),
//                request.nome(),
//                request.telefone(),
//                request.email(),
//                request.ativo(),
//                request.ehTecnicoTi(),
//                request.ehTecnicoNivelDois(),
//                request.ehAdministrador()
//        );
//
//        // Agora chamamos o metodo criarUsuario da instancia do serviço
//        usuarioService.criarUsuario(domain);
//
//        // Retorna uma resposta HTTP sem conteúdo (204 No Content)
//        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Este método lista todos os usuários do sistema")
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listAllUsers() {

        List<UsuarioDto> usuarios = mapper.toDto(usuarioService.listAll());
        return ResponseEntity.ok(usuarios);
    }

    @Operation(description = "Este método busca um usuário pelo seu id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDto> getById(@PathVariable(name = "id") UUID idChamado,
                                              Authentication authentication) {
        UsuarioDto usuario = mapper.toDto(usuarioService.getById(idChamado));
        return ResponseEntity.ok(usuario);
    }

}
