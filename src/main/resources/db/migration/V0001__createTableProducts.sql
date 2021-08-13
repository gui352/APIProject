CREATE TABLE produtos
 (
    codigo bigint not null auto_increment,
    produto varchar(100) not null,
    quantidade bigint,
    valor_unitario decimal(5,2) not null,
    valor_total_em_estoque decimal(5,2),
    primary key (codigo)
);