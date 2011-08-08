---------------------------------------------------------------------------------------------
-- Configuracoes do Sistema : Usada para recuperar as funcionalidades disponíveis no ODE  
---------------------------------------------------------------------------------------------

INSERT INTO CONFIGURACOESSISTEMA(id, uuid, version) 
			VALUES (1, '1' , 1);
			
-- Sequences: Deve conter o próximo id a ser utilizado 
INSERT INTO HIBERNATE_SEQUENCES (SEQUENCE_NAME, SEQUENCE_NEXT_HI_VALUE ) 
			VALUES  ('ConfiguracaoSistema', 1000);

---------------------------------------------------------------------------------------------
-- Criação de Menus 
---------------------------------------------------------------------------------------------

-- Projeto			
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, configuracoes_sistema_id, pos, funcionalidade_id) 
            VALUES (1, '1', 1, 'Projetos', '', false, null, 1, 0, null);
            
-- Cadastrar Projetos          
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, configuracoes_sistema_id, pos, funcionalidade_id) 
            VALUES (11, '11', 11, 'Cadastrar Projetos', 'ode.controleProjeto.cci.CtrlProjetoCRUD', false, 1, null, 0, 1);

-- Selecionar Projetos            
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, configuracoes_sistema_id, pos, funcionalidade_id) 
            VALUES (12, '12', 12, 'Selecionar Projeto', 'ode.controleProjeto.cci.CtrlSelecionarProjeto', false, 1, null, 1, 1);

-- Administracao 
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, configuracoes_sistema_id, pos, funcionalidade_id) 
            VALUES (2, '2', 2, 'Administração', '', false, null, 1, 1, null);
-- Usuarios
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, configuracoes_sistema_id, pos, funcionalidade_id) 
            VALUES (21, '21', 21, 'Usuários', 'ode.controleUsuario.cci.UsuarioCtrlCRUD', false, 2, null, 0, 2);

-- Menu Recursos
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, configuracoes_sistema_id, pos, funcionalidade_id) 
            VALUES (3, '3', 3, 'Recursos', '', true, null, 1, 2, null);

-- Recursos Humanos            
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, configuracoes_sistema_id, pos, funcionalidade_id) 
            VALUES (31, '31', 31, 'Recursos Humanos', 'ode.controleProcesso.cci.CtrlCRUDRecursoHumano', true, 3, null, 0, 3);
            
-- Sequences: Deve conter o próximo id a ser utilizado            
INSERT INTO HIBERNATE_SEQUENCES (SEQUENCE_NAME, SEQUENCE_NEXT_HI_VALUE ) 
			VALUES  ('Funcionalidade', 1000);

---------------------------------------------------------------------------------------------
-- Perfis de Acesso: Cada novo perfil deve ser criado aqui.
---------------------------------------------------------------------------------------------

-- Administrador
INSERT INTO PERFILACESSO(
            id, uuid, version, nome)
    		VALUES (1 , '1' , 1 , 'Administrador');

INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 1);

INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 11);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 12);
 
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 2);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 21);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 3);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 31);
    		
-- Desenvolvedor
INSERT INTO PERFILACESSO(
            id, uuid, version, nome)
    		VALUES (2 , '2' , 2 , 'Desenvolvedor');
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (2, 1);

INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (2, 12);
    		
-- Sequences: Deve conter o próximo id a ser utilizado     		
INSERT INTO HIBERNATE_SEQUENCES (SEQUENCE_NAME, SEQUENCE_NEXT_HI_VALUE ) 
			VALUES  ('PerfilAcesso', 1000);

---------------------------------------------------------------------------------------------
-- Recursos Humanos 
---------------------------------------------------------------------------------------------  		
    		
INSERT INTO RECURSOHUMANO (id, uuid, version, nome, ativo)
VALUES (1, '1', 1, 'Ricardo de Almeida Falbo', true);

INSERT INTO RECURSOHUMANO (id, uuid, version, nome, ativo)
VALUES (2, '2', 2, 'Alexandre G. N. Coelho', true);

-- Sequences: Deve conter o próximo id a ser utilizado    
INSERT INTO HIBERNATE_SEQUENCES (SEQUENCE_NAME, SEQUENCE_NEXT_HI_VALUE ) 
VALUES  ('RecursoHumano', 1000);

