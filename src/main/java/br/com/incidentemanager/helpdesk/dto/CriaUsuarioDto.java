package br.com.incidentemanager.helpdesk.dto;


import lombok.Data;

import java.util.Date;

//é o que é exposto para o cliente, front
@Data
public class CriaUsuarioDto {
    String username;
    String password;
    String nome;
    String telefone;
    String email;
    Date criadoEm;
    boolean  ativo;
    boolean  ehTecnicoTi;
    boolean   ehTecnicoNivelDois;
    boolean   ehAdministrador;

}
