package br.com.incidentemanager.helpdesk.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UsuarioDto {
    UUID idUsuario;
    String username;
    String nome;
    String password;
    String telefone;
    String email;
    Date criadoEm;
    boolean ativo;
    boolean ehTecnicoTi;
    boolean ehTecnicoNivelDois;
    boolean ehAdministrador;


}
