CREATE TABLE tickets (
    id_chamado uuid primary key ,
    atendente uuid null,
    assunto varchar(200) not null,
    descricao text not null ,
    status varchar(100) null,
    tipo_solicitacao varchar(100) null,
    criticidade varchar(100) null,
    impacto varchar(100) null,
    ativo boolean not null ,
    foi_escalado boolean not null,
    criado_em timestamp not null,
    criado_por uuid null,
    modificado_em timestamp null,
    modificado_por uuid null
)