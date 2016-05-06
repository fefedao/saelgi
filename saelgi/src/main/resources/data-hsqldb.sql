INSERT INTO endereco (codigo, municipio, uf, bairro, logradouro, numero, complemento, referencia, cep, flagExcluido) VALUES (1, 'Florianópolis', 'SC', 'Coqueiros', 'Rua Pascoal Simoni', '197', 'Casa', 'Em frente a UDESC', '88080350', 'N');
INSERT INTO endereco (codigo, municipio, uf, bairro, logradouro, numero, complemento, referencia, cep, flagExcluido) VALUES (2, 'São José', 'SC', 'Coqueiros', 'Rua Pascoal Simoni', '197', 'Casa', 'Em frente a UDESC', '88080350', 'N');
INSERT INTO endereco (codigo, municipio, uf, bairro, logradouro, numero, complemento, referencia, cep, flagExcluido) VALUES (3, 'Palhoça', 'SC', 'Coqueiros', 'Rua Pascoal Simoni', '197', 'Casa', 'Em frente a UDESC', '88080350', 'N');
INSERT INTO endereco (codigo, municipio, uf, bairro, logradouro, numero, complemento, referencia, cep, flagExcluido) VALUES (4, 'Biguaçu', 'SC', 'Coqueiros', 'Rua Pascoal Simoni', '197', 'Casa', 'Em frente a UDESC', '88080350', 'N');
INSERT INTO endereco (codigo, municipio, uf, bairro, logradouro, numero, complemento, referencia, cep, flagExcluido) VALUES (5, 'Santo Amaro da Imperatriz', 'SC', 'Coqueiros', 'Rua Pascoal Simoni', '197', 'Casa', 'Em frente a UDESC', '88080350', 'N');
INSERT INTO endereco (codigo, municipio, uf, bairro, logradouro, numero, complemento, referencia, cep, flagExcluido) VALUES (6, 'Rancho Queimado', 'SC', 'Coqueiros', 'Rua Pascoal Simoni', '197', 'Casa', 'Em frente a UDESC', '88080350', 'N');
INSERT INTO endereco (codigo, municipio, uf, bairro, logradouro, numero, complemento, referencia, cep, flagExcluido) VALUES (7, 'Ituporanga', 'SC', 'Coqueiros', 'Rua Pascoal Simoni', '197', 'Casa', 'Em frente a UDESC', '88080350', 'N');
INSERT INTO endereco (codigo, municipio, uf, bairro, logradouro, numero, complemento, referencia, cep, flagExcluido) VALUES (8, 'Bombinhas', 'SC', 'Coqueiros', 'Rua Pascoal Simoni', '197', 'Casa', 'Em frente a UDESC', '88080350', 'N');
INSERT INTO endereco (codigo, municipio, uf, bairro, logradouro, numero, complemento, referencia, cep, flagExcluido) VALUES (9, 'Blumenau', 'SC', 'Coqueiros', 'Rua Pascoal Simoni', '197', 'Casa', 'Em frente a UDESC', '88080350', 'N');

INSERT INTO orgao (codigo, esfera, nomeOrgao, email, cnpj, endereco, flagExcluido) VALUES (1, 1, 'Secretaria da Saúde de Florianópolis', 'secretariadasaude@pmf.gov.br', '04.865.161/0001-07', 1, 'N');
INSERT INTO orgao (codigo, esfera, nomeOrgao, email, cnpj, endereco, flagExcluido) VALUES (2, 2, 'Departamento de Desenvolvimento do Estado de Santa Catarina', 'dpmf@sc.gov.br', '53.412.266/0001-08', 2, 'N');
INSERT INTO orgao (codigo, esfera, nomeOrgao, email, cnpj, endereco, flagExcluido) VALUES (3, 2, 'Secretaria da Segurança do Estado de Santa Catarina', 'secretariadaseguranca@sc.gov.br', '42.026.324/0001-49', 3, 'N');
INSERT INTO orgao (codigo, esfera, nomeOrgao, email, cnpj, endereco, flagExcluido) VALUES (4, 3, 'Departamento de Compras Ministério da Defesa', 'r3b2@defesa.gov.br', '50.361.468/0001-80', 4, 'N');
INSERT INTO orgao (codigo, esfera, nomeOrgao, email, cnpj, endereco, flagExcluido) VALUES (5, 1, 'Secretaria da Saúde de Palhoça', 'secsaude@pmpalhoca.gov.br', '79.785.451/0001-31', 5, 'N');
INSERT INTO orgao (codigo, esfera, nomeOrgao, email, cnpj, endereco, flagExcluido) VALUES (6, 1, 'Secretaria do Desenvolvimento de São José','secdesenv@pmsj.gov.br', '04.865.161/0001-07', 6, 'N');
INSERT INTO orgao (codigo, esfera, nomeOrgao, email, cnpj, endereco, flagExcluido) VALUES (7, 3, 'Advocacia da União - Distrito Federal', 'agcu@advoc.jus.gov.br', '04.865.161/0001-07', 7, 'N');
INSERT INTO orgao (codigo, esfera, nomeOrgao, email, cnpj, endereco, flagExcluido) VALUES (8, 2, 'Tribunal de Justiça do Mato Grosso do Sul', 'adroghm@tjms.jus.gov.br', '04.865.161/0001-07', 8, 'N');

INSERT INTO modalidade (codigo, nomeModalidade, flagExcluido) VALUES (1, 'Pregão Presencial', 'N');
INSERT INTO modalidade (codigo, nomeModalidade, flagExcluido) VALUES (2, 'Pregão Eletrônico', 'N');
INSERT INTO modalidade (codigo, nomeModalidade, flagExcluido) VALUES (3, 'Tomada de Preços', 'N');
INSERT INTO modalidade (codigo, nomeModalidade, flagExcluido) VALUES (4, 'Concorrência', 'N');

INSERT INTO licitacao (codigo, orgao, dataDeAbertura, dataEntregaProposta, dataEntregaDocumentacao, modalidade, numeroEdital, flagExcluido) VALUES (1, 1, '2015-12-31 00:00:00', '2015-12-31 00:00:00', '2015-12-31 00:00:00', 1, '213/2010', 'N');
INSERT INTO licitacao (codigo, orgao, dataDeAbertura, dataEntregaProposta, dataEntregaDocumentacao, modalidade, numeroEdital, flagExcluido) VALUES (2, 2, '2015-12-31 00:00:00', '2015-12-31 00:00:00', '2015-12-31 00:00:00', 3, '251/2010', 'N');
INSERT INTO licitacao (codigo, orgao, dataDeAbertura, dataEntregaProposta, dataEntregaDocumentacao, modalidade, numeroEdital, flagExcluido) VALUES (3, 4, '2015-12-31 00:00:00', '2015-12-31 00:00:00', '2015-12-31 00:00:00', 1, '288/2010', 'N');
INSERT INTO licitacao (codigo, orgao, dataDeAbertura, dataEntregaProposta, dataEntregaDocumentacao, modalidade, numeroEdital, flagExcluido) VALUES (4, 1, '2015-12-31 00:00:00', '2015-12-31 00:00:00', '2015-12-31 00:00:00', 2, '219/2010', 'N');
INSERT INTO licitacao (codigo, orgao, dataDeAbertura, dataEntregaProposta, dataEntregaDocumentacao, modalidade, numeroEdital, flagExcluido) VALUES (5, 7, '2015-12-31 00:00:00', '2015-12-31 00:00:00', '2015-12-31 00:00:00', 4, '223/2010', 'N');








