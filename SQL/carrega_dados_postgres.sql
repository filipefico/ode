---------------------------------------------------------------------------------------------
-- KRecursoHumano 
---------------------------------------------------------------------------------------------  		
-- Sequence utilizada = Conhecimento

INSERT INTO CONHECIMENTO (id, uuid, version, nome, descricao) VALUES (1, 'as6c7738-b3bc-40cc-98ab-21c5f59db410', 0, 'Gerente de Projetos','');
INSERT INTO KRECURSO (id) VALUES (1);
INSERT INTO KRECURSOHUMANO (id) VALUES (1);

---------------------------------------------------------------------------------------------
-- Recursos Humanos 
---------------------------------------------------------------------------------------------  		
    		
INSERT INTO RECURSOHUMANO (id, uuid, version, nome, ativo, cargahoraria, cargo_id) VALUES (1, '16ac7738-b3bc-40cc-98ab-21c5f59db410', 0, 'Ricardo de Almeida Falbo', true, 160, 1);

INSERT INTO HIBERNATE_SEQUENCES (SEQUENCE_NAME, SEQUENCE_NEXT_HI_VALUE ) VALUES  ('RecursoHumano', 2);

---------------------------------------------------------------------------------------------
-- Usuários
---------------------------------------------------------------------------------------------

INSERT INTO USUARIO (id, uuid, version, nomeusuario, senha, perfilacessoid, recursohumano_id) VALUES (1, '36ac7738-b3bc-40cc-98ab-21c5f59db410', 0, 'admin', '94b8de52b4a67aede716e535e196161e', 1, 1);

INSERT INTO HIBERNATE_SEQUENCES (SEQUENCE_NAME, SEQUENCE_NEXT_HI_VALUE ) VALUES  ('Usuario', 2);

---------------------------------------------------------------------------------------------
-- KTipoInteracao
---------------------------------------------------------------------------------------------

INSERT INTO CONHECIMENTO (id, uuid, version, nome, descricao) VALUES (2, '65as7738-b3bc-40cc-98ab-21c5f59db410', 0, 'Sequencial', 'Tipo de Interacao Sequencial');
INSERT INTO CONHECIMENTO (id, uuid, version, nome, descricao) VALUES (3, '65as7738-b3bc-40cc-98ab-21c5f59db411', 0, 'Paralelo Independente', 'Tipo de Interacao Paralelo Independente');
INSERT INTO CONHECIMENTO (id, uuid, version, nome, descricao) VALUES (4, '65as7738-b3bc-40cc-98ab-21c5f59db412', 0, 'Paralelo Dependente', 'Tipo de Interacao Paralelo Dependente');
INSERT INTO CONHECIMENTO (id, uuid, version, nome, descricao) VALUES (5, '65as7738-b3bc-40cc-98ab-21c5f59db413', 0, 'Pontual', 'Tipo de Interacao Pontual');
INSERT INTO CONHECIMENTO (id, uuid, version, nome, descricao) VALUES (6, '65as7738-b3bc-40cc-98ab-21c5f59db414', 0, 'Sob-Demanda', 'Tipo de Interacao Sob-Demanda');
INSERT INTO KTIPOINTERACAO (id) VALUES (2);
INSERT INTO KTIPOINTERACAO (id) VALUES (3);
INSERT INTO KTIPOINTERACAO (id) VALUES (4);
INSERT INTO KTIPOINTERACAO (id) VALUES (5);
INSERT INTO KTIPOINTERACAO (id) VALUES (6);

---------------------------------------------------------------------------------------------
-- KCategoriaProcesso
---------------------------------------------------------------------------------------------

INSERT INTO CONHECIMENTO (id, uuid, version, nome, descricao) VALUES (7, '65as7738-b3bc-40cc-98ab-21c5f59db415', 0, 'Fundamental', 'Categoria Fundamental');
INSERT INTO CONHECIMENTO (id, uuid, version, nome, descricao) VALUES (8, '65as7738-b3bc-40cc-98ab-21c5f59db416', 0, 'Apoio', 'Categoria de Apoio');
INSERT INTO CONHECIMENTO (id, uuid, version, nome, descricao) VALUES (9, '65as7738-b3bc-40cc-98ab-21c5f59db417', 0, 'Organizacional', 'Categoria Organizacional');
INSERT INTO KCATEGORIAPROCESSO (id) VALUES (7);
INSERT INTO KCATEGORIAPROCESSO (id) VALUES (8);
INSERT INTO KCATEGORIAPROCESSO (id) VALUES (9);

INSERT INTO HIBERNATE_SEQUENCES (SEQUENCE_NAME, SEQUENCE_NEXT_HI_VALUE ) VALUES  ('Conhecimento', 10);
