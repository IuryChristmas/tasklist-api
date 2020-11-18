CREATE TABLE IF NOT EXISTS tasks (
	id bigint not null auto_increment,
    titulo varchar(100) not null,
    status int not null,
    descricao varchar(250),
    data_criacao date,
    data_atualizacao timestamp default current_timestamp,
    data_conclusao date,
    primary key(id)
);

INSERT INTO tasks(titulo, status, descricao, data_criacao) values('Lavar a roupa', 0, 'colocar as roupas para lavar', current_timestamp);
INSERT INTO tasks(titulo, status, descricao, data_criacao) values('Levar o lixo', 0, 'colocar o lixo em um saco e por na rua', current_timestamp);