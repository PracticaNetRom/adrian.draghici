package ro.netrom.summercamp.summercamp2017.services;

import ro.netrom.summercamp.summercamp2017.data.SmtpInfo;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class MailerService {

	public synchronized static boolean sendEmail(String[] recipients, String subject, String body) {
		SmtpInfo smtpInfo=new SmtpInfo();
		Properties props = new Properties();
		props.put("mail.smtp.auth", smtpInfo.getSmtpUserAuthentication());
        props.put("mail.smtp.starttls.enable", smtpInfo.getSmtpStartTLSEnable());
        props.put("mail.smtp.host", smtpInfo.getSmtpServer());
        props.put("mail.smtp.port", smtpInfo.getSmtpPort());
        

//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "587");
//        Session session = Session.getInstance(props,
//      		  new javax.mail.Authenticator() {
//      			protected PasswordAuthentication getPasswordAuthentication() {
//      				return new PasswordAuthentication(smtpInfo.getUsername(), smtpInfo.getPassword());
//      			}
//      		  });
//        
//        try {
//
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(smtpInfo.getUsername()));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ghimis01@gmail.com"));
//			message.setSubject("Testing Subject");
//			message.setText("Dear Mail Crawler,"
//				+ "\n\n No spam to my email, please!");
//
//			Transport.send(message);
//
//			System.out.println("Done");
//
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
        
        
//        Session session = Session.getInstance(props);
        Session session = Session.getInstance(props,
      		  new javax.mail.Authenticator() {
      			protected PasswordAuthentication getPasswordAuthentication() {
      				return new PasswordAuthentication(smtpInfo.getUsername(), smtpInfo.getPassword());
      			}
      		  });
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
            Transport.send(message);
//            Transport transport;
//            if (smtpInfo.getSmtpStartTLSEnable()) {
//                transport = session.getTransport("smtps");
//            } else {
//                transport = session.getTransport("smtp");
//            }
//            transport.connect(smtpInfo.getSmtpServer(), smtpInfo.getUsername(), smtpInfo.getPassword());
//            transport.sendMessage(message, message.getAllRecipients());
//            transport.close();
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
