package br.com.incidentemanager.helpdesk.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "ticket_attachments")
public class AnexoChamadoEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_anexo")
    private UUID idAnexo;

    @ManyToOne
    @JoinColumn(name = "id_chamado")
    private ChamadoEntity idChamado;

    @ManyToOne
    @JoinColumn(name = "id_chamado_interacao")
    private InteracaoChamadoEntity idInteracaoChamado;

    private String filename;

    @Column(name = "criado_em")
    private Date criadoEm;

    @ManyToOne
    @JoinColumn(name = "criado_por")
    private UsuarioEntity criadoPor;

    @Column(name = "modificado_em")
    private Date modificadoEm ;

    @ManyToOne
    @JoinColumn(name = "modificado_por")
    private UsuarioEntity modificadoPor ;

    public ChamadoEntity getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(ChamadoEntity idChamado) {
        this.idChamado = idChamado;
    }
}
