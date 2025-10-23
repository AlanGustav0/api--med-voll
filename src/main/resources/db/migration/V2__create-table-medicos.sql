create table if not exists MEDICOS(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    ativo tinyint,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    especialidade varchar(100) not null,
    endereco_id bigint,

    primary key(id),
    foreign key(endereco_id) references ENDERECO(id)
);