CREATE DATABASE aguaconta
GO

USE aguaconta
GO

CREATE TABLE CLIENTE
(
	COD_CLIENTE INT IDENTITY(1,1),
	NOME VARCHAR(100) NOT NULL,
	TIPO INT NOT NULL,
	CPF_CNPJ VARCHAR(14) NOT NULL, 
	IDENT VARCHAR(MAX),
	EMAIL VARCHAR(MAX),
	TEL_FIXO VARCHAR(11),
	TEL_CELULAR VARCHAR(11),
	TEL_RECADO VARCHAR(11),
	RESP_RECADO VARCHAR(100),
	
	CONSTRAINT pk_cliente PRIMARY KEY (COD_CLIENTE),
	CONSTRAINT un_cliente UNIQUE(CPF_CNPJ)
	
)
GO


CREATE TABLE CAD_HIDROMETRO
(
	ID_HIDROMETRO INT IDENTITY(1,1),
	HIDROMETRO VARCHAR(50) NOT NULL,
	
	CONSTRAINT pk_hidrometro PRIMARY KEY (ID_HIDROMETRO),
	CONSTRAINT un_hidrometro UNIQUE(HIDROMETRO)
		
)
GO

CREATE TABLE UNID_CONSUMO
(
	COD_UNID_CONSUMO INT IDENTITY(1,1),
	NOME_UNID VARCHAR(50) NOT NULL,
	CIDADE VARCHAR(MAX) NOT NULL,
	UF CHAR(2) NOT NULL,

	CONSTRAINT pk_unid_consumo PRIMARY KEY (COD_UNID_CONSUMO),
	CONSTRAINT un_nome_unid UNIQUE(NOME_UNID)
)
GO

CREATE TABLE CONFIG_VALORES
(
	ID_CONFIG_VALORES INT IDENTITY(1,1),
	COD_UNID_CONSUMO INT NOT NULL, 
	MES_INICIAL INT,
	ANO_INICIAL INT,
	MES_FINAL INT,
	ANO_FINAL INT,
	MINIMO INT,
	MINIMO_VALOR FLOAT,
	FAIXA_UM INT,
	VALOR_UM FLOAT,
	FAIXA_DOIS INT,
	VALOR_DOIS FLOAT,
	FAIXA_TRES INT,
	VALOR_TRES FLOAT,
	FAIXA_QUATRO INT,
	VALOR_QUATRO FLOAT,
	FAIXA_CINCO INT,
	VALOR_CINCO FLOAT,
	
	CONSTRAINT pk_config_valores PRIMARY KEY (ID_CONFIG_VALORES),
	CONSTRAINT fk_unid_consumo_valores FOREIGN KEY (COD_UNID_CONSUMO) REFERENCES UNID_CONSUMO (COD_UNID_CONSUMO)	
)
GO

CREATE TABLE CONFIG_VALORES_OLD
(
	ID_CONFIG_VALORES INT IDENTITY(1,1),
	COD_UNID_CONSUMO INT NOT NULL, 
	MINIMO INT,
	VALOR_MINIMO FLOAT,
	FAIXA_UM INT,
	VALOR_UM FLOAT,
	FAIXA_DOIS INT,
	VALOR_DOIS FLOAT,
	FAIXA_TRES INT,
	VALOR_TRES FLOAT,
	FAIXA_QUATRO INT,
	VALOR_QUATRO FLOAT,
	FAIXA_CINCO INT,
	VALOR_CINCO FLOAT,
	
	CONSTRAINT pk_config_valores_old PRIMARY KEY (ID_CONFIG_VALORES)	
)
GO

CREATE TABLE CONFIG_GERAL
(
	ID_CONFIG_GERAL INT IDENTITY(1,1),
	COD_UNID_CONSUMO INT NOT NULL, 
	DIA_LEITURA INT NOT NULL,
	MES_LEITURA INT NOT NULL,
	ANO_LEITURA INT NOT NULL,
	DIA_VENCIMENTO INT NOT NULL,
	MES_VENCIMENTO INT NOT NULL,
	ANO_VENCIMENTO INT NOT NULL,
	JUROS FLOAT,
	MULTA FLOAT,
	TEXTO_CONTA VARCHAR(MAX) NOT NULL,
	
	CONSTRAINT pk_config PRIMARY KEY (ID_CONFIG_GERAL),
	CONSTRAINT fk_unid_consumo_geral FOREIGN KEY (COD_UNID_CONSUMO) REFERENCES UNID_CONSUMO (COD_UNID_CONSUMO)	
)
GO

CREATE TABLE CONFIG_GERAL_OLD
(
	ID_CONFIG_GERAL INT IDENTITY(1,1),
	COD_UNID_CONSUMO INT NOT NULL, 
	DIA_LEITURA INT NOT NULL,
	MES_LEITURA INT NOT NULL,
	ANO_LEITURA INT NOT NULL,
	DIA_VENCIMENTO INT NOT NULL,
	MES_VENCIMENTO INT NOT NULL,
	ANO_VENCIMENTO INT NOT NULL,
	JUROS FLOAT,
	MULTA FLOAT,
	TEXTO_CONTA VARCHAR(MAX) NOT NULL,
	
	CONSTRAINT pk_config_old PRIMARY KEY (ID_CONFIG_GERAL),	
)
GO

