CREATE TABLE CONFIG_VALORES
(
	ID_CONFIG_VALORES INT IDENTITY(1,1),
	FAIXA_INICIAL INT,
	FAIXA_FINAL INT,
	VALOR FLOAT,
	
	CONSTRAINT pk_config_valores PRIMARY KEY (ID_CONFIG_VALORES)	
)