package academy.kata.pp_3_1_4.service;

import academy.kata.pp_3_1_4.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {

    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;
    @Autowired
    public MailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public MailSender() {
    }

    public void send(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setSubject("Registration");
        mailMessage.setTo(user.getEmail());

        String message = String.format("Логин: %s \nПароль: %s", user.getEmail(), user.getPassword());
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
