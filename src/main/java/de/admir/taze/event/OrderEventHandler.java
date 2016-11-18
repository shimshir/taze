package de.admir.taze.event;

import de.admir.taze.model.order.ConfirmationToken;
import de.admir.taze.model.order.Order;
import de.admir.taze.model.order.OrderStatusEnum;
import de.admir.taze.repository.ConfirmationTokenRepository;
import de.admir.taze.service.MailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class OrderEventHandler {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    // setter inject
    private MailService mailService;
    // setter inject
    private ConfirmationTokenRepository confirmationTokenRepository;

    @HandleBeforeSave
    public void handleOrderBeforeSave(Order order) {
        if (OrderStatusEnum.ORDERED.equals(order.getStatus())) {
            ConfirmationToken token = new ConfirmationToken();
            confirmationTokenRepository.save(token);
            order.setToken(token);
        }
    }

    @HandleAfterSave
    public void handleOrderAfterSave(Order order) {
        if (OrderStatusEnum.ORDERED.equals(order.getStatus())) {
            mailService.sendConfirmationEmail(order);
        }
        if (OrderStatusEnum.CONFIRMED.equals(order.getStatus())) {
            ConfirmationToken token = order.getToken();
            token.setUsed(true);
            confirmationTokenRepository.save(token);
        }
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Autowired
    public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }
}
