package de.admir.event;

import de.admir.model.OrderStatusEnum;
import de.admir.model.order.Order;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class OrderEventHandler {

    private static final Logger LOG = Logger.getLogger(OrderEventHandler.class);
    private static final SimpleMailMessage msg = new SimpleMailMessage();

    @Autowired
    private MailSender mailSender;

    @HandleAfterSave
    public void handleOrderAfterSave(Order order) {
        if (order.getStatus().equals(OrderStatusEnum.ORDERED)) {
            msg.setTo(order.getCustomer().getEmail());
            msg.setSubject("Primljena narud\u017Eba");
            msg.setText("order: " + order.toString() + "\n\n" + "entries: " + order.getEntries().toString());
            mailSender.send(msg);
        }
    }
}
