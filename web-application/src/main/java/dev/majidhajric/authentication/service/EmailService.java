package dev.majidhajric.authentication.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final TemplateService templateService;

    public void sendPlain(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

    public void sendHtml(String to, String subject, String content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = null;
            helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

            helper.setFrom("noreply@localhost");
            helper.setTo(to);
            helper.setSubject(subject);
            mimeMessage.setContent(content, MediaType.TEXT_HTML_VALUE);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send email", e);
            throw new RuntimeException(e);
        }
    }

    public void sendTemplate(String to, String subject, String template, Map<String, Object> model) {
        String content = templateService.process(template, model);
        sendHtml(to, subject, content);
    }
}
