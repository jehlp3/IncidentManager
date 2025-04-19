package br.com.incidentemanager.helpdesk.entity;

import br.com.incidentemanager.helpdesk.enums.ChamadoCriticidade;
import br.com.incidentemanager.helpdesk.enums.ChamadoImpacto;
import br.com.incidentemanager.helpdesk.enums.ChamadoStatus;
import br.com.incidentemanager.helpdesk.enums.ChamadoTipoSolicitacao;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "tickets")
public class ChamadoEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_chamado")
    private UUID idChamado;

    @ManyToOne
    @JoinColumn(name = "atendente")
    private UsuarioEntity atendente;

    private String assunto;

    private String descricao;

    @Enumerated(EnumType.STRING) //Para salvar a String e não a posição dela,
    // problema apontado, se outro DEV vir e criar um novo enum no meio,
    // será alterado apenas no código, no BD ele continua igual.
    private ChamadoStatus status;

    @Enumerated(EnumType.STRING)
    private ChamadoTipoSolicitacao tipo_solicitacao;

    @Enumerated(EnumType.STRING)
    private ChamadoCriticidade criticidade;

    @Enumerated(EnumType.STRING)
    private ChamadoImpacto impacto;

    private boolean ativo;

    private boolean foi_escalado;

    @Column(name = "criado_em")
    private Date criadoEm;

    @ManyToOne
    @JoinColumn(name = "criado_por")
    private UsuarioEntity criadoPor;

    @Column(name = "modificado_em")
    private Date modificado_em ;

    @ManyToOne
    @JoinColumn(name = "modificado_por")
    private UsuarioEntity modificado_por ;
}
