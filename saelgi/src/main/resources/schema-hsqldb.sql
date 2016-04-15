--DROP TABLE licitacao IF EXISTS;

CREATE TABLE licitacao (
  codigo    INTEGER IDENTITY PRIMARY KEY,
  codigoOrgao INTEGER,
  dataDeAbertura TIMESTAMP,
  dataEntregaProposta TIMESTAMP,
  dataEntregaDocumentacao TIMESTAMP,
  codigoModalidade INTEGER,
  numeroEdital  VARCHAR(50)
);

--DROP TABLE orgao IF EXISTS;

CREATE TABLE orgao (
  codigo    INTEGER IDENTITY PRIMARY KEY,
  esfera INTEGER,
  nomeOrgao VARCHAR(200),
  email VARCHAR(50),
  cnpj VARCHAR(20),
  codigoEndereco INTEGER
);

--DROP TABLE modalidade IF EXISTS;

CREATE TABLE modalidade (
  codigo    INTEGER IDENTITY PRIMARY KEY,
  nomeModalidade VARCHAR(20)
);

--DROP TABLE documentoEmpresa IF EXISTS;

CREATE TABLE documentoEmpresa (
  codigo    INTEGER IDENTITY PRIMARY KEY,
  codigoEmpresa INTEGER,
  codigoDocumento INTEGER,
  dataExpedicao TIMESTAMP,
  dataVencimento TIMESTAMP,
  flValido VARCHAR(1),
  flAutenticavel VARCHAR(1),
  flConferidoInternet VARCHAR(1),
  siteOrigem VARCHAR(200),
  blDocumentoPdf  BLOB
);

--DROP TABLE documento IF EXISTS;

CREATE TABLE documento (
  codigo    INTEGER IDENTITY PRIMARY KEY,
  nome VARCHAR(100)
);

--DROP TABLE documentoLicitacao IF EXISTS;

CREATE TABLE documentoLicitacao (
  codigo    INTEGER IDENTITY PRIMARY KEY,
  codigoLicitacao INTEGER,
  codigoEmpresa INTEGER
);

--DROP TABLE endereco IF EXISTS;

CREATE TABLE endereco (
  codigo    INTEGER IDENTITY PRIMARY KEY,
  municipio VARCHAR(100),
  uf VARCHAR(2),
  bairro VARCHAR(60),
  logradouro VARCHAR(200),
  numero VARCHAR(20),
  complemento VARCHAR(100),
  referencia VARCHAR(100)
);

--DROP TABLE empresa IF EXISTS;

CREATE TABLE empresa (
  codigo    INTEGER IDENTITY PRIMARY KEY,
  razaoSocial VARCHAR(100),
  nomeFantasia VARCHAR(100),
  email VARCHAR(50),
  cnpj VARCHAR(20)
);

--DROP TABLE representante IF EXISTS;

CREATE TABLE representante (
  codigo    INTEGER IDENTITY PRIMARY KEY,
  codigoEmpresa  INTEGER,
  nome VARCHAR(100),
  email VARCHAR(50),
  cnpj VARCHAR(20)
);

--DROP TABLE telefone IF EXISTS;

CREATE TABLE telefone (
  codigo    INTEGER IDENTITY PRIMARY KEY,
  codigoEmpresa INTEGER,
  codigoRepresentante INTEGER,
  codigoOrgao INTEGER,
  telefone VARCHAR(20),
  tipoTelefone VARCHAR(2),
  flPrincipal VARCHAR(1)
);

--DROP TABLE usuario IF EXISTS;

CREATE TABLE usuario (
  codigo    INTEGER IDENTITY PRIMARY KEY,
  codigoEmpresa  INTEGER,
  usuario VARCHAR(20),
  senha VARCHAR(20)
);