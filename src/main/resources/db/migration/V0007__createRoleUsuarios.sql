CREATE TABLE role_usuarios(
    id bigint not null auto_increment,
    usuarios_id bigint not null,
    role_nome_role varchar(45) not null,
    primary key(id)
);

ALTER TABLE role_usuarios add CONSTRAINT fk_usuarios
FOREIGN KEY(usuarios_id) REFERENCES pessoas(codigo);

ALTER TABLE role_usuarios add CONSTRAINT fk_role
FOREIGN KEY(role_nome_role) REFERENCES roles(nome_role);