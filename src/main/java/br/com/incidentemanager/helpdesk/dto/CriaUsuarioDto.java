package br.com.incidentemanager.helpdesk.dto;


//é o que é exposto para o cliente, front
public record CriaUsuarioDto(    String username,
                                 String senha,
                                 String nome,
                                 String telefone,
                                 String email,
                                 boolean  ativo,
                                 boolean  ehTecnicoTi,
                                 boolean   ehTecnicoNivelDois,
                                 boolean   ehAdministrador) {

}
