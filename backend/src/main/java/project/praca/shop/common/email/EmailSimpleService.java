package project.praca.shop.common.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSimpleService implements EmailSender {

    private final JavaMailSender mailSender;

    @Async
    @Override
    public void send(String to, String subject, String msg){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("");
        message.setReplyTo("");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
}