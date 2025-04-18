package br.com.incidentemanager.helpdesk.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UsuarioEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_usuario")
    private UUID idUsuario;

    private String username;

    private String nome;

    private String senha;

    private String email;

    private boolean ativo;

    private String telefone;

    private boolean ehTecnicoTi;

    private boolean ehTecnicoNivelDois;

    private boolean ehAdministrador;

    @Column(name = "criado_em")
    private Date criadoEm;

    @Column(name = "criado_por")
    private UUID criadoPor;

    @Column(name = "modificado_em")
    private Date modificado_em ;

    @Column(name = "modificado_por")
    private UUID modificado_por ;

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setModificado_por(UUID modificado_por) {
        this.modificado_por = modificado_por;
    }

    public void setModificado_em(Date modificado_em) {
        this.modificado_em = modificado_em;
    }

    public void setCriadoPor(UUID criadoPor) {
        this.criadoPor = criadoPor;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }

    public void setEhAdministrador(boolean ehAdministrador) {
        this.ehAdministrador = ehAdministrador;
    }

    public void setEhTecnicoTi(boolean ehTecnicoTi) {
        this.ehTecnicoTi = ehTecnicoTi;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEhTecnicoNivelDois(boolean ehTecnicoNivelDois) {
        this.ehTecnicoNivelDois = ehTecnicoNivelDois;
    }
}
