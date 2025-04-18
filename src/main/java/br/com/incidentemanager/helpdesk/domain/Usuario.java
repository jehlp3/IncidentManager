package br.com.incidentemanager.helpdesk.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Usuario {
    UUID idUsuario;
    String username;
    String senha;
    String nome;
    String telefone;
    String email;
    boolean  ativo;
    boolean  ehTecnicoTi;
    boolean   ehTecnicoNivelDois;
    boolean   ehAdministrador;
}
