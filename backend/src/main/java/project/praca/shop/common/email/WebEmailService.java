package project.praca.shop.common.email;

import org.springframework.stereotype.Service;

@Service
public class WebEmailService implements EmailSender{
    @Override
    public void send(String to, String subject, String msg) {
        throw new RuntimeException("Not implemented yet!");
    }
}
