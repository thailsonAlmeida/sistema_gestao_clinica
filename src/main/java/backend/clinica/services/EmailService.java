package backend.clinica.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    
    @Autowired(required=true)
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom("a4611ac39c-6e3ffb+1@inbox.mailtrap.io");
            mailSender.send(message);
            logger.info("E-mail enviado com sucesso para {}", to);
        } catch (Exception e) {
            logger.error("Erro ao enviar e-mail para {}: {}", to, e.getMessage());
            e.printStackTrace(); 
        }
    }
}
