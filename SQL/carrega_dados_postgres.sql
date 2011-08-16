---------------------------------------------------------------------------------------------
-- Menus/Funcionalidades 
---------------------------------------------------------------------------------------------

-- Projeto			
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (1, '1', 1, 'Projetos', '', false, null, 0, null);
            
-- Cadastrar Projetos          
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (11, '11', 11, 'Cadastrar Projetos', 'ode.controleProjeto.cci.CtrlProjetoCRUD', false, 1, 0, 1);

-- Selecionar Projetos            
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (12, '12', 12, 'Selecionar Projeto', 'ode.controleProjeto.cci.CtrlSelecionarProjeto', false, 1, 1, 1);

-- Administracao 
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (2, '2', 2, 'Administração', '', false, null, 1, null);
-- Usuarios
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (21, '21', 21, 'Usuários', 'ode.controleUsuario.cci.UsuarioCtrlCRUD', false, 2, 0, 2);

-- Menu Recursos
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (3, '3', 3, 'Recursos', '', false, null, 2, null);

-- Recursos Humanos            
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (31, '31', 31, 'Recursos Humanos', 'ode.controleProcesso.cci.CtrlCRUDRecursoHumano', false, 3, 0, 3);

-- Conhecimento           
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (4, '4', 4, 'Conhecimento', '', false, null, 3, null);
            
-- Recursos           
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (41, '41', 41, 'Recursos', '', false, 4, 0, 4);
            
-- Ferramentas de Software
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (411, '411', 411, 'Ferramentas de Software', 'ode.conhecimento.processo.Cci.CtrlKFerramentaSoftwareCRUD', false, 41, 0, 41);

-- Recursos Humanos
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (412, '412', 412, 'Recursos Humanos', 'ode.conhecimento.processo.Cci.CtrlKRecursoHumanoCRUD', false, 41, 1, 41);
            
-- Recursos de Hardware
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (413, '413', 413, 'Recursos de Hardware', 'ode.conhecimento.processo.Cci.CtrlKRecursoHardwareCRUD', false, 41, 2, 41);
            
-- Processo
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (42, '42', 42, 'Processos', '', false, 4, 0, 4);
            
-- Paradigmas
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (421, '421', 421, 'Paradigmas', 'ode.conhecimento.processo.Cci.CtrlKParadigmaCRUD', false, 42, 0, 42);

-- Tipos de Software
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (422, '422', 422, 'Tipos de Software', 'ode.conhecimento.processo.Cci.CtrlTipoSoftwareCRUD', false, 42, 1, 42);
            
-- Tipos de Artefato
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (423, '423', 423, 'Tipos de Artefato', 'ode.conhecimento.processo.Cci.CrtlTipoKArtefatoCRUD', false, 42, 2, 42);

-- Artefatos
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (424, '424', 424, 'Artefatos', 'ode.conhecimento.processo.Cci.CtrlKArtefatoCRUD', false, 42, 3, 42);
            
-- Domínios de Aplicação
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (425, '425', 425, 'Domínios de Aplicação', 'ode.conhecimento.processo.Cci.CtrlKDominioAplicacaoCRUD', false, 42, 4, 42);
                        
-- Categorias de Processo
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (426, '426', 426, 'Categorias de Processo', 'ode.conhecimento.processo.Cci.CtrlKCategoriaProcessoCRUD', false, 42, 5, 42);
            
-- Processos
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (427, '427', 427, 'Processos', 'ode.conhecimento.processo.Cci.CtrlKProcessoCRUD', false, 42, 6, 42);
            
-- Atividades
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (428, '428', 428, 'Atividades', 'ode.conhecimento.processo.Cci.CtrlKAtividadeCRUD', false, 42, 7, 42);
            
-- Procedimentos
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (429, '429', 429, 'Procedimentos', 'ode.conhecimento.processo.Cci.CtrlKProcedimentoCRUD', false, 42, 8, 42);
            
-- Organização
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (43, '43', 43, 'Organização', '', false, 4, 0, 4);
            
-- Domínios de Conhecimento
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (431, '431', 431, 'Domínios de Conhecimento', 'ode.conhecimento.organizacao.Cci.CtrlKDominioConhecimentoCRUD', false, 43, 0, 43);
            
-- Processo Padrão
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (5, '5', 5, 'Processo Padrão', '', false, null, 4, null);

-- Componentes de Processo Padrão             
INSERT INTO FUNCIONALIDADE(
            id, uuid, version, nome, srcctrl, disponivelapenasparaprojetosabertos, funcionalidadepai_id, pos, funcionalidade_id) 
            VALUES (51, '51', 51, 'Componentes de Processo Padrão', 'ode.processoPadrao.Cci.CtrlDefinirProcessoPadraoCRUD', false, 5, 0, 5);
  
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

INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 4);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 41);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 411);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 412);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 413);

INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 42);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 421);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 422);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 423);

    		INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 424);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 425);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 426);

INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 427);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 428);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 429);

INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 43);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 431);

INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 5);
    		
INSERT INTO PERFILACESSO_FUNCIONALIDADE(
            perfilacesso_id, funcionalidadespermitidas_id)
    		VALUES (1, 51);
    		
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
