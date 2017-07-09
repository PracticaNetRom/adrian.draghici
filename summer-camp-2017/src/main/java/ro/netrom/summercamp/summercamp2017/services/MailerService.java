package ro.netrom.summercamp.summercamp2017.services;

import ro.netrom.summercamp.summercamp2017.data.SmtpInfo;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class MailerService {

	public synchronized static boolean sendEmail(String[] recipients, String subject, String body) {
		SmtpInfo smtpInfo=new SmtpInfo();
		Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", smtpInfo.getSmtpStartTLSEnable());
        props.put("mail.smtp.host", smtpInfo.getSmtpServer());
        props.put("mail.smtp.user", smtpInfo.getUsername());
        props.put("mail.smtp.password", smtpInfo.getPassword());
        props.put("mail.smtp.port", smtpInfo.getSmtpPort());
        props.put("mail.smtp.auth", smtpInfo.getSmtpUserAuthentication());
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(smtpInfo.getUsername(), "Ziarul Online by Ady"));
            InternetAddress[] toAddress = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                toAddress[i] = new InternetAddress(recipients[i]);
            }
            System.out.println("\nEmail will send to:");
            for (InternetAddress toAddres : toAddress) {
                message.addRecipient(Message.RecipientType.TO, toAddres);
                System.out.println(toAddres);
            }
            message.setSubject(subject);
            message.setText(body,"UTF-8", "html");
            Transport transport;
            if (smtpInfo.getSmtpStartTLSEnable()) {
                transport = session.getTransport("smtps");
            } else {
                transport = session.getTransport("smtp");
            }
            transport.connect(smtpInfo.getSmtpServer(), smtpInfo.getUsername(), smtpInfo.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            System.out.println(ae);
            System.out.println("Email NOT sent!");
            return false;
        } catch (MessagingException | UnsupportedEncodingException me) {
            System.out.println(me);
            System.out.println("Email NOT sent!");
            return false;
        }
        System.out.println("Email sent!");
        return true;
    }
	
}
