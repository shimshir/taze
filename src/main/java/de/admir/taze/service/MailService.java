package de.admir.taze.service;

import de.admir.taze.model.order.Order;

import de.admir.taze.repository.OrderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Template;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Service
public class MailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Configuration freemarkerConfiguration;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private OrderRepository orderRepository;

    private MimeMessage mimeMessage;
    private MimeMessageHelper mimeMessageHelper;

    @PostConstruct
    private void initMailing() {
        mimeMessage = mailSender.createMimeMessage();
        mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    }

    @Value("${taze.frontend.host}")
    private String host;

    /**
     * Because the method is executed asynchronously it needs to be transactional and has to reload the order entity
     */
    @Async
    @Transactional
    public void sendConfirmationEmail(Long orderId) {
        try {
            Order order = orderRepository.findOne(orderId);
            Template emailTemplate = freemarkerConfiguration.getTemplate("email/confirmEmail.ftl");
            Map<String, Object> model = new HashMap<>();
            model.put("order", order);
            model.put("confirmationLink", createConfirmationLink(order));
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(emailTemplate, model);
            mimeMessageHelper.setTo(order.getCustomer().getEmail());
            mimeMessageHelper.setSubject("Molimo potvrdite narud\u017ebu");
            mimeMessageHelper.setText(text, true);
            mailSender.send(mimeMessage);
            logger.info("Order confirmation e-mail sent to " + order.getCustomer().getEmail());
        } catch (MailAuthenticationException e) {
            logger.error("Failed to authenticate with mail server", e);
        } catch (MailSendException e) {
            logger.error("Failed to send email", e);
        } catch (IOException e) {
            logger.error("Failed to read confirmEmail.ftl template", e);
        } catch (TemplateException e) {
            logger.error("Template runtime exception", e);
        } catch (MessagingException e) {
            logger.error("Messaging exception", e);
        }
    }

    private String createConfirmationLink(Order order) {
        return String.format("%s/confirmed-order?orderId=%d&token=%s", host, order.getId(), order.getToken().getValue().getId());
    }
}
