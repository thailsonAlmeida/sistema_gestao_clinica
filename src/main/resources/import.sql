INSERT INTO TB_PATIENT ( NAME , ADDRESS , BIRTH_DAY , CONTACT ) VALUES ('Sandro Almeida', 'Rua Canada, n45', '10/08/1994', '(15) 98546-2079');
INSERT INTO TB_PATIENT ( NAME , ADDRESS , BIRTH_DAY , CONTACT ) VALUES ('Marina Silva', 'Rua Brazil, n121', '20/07/1994', '(15) 99653-1820');
INSERT INTO TB_PATIENT ( NAME , ADDRESS , BIRTH_DAY , CONTACT ) VALUES ('Carlos Beltrão', 'Rua Alaska, n361', '16/06/1994', '(15) 99845-1478');
INSERT INTO TB_PATIENT ( NAME , ADDRESS , BIRTH_DAY , CONTACT ) VALUES ('Leticia Carmargo', 'Rua Jamaica, n87', '12/05/1994', '(15) 99475-8525');
INSERT INTO TB_PATIENT ( NAME , ADDRESS , BIRTH_DAY , CONTACT ) VALUES ('Patricia Silver', 'Rua Fernão, n19', '03/04/1994', '(15) 99333-6932');
INSERT INTO TB_PATIENT ( NAME , ADDRESS , BIRTH_DAY , CONTACT ) VALUES ('Camila Teles', 'Rua São Paulo, n89', '09/03/1994', '(15) 99365-4785');
INSERT INTO TB_PATIENT ( NAME , ADDRESS , BIRTH_DAY , CONTACT ) VALUES ('Anderson Marson', 'Rua Lopes, n36', '13/02/1994', '(15) 99253-5565');
INSERT INTO TB_PATIENT ( NAME , ADDRESS , BIRTH_DAY , CONTACT ) VALUES ('Freitas Pereira', 'Rua Miriam, n465', '14/01/1994', '(15) 99365-6632');
INSERT INTO TB_PATIENT ( NAME , ADDRESS , BIRTH_DAY , CONTACT ) VALUES ('Alex Azevedo', 'Rua EUA, n265', '19/12/1994', '(15) 99456-9859');
INSERT INTO TB_PATIENT ( NAME , ADDRESS , BIRTH_DAY , CONTACT ) VALUES ('Silva Almeida', 'Rua Canada, n51', '20/11/1994', '(15) 99485-0203');
INSERT INTO TB_PATIENT ( NAME , ADDRESS , BIRTH_DAY , CONTACT ) VALUES ('Julio Castro', 'Rua Azerbaijão, n165', '21/10/1994', '(15) 99498-2521');

INSERT INTO TB_PROFESSIONAL ( NAME , SPECIALTY , CONTACT ) VALUES ('Luana Silva Assunção', 'Audiologia','(15) 98965-7895');
INSERT INTO TB_PROFESSIONAL ( NAME , SPECIALTY , CONTACT ) VALUES ('Clara Almeida Teles', 'Linguagem','(15) 94758-4656');
INSERT INTO TB_PROFESSIONAL ( NAME , SPECIALTY , CONTACT ) VALUES ('Leticia Farias Assad', 'Voz','(15) 99856-6985');
INSERT INTO TB_PROFESSIONAL ( NAME , SPECIALTY , CONTACT ) VALUES ('Leila Fagundes Silva', 'Disfagia','(15) 97856-8745');
INSERT INTO TB_PROFESSIONAL ( NAME , SPECIALTY , CONTACT ) VALUES ('Laura Pereira Beltrão', 'Fonoaudiologia Escolar','(15) 98974-6584');

INSERT INTO TB_SCHEDULING ( PROFESSIONAL_ID , PATIENT_ID , DATE_HOUR ) VALUES (1, 1, '2024-11-09T09:14:03.465664400');

INSERT INTO TB_REPORT ( DATE_REPORT , PATIENT_ID , PROFESSIONAL_ID , DESCRIPTION ) VALUES ('2024-11-09T09:16:39.379850600', 1, 1, 'Com aplicação da técnica... foi observado no paciente as seguintes reações...');