package br.com.incidentemanager.helpdesk.domain;

import java.util.UUID;

public record Usuario(
        UUID idUsuario,
        String username,
        String senha,
        String nome,
        String telefone,
        String email,
        boolean  ativo,
        boolean  ehTecnicoTi,
        boolean   ehTecnicoNivelDois,
        boolean   ehAdministrador
) {
}
