CREATE TABLE CONFIG_VALORES_OLD
(
	ID_CONFIG_VALORES INT IDENTITY(1,1),
	FAIXA_INICIAL INT,
	FAIXA_FINAL INT,
	VALOR FLOAT
	
	CONSTRAINT pk_config_valores_old PRIMARY KEY (ID_CONFIG_VALORES)	
)