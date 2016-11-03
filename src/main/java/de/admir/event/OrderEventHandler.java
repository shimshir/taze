package de.admir.event;

import de.admir.model.order.ConfirmationToken;
import de.admir.model.order.Order;
import de.admir.repository.ConfirmationTokenRepository;
import de.admir.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class OrderEventHandler {

    private static final Logger LOG = Logger.getLogger(OrderEventHandler.class);

    @Autowired
    private MailService mailService;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @HandleBeforeSave
    public void handleOrderBeforeSave(Order order) {
        if ("ordered".equalsIgnoreCase(order.getStatus().getId())) {
            ConfirmationToken token = new ConfirmationToken();
            confirmationTokenRepository.save(token);
            order.setToken(token);
        }
    }

    @HandleAfterSave
    public void handleOrderAfterSave(Order order) {
        if ("ordered".equalsIgnoreCase(order.getStatus().getId())) {
            mailService.sendConfirmationEmail(order);
        }
        if ("confirmed".equalsIgnoreCase(order.getStatus().getId())) {
            ConfirmationToken token = order.getToken();
            token.setUsed(true);
            confirmationTokenRepository.save(token);
        }
    }
}
