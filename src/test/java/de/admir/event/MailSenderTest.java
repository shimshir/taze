package de.admir.event;

import de.admir.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class MailSenderTest {
    @Autowired
    private MailSender mailSender;

    @Test
    public void testSendMail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("me.admir@gmail.com");
        msg.setSubject("Test e-mail subject");
        msg.setText("Test e-mail text");
        mailSender.send(msg);
    }
}
