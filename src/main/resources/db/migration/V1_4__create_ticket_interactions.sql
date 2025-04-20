CREATE TABLE ticket_interactions (
    id_interacao uuid primary key ,
    id_chamado uuid not null,
    enviado_pelo_usuario_id uuid not null,
    status varchar(100) null,
    mensagem text not null,
    criado_em timestamp not null,
    criado_por uuid null,
    modificado_em timestamp null,
    modificado_por uuid null
)