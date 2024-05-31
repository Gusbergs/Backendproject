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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;


@SpringBootTest
@ActiveProfiles("test")
public class EmailServiceTest {

    @MockBean
    private JavaMailSender mailSender;

    @Autowired
    private EmailService emailService;


    @Test
    public void testSendEmail() throws Exception {

        MimeMessage mimeMessage = new MimeMessage((Session) null);

        Mockito.when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "hej " + to;

        emailService.sendHtmlEmail(to, subject, text);

        ArgumentCaptor<MimeMessage> messageCaptor = ArgumentCaptor.forClass(MimeMessage.class);
        verify(mailSender).send(messageCaptor.capture());


        assertTrue(text.contains("hej test@example.com"));
        assertTrue(subject.contains("Test Subject"));

    }}
