package de.admir.taze.service;

import de.admir.taze.model.order.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@Service
public class MailService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Configuration configuration;
    @Autowired
    private JavaMailSender mailSender;

    private MimeMessage mimeMessage;
    private MimeMessageHelper mimeMessageHelper;

    @PostConstruct
    private void initMailing() {
        mimeMessage = mailSender.createMimeMessage();
        mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    }

    @Value("${taze.frontend.host}")
    private String host;

    @Async
    public void sendConfirmationEmail(Order order) {
        try {
            Template emailTemplate = configuration.getTemplate("email/confirmEmail.ftl");
            Map<String, Object> model = new HashMap<>();
            model.put("order", order);
            model.put("confirmationLink", createConfirmationLink(order));
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(emailTemplate, model);
            mimeMessageHelper.setTo(order.getCustomer().getEmail());
            mimeMessageHelper.setSubject("Molimo potvrdite narud\u017ebu");
            mimeMessageHelper.setText(text, true);
            mailSender.send(mimeMessage);
            LOG.info("Order confirmation e-mail sent to " + order.getCustomer().getEmail());
        } catch (IOException e) {
            LOG.error("Failed to read confirmEmail.ftl template", e);
        } catch (TemplateException e) {
            LOG.error("Template runtime exception", e);
        } catch (MessagingException e) {
            LOG.error("Messaging exception", e);
        }
    }

    private String createConfirmationLink(Order order) {
        return String.format("%s/confirmed-order?orderId=%d&token=%s", host, order.getId(), order.getToken().getValue().getId());
    }
}
