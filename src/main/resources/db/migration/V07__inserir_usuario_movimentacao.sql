alter table movimentacao
add codigo_usuario BIGINT(20) NOT NULL;

ALTER TABLE movimentacao ADD CONSTRAINT fk_usuario_movimentacao
FOREIGN KEY(codigo_usuario) REFERENCES usuario(codigo);