package backend.clinica.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // Configure os parâmetros de SMTP conforme o seu servidor de e-mail
        mailSender.setHost("sandbox.smtp.mailtrap.io");
        mailSender.setPort(587);
        mailSender.setUsername("69c4343a64bcd8");
        mailSender.setPassword("7b202ba681bb0e");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
