CREATE TABLE role_pessoas(
    id bigint not null auto_increment,
    pessoas_codigo bigint not null,
    role_nome_role varchar(45) not null,
    primary key(id)
);

ALTER TABLE role_pessoas add CONSTRAINT fk_usuarios
FOREIGN KEY(pessoas_codigo) REFERENCES pessoa(codigo);

ALTER TABLE role_pessoas add CONSTRAINT fk_role
FOREIGN KEY(role_nome_role) REFERENCES role(nome_role);