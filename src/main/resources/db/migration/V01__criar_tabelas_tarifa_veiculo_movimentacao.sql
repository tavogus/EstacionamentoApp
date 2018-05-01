create table tarifa (
   codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   descricao VARCHAR(50) NOT NULL,
   valor DECIMAL(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE veiculo (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    placa VARCHAR(50) NOT NULL,
    modelo VARCHAR(80) NOT NULL,
    tarifa BIGINT(20) NOT NULL,
    FOREIGN KEY (tarifa) REFERENCES tarifa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE movimentacao (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_hora_entrada DATETIME,
    data_hora_saida DATETIME,
    status VARCHAR(30) NOT NULL,
    total BIGINT(20),
    veiculo_codigo BIGINT(20) NOT NULL,
    FOREIGN KEY (veiculo_codigo) REFERENCES veiculo(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;