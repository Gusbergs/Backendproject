package com.example.backendproject.service;

import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
public class EmailServiceTest {

    @MockBean
    private JavaMailSender mailSender;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSendHtmlEmail() throws Exception {

        MimeMessage mimeMessage = new MimeMessage((Session) null);

        Mockito.when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        String to = "test@example.com";
        String subject = "Test Subject";
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", "Bob");
        variables.put("nr", "123");
        variables.put("startDate", "2024-06-30");
        variables.put("endDate", "2024-06-31");
        variables.put("discountPrice", "550");
        variables.put("goodByeMsg", "Tack för din bokning!");

        emailService.sendHtmlEmail(to, subject, variables);

        ArgumentCaptor<MimeMessage> messageCaptor = ArgumentCaptor.forClass(MimeMessage.class);
        verify(mailSender).send(messageCaptor.capture());

        String content = templateEngine.process("emailTemplate", new Context(Locale.getDefault(), variables));

        assertTrue(content.contains("Hej Bob! Din bokning är nu bokad!"));
        assertTrue(content.contains("Hotellrum Nr: 123"));
        assertTrue(content.contains("Från: 2024-06-30"));
        assertTrue(content.contains("Till: 2024-06-31"));
        assertTrue(content.contains("Total priset blir 550 Kr"));
        assertTrue(content.contains("Tack för din bokning!"));
    }}
