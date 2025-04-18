CREATE TABLE users (
    id_usuario uuid primary key ,
    nome varchar(100) not null,
    username varchar(100) not null,
    senha varchar(30),
    email varchar(100),
    ativo boolean not null ,
    telefone char(11) null,
    eh_tecnico_ti boolean not null,
    eh_tecnico_nivel_dois boolean not null,
    eh_administrador boolean not null,
    criado_em timestamp not null,
    criado_por uuid null,
    modificado_em timestamp null,
    modificado_por uuid null
)