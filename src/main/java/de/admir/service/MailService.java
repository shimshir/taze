package de.admir.service;

import de.admir.model.order.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private static final SimpleMailMessage MSG = new SimpleMailMessage();

    @Autowired
    private MailSender mailSender;

    @Value("${taze.frontend.host}")
    private String host;

    public void sendConfirmationEmail(Order order) {
        MSG.setTo(order.getCustomer().getEmail());
        MSG.setSubject("Primljena narud\u017Eba");
        MSG.setText("order: " + order.toString() + "\n\n" + createConfirmationLink(order.getId(), order.getToken().getValue()) + "\n\n" + "entries: " + order.getEntries().toString());
        mailSender.send(MSG);
    }

    private String createConfirmationLink(Long orderId, String token) {
        return String.format("%s/confirmedOrder?orderId=%d&token=%s", host, orderId, token);
    }
}
