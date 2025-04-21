package br.com.incidentemanager.helpdesk.entity;

import br.com.incidentemanager.helpdesk.enums.ChamadoStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "ticket_interactions")
public class InteracaoChamadoEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_interacao")
    private UUID idInteracaoChamado;

    @ManyToOne
    @JoinColumn(name = "id_chamado")
    private static ChamadoEntity idChamado; //Variável ticket no vídeo

    @Enumerated(EnumType.STRING)
    private ChamadoStatus status;

    @ManyToOne
    @JoinColumn(name = "enviado_pelo_usuario_id")
    private UsuarioEntity enviadoPeloUsuarioId;

    private String mensagem;

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

    public static void setIdChamado(ChamadoEntity idChamado) {
        InteracaoChamadoEntity.idChamado = idChamado;
    }

//    public static ChamadoEntity getIdChamado() {
//        return idChamado;
//    }
//
//    public void setIdChamado(ChamadoEntity idChamado) {
//        this.idChamado = idChamado;
//    }
}
