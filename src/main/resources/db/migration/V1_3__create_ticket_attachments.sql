CREATE TABLE ticket_attachments (
    id_anexo uuid primary key ,
    id_chamado uuid null,
    id_chamado_interacao uuid null,
    filename varchar(200) not null,
    criado_em timestamp not null,
    criado_por uuid null,
    modificado_em timestamp null,
    modificado_por uuid null
)