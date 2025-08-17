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
        clearDatabase(); // limpa todas as tabelas
        seedUsers();     // insere novos usuários
    }  

    // --- LIMPAR BANCO ---
    private void clearDatabase() {
        // Desabilita triggers temporariamente (opcional, se necessário)
        entityManager.createNativeQuery("ALTER TABLE tb_report DISABLE TRIGGER ALL").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE tb_scheduling DISABLE TRIGGER ALL").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE tb_professional DISABLE TRIGGER ALL").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE tb_patient DISABLE TRIGGER ALL").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE tb_user DISABLE TRIGGER ALL").executeUpdate();

        // Trunca todas as tabelas em cascata para respeitar FK
        entityManager.createNativeQuery("TRUNCATE TABLE tb_report RESTART IDENTITY CASCADE").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE tb_scheduling RESTART IDENTITY CASCADE").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE tb_professional RESTART IDENTITY CASCADE").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE tb_patient RESTART IDENTITY CASCADE").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE tb_user RESTART IDENTITY CASCADE").executeUpdate();

        // Reabilita triggers
        entityManager.createNativeQuery("ALTER TABLE tb_report ENABLE TRIGGER ALL").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE tb_scheduling ENABLE TRIGGER ALL").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE tb_professional ENABLE TRIGGER ALL").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE tb_patient ENABLE TRIGGER ALL").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE tb_user ENABLE TRIGGER ALL").executeUpdate();
    }

    // --- USERS ---
    private void seedUsers() {
        insertUserIfNotExists("manager@email.com",
                "INSERT INTO TB_USER (LOGIN, PASSWORD, ROLE) VALUES ('manager@email.com', '$2a$10$So8LX2Hq7LVGWLDwBJ3Ji.437AILoxvD9i5kvNDgeMyzdplTDdxSu', 1)");       
    }

    private void insertUserIfNotExists(String login, String sql) {
        Long count = ((Number) entityManager.createNativeQuery(
                "SELECT COUNT(*) FROM TB_USER WHERE LOGIN = :login")
                .setParameter("login", login)
                .getSingleResult()).longValue();
        if (count == 0) {
            entityManager.createNativeQuery(sql).executeUpdate();
        }
    }
}