---------------------------------------------------------------------------------------------
-- Usuários
---------------------------------------------------------------------------------------------

INSERT INTO NUCLEOUSERDETAILS (id, uuid, version, username, password, perfilacesso_id, recursohumano_id)
VALUES (1, '36ac7738-b3bc-40cc-98ab-21c5f59db410', 0, 'admin', 'e8d95a51f3af4a3b134bf6bb680a213a',1, 1);

INSERT INTO NUCLEOUSERDETAILS (id, uuid, version, username, password, perfilacesso_id, recursohumano_id)
VALUES (2, '45as7738-b3bc-40cc-98ab-21c5f59db410', 0, 'alexandre.coelho', 'e8d95a51f3af4a3b134bf6bb680a213a', 2, 2);

INSERT INTO HIBERNATE_SEQUENCES (SEQUENCE_NAME, SEQUENCE_NEXT_HI_VALUE ) 
VALUES  ('NucleoUserDetails', 1);

---------------------------------------------------------------------------------------------
-- Granted Authority : Utilizada na autenticação de users. Não mexer.
---------------------------------------------------------------------------------------------

INSERT INTO GRANTEDAUTHORITYIMPL (id, uuid, version, authority)
VALUES (1, '1', 1, 'ROLE_SUPERVISOR');

INSERT INTO GRANTEDAUTHORITYIMPL (id, uuid, version, authority)
VALUES (2, '2', 2, 'ROLE_SUPERVISOR');

INSERT INTO HIBERNATE_SEQUENCES (SEQUENCE_NAME, SEQUENCE_NEXT_HI_VALUE ) 
VALUES  ('GrantedAuthorityImpl', 1000);

---------------------------------------------------------------------------------------------
-- Permitir que o usuários desse gerados nesse script possam autenticar no ODE 
---------------------------------------------------------------------------------------------

INSERT INTO NUCLEOUSERDETAILS_GRANTEDAUTHORITYIMPL
VALUES (1,1);
INSERT INTO NUCLEOUSERDETAILS_GRANTEDAUTHORITYIMPL 
VALUES (2,2);

---------------------------------------------------------------------------------------------
-- KTipoInteracao
---------------------------------------------------------------------------------------------

INSERT INTO KTIPOINTERACAO (id, uuid, version, nome, descricao) VALUES (1, '65as7738-b3bc-40cc-98ab-21c5f59db410', 0, 'Sequencial', 'Tipo de Interacao Sequencial');
INSERT INTO KTIPOINTERACAO (id, uuid, version, nome, descricao) VALUES (2, '65as7738-b3bc-40cc-98ab-21c5f59db411', 0, 'Paralelo Independente', 'Tipo de Interacao Paralelo Independente');
INSERT INTO KTIPOINTERACAO (id, uuid, version, nome, descricao) VALUES (3, '65as7738-b3bc-40cc-98ab-21c5f59db412', 0, 'Paralelo Dependente', 'Tipo de Interacao Paralelo Dependente');
INSERT INTO KTIPOINTERACAO (id, uuid, version, nome, descricao) VALUES (4, '65as7738-b3bc-40cc-98ab-21c5f59db413', 0, 'Pontual', 'Tipo de Interacao Pontual');
INSERT INTO KTIPOINTERACAO (id, uuid, version, nome, descricao) VALUES (5, '65as7738-b3bc-40cc-98ab-21c5f59db414', 0, 'Sob-Demanda', 'Tipo de Interacao Sob-Demanda');

---------------------------------------------------------------------------------------------
-- KCategoriaProcesso
---------------------------------------------------------------------------------------------

INSERT INTO KCATEGORIAPROCESSO (id, uuid, version, nome, descricao) VALUES (6, '65as7738-b3bc-40cc-98ab-21c5f59db415', 0, 'Fundamental', 'Categoria Fundamental');
INSERT INTO KCATEGORIAPROCESSO (id, uuid, version, nome, descricao) VALUES (7, '65as7738-b3bc-40cc-98ab-21c5f59db416', 0, 'Apoio', 'Categoria de Apoio');
INSERT INTO KCATEGORIAPROCESSO (id, uuid, version, nome, descricao) VALUES (8, '65as7738-b3bc-40cc-98ab-21c5f59db417', 0, 'Organizacional', 'Categoria Organizacional');
