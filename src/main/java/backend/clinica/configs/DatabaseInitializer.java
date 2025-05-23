package backend.clinica.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Component
public class DatabaseInitializer implements CommandLineRunner {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        seedUsers();
        seedPatients();
        seedProfessionals();
        seedSchedulings();
        seedReports();
    }   
    
    private void seedUsers() {
        entityManager.createNativeQuery("INSERT INTO TB_USER (ID, LOGIN, PASSWORD, ROLE) VALUES (100, 'manager@email.com', '$2a$10$So8LX2Hq7LVGWLDwBJ3Ji.437AILoxvD9i5kvNDgeMyzdplTDdxSu', 1)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_USER (ID, LOGIN, PASSWORD, ROLE) VALUES (200, 'professional@email.com', '$2a$10$JlDB7IVj7DAqe/OfhX9vxehbY.bqfPWMU.mUAB8mc9gulQGEa0WdK', 0)").executeUpdate();
        
        entityManager.createNativeQuery("INSERT INTO TB_USER (ID, LOGIN, PASSWORD, ROLE) VALUES (201, 'luana@email.com', '$2a$10$JlDB7IVj7DAqe/OfhX9vxehbY.bqfPWMU.mUAB8mc9gulQGEa0WdK', 0)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_USER (ID, LOGIN, PASSWORD, ROLE) VALUES (202, 'clara@email.com', '$2a$10$JlDB7IVj7DAqe/OfhX9vxehbY.bqfPWMU.mUAB8mc9gulQGEa0WdK', 0)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_USER (ID, LOGIN, PASSWORD, ROLE) VALUES (203, 'leticia@email.com', '$2a$10$JlDB7IVj7DAqe/OfhX9vxehbY.bqfPWMU.mUAB8mc9gulQGEa0WdK', 0)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_USER (ID, LOGIN, PASSWORD, ROLE) VALUES (204, 'leila@email.com', '$2a$10$JlDB7IVj7DAqe/OfhX9vxehbY.bqfPWMU.mUAB8mc9gulQGEa0WdK', 0)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_USER (ID, LOGIN, PASSWORD, ROLE) VALUES (205, 'laura@email.com', '$2a$10$JlDB7IVj7DAqe/OfhX9vxehbY.bqfPWMU.mUAB8mc9gulQGEa0WdK', 0)").executeUpdate();
        // Adicione os demais usuários aqui...
    }

    private void seedPatients() {
        entityManager.createNativeQuery("INSERT INTO TB_PATIENT (NAME, ADDRESS, BIRTH_DAY, CONTACT, EMAIL) VALUES ('Sandro Almeida', 'Rua Canada, n45', '10/08/1994', '(15) 98546-2079', 'a4611ac39c-6e3ffb+1@inbox.mailtrap.io')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PATIENT (NAME, ADDRESS, BIRTH_DAY, CONTACT, EMAIL) VALUES ('Marina Silva', 'Rua Brazil, n121', '20/07/1994', '(15) 99653-1820', 'a4611ac39c-6e3ffb+1@inbox.mailtrap.io')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PATIENT (NAME, ADDRESS, BIRTH_DAY, CONTACT, EMAIL) VALUES ('Carlos Beltrão', 'Rua Alaska, n361', '16/06/1994', '(15) 99845-1478', 'a4611ac39c-6e3ffb+1@inbox.mailtrap.io')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PATIENT (NAME, ADDRESS, BIRTH_DAY, CONTACT, EMAIL) VALUES ('Leticia Carmargo', 'Rua Jamaica, n87', '12/05/1994', '(15) 99475-8525', 'a4611ac39c-6e3ffb+1@inbox.mailtrap.io')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PATIENT (NAME, ADDRESS, BIRTH_DAY, CONTACT, EMAIL) VALUES ('Patricia Silver', 'Rua Fernão, n19', '03/04/1994', '(15) 99333-6932', 'a4611ac39c-6e3ffb+1@inbox.mailtrap.io')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PATIENT (NAME, ADDRESS, BIRTH_DAY, CONTACT, EMAIL) VALUES ('Camila Teles', 'Rua São Paulo, n89', '09/03/1994', '(15) 99365-4785', 'a4611ac39c-6e3ffb+1@inbox.mailtrap.io')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PATIENT (NAME, ADDRESS, BIRTH_DAY, CONTACT, EMAIL) VALUES ('Anderson Marson', 'Rua Lopes, n36', '13/02/1994', '(15) 99253-5565', 'a4611ac39c-6e3ffb+1@inbox.mailtrap.io')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PATIENT (NAME, ADDRESS, BIRTH_DAY, CONTACT, EMAIL) VALUES ('Freitas Pereira', 'Rua Miriam, n465', '14/01/1994', '(15) 99365-6632', 'a4611ac39c-6e3ffb+1@inbox.mailtrap.io')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PATIENT (NAME, ADDRESS, BIRTH_DAY, CONTACT, EMAIL) VALUES ('Alex Azevedo', 'Rua EUA, n265', '19/12/1994', '(15) 99456-9859', 'a4611ac39c-6e3ffb+1@inbox.mailtrap.io')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PATIENT (NAME, ADDRESS, BIRTH_DAY, CONTACT, EMAIL) VALUES ('Julio Castro', 'Rua Azerbaijão, n165', '21/10/1994', '(15) 99498-2521', 'a4611ac39c-6e3ffb+1@inbox.mailtrap.io')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PATIENT (NAME, ADDRESS, BIRTH_DAY, CONTACT, EMAIL) VALUES ('Mirela Leitão Castro', 'Rua Felipinas, n165', '21/10/1994', '(15) 98393-2621', 'a4611ac39c-6e3ffb+1@inbox.mailtrap.io')").executeUpdate();
    }

    private void seedProfessionals() {
        entityManager.createNativeQuery("INSERT INTO TB_PROFESSIONAL (NAME, SPECIALTY, CONTACT, EMAIL, REGISTRY, USER_ID) VALUES ('Luana Silva Assunção', 'Audiologia','(15) 98965-7895', 'luana@email.com', '2653', 201)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PROFESSIONAL (NAME, SPECIALTY, CONTACT, EMAIL, REGISTRY, USER_ID) VALUES ('Clara Almeida Teles', 'Linguagem','(15) 94758-4656', 'clara@email.com', '3353', 202)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PROFESSIONAL (NAME, SPECIALTY, CONTACT, EMAIL, REGISTRY, USER_ID) VALUES ('Leticia Farias Assad', 'Voz','(15) 99856-6985', 'leticia@email.com', '5653', 203)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PROFESSIONAL (NAME, SPECIALTY, CONTACT, EMAIL, REGISTRY, USER_ID) VALUES ('Leila Fagundes Silva', 'Disfagia','(15) 97856-8745', 'leila@email.com', '3653', 204)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_PROFESSIONAL (NAME, SPECIALTY, CONTACT, EMAIL, REGISTRY, USER_ID) VALUES ('Laura Pereira Beltrão', 'Fonoaudiologia Escolar','(15) 98974-6584', 'laura@email.com','2143', 205)").executeUpdate();
        // Adicione os demais profissionais aqui...
    }

    private void seedSchedulings() {
        entityManager.createNativeQuery("INSERT INTO TB_SCHEDULING (PROFESSIONAL_ID, PATIENT_ID, DATE_HOUR, IS_CONFIRMED, IS_PRESENT, IS_CANCEL) VALUES (1, 5, '2024-09-10T10:14', false, false, false)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_SCHEDULING (PROFESSIONAL_ID, PATIENT_ID, DATE_HOUR, IS_CONFIRMED, IS_PRESENT, IS_CANCEL) VALUES (1, 5, '2024-10-10T10:14', false, false, false)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_SCHEDULING (PROFESSIONAL_ID, PATIENT_ID, DATE_HOUR, IS_CONFIRMED, IS_PRESENT, IS_CANCEL) VALUES (1, 5, '2024-11-10T10:14', true, false, false)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_SCHEDULING (PROFESSIONAL_ID, PATIENT_ID, DATE_HOUR, IS_CONFIRMED, IS_PRESENT, IS_CANCEL) VALUES (2, 4, '2024-11-11T13:14', false, false, false)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_SCHEDULING (PROFESSIONAL_ID, PATIENT_ID, DATE_HOUR, IS_CONFIRMED, IS_PRESENT, IS_CANCEL) VALUES (3, 3, '2024-11-13T07:14', true, false, false)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_SCHEDULING (PROFESSIONAL_ID, PATIENT_ID, DATE_HOUR, IS_CONFIRMED, IS_PRESENT, IS_CANCEL) VALUES (4, 2, '2024-11-14T08:14', false, false, false)").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_SCHEDULING (PROFESSIONAL_ID, PATIENT_ID, DATE_HOUR, IS_CONFIRMED, IS_PRESENT, IS_CANCEL) VALUES (5, 1, '2024-11-15T09:14', true, false, false)").executeUpdate();
        // Adicione os demais agendamentos aqui...
    }

    private void seedReports() {
        entityManager.createNativeQuery("INSERT INTO TB_REPORT (DATE_REPORT, PATIENT_ID, PROFESSIONAL_ID, REPORT_TYPE, DESCRIPTION) VALUES ('2024-11-09T09:16', 1, 1, 'AVALIATIVO', 'Queixa Principal: Dificuldade em ouvir em ambientes ruidosos e diminuição da audição em altas frequências. Histórico: O paciente relata perda auditiva progressiva há aproximadamente 5 anos, sem causa aparente. Nega o uso de ototóxicos e histórico de otites. Exames Realizados: Audiometria tonal limiar: Perda auditiva neurosensorial moderada, simétrica, com maior comprometimento nas altas frequências. Logoaudiometria: Compreensão da fala adequada para o nível de perda auditiva. Impedanciometria: Curvas tipo A bilateralmente, sugerindo função timpânica normal. Diagnóstico: Perda auditiva neurosensorial bilateral, presbiacústica. Conduta: Orientar o paciente sobre a natureza da perda auditiva e a importância do uso de aparelhos auditivos. Realizar moldagem auricular para confecção de aparelhos auditivos. Acompanhamento periódico para ajuste e verificação da eficácia dos aparelhos. Considerações: O paciente apresenta bom potencial de reabilitação auditiva com o uso de aparelhos auditivos. Recomenda-se o acompanhamento regular com o fonoaudiólogo para otimização da reabilitação.')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO TB_REPORT (DATE_REPORT, PATIENT_ID, PROFESSIONAL_ID, REPORT_TYPE, DESCRIPTION) VALUES ('2024-11-09T09:16', 1, 1, 'AVALIATIVO', 'Analise: Melhoria na em ouvir em ambientes ruidosos e diminuição da audição em altas frequências. Histórico: O paciente relata certa recuperação auditiva progressiva. Exames Realizados: Audiometria tonal limiarrecuperação de 25%')").executeUpdate();
        // Adicione os demais relatórios aqui...
    }
    
    
}


