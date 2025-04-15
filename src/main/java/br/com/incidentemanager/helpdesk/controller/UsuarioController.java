package br.com.incidentemanager.helpdesk.controller;

import br.com.incidentemanager.helpdesk.domain.Usuario;
import br.com.incidentemanager.helpdesk.dto.CriaUsuarioDto;
import br.com.incidentemanager.helpdesk.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

// Especifica que essa classe é um controller
@Controller
//@RequiredArgsConstructor // Lombok irá gerar o construtor automaticamente para injetar o UsuarioService
@RequestMapping(path = "/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    // O Spring irá injetar automaticamente o UsuarioService
    //private final UsuarioService usuarioService; //Não funcionou


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CriaUsuarioDto request) {

        // Cria a instância de Usuario usando os dados do DTO
        Usuario domain = new Usuario(
                UUID.randomUUID(),
                request.username(),
                request.senha(),
                request.nome(),
                request.telefone(),
                request.email(),
                request.ativo(),
                request.ehTecnicoTi(),
                request.ehTecnicoNivelDois(),
                request.ehAdministrador()
        );

        // Agora chamamos o método criarUsuario da instância do serviço
        usuarioService.criarUsuario(domain);

        // Retorna uma resposta HTTP sem conteúdo (204 No Content)
        return ResponseEntity.noContent().build();
    }
}