CREATE TABLE END_COBRANCA
(
	ID_END_COB INT IDENTITY(1,1),
	COD_CLIENTE INT,
	ENDERECO VARCHAR(MAX) NOT NULL,
	NUMERO INT,
	COMPLEMENTO VARCHAR(MAX), 
	BAIRRO VARCHAR(MAX),
	CIDADE VARCHAR(MAX),
	SIG_UF VARCHAR(2),
	CEP VARCHAR(10),
	
	CONSTRAINT pk_end_cob PRIMARY KEY (ID_END_COB),
	CONSTRAINT fk_end_cliente FOREIGN KEY (COD_CLIENTE) REFERENCES CLIENTE (COD_CLIENTE)
)
GO

CREATE TABLE END_INSTALACAO
(
	COD_END_INST INT,
	COD_CLIENTE INT NOT NULL,
	HIDROMETRO VARCHAR(50) NOT NULL,
	ENDERECO VARCHAR(MAX) NOT NULL,
	GLEBA VARCHAR(MAX),
	CHACARA VARCHAR(MAX), 
	COD_UNID_CONSUMO INT NOT NULL,
	NOME_UNID_CONSUMO VARCHAR(50) NOT NULL,
	CIDADE VARCHAR(MAX) NOT NULL,
	SIG_UF VARCHAR(2),
	TEL_CELULAR VARCHAR(11),
	DT_ATIVACAO DATETIME,
	DT_DESATIVACAO DATETIME,
	
	CONSTRAINT pk_end_inst PRIMARY KEY (COD_END_INST),
	CONSTRAINT fk_inst_cliente FOREIGN KEY (COD_CLIENTE) REFERENCES CLIENTE (COD_CLIENTE),
	CONSTRAINT fk_unid_consumo_inst FOREIGN KEY (COD_UNID_CONSUMO) REFERENCES UNID_CONSUMO (COD_UNID_CONSUMO)
)
GO

CREATE TABLE CONTA
(
	ID_CONTA INT IDENTITY(1,1),
	COD_CLIENTE INT NOT NULL,
	COD_END_INST INT NOT NULL,
	HIDROMETRO VARCHAR(50) NOT NULL,
	UNID_CONSUMO INT NOT NULL,
	MES INT NOT NULL,
	ANO INT NOT NULL, 
	DT_LEITURA DATE NOT NULL,
	DT_VENCIMENTO DATE NOT NULL,
	LEITURA_ANTERIOR INT NOT NULL,
	LEITURA_ATUAL INT NOT NULL,
	CONSUMO INT NOT NULL,
	VALOR FLOAT NOT NULL,
	OUTROS_VALORES FLOAT,
	DESCRICAO VARCHAR(MAX),
	DESCONTO INT,
	TOTAL FLOAT NOT NULL,
	
	CONSTRAINT pk_conta PRIMARY KEY (ID_CONTA),
	CONSTRAINT fk_conta_instalacao FOREIGN KEY (COD_CLIENTE) REFERENCES CLIENTE (COD_CLIENTE),
	CONSTRAINT fk_unid_consumo_conta FOREIGN KEY (UNID_CONSUMO) REFERENCES UNID_CONSUMO (COD_UNID_CONSUMO),
	CONSTRAINT fk_endinst_conta FOREIGN KEY (COD_END_INST) REFERENCES END_INSTALACAO (COD_END_INST),
	CONSTRAINT un_conta UNIQUE(HIDROMETRO, MES, ANO)
)
GO

CREATE TABLE ITENS_CONTA
(
	ID_ITENS_CONTA INT IDENTITY(1,1),
	COD_CLIENTE INT NOT NULL,
	COD_END_INST INT NOT NULL,
	HIDROMETRO VARCHAR(50) NOT NULL,
	MES INT,
	ANO INT,
	DESCRICAO VARCHAR(20),
	VALOR FLOAT,
	
	CONSTRAINT pk_itens_conta PRIMARY KEY (ID_ITENS_CONTA),
	CONSTRAINT fk_iten_cliente FOREIGN KEY (COD_CLIENTE) REFERENCES CLIENTE (COD_CLIENTE),
	CONSTRAINT fk_hidrometro_itens FOREIGN KEY (HIDROMETRO) REFERENCES CAD_HIDROMETRO (HIDROMETRO),
	CONSTRAINT fk_endinst_itens FOREIGN KEY (COD_END_INST) REFERENCES END_INSTALACAO (COD_END_INST),
	CONSTRAINT un_dados_conta UNIQUE(COD_CLIENTE, HIDROMETRO, MES, ANO, DESCRICAO)
)
GO

CREATE TABLE PARCELAMENTOS
(
	ID_PARCELAMENTOS INT IDENTITY(1,1),
	COD_CLIENTE INT NOT NULL,
	COD_END_INST INT NOT NULL,
	PARCELA_ATUAL INT,
	PARCELA_FINAL INT,
	VALOR FLOAT,
	DESCRICAO VARCHAR(MAX),

	CONSTRAINT pk_parcelamentos PRIMARY KEY (ID_PARCELAMENTOS),
	CONSTRAINT fk_parcel_cliente FOREIGN KEY (COD_CLIENTE) REFERENCES CLIENTE (COD_CLIENTE),
	CONSTRAINT fk_endinst_parcel FOREIGN KEY (COD_END_INST) REFERENCES END_INSTALACAO (COD_END_INST)
)
GO